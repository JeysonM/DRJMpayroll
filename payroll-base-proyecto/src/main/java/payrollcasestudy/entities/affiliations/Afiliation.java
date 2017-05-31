package payrollcasestudy.entities.affiliations;

import payrollcasestudy.entities.PayCheck;

public interface Afiliation {
	public double calculateDeduction(PayCheck payCheck);
}
