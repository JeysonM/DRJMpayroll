package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;
import java.util.HashMap;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.views.EmployeeView;
import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
	public static void main(String[] args) {
		//staticFileLocation("/public");
		get("/", (request, response) -> {
			HashMap<String,Object> model=new HashMap<String,Object>();
			String word="palabra";
			model.put("word",word);
		      return new ModelAndView(model, "indexEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees", (request, response) -> EmployeeView.showAllEmployees());
		
		get("/employees/new", (request, response) -> {
            return new ModelAndView(new HashMap(), "formCreateEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/create", (request, response) -> {
			EmployeeView.createNewEmployee(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"));
            return new ModelAndView(new HashMap(), "formCreateEmployee.vtl");
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
