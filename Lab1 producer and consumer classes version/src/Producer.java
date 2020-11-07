public class Producer extends Thread {

    private final int[] arrayA;
    private final Buffer buffer;

    public Producer(int[] array, Buffer buffer) {
        this.arrayA = array;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i : arrayA) {
                buffer.put(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}