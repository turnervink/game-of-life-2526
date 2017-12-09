import ca.bcit.comp2526.a2c.CouldNotRemoveException;
import ca.bcit.comp2526.a2c.DoubleLinkedList;
import ca.bcit.comp2526.a2c.CouldNotAddException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Turner Vink - A01012232
 * @version 1.0
 */
class DoubleLinkedListTest {
    private DoubleLinkedList<Integer> testObject;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testObject = new DoubleLinkedList<>();
    }

    @Test
    @DisplayName("New list is empty")
    void testNewListIsEmpty() {
        assertTrue(testObject.isEmpty());
    }

    @Test
    @DisplayName("Adding a null value to the front throws CouldNotAddException")
    void testAddToFrontNull() {
        assertThrows(CouldNotAddException.class, () -> testObject.addToFront(null));
    }

    @Test
    @DisplayName("Adding a null value to the end throws CouldNotAddException")
    void testAddToEndNull() {
        assertThrows(CouldNotAddException.class, () -> testObject.addToEnd(null));
    }

    @Test
    @DisplayName("Add to front works")
    void testAddToFront() throws CouldNotAddException {
        final int testValue = 999;
        testObject.addToFront(testValue);

        int cur = 0;
        for (int i : testObject) {
            cur = i;
        }

        assertEquals(testValue, cur);
    }

    @Test
    @DisplayName("Add to end works")
    void testAddToEnd() throws CouldNotAddException {
        final int testValue = 999;
        testObject.addToEnd(testValue);

        int cur = 0;
        for (int i : testObject) {
            cur = i;
        }

        assertEquals(testValue, cur);
    }

    @Test
    @DisplayName("Cannot remove from the front of an empty list")
    void testRemoveFromFrontEmpty() {
        assertThrows(CouldNotRemoveException.class, () -> testObject.removeFromFront());
    }

    @Test
    @DisplayName("Cannot remove from the end of an empty list")
    void testRemoveFromEndEmpty() {
        assertThrows(CouldNotRemoveException.class, () -> testObject.removeFromEnd());
    }

    @Test
    @DisplayName("Remove from front works with a single element")
    void testRemoveFromSingleValueListFront() throws CouldNotAddException, CouldNotRemoveException {
        final int testValue = 97;
        testObject.addToFront(testValue);
        int removedValue = testObject.removeFromFront();
        assertEquals(testValue, removedValue);
    }

    @Test
    @DisplayName("Remove from end works with a single element")
    void testRemoveFromSingleValueListEnd() throws CouldNotAddException, CouldNotRemoveException {
        final int testValue = 92;
        testObject.addToEnd(testValue);
        int removedValue = testObject.removeFromEnd();
        assertEquals(testValue, removedValue);
    }

    @Test
    @DisplayName("Iterator for an empty list is null")
    void testIteratorEmptyList() {
        Iterator<Integer> iter = testObject.iterator();
        assertFalse(iter.hasNext());
    }

    @Test
    @DisplayName("Iterator for a list with a single value works")
    void testIteratorSingleValueList() throws CouldNotAddException {
        final int testValue = 42;
        testObject.addToFront(testValue);

        Iterator<Integer> iter = testObject.iterator();
        assertTrue(iter.hasNext());
        assertEquals(testValue, (int) iter.next());
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next());
    }

    @Test
    @DisplayName("Iterator for a list with many elements works")
    void testIteratorManyValueList() throws CouldNotAddException {
        Random rand = new Random();
        final int maxValue = 500;
        final int listSize = rand.nextInt(maxValue);
        for (int i = 0; i < listSize; i++) {
            testObject.addToFront(i);
        }

        Iterator<Integer> iter = testObject.iterator();
        for (int i = 0; i < listSize; i++) {
            assertTrue(iter.hasNext());
            assertEquals(listSize - i - 1, (int) iter.next());
        }
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, () -> iter.next ());
    }

}