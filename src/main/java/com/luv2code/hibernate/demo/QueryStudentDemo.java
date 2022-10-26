package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();

        try {
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> students = session.createQuery("from Student ").list();

            // display the students
            displayStudents(students);

            // query students: lastName = 'Doe'
            students = session.createQuery("from Student s where s.lastName='Doe'").list();

            // display the students
            System.out.println("\n\nStudents who have last name of Doe");
            displayStudents(students);

            // query students: lastName='Doe' OR firstName='Daffy'
            students = session.createQuery("from Student  s where"
                                + " s.lastName='Doe' OR s.firstName='Daffy'").list();

            System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
            displayStudents(students);

            // query students where email LIKE '%gmail.com'
            students = session.createQuery("from Student s where s.email LIKE '%gmail.com'").list();

            System.out.println("\n\nStudents who email ends with gmail.com");
            displayStudents(students);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student tempStudent : students) {
            System.out.println(tempStudent);
        }
    }
}
