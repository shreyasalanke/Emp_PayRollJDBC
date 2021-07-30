package com.bridgelabz.emppayrolldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService
{
	public List<EmployeePayrollData> readData() 
	{
		String sql = "SELECT * FROM employee_payroll;";
		List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
		try(Connection connection = this.getConnection();) 
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				LocalDate startDate = resultSet.getDate("start").toLocalDate();
				employeePayrollList.add(new EmployeePayrollData(id, name, salary,startDate));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return employeePayrollList;
	}

	private Connection getConnection() throws SQLException 
	{
		String jdbcURL = "jdbc:mysql://localhost:3306/emp_payroll_service?useSSL=false";
		String userName = "root";
		String password = "ShreyaS@18";
		Connection connection;
		System.out.println("Connecting to database: " + jdbcURL);
		connection = DriverManager.getConnection(jdbcURL, userName, password);
		System.out.println("Connection successful: " + connection);
		return connection;
	}
}
