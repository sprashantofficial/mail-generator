package com.example.project;

import java.util.List;
import java.util.Scanner;

import com.example.project.dao.EmployeeDAO;
import com.example.project.model.Employee;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter first name of employee:");
		String firstName = sc.next();

		System.out.println("Enter last name of employee:");
		String lastName = sc.next();

		EmployeeDAO empDAO = new EmployeeDAO();
		final String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@xyz.com";
		List<Employee> empList = empDAO.getEmployees();

		Employee emp = checkEmailExists(empList, email);
		if (emp == null) {
			Employee employee = new Employee(firstName, lastName, email);
			empDAO.saveEmployee(employee);
			System.out.println("Email: " + email + " generated!!");
		} else {
			System.out.println("Default " + email + " exists. Please provide an unique email id");
			String newEmail = sc.next().toLowerCase();

			while (true) {
				emp = checkEmailExists(empList, newEmail);
				if (emp == null) {
					Employee employee = new Employee(firstName, lastName, newEmail);
					empDAO.saveEmployee(employee);
					System.out.println("Email: " + newEmail + " generated!!");
					break;
				} else {
					System.out.println(newEmail + " exists. Please provide an unique email id");
					newEmail = sc.next();
				}
			}

		}

		sc.close();
	}

	static Employee checkEmailExists(List<Employee> empList, String email) {
		Employee emp = empList.stream().filter(e -> email.equals(e.getEmail())).findAny().orElse(null);

		return emp;
	}

}
