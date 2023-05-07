package Algorithms_2023.hw4_LinkedList;
/*реализовать класс MyLinkedList путем имплементации интерфейса Linked
дз должно содержать и интерфейс и его имплементацию.

interface Linked
-add( int data); void - добавляет элемент в конец списка
-addAt(int index, int data); void - добавляет элемент по индексу
-remove(int data); void - удаляет первое вхождение элемента в список
-removeAt(int index); void - удаляет элемент по индексу
-set(int index, int data); void - заменяет значения элемента по указанному индексу
-get(int index); int - возвращает элемент по указанному индексу
-clear(); void - удаляет все элементы, оставляет пустой список
-contains(int data); boolean - возвращает true/false - если элемент есть в списке
-isEmpty(); boolean - возвращает true/false - если в списке есть что-то.
-size(); int  - возвращает количество элементов в списке


класс MyLinkedList
поля:
Node last;
Node first;
int size;


конструкторы:
MyLinkedList()


класс Node (реализовать, как вложенный класс):
поля:
Node next;
Node previous;
int data;


ваша реализация должна представлять собой двусвязный НЕ круговой список.


доп задание по желанию:
Произвести дополнительную логику в методы, принимающие индекс так, чтобы к ноде можно было добраться
кратчайшим путем - от начала, если нода ближе к началу, или от конца списка, если нода ближе к концу.
Это касается методов get(int index), set(int index, int data), addAt(int index, int data), removeAt(int index)*/


    public class MyLinkedList implements Linked {
        private Node last;
        private Node first;
        private int size;

        public MyLinkedList() {
            size = 0;
        }

        private class Node {
            private Node next;
            private Node previous;
            private int data;

            public Node(int data) {
                this.data = data;
            }
        }

        @Override
        public void add(int data) {
            Node newNode = new Node(data);
            if (size == 0) {
                first = newNode;
            } else {
                newNode.previous = last;
                last.next = newNode;
            }
            last = newNode;
            size++;
        }

        @Override
        public void addAt(int index, int data) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            if (index == 0) {
                Node newNode = new Node(data);
                if (size == 0) {
                    last = newNode;
                } else {
                    first.previous = newNode;
                    newNode.next = first;
                }
                first = newNode;
                size++;
            } else if (index == size) {
                add(data);
            } else {
                Node currentNode = getNode(index);
                Node newNode = new Node(data);
                currentNode.previous.next = newNode;
                newNode.previous = currentNode.previous;
                newNode.next = currentNode;
                currentNode.previous = newNode;
                size++;
            }
        }

        @Override
        public void remove(int data) {
            Node currentNode = first;
            while (currentNode != null) {
                if (currentNode.data == data) {
                    if (currentNode == first) {
                        first = first.next;
                        if (first != null) {
                            first.previous = null;
                        }
                    } else if (currentNode == last) {
                        last = last.previous;
                        last.next = null;
                    } else {
                        currentNode.previous.next = currentNode.next;
                        currentNode.next.previous = currentNode.previous;
                    }
                    size--;
                    return;
                }
                currentNode = currentNode.next;
            }
        }

        @Override
        public void removeAt(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            if (index == 0) {
                first = first.next;
                if (first != null) {
                    first.previous = null;
                }
            } else if (index == size - 1) {
                last = last.previous;
                last.next = null;
            } else {
                Node currentNode = getNode(index);
                currentNode.previous.next = currentNode.next;
                currentNode.next.previous = currentNode.previous;
            }
            size--;
        }

        @Override
        public void set(int index, int data) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Node currentNode = getNode(index);
            currentNode.data = data;
        }

        @Override
        public int get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            Node currentNode = getNode(index);
            return currentNode.data;
        }

        @Override
        public void clear() {
            first = null;
            last = null;
            size = 0;
        }

        @Override
        public boolean contains(int data) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public int size() {
            return 0;
        }

    }


