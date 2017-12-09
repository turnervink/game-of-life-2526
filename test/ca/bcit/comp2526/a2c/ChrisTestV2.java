package ca.bcit.comp2526.a2c;

import static org.junit.jupiter.api.Assertions.*;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * DoubleLinkedListTest.
 *
 * @author BCIT
 * @version 2017
 */
class ChrisTestV2 {

    private DoubleLinkedList<Integer> testObject;

    /**
     * Instantiates a fresh DoubleLinkedList for each unit test.
     *
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        testObject = new DoubleLinkedList<>();
    }

    /**
     * A new empty list has a method called size() that returns an int equal to
     * zero, and accessors for first and last element called getFirst() and
     * getLast() respectively that return null.
     */
    @Test
    @DisplayName("New empty list has size zero, contains nothing")
    void testSizeEmptyList() {
        assertEquals(0, testObject.size());
        assertNull(testObject.getFirst());
        assertNull(testObject.getLast());
    }

    /**
     * A DoubleLinkedList must implement Iterable.
     */
    @Test
    @DisplayName("DoubleLinkedList must implement Iterable")
    void testIsIterable() {
        assertTrue(testObject instanceof Iterable);
    }

    /**
     * A DoubleLinkedList must implement Serializable.
     */
    @Test
    @DisplayName("DoubleLinkedList must implement Serializable")
    void testDoubleLinkedListIsSerializable() {
        assertTrue(testObject instanceof Serializable);
    }

    /**
     * A Node must implement Serializable.
     *
     * NOTE: My node is implemented differently
     */
//    @Test
//    @DisplayName("Node must implement Serializable")
//    void testNodeIsSerializable() {
//        Node<String> node = new Node<>("Test");
//        assertTrue(node instanceof Serializable);
//    }

    /**
     * When adding a null element to a list, the list should throw a
     * CouldNotAddException. We can't add a null to the linked list.
     */
    @Test
    @DisplayName("Adding a null to front throws CouldNotAddException")
    void testAddToFrontNull() {
        assertThrows(CouldNotAddException.class, () -> {
            testObject.addToFront(null);
        });
    }

    /**
     * After adding a non-null element to the front of a new empty list, the new
     * size should be one, and the list's head and tail should both point to the
     * new element.
     *
     * @throws CouldNotAddException
     */
    @Test
    @DisplayName("Add non-null element to front of empty list")
    void testAddToFrontOneElement() throws CouldNotAddException {
        final int testValue = 22;
        testObject.addToFront(testValue);
        assertEquals(1, testObject.size());
        assertNotNull(testObject.getFirst());
        assertNotNull(testObject.getLast());
        assertEquals(testObject.getFirst(), testObject.getLast());
    }

    /**
     * After adding two non-null elements to the front of a new empty list, the
     * new size should be two, the list's head should point to the second
     * element added, and the list's tail should point to the first element
     * added.
     *
     * @throws CouldNotAddException
     */
    @Test
    @DisplayName("Add two non-null elements to front of empty list")
    void testAddToFrontTwoElements() throws CouldNotAddException {
        final int firstValue = 1;
        final int secondValue = 2;
        testObject.addToFront(firstValue);
        testObject.addToFront(secondValue);
        assertEquals(2, testObject.size());
        assertNotNull(testObject.getFirst());
        assertEquals(2, testObject.getFirst().intValue());
        assertNotNull(testObject.getLast());
        assertEquals(1, testObject.getLast().intValue());
        assertNotEquals(testObject.getFirst(), testObject.getLast());
    }

    /**
     * After adding many non-null elements to the front of a new empty list, the
     * new size should be correct, the list's head should point to the most
     * recent element added, and the list's tail should point to the first
     * element added.
     *
     * @throws CouldNotAddException
     */
    @Test
    @DisplayName("Add many non-null elements to front of empty list")
    void testAddToFrontManyElements() throws CouldNotAddException {
        Random random = new Random();
        final int reasonableMaximumSize = 1000;
        final int listSize = random.nextInt(reasonableMaximumSize);
        for (int i = 0; i < listSize; ++i) {
            testObject.addToFront(i); // Autoboxing for the win!
            assertEquals(i + 1, testObject.size());
        }
        assertEquals(listSize, testObject.size());
        assertNotNull(testObject.getFirst());
        assertEquals(listSize - 1, testObject.getFirst().intValue());
        assertNotNull(testObject.getLast());
        assertEquals(0, testObject.getLast().intValue());
        assertNotEquals(testObject.getFirst(), testObject.getLast());
    }

