package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.ServiceCharge;

public class Afiliations {
	private int numberId;
	private Map<Integer, Afiliation> myAfiliations = new HashMap<Integer, Afiliation>();
	
	public Afiliations(int numberId){
		this.numberId = numberId;
	}
}
