import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class OperationLogger extends Thread {

    private static final OperationLogger INSTANCE = new OperationLogger();

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    private OperationLogger() {
        setName("OperationLoggerThread");
        setDaemon(true);
        start();
    }

    public static void log(String message) {
        INSTANCE.queue.offer(formatMessage(message));
    }

    public static void shutdown() {
        INSTANCE.running = false;
        INSTANCE.interrupt();
    }

    private static String formatMessage(String message) {
        return "[" + LocalDateTime.now() + "] " + message;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String message = queue.take();
                System.out.println(message);
            } catch (InterruptedException e) {
                if (!running) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        queue.forEach(System.out::println);
    }
}
