package users;

import java.time.LocalDate;

import banking.BankingSystem;

public class Teller extends User {
	
	private static BankingSystem bs = new BankingSystem();
	
	public Teller(String firstName, String lastName, LocalDate birthDate, String licenseNumber, String occupation, Address address) {
		super(firstName, lastName, birthDate, licenseNumber, occupation, address);
	}

	public static BankingSystem accessBankingSystem() {
		return bs;
	}

}
