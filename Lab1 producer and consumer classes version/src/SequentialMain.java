public class SequentialMain {

    public static void main(String[] args) {

        final int[] arrayA = new int[Main.ARR_SIZE];
        final int[] arrayB = new int[arrayA.length];

        for (int i = 0; i < arrayA.length; i++) {
            arrayA[i] = i;
        }

        int[] buffer = new int[Main.BUF_SIZE];

        int leftIndex = 0;
        int elementsNumToWrite = buffer.length;

        final long start = System.currentTimeMillis();

        while (leftIndex + elementsNumToWrite <= arrayA.length) {

            // запись в буфер:
            for (int bufferIndex = 0; bufferIndex < elementsNumToWrite; bufferIndex++) {
                buffer[bufferIndex] = arrayA[leftIndex + bufferIndex];
            }

            // перезапись из буфера в массив B:
            for (int bufferIndex = 0; bufferIndex < elementsNumToWrite; bufferIndex++) {
                arrayB[leftIndex + bufferIndex] = buffer[bufferIndex];
            }

            leftIndex = leftIndex + buffer.length;
            if (leftIndex + buffer.length > arrayA.length) {
                elementsNumToWrite = arrayA.length - leftIndex;
            }
        }

        final long finish = System.currentTimeMillis();

        final long timeConsumedMillis = finish - start;
        System.out.println("Time: " + timeConsumedMillis);
    }
}