package Main;
import static spark.Spark.*;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;

import com.google.gson.Gson;

import payrollcasestudy.api.JsonUtil;
import payrollcasestudy.boundaries.ConnectionMySQL;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;
import payrollcasestudy.transactions.services.EmployeeService;
import payrollcasestudy.transactions.services.PaymentService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.TemplateEngine;
import spark.template.velocity.VelocityTemplateEngine;

public class Main {
	public static void main(String[] args) {
		Repository repository = new ConnectionMySQL();
		EmployeeService employeeService = new EmployeeService(repository);
		PaymentService paymentService = new PaymentService(repository);
		Routes routes = new Routes(employeeService, paymentService);
		routes.manageRoutes();
	}
}
