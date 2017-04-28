package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.views.EmployeeView;
import spark.Route;

public class Main {
	public static void main(String[] args) {
		get("/", (request, response) -> root());
		
		//post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo") ));
		get("/employees/new", (request, response) -> EmployeeView.formCreateEmployee());
		post("/create", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"),request.queryParams("name"),request.queryParams("address")));
		get("/employees", (request, response) -> EmployeeView.showEmployee());
	}

	
	private static String root() {
		return "<html>"
				+ "<body>"
				+ "<h1>Pagina de Inicio</h1>" 
				+ "</body>"
				+ "</html>";
		
	}
	
}
