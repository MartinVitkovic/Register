package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/** register.Register of persons. */
	private Register register;

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	private enum OptionFind {
		ID, NAME, NUMBER, BACK
	}

	private enum OptionRemove {
		ID, FIRSTLETTER, BACK
	}

	public ConsoleUI(Register register) {
		this.register = register;
	}

	public void run() throws PhoneNumberFormatException {
		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				return;
			}
		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	
	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	private void printRegister() {
		for (int i = 0; i < register.getCount(); i++) {
			System.out.println("[" + (i + 1) + ".]" + register.getPerson(i));
		}
	}

	
	private void addToRegister() throws PhoneNumberFormatException {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();

		register.addPerson(new Person(name, phoneNumber));
	}

	//method updateRegister
	private void updateRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		System.out.println("New Name: ");
		person.setName(readLine());
		System.out.println("New Phone Number: ");
		try {
			person.setPhoneNumber(readLine());
		} catch (PhoneNumberFormatException e) {
			e.printStackTrace();
		}
	}

	//method remove from register
	private void removeFromRegister() {
		while (true) {
			switch (showRemoveMenu()) {
			case ID:
				removeFromRegisterByID();
				break;
			case FIRSTLETTER:
				removeFromRegisterByFirstLetter();
				break;
			case BACK:
				return;
			}
		}
	}

	private OptionRemove showRemoveMenu() {
		System.out.println("Choose option: ");
		for (OptionRemove option : OptionRemove.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("OptionFind: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > OptionRemove.values().length);

		return OptionRemove.values()[selection - 1];
	}

	private void removeFromRegisterByID() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		register.removePerson(person);
	}

	private void removeFromRegisterByFirstLetter() {
		System.out.println("Enter firstLetter: ");
		char firstLetter = readLine().charAt(0);
		if (register instanceof ListRegister) {
			((ListRegister) register).deleteAllBy(firstLetter);
		} else {
			System.err.println("Register isn't list");
		}
	}
	//end of remove from register


	//method findInRegister
	private void findInRegister() {
		while (true) {
			switch (showFindMenu()) {
			case ID:
				findInRegisterById();
				break;
			case NAME:
				findInRegisterByName();
				break;
			case NUMBER:
				findInRegisterByNumber();
				break;
			case BACK:
				return;
			}
		}
	}

	private OptionFind showFindMenu() {
		System.out.println("Choose option: ");
		for (OptionFind option : OptionFind.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("OptionFind: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > OptionFind.values().length);

		return OptionFind.values()[selection - 1];
	}

	private void findInRegisterById() {
		System.out.println("Enter ID: ");
		int index = Integer.parseInt(readLine());
		// Person person = register.getPerson(index - 1);
		for (int i = 0; i < register.getCount(); i++) {
			if (index == i + 1) {
				System.out.println(register.getPerson(i));
			}
		}
	}

	private void findInRegisterByName() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println(register.findPersonByName(name));
	}

	private void findInRegisterByNumber() {
		System.out.println("Enter Number: ");
		String phoneNumber = readLine();
		for (int i = 0; i < phoneNumber.length(); i++) {
			if (!Character.isDigit(phoneNumber.charAt(i))) {
				System.err.println("Zadal si neplatne cislo!");
			}
		}
		try {
			System.out.println(register.findPersonByPhoneNumber(phoneNumber));
		} catch (NullPointerException e) {
			// TODO: handle exception
			System.out.println("Cislo sa nenachadza v registri");
		}
	}
	//end of find in register

}
