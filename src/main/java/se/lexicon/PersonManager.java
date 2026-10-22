package se.lexicon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PersonManager {
	public ArrayList<Person> personList = new ArrayList<>();
	public boolean addPerson(Person person) {
		if (isEmailDuplicate(person.email)) {
			return false;
		}
		personList.add(person);
		return true;
	}

	public boolean isEmailDuplicate(String emailToTest) {
		for (Person p : personList) {
			if (p.email.equalsIgnoreCase(emailToTest)) {
				return true;
			}
		}
		return false;
	}

	public Person searchPersonByEmail(String searchEmail) {
		for (Person p : personList) {
			if (p.email.equalsIgnoreCase(searchEmail)) {
				return p;
			}
		}
		return null;
	}

	public boolean deletePersonByEmail(String targetEmail) {
		Person toRemove = searchPersonByEmail(targetEmail);
		if (toRemove != null) {
			personList.remove(toRemove);
			return true;
		}
		return false;
	}

	public ArrayList<Person> sortPeopleByFirstName() {
		Collections.sort(personList, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.firstName.compareToIgnoreCase(p2.firstName);
			}
		});
		return personList;
	}
	public boolean isEmpty() {
		return personList.isEmpty();
	}
	void saveToCSV(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		for (Person p : personList) {
			writer.write(p.firstName + "," + p.lastname + "," + p.tel + "," + p.address + "," + p.email);
			writer.newLine();
		}
		writer.close();
	}

	void loadFromCSV(String filename) throws IOException {
		personList.clear();

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] data = line.split(",");
			if (data.length == 5) {
				Person p = new Person(data[0], data[1], data[2], data[3], data[4]);
				personList.add(p);
			}
		}
		reader.close();
	}
}
