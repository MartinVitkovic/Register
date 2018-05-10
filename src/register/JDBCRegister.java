package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRegister implements Register {
	private static final String URL = "jdbc:postgresql://localhost/register";
	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String INSERT = "INSERT INTO register(id, meno, cislo) VALUES (?, ?, ?)";
	private static final String SELECT_GET_PERSON = "SELECT meno, cislo FROM register where id = ?";
	private static final String SELECT_BY_NAME = "SELECT meno, cislo FROM register where meno = ?";
	private static final String SELECT_BY_NUMBER = "SELECT meno, cislo FROM register where cislo = ?";
	private static final String DELETE = "DELETE FROM register WHERE meno = ?";
	private static final String SELECT_COUNT = "SELECT max(id) from register";
	
	/** Number of persons in this register. */
	//private int count;

	// Constructor
	public JDBCRegister() {
		//int count = this.count;
	}

	@Override
	public int getCount() {
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
							Statement statement = connection.createStatement();
							ResultSet resultSet = statement.executeQuery(SELECT_COUNT)) {
						resultSet.next();
						return resultSet.getInt(1);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return 0; 	
	}

	@Override
	public Person getPerson(int index) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SELECT_GET_PERSON);) {			
			statement.setInt(1, index+1);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getString(1), rs.getString(2));				
				return (person);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (PhoneNumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addPerson(Person person) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setInt(1, getCount()+1);
			statement.setString(2, person.getName());
			statement.setString(3, person.getPhoneNumber());
			statement.executeUpdate();
			//count++;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public Person findPersonByName(String name) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);) {
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getString(1), rs.getString(2));
				return (person);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (PhoneNumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_NUMBER);) {
			statement.setString(1, phoneNumber);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Person person = new Person(rs.getString(1), rs.getString(2));
				return (person);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} catch (PhoneNumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removePerson(Person person) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(DELETE);) {
			statement.setString(1, person.getName());
			statement.executeUpdate();
			//count--;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} 
	}


	// public void

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

}
