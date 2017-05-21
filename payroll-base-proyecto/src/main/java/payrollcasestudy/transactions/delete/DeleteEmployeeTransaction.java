package payrollcasestudy.transactions.delete;

import java.sql.SQLException;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.Transaction;

public class DeleteEmployeeTransaction implements Transaction{
    private int employeeId;

    public DeleteEmployeeTransaction(int employeeId) {
        this.employeeId = employeeId;
    }

    public void execute(Repository repository) throws SQLException {
        repository.deleteEmployee(employeeId);
    }
}
