package payrollcasestudy.transactions.add;

import org.junit.Test;
import payrollcasestudy.boundaries.MemoryDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

import java.sql.SQLException;

/**
 * No Listing
 */
public class AddCommissionedEmployeeTransactionTest {

	private static final Repository repository = new MemoryDatabase();

    @Test
    public void testAddCommissionedEmployee() throws SQLException{
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddCommissionedEmployeeTransaction(employeeId, "Michael", "Home", 200.0 , 20.0);
        addEmployeeTransaction.execute(repository);
        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getName(), is("Michael"));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(CommissionedPaymentClassification.class)));
        CommissionedPaymentClassification commissionedClassification =
                (CommissionedPaymentClassification) paymentClassification;
        assertThat(commissionedClassification.getCommissionRate(), is(closeTo(20.0, FLOAT_ACCURACY)));
        assertThat(commissionedClassification.getMonthlySalary(), is(closeTo(200.0, FLOAT_ACCURACY)));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(BiweeklyPaymentSchedule.class)));
    }

}
