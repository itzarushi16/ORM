package com.lcwd.hiber;
import java.util.List; 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.lcwd.hiber.entities.Student;
import com.lcwd.hiber.entities.Certificate;
import com.lcwd.hiber.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
    	// This is to test it after commenting the rest

    	// Create StudentService object
    	StudentService studentService = new StudentService();

    	// Get students by college criteria (college = "IIT Kanpur")
    	List<Student> studentsByCollegeCriteria = studentService.getStudentsByCollegeCriteria("IIT Kanpur");

    	// Print size of students list
    	System.out.println("Students from IIT Kanpur: " + studentsByCollegeCriteria.size());

    	// Get students with pagination (pageNo = 1, pageSize = 1)
    	List<Student> studentWithPagination = studentService.getStudentsWithPagination(1, 1);

    	// Print size of paginated students list
    	System.out.println("Students in page 1 with size 1: " + studentWithPagination.size());

    	// Get student by ID = 4
    	Student student = studentService.getById(4);

    	// Print student name
    	System.out.println("Student name with ID 4: " + student.getName());

    	// Print number of certificates the student has
    	System.out.println("Certificates count: " + student.getCertificates().size());

    	// Print all certificate titles of the student
    	student.getCertificates().forEach(cer -> {
    	    System.out.println("Certificate Title: " + cer.getTitle());
    	});

    	
    	
    	
      /*  System.out.println("Hello World!");

        Student student = new Student();
        student.setName("Rohit Sharma");
        student.setCollege("IIT Delhi");
        student.setPhone("9876543210");
        student.setActive(true);
        student.setAbout("Final year CS student");

        // Create Certificates
        Certificate c1 = new Certificate();
        c1.setCourse("Java");
        c1.setDuration("3 months");
        c1.setGrade("A");
        c1.setStudent(student);

        Certificate c2 = new Certificate();
        c2.setCourse("Python");
        c2.setDuration("2 months");
        c2.setGrade("A+");
        c2.setStudent(student);

        // Add to Student using helper method
        student.addCertificate(c1);
        student.addCertificate(c2);
        //session.persist(student); 
        // Save only student (cascade = ALL will also save certificates)
     //  session.save(student);


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
       // System.out.println(sessionFactory);

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
          //  session.persist(student);
            session.persist(student); // this will also save certificate because of cascade
            transaction.commit();

            System.out.println("student saved successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        */
    }
    
}
