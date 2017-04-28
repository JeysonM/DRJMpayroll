package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.views.EmployeeView;
import spark.Route;

public class Main {
	public static void main(String[] args) {
		get("/", (request, response) -> hola());
		
		//post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo") ));
		get("/employees/new", (request, response) -> EmployeeView.formCreateEmployee());
		post("/create", (request, response) -> EmployeeView.createNewEmployee(request.queryParams("id"),request.queryParams("name"),request.queryParams("address")));
		get("/employees", (request, response) -> EmployeeView.showEmployee());
	}



	private static String responder_saludo(String nombre) {
		System.out.println("----------RESPONDIENDO---------");
		return "Hola "+nombre;
	}
	
	private static String hola() {
		return "<html>"
				+ "<body>"
				+ "<form method='post' action='/hola'>" 
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='nombre_saludo'>"
				+ "<input type='submit' value='Saluda'"
				+ "</body>"
				+ "</html>";
		
	}
	
}
