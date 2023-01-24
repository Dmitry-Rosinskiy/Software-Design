package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    @Test
    void empty() {
        var list = new MyLinkedList<Integer>();
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(0, array.size(),
                "Size is " + array.size() + " when should be 0");
    }

    @ParameterizedTest(name = "{0} adds")
    @CsvSource({ "1", "2", "3", "4", "5", "6", "7" })
    void size1(int count) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count; ++i) {
            list.add(0);
        }
        assertEquals(count, list.size(), "Size is " + list.size()
                    + " when should be " + count);
    }

    @ParameterizedTest(name = "{0} adds (element {1})")
    @CsvSource({ "1, 0", "2, 1", "3, -1", "4, 2", "5, -2"})
    void add1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        list.add(elem);
        for (int i = 1; i < count; ++i) {
            list.add(elem + 1);
        }
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(elem, array.get(0), "First element is " + array.get(0)
                    + " when should be " + elem);
    }

    @ParameterizedTest(name = "{0} adds (element {1})")
    @CsvSource({ "1, 0", "2, 1", "3, -1", "4, 2", "5, -2"})
    void add2(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(elem - 1);
        }
        list.add(elem);
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(elem, array.get(array.size() - 1), "First element is "
                    + array.get(array.size() - 1) + " when should be " + elem);
    }

    @ParameterizedTest(name = "{0} adds (position {1})")
    @CsvSource({ "2, 0", "2, 1", "3, 1", "4, 1", "4, 2", "5, 1", "5, 2", "5, 3"})
    void add3(int count, int pos) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count; ++i) {
            list.add(i + 1);
        }
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(pos + 1, array.get(pos), "Element on position " + pos + " is "
                    + array.get(pos) + " when should be " + (pos + 1));
    }

    @ParameterizedTest(name = "{0} adds (element {1})")
    @CsvSource({ "2, 0", "3, 1", "4, -1", "5, 2", "6, -2" })
    void addFirst(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(i - 1);
        }
        list.addFirst(elem);
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(elem, array.get(0), "First element is " + array.get(0)
                    + " when should be " + elem);
    }

    @ParameterizedTest(name = "{0} adds (element {1})")
    @CsvSource({ "2, 0", "3, 1", "4, -1", "5, 2", "6, -2" })
    void addLast(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(i + 1);
        }
        list.addLast(elem);
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(elem, array.get(array.size() - 1), "Last element is "
                    + array.get(array.size() - 1)  + " when should be " + elem);
    }

    @Test
    void size2() {
        var list = new MyLinkedList<>(Arrays.asList(-1, -2, -3, -4, -5, -6, -7, -8, -9, -10));
        assertEquals(10, list.size(), "Size is " + list.size()
                    + " when should be 10");
    }

    @ParameterizedTest(name = "{0} elements")
    @CsvSource( {"0", "1", "2", "3", "4", "5"} )
    void clear(int count) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count; ++i) {
            list.add(i + 10);
        }
        list.clear();
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(0, array.size(), "List has " + array.size()
                    + " element" + (array.size() > 1 ? "s" : "") + " when should be empty");
    }

    @ParameterizedTest(name = "add element {0} on position {1}")
    @CsvSource( {"1, 1", "-1, 2", "2, 3", "-2, 4", "3, 5", "-3, 6", "4, 7", "-4, 8"} )
    void addIndex(int elem, int pos) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 10; ++i) {
            list.add(0);
        }
        list.add(pos, elem);
        ArrayList<Integer> array = new ArrayList<>(list);
        assertEquals(elem, array.get(pos), "Element on position " + pos + " is "
                    + array.get(pos) + " when should be " + elem);
    }

    @ParameterizedTest(name = "contains element {0}")
    @CsvSource( {"0", "1", "-1", "2", "-2", "3", "-3"} )
    void contains1(int elem) {
        var list = new MyLinkedList<>(Arrays.asList(0, 1, -1, 2, -2, 3, -3));
        assertTrue(list.contains(elem), "List does not contain element "
                + elem);
    }

    @ParameterizedTest(name = "contains element {0}")
    @CsvSource( {"1", "2", "3", "4", "5", "6", "7"} )
    void contains2(int elem) {
        var list = new MyLinkedList<>(Arrays.asList(0, -1, -2, -3, -4, -5, -6));
        assertFalse(list.contains(elem), "List contains element "
                + elem);
    }

    @ParameterizedTest(name = "{0} elements (element {1})")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void element(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(0);
        }
        list.addFirst(elem);
        assertEquals(elem, list.element(), "First element is " + list.element()
                    + "when should be " + elem);
        assertEquals(count, list.size(), "Size is " + list.size() + " when should be "
                    + count);
    }

    @ParameterizedTest(name = "get element {0} on position {1}")
    @CsvSource( {"1, 0", "-1, 1", "2, 2", "-2, 3", "3, 4", "-3, 5", "4, 6", "-4, 7"} )
    void getIndex(int elem, int pos) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 8; ++i) {
            list.add(0);
        }
        list.add(pos, elem);
        assertEquals(elem, list.get(pos), "Element on position " + pos + " is "
                    + list.get(pos) + " when should be " + elem);
    }

    @ParameterizedTest(name = "get first element {1} ({0} elements)")
    @CsvSource({ "2, 0", "3, 1", "4, -1", "5, 2", "6, -2" })
    void getFirst(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(i - 1);
        }
        list.addFirst(elem);
        assertEquals(elem, list.getFirst(), "First element is " + list.getFirst()
                    + " when should be " + elem);
    }

    @ParameterizedTest(name = "get last element {1} ({0} elements)")
    @CsvSource({ "2, 0", "3, 1", "4, -1", "5, 2", "6, -2" })
    void getLast(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(i + 1);
        }
        list.addLast(elem);
        assertEquals(elem, list.getLast(), "Last element is "
                    + list.getLast()  + " when should be " + elem);
    }

    @ParameterizedTest(name = "get first index {1} of element {0}")
    @CsvSource( {"1, 0", "-1, 1", "2, 2", "-2, 3", "3, 4", "-3, 5", "4, 6", "-4, 7"} )
    void indexOf1(int elem, int index) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 8; ++i) {
            list.add(0);
        }
        list.add(index, elem);
        assertEquals(index, list.indexOf(elem), "Element " + elem + " has first index "
                    + list.indexOf(elem) + " when should be " + index);
    }

    @ParameterizedTest(name = "get first index of element ({0} elements)")
    @CsvSource( {"1", "2", "3", "4", "5"} )
    void indexOf2(int count) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count; ++i) {
            list.add(0);
        }
        assertEquals(0, list.indexOf(0), "First index of element 0 is "
                    + list.indexOf(0) + " when should be 0");
    }

    @ParameterizedTest(name = "return first index -1 when element {0} does not exist")
    @CsvSource( {"1", "-1", "2", "-2", "3", "-3"} )
    void indexOf3(int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 10; ++i) {
            list.add(0);
        }
        assertEquals(-1, list.indexOf(elem), "First index of element " + elem
                    + " is " + list.indexOf(0) + " when should be -1 (does not exist)");
    }

    @ParameterizedTest(name = "get last index {1} of element {0}")
    @CsvSource( {"1, 0", "-1, 1", "2, 2", "-2, 3", "3, 4", "-3, 5", "4, 6", "-4, 7"} )
    void lastIndexOf1(int elem, int index) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 8; ++i) {
            list.add(0);
        }
        list.add(index, elem);
        assertEquals(index, list.lastIndexOf(elem), "Element " + elem + " has last index "
                    + list.lastIndexOf(elem) + " when should be " + index);
    }

    @ParameterizedTest(name = "get last index of element ({0} elements)")
    @CsvSource( {"1", "2", "3", "4", "5"} )
    void lastIndexOf2(int count) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count; ++i) {
            list.add(0);
        }
        assertEquals(count - 1, list.lastIndexOf(0),
                    "Last index of element 0 is " + list.indexOf(0) + " when should be "
                            + (count - 1));
    }

    @ParameterizedTest(name = "return last index -1 when element {0} does not exist")
    @CsvSource( {"1", "-1", "2", "-2", "3", "-3"} )
    void lastIndexOf3(int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 10; ++i) {
            list.add(0);
        }
        assertEquals(-1, list.lastIndexOf(elem), "Last index of element " + elem
                    + " is " + list.lastIndexOf(0) + " when should be -1 (does not exist)");
    }

    @ParameterizedTest(name = "peek element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void peek1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        list.add(elem);
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        assertEquals(elem, list.peek(), "First element is " + list.peek()
                    + " when should be " + elem);
        assertEquals(count, list.size(), "Size is " + list.size() + " when should be "
                    + count);
    }

    @Test
    void peek2() {
        var list = new MyLinkedList<Integer>();
        assertNull(list.peek(), "First element is " + list.peek()
                + " when should be null (does not exist)");
    }

    @ParameterizedTest(name = "peek first element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void peekFirst1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        list.addFirst(elem);
        assertEquals(elem, list.peekFirst(), "First element is " + list.peekFirst()
                    + " when should be " + elem);
        assertEquals(count, list.size(), "Size is " + list.size() + " when should be "
                    + count);
    }

    @Test
    void peekFirst2() {
        var list = new MyLinkedList<Integer>();
        assertNull(list.peekFirst(), "First element is " + list.peekFirst()
                + " when should be null (does not exist)");
    }

    @ParameterizedTest(name = "peek last element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void peekLast1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        list.addLast(elem);
        assertEquals(elem, list.peekLast(), "Last element is " + list.peekLast()
                    + " when should be " + elem);
        assertEquals(count, list.size(), "Size is " + list.size() + " when should be "
                    + count);
    }

    @Test
    void peekLast2() {
        var list = new MyLinkedList<Integer>();
        assertNull(list.peekLast(), "Last element is " + list.peekLast()
                + " when should be null (does not exist)");
    }

    @ParameterizedTest(name = "poll element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void poll1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        list.add(elem);
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        Integer pollElement = list.poll();
        assertEquals(elem, pollElement, "First element is " + pollElement
                    + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count - 1));
    }

    @Test
    void poll2() {
        var list = new MyLinkedList<Integer>();
        Integer pollElement = list.poll();
        assertNull(pollElement, "First element is " + pollElement
                + " when should be null (does not exist)");
    }

    @ParameterizedTest(name = "poll first element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void pollFirst1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        list.addFirst(elem);
        Integer pollElement = list.pollFirst();
        assertEquals(elem, pollElement, "First element is " + pollElement
                    + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count - 1));
    }

    @Test
    void pollFirst2() {
        var list = new MyLinkedList<Integer>();
        Integer pollElement = list.pollFirst();
        assertNull(pollElement, "First element is " + pollElement
                + " when should be null (does not exist)");
    }

    @ParameterizedTest(name = "peek last element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2"} )
    void pollLast1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        list.addLast(elem);
        Integer pollElement = list.pollLast();
        assertEquals(elem, pollElement, "Last element is " + pollElement
                    + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count - 1));
    }

    @Test
    void pollLast2() {
        var list = new MyLinkedList<Integer>();
        Integer pollElement = list.pollLast();
        assertNull(pollElement, "Last element is " + pollElement
                + " when should be null (does not exist)");
    }

    @ParameterizedTest(name = "pop element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2", "5, 3,", "6, -3"})
    void pop(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(0);
        }
        list.addFirst(elem);
        int popElement = list.pop();
        assertEquals(elem, popElement, "First element is " + popElement
                    + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count - 1));
    }

    @ParameterizedTest(name = "push element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, 2", "4, -2", "5, 3,", "6, -3"})
    void push(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count; ++i) {
            list.add(0);
        }
        list.push(elem);
        assertEquals(elem, list.getFirst(), "First element is " + list.getFirst()
                    + " when should be " + elem);
        assertEquals(count + 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count + 1));
    }

    @ParameterizedTest(name = "remove first element {1} ({0} elements)")
    @CsvSource( {"1, 1", "2, -1", "3, -2", "4, 3", "5, -3"} )
    void remove1(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        list.add(elem);
        for (int i = 1; i < count; ++i) {
            list.add(0);
        }
        int removeElement = list.remove();
        assertEquals(elem, removeElement, "Removed element is " + removeElement
                    + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                + " when should be " + (count - 1));

    }

    @ParameterizedTest(name = "{0} removes")
    @CsvSource( {"1", "2", "3", "4", "5", "6", "7"} )
    void remove2(int count) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 7; ++i) {
            list.add(i);
        }
        for (int i = 0; i < count; ++i) {
            list.remove();
        }
        assertEquals(7 - count, list.size(), "Size is " + list.size()
                + " when should be " + (7 - count));
    }

    @ParameterizedTest(name = "remove element {0}")
    @CsvSource( {"1", "2", "3", "4", "5"} )
    void removeObject1(int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; ++i) {
            list.add(i + 1);
        }
        boolean removed = list.remove((Object) elem);
        assertTrue(removed, "Element " + elem + " is not removed when it should be");
        assertEquals(5 - 1, list.size(), "Size is " + list.size()
                    + " when it should be " + (5 - 1));
    }

    @ParameterizedTest(name = "remove element {0} (does not exist)")
    @CsvSource( {"1", "2", "3", "4", "5"} )
    void removeObject2(int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; ++i) {
            list.add(-i);
        }
        boolean removed = list.remove((Object) elem);
        assertFalse(removed, "Element " + elem
                    + " is removed when it should not be (does not exist)");
        assertEquals(5, list.size(), "Size is " + list.size()
                + " when it should be 5");
    }

    @ParameterizedTest(name = "remove element from position {0})")
    @CsvSource({ "0", "1", "2", "3", "4", "5", "6" })
    void removeIndex(int pos) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 8; ++i) {
            list.add(i + 1);
        }
        int removeElement = list.remove(pos);
        assertEquals(pos + 1, removeElement, "Removed element on position " + pos
                    + " is " + removeElement + " when should be " + (pos + 1));
        assertEquals(8 - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (8 - 1));
    }

    @ParameterizedTest(name = "remove first element {1} ({0} elements})")
    @CsvSource({ "1, 1", "2, -1", "3, 2", "4, -2", "5, 3", "6, -3" })
    void removeFirst(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(0);
        }
        list.addFirst(elem);
        int removeElement = list.removeFirst();
        assertEquals(elem, removeElement, "Removed first element is "
                    + removeElement + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count - 1));
    }

    @ParameterizedTest(name = "remove first element {1} ({0} elements})")
    @CsvSource({ "1, 1", "2, -1", "3, 2", "4, -2", "5, 3", "6, -3" })
    void removeLast(int count, int elem) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < count - 1; ++i) {
            list.add(0);
        }
        list.addLast(elem);
        int removeElement = list.removeLast();
        assertEquals(elem, removeElement, "Removed last element is "
                    + removeElement + " when should be " + elem);
        assertEquals(count - 1, list.size(), "Size is " + list.size()
                    + " when should be " + (count - 1));
    }

    @ParameterizedTest(name = "set element {0} on position {1}")
    @CsvSource( {"-1, 0", "-2, 1", "-3, 2", "-4, 3", "-5, 4"} )
    void set(int elem, int pos) {
        var list = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; ++i) {
            list.add(i);
        }
        int prevElement = list.set(pos, elem);
        assertEquals(elem, list.get(pos), "Element on position " + pos + " is "
                    + list.get(pos) + " when should be " + elem);
        assertEquals(pos, prevElement, "Previous element on position " + pos + " was "
                    + prevElement + " when should be " + pos);
        assertEquals(5, list.size(), "Size is " + list.size()
                + " when should be 5");
    }

    @ParameterizedTest(name = "array with {0} elements")
    @CsvSource( {"1", "2", "3", "4", "5"} )
    void toArray(int count) {
        var list = new MyLinkedList<Integer>();
        var array = new int[count];
        for (int i = 0; i < count; ++i) {
            list.add(-i - 1);
            array[i] = -i - 1;
        }
        Object[] arrayList = list.toArray();
        boolean equal = true;
        for (int i = 0; i < count; ++i) {
            if ((int) arrayList[i] != array[i]) {
                equal = false;
                break;
            }
        }
        assertTrue(equal, () -> {
            StringBuilder message = new StringBuilder("Array from list is [");
            message.append(arrayList[0]);
            for (int i = 1; i < count; ++i) {
                message.append(", ").append(arrayList[i]);
            }
            message.append("] when should be [");
            message.append(array[0]);
            for (int i = 1; i < count; ++i) {
                message.append(", ").append(array[i]);
            }
            message.append("]");
            return message.toString();
        });
    }
}
