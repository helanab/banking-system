package users;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import accounts.Account;

public class User {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String licenseNumber;
	private List<Account> accounts = new ArrayList<Account>();
	private String occupation;
	private Address address;

	public User(String firstName, String lastName, LocalDate birthDate, String licenseNumber, String occupation, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.licenseNumber = licenseNumber;
		this.occupation = occupation;
		this.address = address;
	}

	public void addAccount(Account a) {
		accounts.add(a);
	}
	
	public Account getAccount(int accountNumber) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountNumber() == accountNumber) {
				return accounts.get(i);
			}
		}
		return null;
	}
	
	public BigDecimal getCombinedAverageOfAccounts() {
		BigDecimal total = new BigDecimal(0);
		for(int i = 0; i < accounts.size(); i++) {
			total.add(accounts.get(i).getAccountBalance());
		}
		return total.divide(new BigDecimal(accounts.size()));
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getLicenseNumber() {
		return this.licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
