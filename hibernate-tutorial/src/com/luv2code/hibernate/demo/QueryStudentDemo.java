package com.luv2code.hibernate.demo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factor
		SessionFactory  factory = new Configuration()
				.configure("hibernate.cfg.xml") //This is option as this is the default file name it checks for configuration
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java Object
			System.out.println("Creating a new student object..");
			//create a student object, start transaction, save object, commit transaction
			
			session.beginTransaction();
			
			// query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			//query students: lastName = 'Doe'
			
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			System.out.println("\n Students who have last name as Doe");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe' or s.firstName = 'Edison'").getResultList();
			System.out.println("\n Students who have last name as Doe or first name as Edison");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			System.out.println("\n Students who have email ending with luv2code.ocom");
			displayStudents(theStudents);
			
			
			
			
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		//display the students
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
