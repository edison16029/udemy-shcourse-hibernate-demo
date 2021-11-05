package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteDemo {

	public static void main(String[] args) {
		
		// create session factor
		SessionFactory  factory = new Configuration()
				.configure("hibernate.cfg.xml") //This is option as this is the default file name it checks for configuration
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			int theId = 3;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Found" + tempInstructor);
			
			if(tempInstructor != null) {
				System.out.println("Deleting : " + tempInstructor);
				//This will also delete instructor detail due to Cascade.ALL
				session.delete(tempInstructor);
			}
			session.getTransaction().commit();
			
			System.out.println("Donewe!");
			
		}
		finally {
			factory.close();
		}

	}

}
