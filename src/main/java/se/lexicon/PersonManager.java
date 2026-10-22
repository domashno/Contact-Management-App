package se.lexicon;

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

	public void sortPeopleByFirstName() {
		Collections.sort(personList, new Comparator<Person>() {
			@Override
			public int compare(Person p1, Person p2) {
				return p1.firstName.compareToIgnoreCase(p2.firstName);
			}
		});
	}
}
