package com.staskost.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.staskost.jpa.model.User;

public class UserDaoImpl implements UserDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

	public void addUser(User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			em.persist(user);
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public User getUserById(int id) {
		EntityManager em = emf.createEntityManager();
		String query = "SELECT u FROM User u WHERE u.id = : userId";
		TypedQuery<User> tq = em.createQuery(query, User.class);
		tq.setParameter("userId", id);
		User user = null;
		try {
			user = tq.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}

	public List<User> getUsers() {
		EntityManager em = emf.createEntityManager();
		String query = "SELECT u FROM User u WHERE u.id IS NOT NULL";
		TypedQuery<User> tq = em.createQuery(query, User.class);
		List<User> users = new ArrayList<User>();
		try {
			users = tq.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return users;
	}

	public void deleteUser(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		User user = null;
		try {
			et = em.getTransaction();
			et.begin();
			user = em.find(User.class, id);
			em.remove(user);
			System.out.println("user deleted");
			et.commit();
		} catch (NoResultException e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void updateUser(int id, User user) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = null;
		User updatedUser = null;
		try {
			et = em.getTransaction();
			et.begin();
			updatedUser = em.find(User.class, id);
			updatedUser.setAddress(user.getAddress());
			updatedUser.setEmail(user.getEmail());
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			em.persist(updatedUser);
			et.commit();
		} catch (Exception e) {
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
