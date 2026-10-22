package se.lexicon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ContactApp {
	static PersonManager manager = new PersonManager();
	static Scanner scanner = new Scanner(System.in);
	static final String FILE_NAME = "contacts.csv";
	public static void main(String[] args) {
		initializeStorage();
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
					saveStorageAndExit();
					running = false;
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please pick a number from 1 to 5.");
			}
		}
	scanner.close();
	}
	static void initializeStorage() {
		File file = new File(FILE_NAME);
		if (file.exists()) {
			try {
				manager.loadFromCSV(FILE_NAME);
				System.out.println("[System] Successfully loaded registered contacts from file storage registry.");
			} catch (IOException e) {
				System.out.println("[Warning] Failed loading local text file data: " + e.getMessage());
			}
		} else {
			System.out.println("[System] Local contacts file path not found. Starting with a fresh empty list.");
		}
	}
	static void saveStorageAndExit() {
		try {
			manager.saveToCSV(FILE_NAME);
			System.out.println("[System] All changes written securely to '" + FILE_NAME + "'.");
		} catch (IOException e) {
			System.out.println("[Error] Could not save registry out to disk: " + e.getMessage());
		}
	}
	static int getValidChoice() {
		while (!scanner.hasNextInt()) {
			System.out.println("Please enter a valid number.");
			scanner.next();
		}
		int choice = scanner.nextInt();
		scanner.nextLine();
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

		if (manager.isEmailDuplicate(email)) {
			System.out.println("Error: A contact with that email already exists!");
			return;
		}

		Person newPerson = new Person(firstName, lastname, tel, address, email);
		manager.addPerson(newPerson);
		System.out.println("Contact added successfully!");
	}
	static void viewPeople() {
		if (manager.personList.isEmpty()) {
			System.out.println("Your contact list is empty.");
			return;
		}
		manager.sortPeopleByFirstName();
		System.out.println("\n--- All Registered contacts (Sorted A-Z) ---");
		for (int i = 0; i < manager.personList.size(); i++) {
			System.out.println((i + 1) + ". " + manager.personList.get(i));
		}
	}
	static void searchPersonByEmail() {
		System.out.print("Enter the email address to search for: ");
		String searchEmail = scanner.nextLine();
		Person p = manager.searchPersonByEmail(searchEmail);
		if (p != null) {
			System.out.println("Found: " + p);
		} else {
			System.out.println("No one found matching email '" + searchEmail + "'.");
		}
	}
	static void deletePersonByEmail() {
		System.out.print("Enter the exact email of the contact to delete: ");
		String targetEmail = scanner.nextLine();
		if (manager.deletePersonByEmail(targetEmail)) {
			System.out.println("Contact successfully removed.");
		} else {
			System.out.println("Contact not found. No deletions made.");
		}
	}

}
