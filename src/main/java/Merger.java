public class Merger extends Thread {

    private final int[] first;
    private final int[] second;
    private final int[] res;

    public Merger(int[] first, int[] second) {
        this.first = first;
        this.second = second;
        this.res = new int[first.length + second.length];
    }

    @Override
    public void run() {
        merge();
    }

    private void merge() {
        int firstPointer = 0; // Указатель на элемент в первом подмассиве
        int secondPointer = 0; // Указатель на элемент в правом подмассиве
        int resIndex = 0; // Указатель на место в результирующем массиве
        while (firstPointer < first.length && secondPointer < second.length) {
            /* Сравниваем элементы из двух подмассивов и помещаем в результирующий
               массив меньший из них */
            if (first[firstPointer] <= second[secondPointer]) {
                res[resIndex++] = first[firstPointer++];
            } else {
                res[resIndex++] = second[secondPointer++];
            }
        } // Выполняем данные проверки, пока не пройдём по одному из подмассивов
        /* Проверяем остались ли какие-либо элементы в оставшемся подмассиве
           и, если да, то переносим их в результирующий */
        if (firstPointer == first.length) {
            while (secondPointer < second.length) {
                res[resIndex++] = second[secondPointer++];
            }
        } else if (secondPointer == second.length) {
            while (firstPointer < first.length) {
                res[resIndex++] = first[firstPointer++];
            }
        }
    }

    public int[] getRes() {
        return res;
    }
}
