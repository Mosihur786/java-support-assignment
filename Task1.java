import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task1 {

    public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

        // FIX: Initialize result list to avoid NullPointerException on add()
        List<LoanAccount> result = new ArrayList<>();

        // FIX: Prevent NullPointerException when accounts list is null
        if (accounts == null) {
            return result;
        }

        for (LoanAccount account : accounts) {

            // FIX: Prevent NullPointerException for null account and null dueDate
            if (account != null &&
                account.getDueDate() != null &&
                account.getDueDate().before(new Date())) {

                // FIX: Only include accounts with positive outstanding balance
                if (account.getOutstandingBalance() > 0) {
                    result.add(account);
                }
            }
        }

        return result;
    }
}
