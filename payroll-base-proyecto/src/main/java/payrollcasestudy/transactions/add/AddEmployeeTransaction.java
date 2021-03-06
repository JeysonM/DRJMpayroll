package payrollcasestudy.transactions.add;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;
import payrollcasestudy.transactions.Transaction;

/**
 * Listing 19-5
 * Listing 19-6
 */
public abstract class AddEmployeeTransaction implements Transaction {
    private int employeeId;
    private String employeeName;
    private String employeeAddress;


    public AddEmployeeTransaction(int employeeId, String employeeName, String employeeAddress){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
    }

    public void execute(Repository repository){
        PaymentClassification paymentClassification = getPaymentClassification();
        PaymentSchedule paymentSchedule = getPaymentSchedule();
        Employee employee = new Employee(employeeId, employeeName, employeeAddress);
        employee.setPaymentClassification(paymentClassification);
        employee.setPaymentSchedule(paymentSchedule);
        repository.addEmployee(employeeId, employee);
       
    }

    protected abstract PaymentSchedule getPaymentSchedule();

    protected abstract PaymentClassification getPaymentClassification();
}
