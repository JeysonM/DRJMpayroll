package Main;

public class Payroll {
	private int employeeId;
	private String name;
	private double totalPay;
	public Payroll(int employeeId, String name, double totalPay){
		this.setEmployeeId(employeeId);
		this.setName(name);
		this.setTotalPay(totalPay);
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}

}
