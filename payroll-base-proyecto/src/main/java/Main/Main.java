package Main;
import static spark.Spark.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import payrollcasestudy.boundaries.ConnectionMySQL;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
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
		//ConnectionMySQL myconnection = new ConnectionMySQL();
		//Repository repository = myconnection;
		//System.out.println(myconnection.getStatusConnection());
		//myconnection.viewEmployeeRosqueteDB_test();
		
		get("/", (request, response) -> {
			
			  view.put("template","indexEmployee.vtl");
		      return new ModelAndView(view, "layout.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/employees", (request, response) -> {
			
			List<Employee> employees = new ArrayList<>();
			employees = EmployeeController.showAllEmployees();
			view.put("employees", employees);
			view.put("template","allEmployees.vtl");
			return new ModelAndView(view, "layout.vtl");
		}, new VelocityTemplateEngine());
		
		get("/employees/newHourly", (request, response) -> {
			view.put("template","newEmployeeHourly.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		get("/employees/newSalaried", (request, response) -> {
			view.put("template","newEmployeeSalaried.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		get("/employees/newCommissioned", (request, response) -> {
			view.put("template","newEmployeeCommissioned.vtl");
            return new ModelAndView(view, "layout.vtl");
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
			view.put("template","showEmployee.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		get("/pay/show/:id", (request, response) -> {
			
			Employee employee;
			PayCheck payCheck;
			employee = EmployeeController.showEmployee(Integer.parseInt(request.params(":id")));
			payCheck = PaymentController.getPayCheckFromPayDayTransaction((request.params(":id")));
			double total = payCheck.getNetPay();
			view.put("employee", employee);
			view.put("total", total);
			view.put("template","payEmployee.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		get("/payAll", (request, response) -> {
			view.put("template","payAll.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payAllTransaction", (request, response) -> {
			PaymentController.calculateAllPays(request.queryParams("year"),
					request.queryParams("month"),request.queryParams("day"));
			
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployees.vtl");
        }, new VelocityTemplateEngine());
		
		
	}

	
	
}
