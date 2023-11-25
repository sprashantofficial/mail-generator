package com.example.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.project.model.Employee;
import com.example.project.util.HibernateUtil;

public class EmployeeDAO {

	public void saveEmployee(Employee emp) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(emp);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
	
}
