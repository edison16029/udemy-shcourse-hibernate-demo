package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
			
//			Student myStudent = session.get(Student.class, studentId);
//			
//			session.delete(myStudent);
//			
//			session.getTransaction().commit();
			
			//Alternate approach
			
			
			session.createQuery("delete from Student where id = 2 ")
				.executeUpdate();
			
			session.getTransaction().commit();
			
			
			
			
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

}
