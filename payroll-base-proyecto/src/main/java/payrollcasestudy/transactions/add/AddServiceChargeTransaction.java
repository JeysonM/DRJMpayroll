package payrollcasestudy.transactions.add;

import java.util.Calendar;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
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
	
	public void execute(Repository repository){
		
		Employee employee = repository.getUnionMember(memberId);
		if(employee != null){
				employee.getUnionAffiliation().addServiceCharge(date, new ServiceCharge(date, amount));
		}
	}
}