    /**
     * New Iterator on a new empty list should report that it does not
     * hasNext().
     */
    @Test
    @DisplayName("Iterator for empty list does not hasNext()")
    void testIteratorEmptyList() {
        Iterator<Integer> iterator = testObject.iterator();
        assertFalse(iterator.hasNext());
    }

    /**
     * New Iterator on a list of size one should report that it hasNext() and
     * then it should return an element when next() is called. After that,
     * hasNext() should return false, and calling next() should throw a
     * NoSuchElementException.
     */
    @Test
    @DisplayName("Iterator for list size 1 works")
    void testIteratorListSize1() throws CouldNotAddException {
        final int testValue = 22;
        testObject.addToFront(testValue);
        Iterator<Integer> iterator = testObject.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(testValue, iterator.next().intValue());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    /**
     * New Iterator on a list of some size should report that it hasNext() when
     * it does, then it should return an element when next() is called. After
     * the iterator has visited all the elements in the list, the hasNext()
     * method should return false, and calling next() should throw a
     * NoSuchElementException.
     */
    @Test
    @DisplayName("Iterator for some large list works")
    void testIteratorLargeList() throws CouldNotAddException {
        Random random = new Random();
        final int reasonableMaximumSize = 1000;
        final int listSize = random.nextInt(reasonableMaximumSize);
        for (int i = 0; i < listSize; ++i) {
            testObject.addToFront(i); // Autoboxing for the win!
        }
        Iterator<Integer> iterator = testObject.iterator();
        for (int i = 0; i < listSize; ++i) {
            assertTrue(iterator.hasNext());
            assertEquals(listSize - i - 1, iterator.next().intValue());
        }
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        });
    }

    // END OF FIRST DRAFT OF JUNIT TESTS FOR DOUBLELINKEDLIST

    /**
     * Test the for-each loop using the iterator after adding a random number of
     * elements using the addToFront method.
     */
    @Test
    @DisplayName("Test the for-each loop post-addToFront")
    void testForEachAddToFrontList() throws CouldNotAddException {
        Random random = new Random();
        final int reasonableMaximumSize = 1000;
        final int listSize = random.nextInt(reasonableMaximumSize);
        for (int i = 0; i < listSize; ++i) {
            testObject.addToFront(i); // Autoboxing for the win!
        }
        int correctValue = listSize - 1;
        for (Integer datum : testObject) {
            assertEquals(correctValue--, datum.intValue());
        }
    }

    /**
     * Test the for-each loop using the iterator after adding a random number of
     * elements using the addToEnd method.
     */
    @Test
    @DisplayName("Test the for-each loop post-addToEnd")
    void testForEachAddToEndList() throws CouldNotAddException {
        Random random = new Random();
        final int reasonableMaximumSize = 1000;
        final int listSize = random.nextInt(reasonableMaximumSize);
        for (int i = 0; i < listSize; ++i) {
            testObject.addToEnd(i); // Autoboxing for the win!
        }
        int correctValue = 0;
        for (Integer datum : testObject) {
            assertEquals(correctValue++, datum.intValue());
        }
    }

    /**
     * When adding a null element to the end of a list, the list should throw a
     * CouldNotAddException. We can't add a null to the linked list.
     */
    @Test
    @DisplayName("Adding a null to end throws CouldNotAddException")
    void testAddToEndNull() {
        assertThrows(CouldNotAddException.class, () -> {
            testObject.addToEnd(null);
        });
    }

    /**
     * After adding a non-null element to the end of a new empty list, the new
     * size should be one, and the list's head and tail should both point to the
     * new element.
     *
     * @throws CouldNotAddException
     */
    @Test
    @DisplayName("Add non-null element to end of empty list")
    void testAddToEndOneElement() throws CouldNotAddException {
        final int testValue = 22;
        testObject.addToEnd(testValue);
        assertEquals(1, testObject.size());
        assertNotNull(testObject.getFirst());
        assertNotNull(testObject.getLast());
        assertEquals(testObject.getFirst(), testObject.getLast());
    }

    /**
     * After adding two non-null elements to the end of a new empty list, the
     * new size should be two, the list's head should point to the first element
     * added, and the list's tail should point to the second element added.
     *
     * @throws CouldNotAddException
     */
    @Test
    @DisplayName("Add two non-null elements to end of empty list")
    void testAddToEndTwoElements() throws CouldNotAddException {
        final int firstValue = 1;
        final int secondValue = 2;
        testObject.addToEnd(firstValue);
        testObject.addToEnd(secondValue);
        assertEquals(2, testObject.size());
        assertNotNull(testObject.getFirst());
        assertEquals(1, testObject.getFirst().intValue());
        assertNotNull(testObject.getLast());
        assertEquals(2, testObject.getLast().intValue());
        assertNotEquals(testObject.getFirst(), testObject.getLast());
    }

    /**
     * After adding many non-null elements to the end of a new empty list, the
     * new size should be correct, the list's head should point to the first
     * element added, and the list's tail should point to the most recently
     * added element..
     *
     * @throws CouldNotAddException
     */
    @Test
    @DisplayName("Add many non-null elements to end of empty list")
    void testAddToEndManyElements() throws CouldNotAddException {
        Random random = new Random();
        final int reasonableMaximumSize = 1000;
        final int listSize = random.nextInt(reasonableMaximumSize);
        for (int i = 0; i < listSize; ++i) {
            testObject.addToEnd(i); // Autoboxing for the win!
            assertEquals(i + 1, testObject.size());
        }
        assertEquals(listSize, testObject.size());
        assertNotNull(testObject.getFirst());
        assertEquals(0, testObject.getFirst().intValue());
        assertNotNull(testObject.getLast());
        assertEquals(listSize - 1, testObject.getLast().intValue());
        assertNotEquals(testObject.getFirst(), testObject.getLast());
    }

    /**
     * When removing an element from the front of an empty list,
     * the list should throw a CouldNotRemoveException.  We can't
     * remove anything from an empty list.
     */
    @Test
    @DisplayName("Removing from front of empty list throws CouldNotRemoveException")
    void testRemoveFromFrontEmpty() {
        assertThrows(CouldNotRemoveException.class, () -> {
            testObject.removeFromFront();
        });
    }

    /**
     * After removing an element from the front of the list, we need to
     * make sure that head and tail point to null, that size equals zero,
     * and the element returned is the element we expected.
     *
     * @throws CouldNotAddException addToFront error
     * @throws CouldNotRemoveException removeFromFront error
     */
    @Test
    @DisplayName("Remove non-null element from front of empty list")
    void testRemoveFromFrontOneElement()
            throws CouldNotAddException, CouldNotRemoveException {
        final int testValue = 22;
        testObject.addToFront(testValue);
        assertEquals(1, testObject.size());
        int returnedValue = testObject.removeFromFront();
        assertEquals(0, testObject.size());
        assertNull(testObject.getFirst());
        assertNull(testObject.getLast());
        assertEquals(testValue, returnedValue);
    }

    /**
     * After adding many non-null elements to the end of a new empty list, the
     * new size should be correct, the list's head should point to the first
     * element added, and the list's tail should point to the most recently
     * added element..
     *
     * @throws CouldNotAddException
     * @throws CouldNotRemoveException
     */
    @Test
    @DisplayName("Remove many non-null elements from front of empty list")
    void testRemoveFromFrontManyElements()
            throws CouldNotAddException, CouldNotRemoveException {
        Random random = new Random();
        final int reasonableMaximumSize = 1000;
        final int listSize = random.nextInt(reasonableMaximumSize);
        for (int i = 0; i < listSize; ++i) {
            testObject.addToEnd(i); // Autoboxing for the win!
            assertEquals(i + 1, testObject.size());
        }
        for (int i = 0; i < listSize; ++i) {
            int returnedValue = testObject.removeFromFront(); // Autoboxing for the win!
            assertEquals(listSize - 1 - i, testObject.size());
            assertEquals(i, returnedValue);
        }
        assertNull(testObject.getFirst());
        assertNull(testObject.getLast());
    }


}
