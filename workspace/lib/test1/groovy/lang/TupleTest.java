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
package groovy.lang;

import junit.framework.TestCase;

import java.util.List;

import static org.junit.Assert.assertNotEquals;

public class TupleTest extends TestCase {

    private final Object[] data = {"a", "b", "c"};
    final Tuple t = new Tuple<Object>(data);

    public void testSize() {
        assertEquals("Size of " + t, 3, t.size());

        assertEquals("get(0)", "a", t.get(0));
        assertEquals("get(1)", "b", t.get(1));
    }

    public void testGetOutOfTuple() {
        try {
            t.get(-1);
            fail("Should have thrown IndexOut");
        }
        catch (IndexOutOfBoundsException e) {
            // worked
        }
        try {
            t.get(10);
            fail("Should have thrown IndexOut");
        }
        catch (IndexOutOfBoundsException e) {
            // worked
        }

    }

    public void testContains() {
        assertTrue("contains a", t.contains("a"));
        assertTrue("contains b", t.contains("b"));
    }

    public void testSubList() {
        List s = t.subList(1, 2);
        assertTrue("is a Tuple", s instanceof Tuple);
        assertEquals("size", 1, s.size());
    }

    public void testSubTuple() {
        Tuple s = t.subTuple(1, 2);
        assertTrue("is a Tuple", isTuple(s));
        assertEquals("size", 1, s.size());
    }

    private boolean isTuple(Object s) {
        return s instanceof Tuple;
    }

    public void testHashCodeAndEquals() {
        Tuple a = new Tuple<Object>("a", "b", "c");
        Tuple b = new Tuple<Object>("a", "b", "c");
        Tuple c = new Tuple<Object>("d", "b", "c");
        Tuple d = new Tuple<Object>("a", "b");
        Tuple2<String, String> e = new Tuple2<String, String>("a", "b");
        Tuple2<String, String> f = new Tuple2<String, String>("a", "c");

        assertEquals("hashcode", a.hashCode(), b.hashCode());
        assertTrue("hashcode", a.hashCode() != c.hashCode());

        assertEquals("a and b", a, b);
        assertNotEquals("a != c", a, c);

        assertNotEquals("!a.equals(null)", null, a);

        assertEquals("d.equals(e)", d, e);
        assertEquals("e.equals(d)", e, d);
        assertNotEquals("!e.equals(f)", e, f);
        assertNotEquals("!f.equals(e)", f, e);
    }

    public void testIterator() {
    }

    public void testTuple1() {
        Tuple1<Integer> t = new Tuple1<>(1);

        assertEquals(1, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple2() {
        Tuple2<Integer, Integer> t = new Tuple2<>(1, 2);

        assertEquals(2, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple3() {
        Tuple3<Integer, Integer, Integer> t = new Tuple3<>(1, 2, 3);

        assertEquals(3, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple4() {
        Tuple4<Integer, Integer, Integer, Integer> t = new Tuple4<>(1, 2, 3, 4);

        assertEquals(4, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(Integer.valueOf(4), t.getFourth());
        assertEquals(4, t.get(3));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple5() {
        Tuple5<Integer, Integer, Integer, Integer, Integer> t = new Tuple5<>(1, 2, 3, 4, 5);

        assertEquals(5, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(Integer.valueOf(4), t.getFourth());
        assertEquals(4, t.get(3));

        assertEquals(Integer.valueOf(5), t.getFifth());
        assertEquals(5, t.get(4));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple6() {
        Tuple6<Integer, Integer, Integer, Integer, Integer, Integer> t = new Tuple6<>(1, 2, 3, 4, 5, 6);

        assertEquals(6, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(Integer.valueOf(4), t.getFourth());
        assertEquals(4, t.get(3));

        assertEquals(Integer.valueOf(5), t.getFifth());
        assertEquals(5, t.get(4));

        assertEquals(Integer.valueOf(6), t.getSixth());
        assertEquals(6, t.get(5));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple7() {
        Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, Integer> t = new Tuple7<>(1, 2, 3, 4, 5, 6, 7);

        assertEquals(7, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(Integer.valueOf(4), t.getFourth());
        assertEquals(4, t.get(3));

        assertEquals(Integer.valueOf(5), t.getFifth());
        assertEquals(5, t.get(4));

        assertEquals(Integer.valueOf(6), t.getSixth());
        assertEquals(6, t.get(5));

        assertEquals(Integer.valueOf(7), t.getSeventh());
        assertEquals(7, t.get(6));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple8() {
        Tuple8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> t = new Tuple8<>(1, 2, 3, 4, 5, 6, 7, 8);

        assertEquals(8, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(Integer.valueOf(4), t.getFourth());
        assertEquals(4, t.get(3));

        assertEquals(Integer.valueOf(5), t.getFifth());
        assertEquals(5, t.get(4));

        assertEquals(Integer.valueOf(6), t.getSixth());
        assertEquals(6, t.get(5));

        assertEquals(Integer.valueOf(7), t.getSeventh());
        assertEquals(7, t.get(6));

        assertEquals(Integer.valueOf(8), t.getEighth());
        assertEquals(8, t.get(7));

        assertEquals(t, t.subTuple(0, t.size()));
    }

    public void testTuple9() {
        Tuple9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> t = new Tuple9<>(1, 2, 3, 4, 5, 6, 7, 8, 9);

        assertEquals(9, t.size());

        assertEquals(Integer.valueOf(1), t.getFirst());
        assertEquals(1, t.get(0));

        assertEquals(Integer.valueOf(2), t.getSecond());
        assertEquals(2, t.get(1));

        assertEquals(Integer.valueOf(3), t.getThird());
        assertEquals(3, t.get(2));

        assertEquals(Integer.valueOf(4), t.getFourth());
        assertEquals(4, t.get(3));

        assertEquals(Integer.valueOf(5), t.getFifth());
        assertEquals(5, t.get(4));

        assertEquals(Integer.valueOf(6), t.getSixth());
        assertEquals(6, t.get(5));

        assertEquals(Integer.valueOf(7), t.getSeventh());
        assertEquals(7, t.get(6));

        assertEquals(Integer.valueOf(8), t.getEighth());
        assertEquals(8, t.get(7));

        assertEquals(Integer.valueOf(9), t.getNinth());
        assertEquals(9, t.get(8));

        assertEquals(t, t.subTuple(0, t.size()));
    }
}
