/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.codehaus.groovy.classgen.asm.sc

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.classgen.GeneratorContext
import org.codehaus.groovy.control.CompilationUnit
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.control.customizers.ASTTransformationCustomizer
import org.codehaus.groovy.control.customizers.CompilationCustomizer
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.tools.GroovyClass
import org.objectweb.asm.ClassReader
import org.objectweb.asm.util.CheckClassAdapter

import java.security.CodeSource

/**
 * A mixin class which can be used to transform a static type checking test case into a
 * static compilation test case.
 *
 * On the beginning of a test method, it initializes a property which is available
 * to the developer for additional tests:
 * <ul>
 *     <li>astTrees: a map which has for key the names of classes generated in assertScript and an array of length 2
 *     as a value, for which the first element is the generated AST tree of the class, and the second element is
 *     the result of the ASM check class adapter string, which can be used to verify generated bytecode.</li>
 * </ul>
 */
trait StaticCompilationTestSupport {
    Map<String, Object[]> astTrees
    CustomCompilationUnit compilationUnit

    void extraSetup() {
        astTrees = [:]
        config = new CompilerConfiguration()
        def imports = new ImportCustomizer()
        imports.addImports(
                'groovy.transform.ASTTest', 'org.codehaus.groovy.transform.stc.StaticTypesMarker',
                'org.codehaus.groovy.ast.ClassHelper'
        )
        imports.addStaticStars('org.codehaus.groovy.control.CompilePhase')
        imports.addStaticStars('org.codehaus.groovy.transform.stc.StaticTypesMarker')
        imports.addStaticStars('org.codehaus.groovy.ast.ClassHelper')
        config.addCompilationCustomizers(imports,new ASTTransformationCustomizer(CompileStatic), new ASTTreeCollector(this))
        configure()
        shell = new GroovyShell(config)
        // trick because GroovyShell doesn't allow to provide our own GroovyClassLoader
        // to be fixed when this will be possible
        shell.loader = new CompilationUnitAwareGroovyClassLoader(this.getClass().classLoader, config, this)
    }

    void tearDown() {
        astTrees = null
        compilationUnit = null
        super.tearDown()
    }

    void assertAndDump(String script) {
        try {
            assertScript(script)
        } finally {
            println astTrees
        }
    }

    static class CompilationUnitAwareGroovyClassLoader extends GroovyClassLoader {
        StaticCompilationTestSupport testCase

        CompilationUnitAwareGroovyClassLoader(
                final ClassLoader loader,
                final CompilerConfiguration config,
                final StaticCompilationTestSupport testCase) {
            super(loader, config)
            this.testCase = testCase
        }

        @Override
        protected CompilationUnit createCompilationUnit(final CompilerConfiguration config, final CodeSource source) {
            def cu = new CustomCompilationUnit(config, source, this)
            testCase.compilationUnit = cu
            return cu
        }
    }

    static class CustomCompilationUnit extends CompilationUnit {
        CustomCompilationUnit(final CompilerConfiguration configuration, final CodeSource security, final GroovyClassLoader loader) {
            super(configuration, security, loader)
        }

    }

    static class ASTTreeCollector extends CompilationCustomizer {
        StaticCompilationTestSupport testCase

        ASTTreeCollector(StaticCompilationTestSupport testCase) {
            super(CompilePhase.CLASS_GENERATION)
            this.testCase = testCase
        }

        @Override
        void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) {
            def unit = testCase.compilationUnit
            if (!unit) return
            List<GroovyClass> classes = unit.generatedClasses
            classes.each { GroovyClass groovyClass ->
                StringWriter stringWriter = new StringWriter()
                try {
                    ClassReader cr = new ClassReader(groovyClass.bytes)
                    CheckClassAdapter.verify(cr, source.getClassLoader(), true, new PrintWriter(stringWriter))
                } catch (Throwable e)  {
                    // not a problem
                    e.printStackTrace(new PrintWriter(stringWriter))
                }
                testCase.astTrees[groovyClass.name] = [classNode, stringWriter.toString()] as Object[]
            }
        }
    }

}