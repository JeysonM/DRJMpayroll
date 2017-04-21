package payrollcasestudy.entities.affiliations;

import org.hamcrest.Matcher;

public class UnionAffiliation {
	private int numberId;
	private double amount;
	
	public static final UnionAffiliation NO_AFFILIATION = null;
	
	public UnionAffiliation(int numberId, double amount){
		this.numberId = numberId;
		this.amount = amount;
	}
	

	
	
	
}
