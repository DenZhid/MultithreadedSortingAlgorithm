import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithmTest {

    public static void main(String[] args) {
        int[] a = new int[10];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);
        }
        System.out.println("Массив до сортировки: " + Arrays.toString(a));
        sort(a, 0, a.length);
        System.out.println("Массив после сортировки: " + Arrays.toString(a));
    }

    private static void sort(int[] in, int left, int right) {
        if (left >= right - 1) {
            return;
        } // Если в массиве только один элемент, значит что он уже отсортирован
        int i = partition(in, left, right);
        sort(in, left, i); // Проводим сортировку для элементов слева от последнего опорного элемента
        sort(in, i + 1, right); // Аналогично справа
    }

    /*Сортируем массив так, чтобы получить две части:
      слева от опорного элемента: элементы меньшие или равные опорному
      справа от опорного элемента: элементы большие опорного.
      Из метода возвращаем индекс местонахождения опорного элемента после перестановок */
    private static int partition(int[] in, int left, int right) {
        int pivotal = in[left]; // В качестве опорного элемента выбираем первый элемент массива
        int i = left;
        for (int j = left + 1; j < right; j++) {
            if (in[j] <= pivotal) {
                swap(in, ++i, j);
            }
        }
        swap(in, i, left);
        return i;
    }

    private static void swap(int[] in, int left, int right) {
        int tmp = in[left];
        in[left] = in[right];
        in[right] = tmp;
    }
}