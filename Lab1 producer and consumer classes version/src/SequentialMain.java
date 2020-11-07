public class SequentialMain {

    public static void main(String[] args) {

        final int[] arrayA = new int[Main.ARR_SIZE];
        final int[] arrayB = new int[arrayA.length];

        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = i;
        }

        int[] buffer = new int[Main.BUF_SIZE];

        int leftIndex = 0;
        int rightIndex = buffer.length;

        final long start = System.currentTimeMillis();

        while (leftIndex + rightIndex <= arrayA.length) {

            // запись в буфер:
            for (int i = 0; i < rightIndex; i++) {
                buffer[i] = arrayA[leftIndex + i];
            }

            // перезапись из буфера в массив B:
            for (int i = 0; i < rightIndex; i++) {
                arrayB[leftIndex + i] = buffer[i];
            }

            leftIndex = leftIndex + buffer.length;
            if (leftIndex + buffer.length > arrayA.length) {
                rightIndex = arrayA.length - leftIndex;
            }
        }

        final long finish = System.currentTimeMillis();

        final long timeConsumedMillis = finish - start;
        System.out.println("Time: " + timeConsumedMillis);
    }
}