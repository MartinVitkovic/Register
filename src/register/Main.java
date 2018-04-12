package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Register register = new Register(20);

        register.addPerson(new Person("Janko Hrasko", "0950123456"));
        register.addPerson(new Person("Matus Placko", "0900123456"));
        register.addPerson(new Person("Samuel Hrivnak", "0904186456"));
        register.addPerson(new Person("Silvia Korcekova", "0912123854"));
        register.addPerson(new Person("Martin Hruska", "0909533456"));
        register.addPerson(new Person("Janko Hrasko", "0950123456"));

        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
    }
}