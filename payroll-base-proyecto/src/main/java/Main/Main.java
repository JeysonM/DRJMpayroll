package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.controllers.EmployeeController;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
	public static void main(String[] args) {
		//staticFileLocation("/public");
		HashMap<String,Object> view = new HashMap<String,Object>();
		
		get("/", (request, response) -> {
			
		      return new ModelAndView(view, "indexEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees", (request, response) -> {
			
			List<Employee> employees = new ArrayList<>();
			employees = EmployeeController.showAllEmployees();
			view.put("employees", employees);
			return new ModelAndView(view, "allEmployees.vtl");
		}, new VelocityTemplateEngine());
		
		get("/employees/new", (request, response) -> {
			
            return new ModelAndView(view, "newEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/create", (request, response) -> {
			
			EmployeeController.createNewEmployee(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"));
            return new ModelAndView(view, "newEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		get("/showEmployee/:id", (request, response) -> {
			Employee employee;
			employee = EmployeeController.showEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
            return new ModelAndView(view, "showEmployee.vtl");
        }, new VelocityTemplateEngine());
		
	}

	
	
}
