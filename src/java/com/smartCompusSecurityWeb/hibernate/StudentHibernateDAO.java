package com.smartCompusSecurityWeb.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StudentHibernateDAO {

    public void addStudent(Student student) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(student);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public Student getStudent(int id) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Student.class, id);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    public void updateStudent(Student student) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(student);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Student student =
                    session.get(Student.class, id);

            if (student != null) {
                session.remove(student);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}