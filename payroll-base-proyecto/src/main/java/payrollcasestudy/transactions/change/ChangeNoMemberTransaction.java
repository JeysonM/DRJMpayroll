package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {
	
	
	public ChangeNoMemberTransaction(int employeeId)
	{
		super(employeeId);
	}

	@Override
	public void changeEmployee(Employee employee) {
		int memberId = employee.getUnionAffiliation().getNumberId();
		employee.setUnionAffiliation(null);
		database.deleteUnionMember(memberId);
	}

	

}
