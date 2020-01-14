package com.staskost.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.staskost.jpa.dao.UserDao;
import com.staskost.jpa.dao.UserDaoImpl;
import com.staskost.jpa.model.User;

public class App {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

	public static void main(String[] args) {

		// create users

		User user = User.builder().withAddress("Athens").withEmail("kstas@mail.com").withFirstName("Kostas")
				.withLastName("Stasinos").build();

		User user2 = User.builder().withAddress("Athens").withEmail("nstas@mail.com").withFirstName("Nasos")
				.withLastName("Stasinos").build();

		UserDao userDao = new UserDaoImpl();

		// saves user into database
		userDao.addUser(user);

		// saves user2 into database
		userDao.addUser(user2);

		user.setFirstName("Konstantinos");

		// updates users name from Kostas to Konstantinos..The id that was generated for
		// user is 1
		userDao.updateUser(1, user);

		// gets user with id 1..
		User databaseUser = userDao.getUserById(1);
		System.out.println(databaseUser.getFirstName());

		// fetch all app users
		List<User> users = userDao.getUsers();
		users.forEach(u -> System.out.println(u.getFirstName()));

		// deletes user2 from database
		userDao.deleteUser(2);

		emf.close();
	}

}
