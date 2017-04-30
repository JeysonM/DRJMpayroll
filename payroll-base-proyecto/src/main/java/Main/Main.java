package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;
import java.util.HashMap;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.controllers.EmployeeController;
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
	public static void main(String[] args) {
		//staticFileLocation("/public");
		HashMap<String,Object> view = new HashMap<String,Object>();
		get("/", (request, response) -> {
			HashMap<String,Object> model=new HashMap<String,Object>();
			String word="palabra";
			model.put("word",word);
		      return new ModelAndView(model, "indexEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees", (request, response) -> {
			return new ModelAndView(EmployeeController.showAllEmployees(), "showEmployees.vtl");
		}, new VelocityTemplateEngine());
		
		get("/employees/new", (request, response) -> {
            return new ModelAndView(view, "formCreateEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/create", (request, response) -> {
			EmployeeController.createNewEmployee(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"));
            return new ModelAndView(view, "formCreateEmployee.vtl");
        }, new VelocityTemplateEngine());
		
//		get("/employees", (request, response) -> {
//			HashMap<String,Object> model=new HashMap<String,Object>();
//			//String word="palabra";
//			String employees=EmployeeView.showAllEmployees();
//			//model.put("word",word);
//			model.put("employees",employees);
//		      return new ModelAndView(model, "indexEmployee.vtl");
//		    }, new VelocityTemplateEngine());
	}

	
	
}
