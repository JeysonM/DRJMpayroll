package payrollcasestudy.transactions.add;

import org.junit.Test;

import payrollcasestudy.boundaries.MemoryDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.transactions.Transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

import java.sql.SQLException;

/**
 * Listing 19-2
 */
public class AddSalariedEmployeeTransactionTest {
	private static final Repository repository = new MemoryDatabase();
	
    @Test
    public void testAddSalariedEmployee() throws SQLException{
        int employeeId = 1;
        Transaction addEmployeeTransaction =
                new AddSalariedEmployeeTransaction(employeeId, "Bob", "Home", 1000.0);
        addEmployeeTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getName(), is("Bob"));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        assertThat(paymentClassification, is(instanceOf(SalariedClassification.class)));
        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
        assertThat(salariedClassification.getSalary(), closeTo(1000.0, FLOAT_ACCURACY));

        assertThat(employee.getPaymentSchedule(), is(instanceOf(MonthlyPaymentSchedule.class)));
        assertThat(employee.getUnionAffiliation(), is(UnionAffiliation.NO_AFFILIATION));
    }

}
