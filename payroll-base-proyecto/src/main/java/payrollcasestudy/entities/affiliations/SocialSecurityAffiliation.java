package payrollcasestudy.entities.affiliations;

import payrollcasestudy.entities.PayCheck;

public class SocialSecurityAffiliation implements AffiliationClassification {

	@Override
	public double calculateDiscount(PayCheck payCheck) {
		double _DISCOUNT = 0.12;
		return payCheck.getGrossPay()-_DISCOUNT;
	}

}
