package se.lexicon;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactApp {
	static ArrayList<Person> personList = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println(("Welcome!"));
		boolean running = true;
		while (running) {
			System.out.println("==================================\n"
					+ "  LEXICON CONTACT MANAGEMENT APP\n"
					+ "==================================");
			System.out.println("1. Add New Contact");
			System.out.println("2. View All Contacts");
			System.out.println("3. Search Contact by email");
			System.out.println("4. Delete Contact by email");
			System.out.println("5. Exit");
			System.out.print("Choose an option (1-5): ");

			int choice = getValidChoice();

			switch (choice) {
				case 1:
					addPerson();
					break;
				case 2:
					viewPeople();
					break;
				case 3:
					searchPersonByEmail();
					break;
				case 4:
					deletePersonByEmail();
					break;
				case 5:
					running = false;
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please pick a number from 1 to 5.");
			}
		}
	scanner.close();
	}
	static int getValidChoice() {
		while (!scanner.hasNextInt()) {
			System.out.println("Please enter a valid number.");
			scanner.next();
		}
		int choice = scanner.nextInt();
		scanner.nextLine(); // Clear text buffer
		return choice;
	}
	static void addPerson() {
		System.out.print("Enter First Name: ");
		String firstName = scanner.nextLine();
		System.out.print("Enter Last Name: ");
		String lastname = scanner.nextLine();
		System.out.print("Enter Telephone (tel): ");
		String tel = scanner.nextLine();
		System.out.print("Enter Address: ");
		String address = scanner.nextLine();
		System.out.print("Enter Email Address: ");
		String email = scanner.nextLine();
		if (isEmailDuplicate(email)) {
			System.out.println("Error: A user with the email '" + email + "' already exists. Registration canceled.");
			return;
		}

		Person newPerson = new Person(firstName, lastname, tel, address, email);
		personList.add(newPerson);
		System.out.println("Person added successfully!");
	}
	static boolean isEmailDuplicate(String emailToTest) {
		for (Person p : personList) {
			if (p.email.equalsIgnoreCase(emailToTest)) {
				return true;
			}
		}
		return false;
	}
	static void viewPeople() {
		if (personList.isEmpty()) {
			System.out.println("Your registry is empty.");
			return;
		}
		System.out.println("\n--- All Registered Contacts ---");
		for (int i = 0; i < personList.size(); i++) {
			System.out.println((i + 1) + ". " + personList.get(i));
		}
	}
	static void searchPersonByEmail() {
		System.out.print("Enter the email address to search for: ");
		String searchEmail = scanner.nextLine();
		boolean found = false;

		for (Person p : personList) {
			if (p.email.equalsIgnoreCase(searchEmail)) {
				System.out.println("Found: " + p);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No one found matching email '" + searchEmail + "'.");
		}
	}
	static void deletePersonByEmail() {
		System.out.print("Enter the exact email of the contact to delete: ");
		String targetEmail = scanner.nextLine();
		Person toRemove = null;

		for (Person p : personList) {
			// Evaluates using the unique email identity field
			if (p.email.equalsIgnoreCase(targetEmail)) {
				toRemove = p;
				break;
			}
		}

		if (toRemove != null) {
			personList.remove(toRemove);
			System.out.println("Contct with email '" + targetEmail + "' successfully removed.");
		} else {
			System.out.println("Contact not found. No deletions made.");
		}
	}

}
