package register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register {

	private List<Person> persons = new ArrayList<>();
	private static String FILE_NAME = "register.bin";

	public ListRegister() {
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
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
			this.persons = (List<Person>) ois.readObject();
		} catch (Exception ex) {
			System.err.println("Error " + ex.getMessage());
		}
	}

	@Override
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
			oos.writeObject(persons);
		} catch (IOException ex) {
			System.err.println("Error " + ex.getMessage());
		}
	}

}
