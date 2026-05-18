import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task5 {

    // FIX: Use SLF4J logger instead of printStackTrace()
    private static final Logger logger =
            LoggerFactory.getLogger(Task5.class);

    public ValidationResult validate(Document doc) {

        try {

            // FIX: Handle expected validation failure without unnecessary stack trace
            if (doc == null) {
                logger.warn("Validation failed: document is null");
                return null;
            }

            String content = doc.extractContent();

            // FIX: Handle empty or null content safely
            if (content == null || content.isEmpty()) {
                logger.warn("Validation failed: empty content");
                return null;
            }

            return runValidationRules(content);

        } catch (Exception e) {

            // FIX: Log unexpected runtime exceptions properly
            logger.error("Unexpected error during document validation", e);

            // FIX: Prevent silent failure by returning controlled result
            return null;
        }
    }

    public void validateBatch(List<Document> docs) {

        for (Document doc : docs) {

            try {

                ValidationResult r = validate(doc);

                // FIX: Prevent NullPointerException before calling isValid()
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (Exception e) {

                // FIX: Do not silently swallow exceptions
                logger.error("Batch validation failed", e);
            }
        }
    }

    private ValidationResult runValidationRules(String content) {
        return null;
    }

    private void saveResult(ValidationResult r) {
        // existing logic
    }
}
