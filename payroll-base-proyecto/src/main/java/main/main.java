package Main;
import static spark.Spark.*;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import spark.Route;

public class Main {
	public static void main(String[] args) {
		get("/", (request, response) -> hola());
		post("/hola", (request, response) -> responder_saludo(request.queryParams("nombre_saludo") ));
		get("/employee", (request, response) -> viewCreateEmployee());
		post("/new", (request, response) -> createEmployee(request.queryParams("id"),request.queryParams("name"),request.queryParams("address")));
		get("/Arquitectura", (request, response) -> "Hola Arquitectura");
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
	
	private static String viewCreateEmployee() {
		return "<html>"
				+ "<h1>Nuevo Empleado</h1>"
				+ "<body>"
				+ "<form method='post' action='/new'>" 
				+ "<label>ID:</label>"
				+ "<input type='number' name='id'>"
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='name'>"
				+ "<label>Direccion:</label>"
				+ "<input type='text' name='address'>"
				+ "<input type='submit' value='crear'>"
				+ "</body>"
				+ "</html>";
	}
	
	private static String createEmployee(String employeeId, String name, String address) {
		int employeeIdInt = Integer.parseInt(employeeId);
		Employee employee = new Employee(employeeIdInt,name,address);
        PayrollDatabase.globalPayrollDatabase.addEmployee(employeeIdInt, employee);
        return "Exito al crear";
	}
	
	
}
