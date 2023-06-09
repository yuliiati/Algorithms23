package Algorithms_2023.hw2_BigO;
/*//__________________________________________________________________________________________________________________________

Task 1. Даны два целых числа x и n, напишите функцию для вычисления x^n
     решение 1 - рекурсивно O(n)
     решение 2 - улучшить решение 1 до O(lon n)

//__________________________________________________________________________________________________________________________

Task 2. Имея два отсортированных массива размера m и n соответственно, вам нужно найти элемент, который будет находиться на k-й позиции в конечном отсортированном массиве.
Массив 1 - 100 112 256 349 770
Массив 2 - 72 86 113 119 265 445 892
к = 7
Вывод : 256
Окончательный отсортированный массив -
72, 86, 100, 112, 113, 119, 256, 265, 349, 445, 770, 892
7-й элемент этого массива равен 256.

//__________________________________________________________________________________________________________________________

Task 3. Имея отсортированный массив arr[] и число x, напишите функцию, которая подсчитывает вхождения x в arr[]. Ожидаемая временная сложность O(Log n)
arr[] = {1, 1, 2, 2, 2, 2, 3,}
x = 2
Вывод: 4 раза

//__________________________________________________________________________________________________________________________
Task 4*. Найдите пиковый элемент в двумерном массиве
Элемент является пиковым, если он больше или равен своим четырем соседям слева, справа, сверху и снизу. Например, соседями для A[i][j] являются A[i-1][j], A[i+1][j], A[i][j-1] и A[i][j+1 ]. Для угловых элементов отсутствующие соседи считаются отрицательными бесконечными значениями.
10 20 15
21 30 14
 7 16 32
Выход: 30
30 — пиковый элемент, потому что все его
соседи меньше или равны ему.
32 также можно выбрать в качестве пика.

//__________________________________________________________________________________________________________________________

Note:
1 Смежная диагональ не считается соседней.
2 Пиковый элемент не обязательно является максимальным элементом.
3 Таких элементов может быть несколько.
4 Всегда есть пиковый элемент.*/
//__________________________________________________________________________________________________________________________

public class Hw2Tasks {

    //Task 1:
    //Решение 1 - рекурсивно O(n):

//    --коммент повторение

//    public static int pow(int x, int n) {
//        if (n == 0) {
//            return 1;
//        }
//        return x * pow(x, n - 1);
//    }

    // Решение 2 - улучшение решения 1 до O(log n):
// Принцип "разделяй и властвуй", чтобы решить задачу за O(log n)

    public static int pow(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int temp = pow(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        } else {
            return x * temp * temp;
        }
    }
// Task 2:

    public static int findKthElement(int[] arr1, int[] arr2, int k) {
        int m = arr1.length;
        int n = arr2.length;
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                if (i + j == k - 1) {
                    return arr1[i];
                }
                i++;
            } else {
                if (i + j == k - 1) {
                    return arr2[j];
                }
                j++;
            }
        }
        while (i < m) {
            if (i + j == k - 1) {
                return arr1[i];
            }
            i++;
        }
        while (j < n) {
            if (i + j == k - 1) {
                return arr2[j];
            }
            j++;
        }
        return -1; // Если k больше, чем размер обоих массивов
    }
// Task 3:
//    int[] arr = {1, 1, 2, 2, 2, 2, 3};
//    int x = 2;
//    int count = countOccurrences(arr, x);
//System.out.println("Number of occurrences of " + x + ": " + count);
    // Output: Number of occurrences of 2: 4

    public static int countOccurrences(int[] arr, int x) {
        int first = findFirst(arr, x);
        int last = findLast(arr, x);
        if (first == -1) {
            return 0;
        } else {
            return last - first + 1;
        }
    }

    private static int findFirst(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int findLast(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }


}

