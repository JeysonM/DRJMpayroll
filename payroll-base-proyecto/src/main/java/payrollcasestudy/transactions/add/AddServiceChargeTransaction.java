package payrollcasestudy.transactions.add;

import java.util.Calendar;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;

public class AddServiceChargeTransaction implements Transaction {
	private int memberId;
	private Calendar date;
	private double amount;
	
	public AddServiceChargeTransaction(int memberId, Calendar date, double amount){
        this.memberId = memberId;
        this.date = date;
        this.amount = amount;
    }
	
	public void execute(){
		//Employe employee = PayrollDatebase.globalPayroll
		Employee employee = PayrollDatabase.globalPayrollDatabase.getUnionMember(this.memberId);
		if(employee != null){
			//employee.addServiceCard();
			
		}
	}
}




