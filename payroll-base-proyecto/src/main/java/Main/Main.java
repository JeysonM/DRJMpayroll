package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;
import payrollcasestudy.transactions.controllers.EmployeeController;
import payrollcasestudy.transactions.controllers.PaymentController;
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
		
		get("/employees/newHourly", (request, response) -> {
			
            return new ModelAndView(view, "newEmployeeHourly.vtl");
        }, new VelocityTemplateEngine());
		
		get("/employees/newSalaried", (request, response) -> {
			
            return new ModelAndView(view, "newEmployeeSalaried.vtl");
        }, new VelocityTemplateEngine());
		
		get("/employees/newCommissioned", (request, response) -> {
			
            return new ModelAndView(view, "newEmployeeCommissioned.vtl");
        }, new VelocityTemplateEngine());
		
		post("/createHourly", (request, response) -> {
			
			EmployeeController.createNewEmployeeHourly(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("hourly"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/createSalaried", (request, response) -> {
			
			EmployeeController.createNewEmployeeSalaried(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("salaried"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/createCommissioned", (request, response) -> {
			
			EmployeeController.createNewEmployeeCommissioned(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("salaried"),
					request.queryParams("rate"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payHourly", (request, response) -> {
			
			PaymentController.createPaymentForHourly(request.queryParams("year"),
					request.queryParams("month"),request.queryParams("day"), request.queryParams("hours"),
					request.queryParams("employeeId"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payCommissioned", (request, response) -> {
			
			PaymentController.createPaymentForSalesReceipt(request.queryParams("year"),
					request.queryParams("month"),request.queryParams("day"), request.queryParams("sales"),
					request.queryParams("employeeId"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		get("/employees/show/:id", (request, response) -> {
			Employee employee;
			//Calendar calendar = new GregorianCalendar(2017,6,14);
			employee = EmployeeController.showEmployee(Integer.parseInt(request.params(":id")));
			
			view.put("employee", employee);
            return new ModelAndView(view, "showEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		get("/pay/show/:id", (request, response) -> {
			
			Employee employee;
			PayCheck payCheck;
			employee = EmployeeController.showEmployee(Integer.parseInt(request.params(":id")));
			payCheck = PaymentController.calculateAllPays("2001","10","9",request.params(":id"));
			double total = payCheck.getNetPay();
			view.put("employee", employee);
			view.put("total", total);
            return new ModelAndView(view, "payEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payAll", (request, response) -> {
			
			
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
	
	}

	
	
}
