package payrollcasestudy.transactions.services;

import java.util.ArrayList;

import payrollcasestudy.api.ServiceFromJson;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;


public class AddCards implements ServicesJson {

	@Override
	public void addServicesFromJson(Repository repository, String json) {
		ServiceFromJson service = new ServiceFromJson();
		ArrayList<AddTimeCardTransaction> list = service.FromGson(json);
		for(AddTimeCardTransaction addTimeCard : list){
			addTimeCard.execute(repository);
		}	
	}

}
