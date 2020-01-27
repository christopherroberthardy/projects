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
package groovy.bugs

class Groovy8943Bug extends GroovyTestCase {
    void testImplicitGetAtInStaticMethod() {
        assertScript '''
            class Pippo {
                int getAt(String pluto) { return 1 }
            }

            @groovy.transform.TypeChecked
            static void method() {
                def pippo = new Pippo()
                int ans = pippo.getAt('a')
                assert ans == 1
                ans = pippo['a']
                assert ans == 1
            }

            method()
        '''
    }
}
