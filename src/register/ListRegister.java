package register;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register {

	private List<Person> persons = new ArrayList<>();
	
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

	Iterator<Person> iterator = persons.iterator();

	@Override
	public void removePerson(Person person) {

		while (iterator.hasNext()) {
			if (iterator.next().equals(person)) {
				iterator.remove();
			}

		}
	}

}
