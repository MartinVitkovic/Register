package register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JDBCListRegister implements Register {

	private List<Person> persons = new ArrayList<>();
	private static final String URL = "jdbc:postgresql://localhost/register";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String INSERT = "INSERT INTO registerList(meno, cislo) VALUES (?, ?)";
	private static final String SELECT = "SELECT * FROM registerList";
	private int index;
	// private static String FILE_NAME = "register.bin";

	public JDBCListRegister() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	@Override
	public void addPerson(Person person) {
		for (Person personInList : persons) {
			if (personInList.getName().equals(person.getName())) {
				System.out.println("Do registra nemozno pridat osobu s danym menom, pretoze uz sa tam nachadza!");
				return;
			} else if (personInList.getPhoneNumber().equals(person.getPhoneNumber())) {
				System.out.println(
						"Do registra nemozno pridat osobu s danym telefonnym cislom, pretoze uz sa tam nachadza!");
				return;
			}
		}
		persons.add(person);
		Collections.sort(persons);
	}

	@Override
	public Person findPersonByName(String name) {
		for (Person personInRegister : persons) {
			if (personInRegister.getName().equals(name)) {
				return personInRegister;
			}
		}
		System.out.println("Osoba s danym menom sa v registri nenachadza!");
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (Person personInRegister : persons) {
			if (personInRegister.getPhoneNumber().equals(phoneNumber)) {
				return personInRegister;
			}
		}
		System.out.println("Osoba s danym cislom sa v registri nenachadza!");
		return null;
	}

	@Override
	public void removePerson(Person person) {
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(person)) {
				iterator.remove();
			}
		}
	}

	public void deleteAllBy(char firstLetter) {
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getName().startsWith(Character.toString(firstLetter))) {
				iterator.remove();
			}
		}
	}

	@Override
	public void load() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SELECT)) {
			while (resultSet.next()) {
				Person person = new Person(resultSet.getString("meno"), resultSet.getString("cislo"));
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PhoneNumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
			// Iterator<Person> iterator = persons.iterator();
			Statement statement = connection.createStatement();
			statement.executeUpdate("TRUNCATE TABLE registerList");
			PreparedStatement pStatement = connection.prepareStatement(INSERT);
			for (Person per : persons) {
				pStatement.setString(1, per.getName());
				pStatement.setString(2, per.getPhoneNumber());
				pStatement.executeUpdate();
			}		
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
