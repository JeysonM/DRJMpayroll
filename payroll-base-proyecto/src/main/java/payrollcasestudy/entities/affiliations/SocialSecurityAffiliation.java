package payrollcasestudy.entities.affiliations;

import payrollcasestudy.entities.PayCheck;

public class SocialSecurityAffiliation implements AffiliationClassification {

	@Override
	public double calculateDiscount(PayCheck payCheck) {
		return 0;
	}

}
