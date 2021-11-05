package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		// create session factor
		SessionFactory  factory = new Configuration()
				.configure("hibernate.cfg.xml") //This is option as this is the default file name it checks for configuration
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("Instructor : " + tempInstructor);
			
			System.out.println("Courses : " + tempInstructor.getCourses());
			
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("Closing the session");
			
			System.out.println("Courses : " + tempInstructor.getCourses());
			System.out.println("Done!");
			
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
