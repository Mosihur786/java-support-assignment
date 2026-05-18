# Task 2 — ConcurrentModificationException Analysis

Problem Code:-
A PSU bank client reported the following error intermittently at peak load. Answer the three questions below the trace in ANALYSIS.md or as comments in Task2Analysis.java.

ERROR 2024-04-10 09:14:33 [http-nio-8080-exec-23]
  c.s.dlp.service.StatementProcessorService - Processing failed
 
java.util.ConcurrentModificationException
  at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:911)
  at java.util.ArrayList$Itr.next(ArrayList.java:861)
  at c.s.dlp.service.StatementProcessorService
       .filterTransactions(StatementProcessorService.java:142)
  at c.s.dlp.service.StatementProcessorService
       .processStatement(StatementProcessorService.java:98)
  at c.s.dlp.controller.StatementController
       .upload(StatementController.java:67)


## 1. What is the exact cause of ConcurrentModificationException in Java?

ConcurrentModificationException occurs when a collection is structurally modified while it is being iterated using an Iterator, enhanced for-loop, or stream, except through the iterator's own remove() method.

Structural modifications include:
- Adding elements
- Removing elements
- Clearing elements

during active iteration.

---

## 2. What code pattern at line 142 most likely triggered this error?

Most likely code pattern:

```java
for (Transaction tx : transactions) {
    if (tx.isInvalid()) {
        transactions.remove(tx);
    }
}
```

or:

```java
Iterator<Transaction> iterator = transactions.iterator();

while (iterator.hasNext()) {
    Transaction tx = iterator.next();
    transactions.remove(tx);
}
```

The collection is being modified directly during iteration.

---

## 3. Provide the minimal code change (one or two lines) that resolves this safely.

Use the iterator's remove() method instead of modifying the list directly.

```java
Iterator<Transaction> iterator = transactions.iterator();

while (iterator.hasNext()) {
    Transaction tx = iterator.next();

    if (tx.isInvalid()) {
        iterator.remove();
    }
}
```

This safely removes elements during iteration without causing ConcurrentModificationException.
