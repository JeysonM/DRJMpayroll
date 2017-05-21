package payrollcasestudy.boundaries;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Connection;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;


public class ConnectionMySQL implements Repository{
	
	public static ConnectionMySQL relationalDatabase = new ConnectionMySQL();
	
	private Connection connection;
	private String localhost = "jdbc:mysql://localhost:33060";
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
		int rs=0;
		int rs2=0;
		HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification(); 
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query_employee = "INSERT INTO rosquete_db.employee "
					+ "(ci_employee, first_name, last_name, address, payment_type) "
					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', 'Undefined', '"+employee.getAddress()+"'), 'hourly')";
			String query_classification = "INSERT INTO rosquete_db.hourly_payment_classification "
					+ "(ci_employee, hourlyRate) "
					+ "VALUES ('"+employee.getEmployeeId()+"', '"+hourlyClassification.getHourlyRate()+"')";
			
			Statement stmt = (Statement) connection.createStatement();
			Statement stmt2 = (Statement) connection.createStatement();
			rs = ((java.sql.Statement) stmt).executeUpdate(query_employee);
			rs2 = ((java.sql.Statement) stmt2).executeUpdate(query_classification);
			
			System.out.println("Creo un nuevo empleado");
		}catch (Exception e){
			System.out.println("Me mataste");
			System.err.println(e);
		}
		
	}
	
	public ResultSet connectionWithTableOfEmployees()
    {
		ResultSet rs=null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query = "SELECT * FROM rosquete_db.employee";
			Statement stmt = (Statement) connection.createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(query);
			System.out.println("Se conecto con la tabla");
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
			ResultSet results = connectionWithTableOfEmployees();
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

	public Employee getEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Integer> getAllEmployeeIds() {
		// TODO Auto-generated method stub
		return null;
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
		Employee employee=new Employee(000,"undefined","undefined");
		ResultSet rs=null;
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			String query = "SELECT * "
					+ "FROM rosquete_db.hourly_payment_classification "
					+ "INNER JOIN rosquete_db.employee ON rosquete_db.hourly_payment_classification.ci_employee=rosquete_db.employee.ci_employee "
					+ "WHERE rosquete_db.hourly_payment_classification.ci_employee='"+employeeId+"'";
			Statement stmt = (Statement) connection.createStatement();
			rs = ((java.sql.Statement) stmt).executeQuery(query);
			while(rs.next()){
				employee = new Employee(Integer.parseInt(rs.getString("ci_employee")),
						  rs.getString("first_name"),
						  rs.getString("address"));
				HourlyPaymentClassification hourlyClassification =  new HourlyPaymentClassification(Double.parseDouble(rs.getString("hourlyRate")));
				employee.setPaymentClassification(hourlyClassification);
			}
			
			return employee;
		}catch (Exception e){
			System.out.println("se murio");
			System.err.println(e);
			return employee;
		}
		
	}

}
