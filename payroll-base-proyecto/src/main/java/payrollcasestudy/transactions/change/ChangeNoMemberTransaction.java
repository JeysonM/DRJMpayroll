package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction implements Transaction {
	
	int employeeId;
	
	public ChangeNoMemberTransaction(int employeeId)
	{
		this.employeeId=employeeId;
	}

	public void execute() {
		PayrollDatabase.globalPayrollDatabase.changeNullUnionAffilliationAEmployee(employeeId);
		
	}

	

}
