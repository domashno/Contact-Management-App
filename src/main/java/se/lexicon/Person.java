package se.lexicon;

public class Person {
	String firstName;
	String lastname;
	String tel;
	String address;
	String email;
	public Person(String firstName, String lastname, String tel, String address, String email) {
		this.firstName = firstName;
		this.lastname = lastname;
		this.tel = tel;
		this.address = address;
		this.email = email;
	}
	public String getFirstName() { return firstName; }
	public String getLastname() { return lastname; }
	public String getTel() { return tel; }
	public String getAddress() { return address; }
	public String getEmail() { return email; }
	@Override
	public String toString() {
		return "Name: " + firstName + " " + lastname +
				" | Tel: " + tel +
				" | Address: " + address +
				" | Email: " + email;
	}
}
