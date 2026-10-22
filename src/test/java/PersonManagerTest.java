import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.lexicon.Person;
import se.lexicon.PersonManager;

public class PersonManagerTest {
	PersonManager manager;
	Person alice;
	Person bob;

	@BeforeEach
	void setUp() {
		manager = new PersonManager();
		alice = new Person ("Alice", "Smith", "12345", "123 St", "alice@email.com") ;
		bob = new Person("Bob", "Jones", "67890", "456 Rd", "bob@email.com");
	}

	@Test
	void testAddPerson_Positive() {
		boolean result = manager.addPerson(alice);
		assertTrue(result, "Should successfully add a unique contact.");
		assertEquals(1, manager.personList.size());
	}

	@Test
	void testAddPerson_Negative_DuplicateEmail() {
		manager.addPerson(alice);
		Person duplicateAlice = new Person("Amelia", "S", "000", "999 Ln", "alice@email.com");

		boolean result = manager.addPerson(duplicateAlice);
		assertFalse(result, "Should refuse to add a contact with a duplicate email.");
		assertEquals(1, manager.personList.size(), "List size should remain unchanged.");
	}

	@Test
	void testSearchPersonByEmail_Positive() {
		manager.addPerson(alice);

		Person found = manager.searchPersonByEmail("alice@email.com");
		assertNotNull(found, "contact should be discovered.");
		assertEquals("Alice", found.getFirstName());
	}

	@Test
	void testSearchPersonByEmail_Negative_NotFound() {
		manager.addPerson(alice);

		Person found = manager.searchPersonByEmail("missing@email.com");
		assertNull(found, "Searching for an unregistered email should yield null.");
	}
	@Test
	void testDeletePersonByEmail_Positive() {
		manager.addPerson(alice);

		boolean result = manager.deletePersonByEmail("alice@email.com");
		assertTrue(result, "Should successfully remove the matching contact.");
		assertTrue(manager.personList.isEmpty());
	}

	@Test
	void testDeletePersonByEmail_Negative_NotFound() {
		manager.addPerson(alice);

		boolean result = manager.deletePersonByEmail("nonexistent@email.com");
		assertFalse(result, "Should return false if target contact is missing.");
		assertEquals(1, manager.personList.size(), "List must retain existing contacts.");
	}
	@Test
	void testSortPeopleByFirstName_Positive() {
		manager.addPerson(bob);
		manager.addPerson(alice);

		manager.sortPeopleByFirstName();

		assertEquals("Alice", manager.personList.get(0).getFirstName(), "Alice must bubble up to index 0.");
		assertEquals("Bob", manager.personList.get(1).getFirstName(), "Bob must downshift to index 1.");
	}

	@Test
	void testSortPeopleByFirstName_Negative_EmptyList() {
		assertDoesNotThrow(() -> manager.sortPeopleByFirstName(),
				"Sorting an completely empty list should not trigger NullPointerExceptions.");
	}
}
