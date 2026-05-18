import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3 {

    // FIX: int is not thread-safe in concurrent environments.
    // AtomicInteger prevents race conditions and lost updates.
    private AtomicInteger processedCount = new AtomicInteger(0);

    public void process(List<StatementRecord> records) {

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (StatementRecord record : records) {

            executor.submit(() -> {
                processRecord(record);

                // FIX: Thread-safe increment operation
                processedCount.incrementAndGet();
            });
        }

        executor.shutdown();

        try {

            executor.awaitTermination(5, TimeUnit.MINUTES);

        } catch (InterruptedException e) {

            // FIX: Restore interrupted state instead of swallowing interruption
            Thread.currentThread().interrupt();
        }
    }

    public int getProcessedCount() {
        return processedCount.get();
    }

    private void processRecord(StatementRecord record) {
        // existing logic
    }
}
