package Algorithms_2023.hw3_DynamicArrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

// Для тестирования методов removeXXX() и fillXXX() можно написать следующие юнит тесты:

    public class DynamicArrayTest {

        @Test
        public void testRemoveLast() {
            DynamicArray array = new DynamicArray();
            long startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.add(i);
            }
            long addTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.removeLast();
            }
            long removeTime = System.nanoTime() - startTime;

            assertEquals(0, array.size());
            Assert.assertTrue(removeTime < addTime);
        }

        @Test
        public void testRemoveFirst() {
            DynamicArray array = new DynamicArray();
            long startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.add(i);
            }
            long addTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.removeFirst();
            }
            long removeTime = System.nanoTime() - startTime;

            assertEquals(0, array.size());
            Assert.assertTrue(removeTime < addTime);
        }

        @Test
        public void testRemoveMid() {
            DynamicArray array = new DynamicArray();
            long startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.add(i);
            }
            long addTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            for (int i = 0; i < 50000; i++) {
                array.remove(25000);
            }
            long removeTime = System.nanoTime() - startTime;

            assertEquals(50000, array.size());
            Assert.assertTrue(removeTime < addTime);
        }

        @Test
        public void testFillFromLast() {
            DynamicArray array = new DynamicArray();
            long startTime = System.nanoTime();
            array.fillFromLast(100000);
            long fillTime = System.nanoTime() - startTime;

            assertEquals(100000, array.size());

            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                assertEquals(100000 - i - 1, array.get(i));
            }

            startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.addAt(i, random.nextInt());
            }
            long addTime = System.nanoTime() - startTime;

            assertEquals(200000, array.size());
            Assert.assertTrue(fillTime < addTime);
        }

        @Test
        public void testFillFromFirst() {
            DynamicArray array = new DynamicArray();
            long startTime = System.nanoTime();
            array.fillFromFirst(100000);
            long fillTime = System.nanoTime() - startTime;

            assertEquals(100000, array.size());

            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                assertEquals(i, array.get(i));
            }

            startTime = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                array.addAt(50000, random.nextInt());
            }
            long addTime = System.nanoTime() - startTime;

            assertEquals(200000, array.size());
            Assert.assertTrue(fillTime < addTime);
        }
// Fill Tests
        @Test
        public void testFillFromLast() {
            DynamicArray array = new DynamicArray(10);
            long time = array.fillFromLast(10);
            assertEquals(10, array.size());
            assertFalse(array.isEmpty());
        }

        @Test
        public void testFillFromFirst() {
            DynamicArray array = new DynamicArray(10);
            long time = array.fillFromFirst(10);
            assertEquals(10, array.size());
            assertFalse(array.isEmpty());
        }

        @Test
        public void testFillFromMid() {
            DynamicArray array = new DynamicArray(10);
            long time = array.fillFromMid(6);
            assertEquals(10, array.size());
            assertFalse(array.isEmpty());
        }

    }
