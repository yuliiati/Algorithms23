package Algorithms_2023.hw5_Queue;

import java.util.NoSuchElementException;
/*Классическая Queue - реализовать используя массив (Array)
    pushToEnd(int data) - вставляет элемент в конец queue
    remove() - удаляет элемент из начала queue
    peek() - возвращает элемент из начала не удаляя его
    isEmpty() - boolean
    size() - int

public class Queue
    private int[] arr;      // массив для хранения элементов queue
    private int head;      // head указывает на передний элемент в queue
    private int tail;       // tail часть указывает на последний элемент в queue
    private int capacity;   // максимальная емкость queue
    private int count;      // текущий размер queue


// Конструктор для инициализации queue

public Queue(int size)
    {
        arr = new int[size];
        capacity = size;
        count = 0;
    }*/
/*
В данном коде используется массив для хранения элементов очереди,
переменные head и tail для отслеживания начала и конца очереди соответственно,
capacity для хранения максимальной емкости очереди,
count для отслеживания текущего размера очереди.

Метод pushToEnd добавляет элемент в конец очереди,
метод remove удаляет элемент из начала очереди и возвращает его,
метод peek возвращает элемент из начала очереди, не удаляя его.
Методы isEmpty и size проверяют, является ли очередь пустой, и возвращают количество элементов в очереди соответственно.
*/
public class Queue {
        private int[] arr;      // массив для хранения элементов queue
        private int head;       // head указывает на передний элемент в queue
        private int tail;       // tail часть указывает на последний элемент в queue
        private int capacity;   // максимальная емкость queue
        private int count;      // текущий размер queue

        // Конструктор для инициализации queue
        public Queue(int size) {
            arr = new int[size];
            capacity = size;
            count = 0;
        }

        // Вставляет элемент в конец queue
        public void pushToEnd(int data) {
            if (count == capacity) {
                throw new IllegalStateException("Queue is full");
            }

            arr[tail] = data;
            tail = (tail + 1) % capacity;
            count++;
        }

        // Удаляет элемент из начала queue
        public int remove() {
            if (count == 0) {
                throw new NoSuchElementException("Queue is empty");
            }

            int data = arr[head];
            head = (head + 1) % capacity;
            count--;

            return data;
        }

        // Возвращает элемент из начала не удаляя его
        public int peek() {
            if (count == 0) {
                throw new NoSuchElementException("Queue is empty");
            }

            return arr[head];
        }

        // Проверяет, является ли queue пустым
        public boolean isEmpty() {
            return count == 0;
        }

        // Возвращает количество элементов в queue
        public int size() {
            return count;
        }
    }


