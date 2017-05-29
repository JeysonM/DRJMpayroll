package Main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import payrollcasestudy.api.JsonUtil;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.services.EmployeeService;
import payrollcasestudy.transactions.services.PaymentService;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class Routes {
	private EmployeeService employeeService;
	private PaymentService paymentService;
	
	public Routes(EmployeeService employeeService, PaymentService paymentService)
	{
		this.employeeService = employeeService;
		this.paymentService = paymentService;
	}
	
	public void manageRoutes()
	{
		JsonUtil apiJson;
		HashMap<String,Object> view = new HashMap<String,Object>();
		get("/", (request, response) -> {
			
			  view.put("template","indexEmployee.vtl");
		      return new ModelAndView(view, "layout.vtl");
		    }, new VelocityTemplateEngine());
		
		get("/services", (request, response) -> {
			view.put("template","services.vtl");
			return new ModelAndView(view, "layout.vtl");
		}, new VelocityTemplateEngine());
		
		get("/employees", (request, response) -> {
			
			List<Employee> employees = new ArrayList<>();
			employees = employeeService.showAllEmployees();
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
			
			employeeService.createNewEmployeeHourly(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("hourly"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/createSalaried", (request, response) -> {
			
			employeeService.createNewEmployeeSalaried(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("salaried"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/createCommissioned", (request, response) -> {
			
			employeeService.createNewEmployeeCommissioned(request.queryParams("id"),
					request.queryParams("name"),request.queryParams("address"), request.queryParams("salaried"),
					request.queryParams("rate"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payHourly", (request, response) -> {
			
			paymentService.createPaymentForHourly(request.queryParams("employeeId"), request.queryParams("year"),
					request.queryParams("month"),request.queryParams("day"), request.queryParams("hours"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payCommissioned", (request, response) -> {
			
			paymentService.createPaymentForSalesReceipt(request.queryParams("employeeId"), request.queryParams("year"),
					request.queryParams("month"),request.queryParams("day"), request.queryParams("sales"));
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployee.vtl");
        }, new VelocityTemplateEngine());
		
		get("/employees/show/:id", (request, response) -> {
			Employee employee;
			//Calendar calendar = new GregorianCalendar(2017,6,14);
			employee = employeeService.showEmployee(Integer.parseInt(request.params(":id")));
			
			view.put("employee", employee);
			view.put("template","showEmployee.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		get("/pay/show/:id", (request, response) -> {
			
			Employee employee;
			PayCheck payCheck;
			employee = employeeService.showEmployee(Integer.parseInt(request.params(":id")));
			payCheck = paymentService.getPayCheckFromPayDayTransaction((request.params(":id")));
			double total = payCheck.getNetPay();
			view.put("employee", employee);
			view.put("total", total);
			view.put("payCheck", payCheck);
			view.put("template","payEmployee.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		get("/payAll", (request, response) -> {
			view.put("template","payAll.vtl");
            return new ModelAndView(view, "layout.vtl");
        }, new VelocityTemplateEngine());
		
		post("/payAllTransaction", (request, response) -> {
			paymentService.calculateAllPays(request.queryParams("year"),
					request.queryParams("month"),request.queryParams("day"));
			
			response.redirect("/employees");
            return new ModelAndView(view, "allEmployees.vtl");
        }, new VelocityTemplateEngine());
		
		get("/api/v1/employees", (req, res) -> employeeService.showAllEmployees(), JsonUtil.json());
		get("/api/v1/pays", (req, res) -> paymentService.getAllPayChecksFromPayDayTransaction(), JsonUtil.json());
		
		get("/api/v1/pays/:id", (request, response) -> {
			//Pair<Employee,PayCheck> pair;
			String pair;
			Employee employee;
			PayCheck payCheck;
			
			employee = employeeService.showEmployee(Integer.parseInt(request.params(":id")));
			payCheck = paymentService.getPayCheckFromPayDayTransaction((request.params(":id")));
			Payroll payroll = new Payroll(employee.getEmployeeId(),employee.getName(),payCheck.getNetPay());
			return payroll;
		}, JsonUtil.json());

	}
		
	}


