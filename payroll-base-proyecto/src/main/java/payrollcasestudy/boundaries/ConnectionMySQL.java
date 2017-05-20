package payrollcasestudy.boundaries;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.mysql.jdbc.Connection;


public class ConnectionMySQL {
	public static void main(String [] args)
	{
		try{
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:33060","root","root");
			//System.out.println("Connection success");
			String query = "SELECT * FROM sakila.actor";
			Statement stmt = (Statement) connection.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			
			while(rs.next()){
				System.out.println("Name: "+rs.getString("first_name") +" - "+rs.getString("last_name"));
				
			}
		}catch (Exception e){
			System.err.println(e);
		}
	}

}
