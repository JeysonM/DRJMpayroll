package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {
	
	
	public ChangeNoMemberTransaction(int employeeId)
	{
		super(employeeId);
	}

	@Override
	public void changeEmployee(Employee employee, Repository repository) {
		int memberId = employee.getUnionAffiliation().getNumberId();
		employee.setUnionAffiliation(UnionAffiliation.NO_AFFILIATION);
		repository.deleteUnionMember(memberId);
	}

	

}
