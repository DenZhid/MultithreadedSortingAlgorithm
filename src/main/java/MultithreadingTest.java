import java.util.Arrays;
import java.util.Random;

public class MultithreadingTest {

    public static void main(String[] args) throws InterruptedException {
        int[] a = new int[20];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(100);
        } // Генерация исходного массива
        System.out.println("Начальный массив: " + Arrays.toString(a));
        int threadPool = 2;
        // Разделение исходного массива на два подмассива
        int[] subArray1 = new int[a.length / threadPool];
        int[] subArray2 = new int[a.length - a.length / threadPool];
        System.arraycopy(a, 0, subArray1, 0, a.length / threadPool);
        System.arraycopy(a, a.length / threadPool, subArray2, 0, a.length - a.length / threadPool);
        // Передача данных подмассивов сортировщикам
        Sorter sorter1 = new Sorter(subArray1);
        Sorter sorter2 = new Sorter(subArray2);
        sorter1.start(); // Начало работы первого потока
        sorter2.start(); // Начало работы второго потока
        sorter1.join();
        sorter2.join(); // Основной поток не продолжит работу, пока не закончат первый и второй потоки
        /* Процесс слияние подмассивов в единый аналогичен процессу сортировки, за исключением того, что используется
           только один поток, а не два */
        Merger merger = new Merger(sorter1.getSortableArr(), sorter2.getSortableArr());
        merger.start();
        merger.join();
        System.arraycopy(merger.getRes(), 0, a,0, a.length);
        System.out.println("Отсортированный массив: " + Arrays.toString(a));
    }
}
