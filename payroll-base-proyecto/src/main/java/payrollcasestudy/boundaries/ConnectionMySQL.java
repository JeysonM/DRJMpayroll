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
	
	@Override
	public void addEmployee(int employeeId, Employee employee) {
		// TODO Auto-generated method stub
		
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
	
	@Override
	public List<Employee> getEmployees()
    {
		List<Employee> employeesList = new ArrayList<>();
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

	@Override
	public Employee getEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> getAllEmployeeIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUnionMember(int memberId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUnionMember(int memberId, Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getUnionMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
