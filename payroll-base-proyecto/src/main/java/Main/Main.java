package Main;



import payrollcasestudy.boundaries.ConnectionMySQL;
import payrollcasestudy.boundaries.MemoryDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.services.EmployeeService;
import payrollcasestudy.transactions.services.PaymentService;


public class Main {
	public static void main(String[] args) {
		Repository repository = new ConnectionMySQL();
		
		EmployeeService employeeService = new EmployeeService(repository);
		PaymentService paymentService = new PaymentService(repository.getRepository());
		Routes routes = new Routes(employeeService, paymentService);
		routes.manageRoutes();
	}
}
