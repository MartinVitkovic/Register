package register;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/* (non-Javadoc)
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/**
	 * Returns the maximum number of persons in this register.
	 * 
	 * @return the maximum number of persons in this register.
	 */
	public int getSize() {
		return persons.length;
	}

	/* (non-Javadoc)
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/* (non-Javadoc)
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		for (int i = 0; i < count; i++) {
			if (persons[i] != null) {
				if (person.getName().equals(persons[i].getName())) {
					System.out.println("Do registra nemozno pridat osobu s danym menom, pretoze uz sa tam nachadza!");
					return;
				} else if (person.getPhoneNumber().equals(persons[i].getPhoneNumber())) {
					System.out.println(
							"Do registra nemozno pridat osobu s danym telefonnym cislom, pretoze uz sa tam nachadza!");
					return;
				}
			}
		}

		persons[count] = person;
		count++;
	}

	// TODO: Implement the method findPersonByName
	/* (non-Javadoc)
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < persons.length; i++) {
			if (name.equals(persons[i].getName())) {
				return persons[i];
			}
		}
		System.out.println("Osoba s danym menom sa v registri nenachadza!");
		return null;
	}

	// TODO: Implement the method findPersonByPhoneNumber
	/* (non-Javadoc)
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int i = 0; i < persons.length; i++) {
			if (phoneNumber.equals(persons[i].getPhoneNumber())) {
				return persons[i];
			}
		}
		System.out.println("Osoba s danym cislom sa v registri nenachadza!");
		return null;
	}

	// TODO: Implement the method removePerson
	/* (non-Javadoc)
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		for (int i = 0; i < persons.length - 1; i++) {
			if (person.equals(persons[i])) {
				System.arraycopy(persons, i + 1, persons, i, persons.length - i - 1);
			}
		}
		count--;
	}
}
