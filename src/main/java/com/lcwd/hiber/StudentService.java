package com.lcwd.hiber;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import com.lcwd.hiber.entities.Student;
import com.lcwd.hiber.util.HibernateUtil;

public class StudentService {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    // save
    public void saveStudent(Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction beginTransaction = session.beginTransaction();
            session.persist(student);
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student getById(long studentId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student updateStudent(long studentId, Student student) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student oldStudent = session.get(Student.class, studentId);

            if (oldStudent != null) {
                oldStudent.setName(student.getName());
                oldStudent.setFatherName(student.getFatherName());
                // ...update more information
                oldStudent = session.merge(oldStudent);
            }

            transaction.commit();
            return oldStudent;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteStudent(long studentId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);

            if (student != null) {
                session.remove(student);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // get all students (HQL)
    public List<Student> getAllStudentsHQL() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student";
            Query<Student> query = session.createQuery(hql, Student.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // get student by name (HQL)
    public Student getStudentByNameHQL(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Student WHERE name = :studentName";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentName", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // criteria api: get all students of same college
    public List<Student> getStudentsByCollegeCriteria(String college) {
        try (Session session = sessionFactory.openSession()) {

            // Step 1: Get CriteriaBuilder
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            // Step 2: Create CriteriaQuery
            CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

            // Step 3: Define root (FROM Student)
            Root<Student> root = criteriaQuery.from(Student.class);

            // Step 4: Apply WHERE condition
            criteriaQuery.select(root)
                         .where(criteriaBuilder.equal(root.get("college"), college));

            // Step 5: Execute query
            Query<Student> query = session.createQuery(criteriaQuery);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
 // get students with pagination
    public List<Student> getStudentsWithPagination(int pageNo, int pageSize) {
        try (Session session = sessionFactory.openSession()) {

            String hql = "FROM Student";  // HQL query

            Query<Student> query = session.createQuery(hql, Student.class);

            // Pagination logic
            query.setFirstResult((pageNo - 1) * pageSize);  // starting row
            query.setMaxResults(pageSize);                  // number of rows

            return query.list();
        }
    }

}
