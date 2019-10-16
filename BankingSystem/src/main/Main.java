package main;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import accounts.saving.StudentSavingsAccount;
import users.Address;
import users.Teller;

class Main {
    public static void main(String[] args) {

	    Account a = new StudentSavingsAccount(1234, 123, new BigDecimal(500), "B00972354", LocalDate.now());
	    System.out.println(a.getClass().getSimpleName() + " hello");

    	Teller teller_helanaBrock = new Teller("Helana", "Brock", LocalDate.of(2000, 3, 3), "B100943757", "Teller", new Address("10007 Fleming Rd", "Fowlerville", "Michigan", 48836));

    	teller_helanaBrock.accessBankingSystem().addUser("Brian", "Gillies", LocalDate.of(2000, 2, 18), "B0098273", "Chemistry Major", "70019 Kimberly Ct", "Fowlerville", "MI", 48836);
    	teller_helanaBrock.accessBankingSystem().addUser("Katie", "Wilkinson", LocalDate.of(1975, 4, 18), "A7634500", "Electrical Engineer", "70020 Hayner Rd", "Fowlerville", "MI", 48836);
    	teller_helanaBrock.accessBankingSystem().addUser("Patty", "Blackmer", LocalDate.of(1961, 8, 18), "B8450280", "Artist", "20021 Main St", "Fowlerville", "MI", 48836);
    	teller_helanaBrock.accessBankingSystem().addUser("Mike", "Daniels", LocalDate.of(1980, 1, 18), "B0348756", "Public Speaker", "7890 Fowlerville Rd", "Fowlerville", "MI", 48836);
    	teller_helanaBrock.accessBankingSystem().addUser("Craig", "McKinsley", LocalDate.of(1998, 6, 18), "A5673920", "Marketing Major", "12008 Joyce Ct", "Fowlerville", "MI", 48836);
    	teller_helanaBrock.accessBankingSystem().addUser("Starla", "Ling", LocalDate.of(1969, 11, 18), "B6589600", "Marketing Major", "12008 Joyce Ct", "Fowlerville", "MI", 48836);

    	teller_helanaBrock.accessBankingSystem().openAccount("Student checking", 1234, 123, "B0098273");
    	teller_helanaBrock.accessBankingSystem().openAccount("Business checking", 1245, 124, "A7634500");
    	teller_helanaBrock.accessBankingSystem().openAccount("Personal checking", 1256, 125, "B8450280");
    	teller_helanaBrock.accessBankingSystem().openAccount("Student savings", 1267, 126, "A5673920");
    	teller_helanaBrock.accessBankingSystem().openAccount("Business savings", 1278, 127, "B0348756");
    	teller_helanaBrock.accessBankingSystem().openAccount("Personal savings", 1289, 128, "B6589600");

    	teller_helanaBrock.accessBankingSystem().getAccount("B0098273", 123).printAccountInformation();
    	teller_helanaBrock.accessBankingSystem().getAccount("A7634500", 124).printAccountInformation();
    	teller_helanaBrock.accessBankingSystem().getAccount("B8450280", 125).printAccountInformation();
    	teller_helanaBrock.accessBankingSystem().getAccount("A5673920", 126).printAccountInformation();
    	teller_helanaBrock.accessBankingSystem().getAccount("B0348756", 127).printAccountInformation();
    	teller_helanaBrock.accessBankingSystem().getAccount("B6589600", 128).printAccountInformation();

    	// Student checking
    	teller_helanaBrock.accessBankingSystem().deposit("B0098273", 123, new BigDecimal(500), teller_helanaBrock);
    	teller_helanaBrock.accessBankingSystem().withdraw("B0098273", 123, new BigDecimal(600), teller_helanaBrock);
    	teller_helanaBrock.accessBankingSystem().withdraw("B0098273", 123, new BigDecimal(500), teller_helanaBrock); // this shouldn't work

    	// Personal checking
    	teller_helanaBrock.accessBankingSystem().deposit("B8450280", 125, new BigDecimal(500), teller_helanaBrock);

    }
}
