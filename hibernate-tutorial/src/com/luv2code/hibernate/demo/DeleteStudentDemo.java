package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factor
		SessionFactory  factory = new Configuration()
				.configure("hibernate.cfg.xml") //This is option as this is the default file name it checks for configuration
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			int studentId = 1;

			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("Getting student with id : " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			
			myStudent.setFirstName("Toby");
			
			session.getTransaction().commit();
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			session.createQuery("update Student set email = 'foo@gmail.com'")
				.executeUpdate();
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

}
