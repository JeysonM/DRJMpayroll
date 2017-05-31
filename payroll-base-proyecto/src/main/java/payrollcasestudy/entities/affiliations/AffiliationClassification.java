package payrollcasestudy.entities.affiliations;

import payrollcasestudy.entities.PayCheck;

public interface AffiliationClassification {
	
	double calculateDiscount(PayCheck payCheck);

}
