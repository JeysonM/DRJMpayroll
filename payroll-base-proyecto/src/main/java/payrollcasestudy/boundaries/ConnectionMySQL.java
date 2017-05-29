package payrollcasestudy.boundaries;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Connection;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentschedule.BiweeklyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.MonthlyPaymentSchedule;
import payrollcasestudy.entities.paymentschedule.WeeklyPaymentSchedule;


public class ConnectionMySQL implements Repository{
	
	public static ConnectionMySQL relationalDatabase = new ConnectionMySQL();
	
	private Connection connection;
	private String localhost = "jdbc:mysql://localhost:3306";
	private String userDB = "root";
	private String password = "root";

	
	public String getStatusConnection() {
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			return "Connection success";
		}catch (Exception e){
			return "Connection failed";
		}
		
	}
	
	public void addEmployee(int employeeId, Employee employee) {
		int result=0;
		try{
			if(employee.isHourlyPaymentClassification())
			{
				result = createEmployeeHourlyPaymentClassificationInDB(employeeId,employee);
			}else if(employee.isCommissionedPaymentClassification()){
				result = createEmployeeCommissionedPaymentClassificationInDB(employeeId,employee);
			}else if(employee.isSalariedClassification()){
				result = createEmployeeSalariedClassificationInDB(employeeId,employee);
			}
			
			System.out.println("Creo un nuevo empleado");
		}catch (Exception e){
			System.out.println("Me mataste");
			System.err.println(e);
		}
		
	}

	public int createEmployeeHourlyPaymentClassificationInDB(int employeeId, Employee employee)
    {
		int result=0;
		HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification();
		
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query_employee = "INSERT INTO rosquete_db.employee "
					+ "(ci_employee, first_name, last_name, address, payment_type, payment_schedule) "
					+ "VALUES ('"+employeeId+"', '"+employee.getName()+"', 'Undefined', '"+employee.getAddress()+"', 'hourly','weekly')";
			String query_classification = "INSERT INTO rosquete_db.hourly_payment_classification "
					+ "(ci_employee, hourlyRate) "
					+ "VALUES ('"+employeeId+"', '"+hourlyClassification.getHourlyRate()+"')";
			
			Statement stmt = (Statement) connection.createStatement();
			result = ((java.sql.Statement) stmt).executeUpdate(query_employee);
			result = ((java.sql.Statement) stmt).executeUpdate(query_classification);
		}catch (Exception e){
			System.err.println(e);
		}
		return result;
    }
	
	public int createEmployeeSalariedClassificationInDB(int employeeId, Employee employee)
    {
		int result=0;
		SalariedClassification salariedPayment =  (SalariedClassification) employee.getPaymentClassification();
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query_employee = "INSERT INTO rosquete_db.employee "
					+ "(ci_employee, first_name, last_name, address, payment_type, payment_schedule) "
					+ "VALUES ('"+employeeId+"', '"+employee.getName()+"', 'Undefined', '"+employee.getAddress()+"', 'salary', 'monthly')";
			String query_classification = "INSERT INTO rosquete_db.salaried_classification "
					+ "(ci_employee, salary) "
					+ "VALUES ('"+employeeId+"', '"+salariedPayment.getSalary()+"')";
			
			Statement stmt = (Statement) connection.createStatement();
			result = ((java.sql.Statement) stmt).executeUpdate(query_employee);
			result = ((java.sql.Statement) stmt).executeUpdate(query_classification);
		}catch (Exception e){
			System.err.println(e);
		}
		return result;
    }
	
	public int createEmployeeCommissionedPaymentClassificationInDB(int employeeId, Employee employee)
    {
		int result=0;
		CommissionedPaymentClassification commissionPayment =  (CommissionedPaymentClassification) employee.getPaymentClassification();
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query_employee = "INSERT INTO rosquete_db.employee "
					+ "(ci_employee, first_name, last_name, address, payment_type, payment_schedule) "
					+ "VALUES ('"+employeeId+"', '"+employee.getName()+"', 'Undefined', '"+employee.getAddress()+"', 'commission','biweekly')";
			String query_classification = "INSERT INTO rosquete_db.commissioned_payment_classification "
					+ "(ci_employee, commissionRate, monthlySalary) "
					+ "VALUES ('"+employeeId+"', '"+commissionPayment.getCommissionRate()+"', '"+commissionPayment.getMonthlySalary()+"')";
			
			Statement stmt = (Statement) connection.createStatement();
			result = ((java.sql.Statement) stmt).executeUpdate(query_employee);
			result = ((java.sql.Statement) stmt).executeUpdate(query_classification);
		}catch (Exception e){
			System.err.println(e);
		}
		return result;
    }
	
	
	public ResultSet connectionWithTableEmployees()
    {
		ResultSet rs=null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query = "SELECT * FROM rosquete_db.employee";
			Statement stmt = (Statement) connection.createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(query);
			System.out.println("Se conecto con la tabla employees");
			return rs;
		}catch (Exception e){
			System.err.println(e);
			return rs;
		}
    }
	
	public List<Employee> getEmployees()
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		try{
			ResultSet results = connectionWithTableEmployees();
			while(results.next()){
				Employee employee = new Employee(Integer.parseInt(results.getString("ci_employee")),
						results.getString("first_name"),results.getString("address"));
				employeesList.add(employee);
			}
			return employeesList;
		}catch (Exception e){
			System.err.println(e);
			return employeesList;
		}
    }


	public Set<Integer> getAllEmployeeIds() {
		Set<Integer> employees = new HashSet<Integer>();
		ResultSet results=null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query = "SELECT ci_employee FROM rosquete_db.employee";
			Statement stmt = (Statement) connection.createStatement();
			results = ((java.sql.Statement) stmt).executeQuery(query);
			while(results.next()){
				employees.add(results.getInt("ci_employee"));
			}
			return employees;
		}catch (Exception e){
			System.err.println(e);
			return null;
		}
	}

	public void deleteUnionMember(int memberId) {
		// TODO Auto-generated method stub
		
	}

	public void addUnionMember(int memberId, Employee employee) {
		// TODO Auto-generated method stub
		
	}

	public Employee getUnionMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public Employee getEmployee(int employeeId) {
		Employee employee= new Employee(000,"undefined","undefined");
		ResultSet result=null;
		try{
			result = returnEmployeePaymentClassificationFromDB(employeeId);
			System.out.println("logre salir de returnEmployeePaymentClassificationFromDB");
			//while(result.next()){
				System.out.println("logre entrar al segundo while");
				employee = new Employee(Integer.parseInt(result.getString("ci_employee")),
					result.getString("first_name"),
					result.getString("address"));
				//System.out.println("first_name >> "+result.getString("first_name").toString());
				//System.out.println("payment_type >> "+result.getString("payment_type").toString());
				
				if(result.getString("payment_type").toString().equals("hourly") &&
						result.getString("payment_schedule").toString().equals("weekly")){
					System.out.println("logre a la condicion hourly");
					HourlyPaymentClassification hourlyClassification =  new HourlyPaymentClassification(Double.parseDouble(result.getString("hourlyRate")));
					WeeklyPaymentSchedule weeklyPayment = new WeeklyPaymentSchedule();
					employee.setPaymentClassification(hourlyClassification);
					employee.setPaymentSchedule(weeklyPayment);
					
				}
				if(result.getString("payment_type").toString().equals("commission") &&
						result.getString("payment_schedule").toString().equals("biweekly")){
					CommissionedPaymentClassification commissionClassification =  new CommissionedPaymentClassification(Double.parseDouble(result.getString("commissionRate")),result.getDouble("monthlySalary"));
					BiweeklyPaymentSchedule biweeklyPayment = new BiweeklyPaymentSchedule();
					employee.setPaymentClassification(commissionClassification);
					employee.setPaymentSchedule(biweeklyPayment);
				}
				if(result.getString("payment_type").toString().equals("salary") &&
						result.getString("payment_schedule").toString().equals("monthly")){
					SalariedClassification salaryClassification =  new SalariedClassification(result.getDouble("salary"));
					MonthlyPaymentSchedule monthlyPayment = new MonthlyPaymentSchedule();
					employee.setPaymentClassification(salaryClassification);
					employee.setPaymentSchedule(monthlyPayment);
				}
			//}
		}catch (Exception e){
			System.out.println("se murio");
			System.err.println(e);
		}
		return employee;
	}
	
	
	
	private ResultSet returnEmployeePaymentClassificationFromDB(int employeeId){
		ResultSet employee = findElementInTable(employeeId,"employee");
		ResultSet paymentClassification=null;
		try{
			while(employee.next()){
				System.out.println("Entro a while de returnEmployee");
				System.out.println("Este es mi tipo >> "+employee.getString("payment_type").toString());
				
				if(employee.getString("payment_type").toString().equals("hourly")){
					System.out.println("verifico si era hourly");
					paymentClassification = executeQueryOfPaymentClassificationInDB(employeeId,"hourly_payment_classification");
				}
				if(employee.getString("payment_type").toString().equals("commission")){
					paymentClassification = executeQueryOfPaymentClassificationInDB(employeeId,"commissioned_payment_classification");
				}
				if(employee.getString("payment_type").toString().equals("salary")){
					paymentClassification = executeQueryOfPaymentClassificationInDB(employeeId,"salaried_classification");
				}
				return paymentClassification;
			}
		}catch (Exception e){
			System.out.println("se murio en returnEmployeePaymentClassificationFromDB");
			System.err.println(e);
		}
		return paymentClassification;
	}

	public ResultSet executeQueryOfPaymentClassificationInDB(int employeeId,String paymentClassification)
    {
		ResultSet result=null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query = "SELECT * "
					+ "FROM rosquete_db."+paymentClassification+" "
					+ "INNER JOIN rosquete_db.employee ON rosquete_db."+paymentClassification+".ci_employee=rosquete_db.employee.ci_employee "
					+ "WHERE rosquete_db."+paymentClassification+".ci_employee='"+employeeId+"'";
			Statement stmt = (Statement) connection.createStatement();
			result = ((java.sql.Statement) stmt).executeQuery(query);
			while(result.next()){
				return result;
			}
			System.out.println("Se conecto con la tabla >> "+paymentClassification.toString()+"");
		}catch (Exception e){
			System.out.println("Mori en executeQueryOfPaymentClassificationInDB");
			System.err.println(e);	
		}
		return result;
    }
	
	
	
	public ResultSet findElementInTable(int id,String table)
    {
		ResultSet result=null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query = "SELECT * "
					+ "FROM rosquete_db."+table+" "
					+ "WHERE rosquete_db."+table+".ci_employee='"+id+"'";
			Statement stmt = (Statement) connection.createStatement();
			result = ((java.sql.Statement) stmt).executeQuery(query);
			
			System.out.println("Se conecto con la tabla >> "+table.toString()+"");
		}catch (Exception e){
			System.err.println(e);
			
		}
		return result;
    }

}
