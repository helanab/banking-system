package banking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import accounts.Account;
import accounts.Account.Status;
import accounts.checking.BusinessCheckingAccount;
import accounts.checking.PersonalCheckingAccount;
import accounts.checking.StudentCheckingAccount;
import accounts.saving.PersonalSavingsAccount;
import accounts.saving.StudentSavingsAccount;
import exceptions.UserAlreadyExistsException;
import exceptions.UserDoesNotExistException;
import users.Address;
import users.Teller;
import users.User;

public class BankingSystem {
	protected HashMap<String, User> userMap = new HashMap<String, User>();

	public Account getAccount(String licenseNumber, int accountNumber) {
		return userMap.get(licenseNumber).getAccount(accountNumber);
	}

	public void withdraw(String licenseNumber, int accountNumber, BigDecimal amountToWithdraw, Teller teller) {
		User user = userMap.get(licenseNumber);
		Account account = user.getAccount(accountNumber);
		account.withdraw(amountToWithdraw);
		Transaction t = BankingInformationDAO.recordTransaction(user, account, amountToWithdraw, teller);
		printReciept(t);
	}

	public void deposit(String licenseNumber, int accountNumber, BigDecimal amountToDeposit, Teller teller) {
		User user = userMap.get(licenseNumber);
		Account account = user.getAccount(accountNumber);
		account.withdraw(amountToDeposit);
		Transaction t = BankingInformationDAO.recordTransaction(user, account, amountToDeposit, teller);
		printReciept(t);
	}

	public void loadInformation() {
		List<User> userList;
		List<Account> accounts;
		List<Transaction> transactions;

		try {
			userList = BankingInformationDAO.loadUsers("users.txt");
		} catch (IOException e) {
			System.out.println("Specifed file for users doesn't exist.");
			userList = null;
		}
		try {
			 accounts = BankingInformationDAO.loadAccounts("accounts.txt");
		} catch (IOException e) {
			System.out.println("Specifed file for accounts doesn't exist.");
			accounts = null;
		}
		try {
			transactions = BankingInformationDAO.loadTransactions("transactions.txt");
		} catch (IOException e) {
			System.out.println("Specifed file for transactions doesn't exist.");
			transactions = null;
		}

		if(userList != null) {
			for(int i = 0; i < userList.size(); i++) {
				userMap.put(userList.get(i).getLicenseNumber(), userList.get(i));
			}
		}

		if(userList != null) {
			for(int i = 0; i < userList.size(); i++) {
				userMap.put(userList.get(i).getLicenseNumber(), userList.get(i));
			}
		}

		if(transactions != null && accounts != null && userList != null) {
			for(int i = 0; i < transactions.size(); i++) {
				for(int j = 0; j < accounts.size(); j++) {
					if(transactions.get(i).getAccountNumber() == accounts.get(j).getAccountNumber()) {
						accounts.get(j).addTransaction(transactions.get(i));
					}
				}
			}
			for(int i = 0; i < accounts.size(); i++) {
				if(userMap.get(accounts.get(i).getPrimaryAccountOwnerLicenseNumber()) != null) {
					userMap.get(accounts.get(i).getPrimaryAccountOwnerLicenseNumber()).addAccount(accounts.get(i));
				}
			}
		}
	}

	public User getUser(String licenseNumber) throws UserDoesNotExistException {
		boolean userExists = doesUserExist(licenseNumber);
		if(userExists == false) {
			throw new UserDoesNotExistException("User does not exist in the system.");
		} else {
			return userMap.get(licenseNumber);
		}
	}

	public void enrollUser(User user) throws UserAlreadyExistsException {
		boolean userExists = doesUserExist(user.getLicenseNumber());
		if(userExists == true) {
			throw new UserAlreadyExistsException("User already exists in the system.");
		} else {
			userMap.put(user.getLicenseNumber(), user);
		}
	}

	public void removeUser(String licenseNumber) throws UserDoesNotExistException {
		boolean userExist = doesUserExist(licenseNumber);
		if(userExist == false) {
			throw new UserDoesNotExistException("User does not exist in the system.");
		} else {
			userMap.remove(licenseNumber);
		}
	}

	public boolean doesUserExist(String licenseNumber) {
		boolean userExists = false;
		for(User user : userMap.values()) {
			if(user.getLicenseNumber() == licenseNumber) {
				userExists = true;
			}
		}
		return userExists;
	}

	public boolean doesAccountExist(Account account) {
		boolean accountExists = false;


		return accountExists;
	}

	public void addUser(String firstName, String lastName, LocalDate birthDate, String licenseNumber, String occupation, String streetAddress, String city, String state, int zipCode) {
		User user = new User(firstName, lastName, birthDate, licenseNumber, occupation, new Address(streetAddress, city, state, zipCode));
		userMap.put(user.getLicenseNumber(), user);
	}

	public void removeDuplicateUsers() {
		for(int i = 0; i < userMap.size(); i++) {
			for(int j = 0; j < userMap.size(); j++) {
				if(userMap.get(i).getLicenseNumber() == userMap.get(j).getLicenseNumber()) {
					userMap.remove(userMap.get(i).getLicenseNumber());
				}
			}
		}
	}

	public void openAccount(String type, int pinNumber, int accountNumber, String primaryAccountOwnerLicenseNumber) {
		switch(type) {
			case "Business checking":
				BusinessCheckingAccount bca = new BusinessCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), primaryAccountOwnerLicenseNumber, LocalDate.now());
				userMap.get(primaryAccountOwnerLicenseNumber).addAccount(bca);
			case "Personal checking":
				PersonalCheckingAccount pca = new PersonalCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), primaryAccountOwnerLicenseNumber, LocalDate.now());
				userMap.get(primaryAccountOwnerLicenseNumber).addAccount(pca);
			case "Student checking":
				StudentCheckingAccount sca = new StudentCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), primaryAccountOwnerLicenseNumber, LocalDate.now());
				userMap.get(primaryAccountOwnerLicenseNumber).addAccount(sca);
			case "Business savings":
				BusinessCheckingAccount bsa = new BusinessCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), primaryAccountOwnerLicenseNumber, LocalDate.now());
				userMap.get(primaryAccountOwnerLicenseNumber).addAccount(bsa);
			case "Personal savings":
				PersonalSavingsAccount psa = new PersonalSavingsAccount(pinNumber, accountNumber, new BigDecimal(0), primaryAccountOwnerLicenseNumber, LocalDate.now());
				userMap.get(primaryAccountOwnerLicenseNumber).addAccount(psa);
			case "Student savings":
				StudentSavingsAccount ssa = new StudentSavingsAccount(pinNumber, accountNumber, new BigDecimal(0), primaryAccountOwnerLicenseNumber, LocalDate.now());
				userMap.get(primaryAccountOwnerLicenseNumber).addAccount(ssa);
		}
	}

	public void closeAccount(String licenseNumber, int accountNumber) {
		User user = userMap.get(licenseNumber);
		user.getAccount(accountNumber).setStatus(Status.CLOSED);
	}

	public void saveData(List<User> users, List<Account> accounts, List<Transaction> transactions) {
		BankingInformationDAO.recordUsers(users);
		BankingInformationDAO.recordAccounts(accounts);
		BankingInformationDAO.recordTransactions(transactions);
	}

	public void printReciept(Transaction t) {
		System.out.println(t.getType());
	}
}
