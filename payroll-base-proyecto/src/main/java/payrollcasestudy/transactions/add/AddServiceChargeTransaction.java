package payrollcasestudy.transactions.add;

import java.util.Calendar;

public class AddServiceChargeTransaction {
	private int memberId;
	private Calendar date;
	private double amount;
	public AddServiceChargeTransaction(int memberId, Calendar date, double amount) {
        this.memberId = memberId;
        this.date = date;
        this.amount = amount;
    }
	
	public void execute(){
		//Employe employee = PayrollDatebase.globalPayroll  
	}
}
