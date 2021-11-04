package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			System.out.println("Crating a new student object..");
			//create a student object, start transaction, save object, commit transaction
			
			Student tempStudent = new Student("Daffy", "Duck", "daffy@luv2code.com");
			
			session.beginTransaction();
			
			System.out.println("Saving the student...");
			
			session.save(tempStudent);
			
			session.getTransaction().commit();
			
			//MY NEW CODE
			
			//find out the student's id: primary key
			
			System.out.println("Saved Student. Generated id : " + tempStudent.getId());
			
			//now get a new session and start transaction and ready the object
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("Getting student with id : " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get Complete: " + myStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

}
