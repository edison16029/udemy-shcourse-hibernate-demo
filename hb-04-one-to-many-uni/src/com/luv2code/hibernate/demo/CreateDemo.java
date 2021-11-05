package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

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

			Instructor tempInstructor = new Instructor("Delin", "GJ", "berby@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.delin.com/youtube", "video");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			session.beginTransaction();
			
			//This will also save details object because of CascadeType.ALL
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			
			System.out.println("Donewe!");
			
		}
		finally {
			factory.close();
		}

	}

}
