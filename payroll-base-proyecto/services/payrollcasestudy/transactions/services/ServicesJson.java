package payrollcasestudy.transactions.services;

import payrollcasestudy.boundaries.Repository;

public interface ServicesJson {
	
	void addServicesFromJson(Repository repository, String json);

}
