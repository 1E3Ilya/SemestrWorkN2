import java.util.Random;

public class Main {
    public static int averageForIterations(int[] a) {
        int sum = 0;
        for (int j : a) {
            sum += j;
        }
        return sum / a.length;
    }

    public static long averageForTime(long[] a) {
        long sum = 0;
        for (long l : a) {
            sum += l;
        }
        return sum / a.length;
    }

    public static void main(String[] args) {
        int[] arr = DataGenerator.generateRandomArray(10000);
        FenwickTree tree = new FenwickTree(10000);

        //Добавление
        long[] timeOfAdd = new long[10000];
        int[] iterationsOfAdd = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            long start = System.nanoTime();
            tree.update(i, arr[i]);
            timeOfAdd[i] = System.nanoTime() - start;
            iterationsOfAdd[i] = tree.iterations;
            tree.iterations = 0;
        }

        //Удаление
        long[] timeOfDelete = new long[1000];
        int[] iterationsOfDelete = new int[1000];
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            long start = System.nanoTime();
            int indexToDel = random.nextInt(10000);
            tree.delete(indexToDel);
            timeOfDelete[i] = System.nanoTime() - start;
            iterationsOfDelete[i] = tree.iterations;
            tree.iterations = 0;
        }

        //подсчет сумм
        long[] timeOfSum = new long[1000];
        int[] iterationsOfSum = new int[1000];
        for (int i = 0; i < 1000; i++) {
            int start = random.nextInt(10000);
            int end = random.nextInt(10000);
            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
            }
            long time = System.nanoTime();
            tree.rangeSum(start, end);
            timeOfSum[i] = System.nanoTime() - time;
            iterationsOfSum[i] = tree.iterations;
            tree.iterations = 0;
        }

        //Считаем средние значения
        System.out.println("Среднее время добавления: " + averageForTime(timeOfAdd));
        System.out.println("Среднее количество итераций добавления: " + averageForIterations(iterationsOfAdd));
        System.out.println("Среднее время удаления: " + averageForTime(timeOfDelete));
        System.out.println("Среднее количество итераций удаления: " + averageForIterations(iterationsOfDelete));
        System.out.println("Среднее время подсчета сумм: " + averageForTime(timeOfSum));
        System.out.println("Среднее количество итераций подсчета сумм: " + averageForIterations(iterationsOfSum));
    }
}
