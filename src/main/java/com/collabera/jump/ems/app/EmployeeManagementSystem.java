package com.collabera.jump.ems.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.collabera.jump.ems.exceptions.EmployeeNotFoundException;
import com.collabera.jump.ems.exceptions.EmployeeWithNameNotFoundException;
import com.collabera.jump.ems.model.Address;
import com.collabera.jump.ems.model.DEPARTMENT;
import com.collabera.jump.ems.model.Employee;
import com.collabera.jump.ems.model.GENDER;
import com.collabera.jump.ems.model.JOBTITLE;
import com.collabera.jump.ems.model.Manager;
import com.collabera.jump.ems.util.DBUtil;
import com.collabera.jump.ems.util.ScannerUtil;
import com.collabera.jump.ems.util.ScannerUtil.Result;
import com.collabera.jump.ems.util.ScannerUtil.TYPES;


public class EmployeeManagementSystem {

	static HashMap<Integer, Employee> employees = null;
	static HashMap<Integer, Manager> managers = null;

	static {
		File file = new File("Employees");
		if (file.exists()) {
			load();
		} else {
			employees = new HashMap<Integer, Employee>();
			managers = new HashMap<Integer, Manager>();
		}

	}

	public static void load() {
		try (ObjectInputStream dis = new ObjectInputStream(new FileInputStream("Employees"))) {
			employees = (HashMap<Integer, Employee>) dis.readObject();
			managers = (HashMap<Integer, Manager>) dis.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public  void createEmployeeWithInputs( ) {
		Result option;

		Employee employee = new Employee();

		// System.out.println("Enter Name:");
		option = ScannerUtil.retryUntilSucceeds("Enter Employee ID:", TYPES.LINE, -1);
		String empId = option.getValue().toString();
		employee.setEmpId(empId);
				
		// System.out.println("Enter Name:");
		option = ScannerUtil.retryUntilSucceeds("Enter Name:", TYPES.LINE, -1);
		String name = option.getValue().toString();
		employee.setName(name);

		option = ScannerUtil.retryUntilSucceeds("Enter Age:", TYPES.INT, -1);
		int age = option.getValueAsInt();

		employee.setAge(age);

		option = ScannerUtil.retryUntilSucceeds("Enter SSN:", TYPES.INT, -1);
		int ssn = option.getValueAsInt();
		employee.setSsn(ssn);

		option = ScannerUtil.retryUntilSucceeds("Enter phone number:", TYPES.LINE, -1);
		String phoneNumber = option.getValue().toString();
		employee.setPhoneNumber(phoneNumber);

		option = ScannerUtil.retryUntilSucceeds("Enter email:", TYPES.LINE, -1);
		String email = option.getValue().toString();
		employee.setEmail(email);

		option = ScannerUtil.retryUntilSucceeds("Enter DOB (mm/dd/yyyy): ", TYPES.DATE, -1);
		Date dateStr = (Date) option.getValue();
		employee.setDob(dateStr);

		System.out.println("Please enter Personal Address:");
		Address address = new Address();
		option = ScannerUtil.retryUntilSucceeds("Enter Street: ", TYPES.LINE, -1);
		String street = option.getValue().toString();
		address.setStreet(street);

		option = ScannerUtil.retryUntilSucceeds("Enter City: ", TYPES.LINE, -1);
		String city = option.getValue().toString();
		address.setCity(city);

		option = ScannerUtil.retryUntilSucceeds("Enter Zipcode: ", TYPES.INT, -1);
		int zipcode = option.getValueAsInt();
		address.setZipcode(zipcode);

		employee.setAddress(address);
		
		
		System.out.println("Please enter Work Address:");
		Address workAddress = new Address();
		option = ScannerUtil.retryUntilSucceeds("Enter Street: ", TYPES.LINE, -1);
		String wStreet = option.getValue().toString();
		address.setStreet(wStreet);

		option = ScannerUtil.retryUntilSucceeds("Enter City: ", TYPES.LINE, -1);
		String wCity = option.getValue().toString();
		address.setCity(wCity);

		option = ScannerUtil.retryUntilSucceeds("Enter Zipcode: ", TYPES.INT, -1);
		int wZipcode = option.getValueAsInt();
		address.setZipcode(wZipcode);

		employee.setWorkAddress(workAddress);

		System.out.println("Please choose a GENDER:");
		for (GENDER gender : GENDER.values()) {
			System.out.println(gender.ordinal() + ". " + gender);
		}

		option = ScannerUtil.retryUntilSucceeds("Enter Gender: ", TYPES.INT, GENDER.values().length);
		int gender = option.getValueAsInt();
		employee.setGender(GENDER.values()[gender]);

		System.out.println("Please choose a DEPARTMENT:");
		for (DEPARTMENT department : DEPARTMENT.values()) {
			System.out.println(department.ordinal() + ". " + department);
		}

		option = ScannerUtil.retryUntilSucceeds("Enter DEPARTMENT: ", TYPES.INT, DEPARTMENT.values().length);
		int department = option.getValueAsInt();
		employee.setDepartment(DEPARTMENT.values()[department]);
		
		System.out.println("Please choose a JOBTITLE:");
		for (JOBTITLE jobtitle : JOBTITLE.values()) {
			System.out.println(jobtitle.ordinal() + ". " + jobtitle);
		}
		
		option = ScannerUtil.retryUntilSucceeds("Enter TITLE: ", TYPES.INT, JOBTITLE.values().length);
		int title = option.getValueAsInt();
		employee.setTitle(JOBTITLE.values()[title]);
		// System.out.println(employee);

		this.createEmployee(employee);

	}
	public boolean createEmployee(Employee employee) {
		
		
	System.out.println(employee);
		try {
			PreparedStatement prepStatement = DBUtil.getConnection().prepareStatement(DBUtil.properties.getProperty("add_employee"));
			
			//INSERT INTO `empdb`.`employee` (`emp_id`, `emp_ssn`, `emp_email_id`, `emp_name`, `emp_age`, `emp_dob`, `emp_phone_num`, `emp_home_address`, `emp_work_address`, `emp_gender`, `reportsTo`, `isManager`, `emp_tiitle`, `emp_department`) VALUES (<{idemployee: }>, <{emp_id: }>, <{emp_ssn: }>, <{emp_email_id: }>, <{emp_name: }>, <{emp_age: }>, <{emp_dob: }>, <{emp_phone_num: }>, <{emp_home_address: }>, <{emp_work_address: }>, <{emp_gender: }>, <{reportsTo: }>, <{isManager: }>, <{emp_tiitle: }>, <{emp_department: }>);
		//	(`emp_id`, `emp_ssn`, `emp_email_id`, `emp_name`, `emp_age`, `emp_dob`, `emp_phone_num`, `emp_home_address`, `emp_work_address`, `emp_gender`, `reportsTo`, `isManager`, `emp_tiitle`, `emp_department`)
			
		//	('THBS663', '12345678', 'siva@siva.com', 'siva', '34', '05/04/1988', ph, 0, 0, 1, null, 0, 1, 1, ** NOT SPECIFIED **, ** NOT SPECIFIED **)
			System.out.println(prepStatement);
			
			prepStatement.setString(1, employee.getEmpId());
			prepStatement.setString(2, employee.getSsn()+"");
			
			prepStatement.setString(3, employee.getEmail());
			prepStatement.setString(4, employee.getName());
			prepStatement.setInt(5, employee.getAge());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			String resultDate = dateFormat.format(employee.getDob());
			prepStatement.setString(6, resultDate);
			
	
			
			prepStatement.setString(7, employee.getPhoneNumber());
			prepStatement.setInt(8, 0); 
		
			prepStatement.setInt(9, 0); 
			
			GENDER gender = employee.getGender();
			prepStatement.setInt(10, gender.ordinal());
			prepStatement.setString(11, null);
			prepStatement.setBoolean(12, false);
			
			JOBTITLE title = employee.getTitle();
			prepStatement.setInt(13, title.ordinal());
			
			
			DEPARTMENT department = employee.getDepartment();
			prepStatement.setInt(14, department.ordinal());
			
			System.out.println(prepStatement);
			
			return prepStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateEmployee(int empId, String column, String value) throws InterruptedException {
		try {
			PreparedStatement prepStatement = DBUtil.getConnection().prepareStatement(DBUtil.properties.getProperty("update_employee"));
//			System.out.println(prepStatement);
			prepStatement.setInt(3, empId);
			prepStatement.setString(1, column);
			prepStatement.setString(2, value);
//			Thread.sleep(1300);
			return prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	public boolean removeEmployee(int employeeId) {
//		return employees.remove(employeeId) != null;
//	}

	public boolean deleteEmployee(int empId) throws InterruptedException {
		try {
			PreparedStatement prepStatement = DBUtil.getConnection().prepareStatement(DBUtil.properties.getProperty("delete_employee"));
//			System.out.println(prepStatement);
			prepStatement.setInt(1, empId);
			System.out.println("Employee successfully deleted.");
			Thread.sleep(1300);
			return prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
		Employee employee = employees.get(employeeId);

		if (employee != null) {
			return employee;
		} else {
			throw new EmployeeNotFoundException("Employee with employee ID : " + employeeId + " not found!");
		}

	}

	public Employee getEmployeeBynName(String name) throws EmployeeWithNameNotFoundException {
		for (Employee employee : employees.values()) {
			if (employee.getName().equalsIgnoreCase(name)) {
				return employee;
			} else {
				throw new EmployeeWithNameNotFoundException("Employee with name " + name + " not found!");
			}
		}
		return null;
	}

//	public void save() {
//		try (ObjectOutputStream dos = new ObjectOutputStream(new FileOutputStream("Employees"))) {
//			dos.writeObject(employees);
//			dos.writeObject(managers);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	
	public void employeeToDelete() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		int empId = sc.nextInt();
		this.deleteEmployee(empId);
//		sc.close();
	}
	
	public void employeeToUpdate() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		int empId = sc.nextInt();
		System.out.println("Please select what record to update: ");
		System.out.println("1. SSN");
		System.out.println("2. Email");
		System.out.println("3. Name");
		System.out.println("4. Age");
		System.out.println("5. DOB");
		System.out.println("6. Phone Number");
		System.out.println("7. Home Address");
		System.out.println("8. Work Address");
		System.out.println("9. Gender");
		System.out.println("10. Reports To");
		System.out.println("11. Manager Status");
		System.out.println("12. Title");
		System.out.println("13. Department");
//		System.out.println("Employee successfully updated.");
		String selectedColumn = null;
		int column = sc.nextInt();
		switch (column) {
		case 1:
			selectedColumn = "emp_ssn";
			break;
		case 2:
			selectedColumn = "emp_email_id";
			break;
		case 3:
			selectedColumn = "emp_name";
			break;
		case 4:
			selectedColumn = "emp_age";
			break;
		case 5:
			selectedColumn = "emp_dob";
			break;
		case 6:
			selectedColumn = "emp_phone_num";
			break;
		case 7:
			selectedColumn = "emp_home_address";
			break;
		case 8:
			selectedColumn = "emp_work_address";
			break;
		case 9:
			selectedColumn = "emp_gender";
			break;
		case 10:
			selectedColumn = "reportsTo";
			break;
		case 11:
			selectedColumn = "isManager";
			break;
		case 12:
			selectedColumn = "emp_title";
			break;
		case 13:
			selectedColumn = "emp_department";
			break;
		}
		System.out.println("Please enter the new value: ");
		String value = sc.nextLine();
		this.updateEmployee(empId, selectedColumn, value);
	}
	
	public void createManagerWithInputs() {
		// TODO Auto-generated method stub
		System.out.println("Select the employees reporting to this manager:");
		for(Integer empId: employees.keySet())
		{
			System.out.println( empId + "] "+ employees.get(empId).getName());
		}
		
		//System.out.println();
		
//		Result options = ScannerUtil.retryUntilSucceeds("Enter a comma separated employee IDs:", TYPES., range)
//		String[] empIds = 
		
	}
}
