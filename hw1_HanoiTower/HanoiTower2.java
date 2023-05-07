package Algorithms_2023.hw1_HanoiTower;
import java.util.Stack;

// Итеративное решение задачи Ханойской башни:
//____________________________________________

public class HanoiTower2 {
    public static void main(String[] args) {
        int n = 3; // количество дисков
        char from = 'A', to = 'C', aux = 'B'; // стержни

        Stack<Integer>[] tower = new Stack[3];
        tower[0] = new Stack<Integer>();
        tower[1] = new Stack<Integer>();
        tower[2] = new Stack<Integer>();

        // заполнение начального стержня
        for (int i = n; i > 0; i--) {
            tower[0].push(i);
        }

        // алгоритм Ханойской башни
        int totalMoves = (int) (Math.pow(2, n) - 1);
        if (n % 2 == 0) {
            char temp = to;
            to = aux;
            aux = temp;
        }
        for (int i = 1; i <= totalMoves; i++) {
            if (i % 3 == 1) {
                move(tower, from, to);
            } else if (i % 3 == 2) {
                move(tower, from, aux);
            } else {
                move(tower, aux, to);
            }
        }
    }

    public static void move(Stack<Integer>[] tower, char from, char to) {
        int disk = tower[from - 'A'].pop();
        tower[to - 'A'].push(disk);
        System.out.println("Move disk " + disk + " from " + from + " to " + to);
    }
}
