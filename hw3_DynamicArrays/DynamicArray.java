package Algorithms_2023.hw3_DynamicArrays;
/*
Реализовать класс DynamicArray путем имплементации интерфейса Dynamic
дз должно содержать и интерфейс и его имплементацию.

interface Dynamic
-add(int index, int data); void - добавляет элемент в конец
-addAt(int data); void - добавляет элемент по индексу, при этом, если это происходит в середине,
то все остальные элементы сдвигаются на 1 вправо
-remove(int data); void - удаляет первое вхождение элемента в массив, все остальные элементы справа,
сдвигаются на 1 влево
-removeAt(int index); void - удаляет элемент по индексу, все остальные элементы справа, сдвигаются на 1 влево
-growSize(); void - удваивает размер массива (capacity)
-shrinkSize(); void - уменьшает размер массива до количества элементов (capacity = size)
-set(int index, int data); void - заменяет значения элемента по указанному индексу
-get(int index); int - возвращает элемент по указанному индексу (должна быть обработка,
                        если capacity позволяет добраться до этого элемента, а size - нет)
-clear(); void - удаляет все элементы из массива возвращая массив исходной длины (длина по умолчанию)
-contains(int data); boolean - возвращает true/false - если элемент есть в массиве
-isEmpty(); boolean - возвращает true/false - если в массиве есть что-то.


class DynamicArray implement Dynamic
поля:
int[] array
int count
int size


конструкторы:
DynamicArray()
DynamicArray(int size)


            доп методы для реализации по желанию:

методы removeXXX() - удаляют все элементы по 1 начиная с последнего/первого/середины соответственно.
        Возвращают время в нс потраченное на удаление. Реализация должна быть такова, что при удалении элемента из
        начала/середины все остальные элементы справа, сдвигаются на 1 влево в каждой итерации.
long removeLast()
long removeFirst()
long removeMid()


методы fillXXX(int count) - создают новый массив такой же длинны (size) и заполняют его
        рандомными элементами (class Random). Реализация должна быть такова, что при добавлении элемента в
        начало/середину все остальные элементы справа, сдвигаются на 1 влево в каждой итерации.
        Возвращают время в нс потраченное на создание.
long fillFromLast(int count)
long fillFromFirst(int count)
long fillFromMid(int count)


Также по желанию добавить Юнит Тесты */

import java.util.Random;

interface Dynamic {
        void add(int index, int data);
        void addAt(int data);
        void remove(int data);
        void removeAt(int index);
        void growSize();
        void shrinkSize();
        void set(int index, int data);
        int get(int index);
        void clear();
        boolean contains(int data);
        boolean isEmpty();
        int indexOf(int data);
}

    public class DynamicArray implements Dynamic {
        private int[] array;
        private int count;
        private int size;

        // Default constructor
        public DynamicArray() {
            size = 16; // Default size
            array = new int[size];
        }

        // Constructor with custom size
        public DynamicArray(int size) {
            this.size = size;
            array = new int[size];
        }

        @Override
        public void add(int index, int data) {
            if (count == size) {
                growSize();
            }
            for (int i = count; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = data;
            count++;
        }

        @Override
        public void addAt(int data) {
            add(count, data);
        }

        @Override
        public void remove(int data) {
            for (int i = 0; i < count; i++) {
                if (array[i] == data) {
                    removeAt(i);
                    break;
                }
            }
        }

        @Override
        public void removeAt(int index) {
            if (index >= count) {
                return;
            }
            for (int i = index; i < count - 1; i++) {
                array[i] = array[i + 1];
            }
            count--;
            if (size > 16 && count < size / 4) {
                shrinkSize();
            }
        }

        @Override
        public void growSize() {
            int[] newArray = new int[size * 2];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            size *= 2;
        }

        @Override
        public void shrinkSize() {
            int[] newArray = new int[size / 2];
            for (int i = 0; i < count; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            size /= 2;
        }

        @Override
        public void set(int index, int data) {
            if (index < count) {
                array[index] = data;
            }
        }

        @Override
        public int get(int index) {
            if (index < count) {
                return array[index];
            }
            return -1; // Or throw an exception
        }

        @Override
        public void clear() {
            size = 16;
            array = new int[size];
            count = 0;
        }

        @Override
        public boolean contains(int data) {
            for (int i = 0; i < count; i++) {
                if (array[i] == data) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean isEmpty() {
            return count == 0;
        }
        @Override
        public int indexOf(int data) {
            for (int i = 0; i < count; i++) {
                if (array[i] == data) {
                    return i;
                }
            }
            return -1;
        }

        // Additional methods
        // Remove methods


        public long removeLast() {
            if (isEmpty()) {
                throw new IllegalStateException("Array is empty");
            }
            long start = System.nanoTime();
            removeAt(count - 1);
            return System.nanoTime() - start;
        }

        public long removeFirst() {
            if (isEmpty()) {
                throw new IllegalStateException("Array is empty");
            }
            long start = System.nanoTime();
            removeAt(0);
            return System.nanoTime() - start;
        }

        public long removeMid() {
            if (isEmpty()) {
                throw new IllegalStateException("Array is empty");
            }
            long start = System.nanoTime();
            removeAt(count / 2);
            return System.nanoTime() - start;
        }
       /*
Для реализации методов fillXXX(int count) необходимо создать новый массив и заполнить его случайными числами.
При добавлении элемента в начало или середину массива все остальные элементы справа
должны сдвигаться на одну позицию влево.

        1) Пример реализации метода fillFromLast(int count):*/

        public long fillFromLast(int count) {
            long startTime = System.nanoTime();
            Random random = new Random();
            int[] tempArray = new int[size];
            for (int i = size - 1; i >= 0; i--) {
                if (count > 0) {
                    tempArray[i] = random.nextInt();
                    count--;
                } else {
                    break;
                }
            }
            for (int i = size - 1; i >= 0; i--) {
                add(tempArray[i]);
            }
            long endTime = System.nanoTime();
            return endTime - startTime;
        }
// 2) Пример реализации метода fillFromFirst(int count):
public long fillFromFirst(int count) {
    long startTime = System.nanoTime();
    Random random = new Random();
    int[] tempArray = new int[size];
    for (int i = 0; i < size; i++) {
        if (count > 0) {
            tempArray[i] = random.nextInt();
            count--;
        } else {
            break;
        }
    }
    for (int i = 0; i < size; i++) {
        add(tempArray[i]);
    }
    long endTime = System.nanoTime();
    return endTime - startTime;
}

// 3. Пример реализации метода fillFromMid(int count):

        public long fillFromMid(int count) {
            long startTime = System.nanoTime();
            Random random = new Random();
            int[] tempArray = new int[size];
            int mid = size / 2;
            int start = mid - count / 2;
            int end = mid + count / 2;
            if (count % 2 != 0) {
                end++;
            }
            for (int i = start; i < end; i++) {
                tempArray[i] = random.nextInt();
            }
            for (int i = size - 1; i >= mid; i--) {
                array[i + count] = array[i];
            }
            for (int i = start; i < end; i++) {
                addAt(i, tempArray[i]);
            }
            long endTime = System.nanoTime();
            return endTime - startTime;
        }



    }
