package banking;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import accounts.Account;
import accounts.Account.Status;
import accounts.checking.BusinessCheckingAccount;
import accounts.checking.PersonalCheckingAccount;
import accounts.checking.StudentCheckingAccount;
import accounts.saving.BusinessSavingsAccount;
import accounts.saving.PersonalSavingsAccount;
import accounts.saving.StudentSavingsAccount;
import exceptions.InsufficientFundsException;
import exceptions.InvalidMonetaryAmountException;
import exceptions.OverDraftLimitException;
import exceptions.UserDoesNotExistException;
import users.Address;
import users.Teller;
import users.User;

/**
 * @author Helana Brock
 *
 */
public class BankingSystem {
	protected HashMap<String, User> userMap = new HashMap<String, User>();
	protected List<User> userList;
	protected List<Account> accounts;
	protected List<Transaction> transactions;
	
	public BankingSystem() {
		loadInformation();
	}
	
	
	/**
	 * 
	 */
	public void loadInformation() {
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
				if(userMap.get(accounts.get(i).getPrimaryAccountOwner().getLicenseNumber()) != null) {
					userMap.get(accounts.get(i).getPrimaryAccountOwner().getLicenseNumber()).addAccount(accounts.get(i));
				}
				accounts.get(i).setPrimaryAccountOwner(userMap.get(accounts.get(i).getPrimaryAccountOwner().getLicenseNumber()));
			}
		}
	}
	
	
	/**
	 * @param licenseNumber
	 * @param accountNumber
	 * @param amountToWithdraw
	 * @param teller
	 * @throws InsufficientFundsException 
	 */
	public void withdraw(String licenseNumber, int accountNumber, BigDecimal amountToWithdraw, Teller teller) throws InsufficientFundsException {
		User user = userMap.get(licenseNumber);
		Account account = user.getAccount(accountNumber);
		try {
			account.withdraw(amountToWithdraw);
			Transaction t = BankingInformationDAO.recordTransaction(user, account, amountToWithdraw, teller);
			printReciept(t);
		} catch (InvalidMonetaryAmountException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @param licenseNumber
	 * @param accountNumber
	 * @param amountToDeposit
	 * @param teller
	 */
	public void deposit(String licenseNumber, int accountNumber, BigDecimal amountToDeposit, Teller teller) {
		User user = userMap.get(licenseNumber);
		Account account = user.getAccount(accountNumber);
		account.deposit(amountToDeposit);
		Transaction t = BankingInformationDAO.recordTransaction(user, account, amountToDeposit, teller);
		printReciept(t);
	}
	
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param licenseNumber
	 * @param occupation
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public void addUser(String firstName, String lastName, LocalDate birthDate, String licenseNumber, String occupation, String streetAddress, String city, String state, int zipCode) {
		User user = new User(firstName, lastName, birthDate, licenseNumber, occupation, new Address(streetAddress, city, state, zipCode));
		userMap.put(user.getLicenseNumber(), user);
	}
	
	
	/**
	 * @param licenseNumber
	 * @return
	 * @throws UserDoesNotExistException
	 */
	public User getUser(String licenseNumber) throws UserDoesNotExistException {
		boolean userExists = doesUserExist(licenseNumber);
		if(userExists == false) {
			throw new UserDoesNotExistException("User does not exist in the system.");
		} else {
			return userMap.get(licenseNumber);
		}
	}
	
	
	/**
	 * @param licenseNumber
	 * @throws UserDoesNotExistException
	 */
	public void removeUser(String licenseNumber) throws UserDoesNotExistException {
		boolean userExist = doesUserExist(licenseNumber);
		if(userExist == false) {
			throw new UserDoesNotExistException("User does not exist in the system.");
		} else {
			userMap.remove(licenseNumber);
		}
	}
	
	
	/**
	 * 
	 */
	public void removeDuplicateUsers() {
		for(int i = 0; i < userMap.size(); i++) {
			for(int j = 0; j < userMap.size(); j++) {
				if(userMap.get(i).getLicenseNumber() == userMap.get(j).getLicenseNumber())
					userMap.remove(userMap.get(i).getLicenseNumber());
			}
		}
	}
	
	
	/**
	 * @param type
	 * @param pinNumber
	 * @param accountNumber
	 * @param primaryAccountOwnerLicenseNumber
	 * @param optIn
	 */
	public void openAccount(String type, int pinNumber, int accountNumber, User user, Status status) {
		switch(type) {
			case "BusinessCheckingAccount":
				BusinessCheckingAccount bca = new BusinessCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), user, LocalDate.now(), null, status, false);
				userMap.get(user.getLicenseNumber()).addAccount(bca);
			case "PersonalCheckingAccount":
				PersonalCheckingAccount pca = new PersonalCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), user, LocalDate.now(), null, status, false);
				userMap.get(user.getLicenseNumber()).addAccount(pca);
			case "StudentCheckingAccount":
				StudentCheckingAccount sca = new StudentCheckingAccount(pinNumber, accountNumber, new BigDecimal(0), user, LocalDate.now(), null, status, false);
				userMap.get(user.getLicenseNumber()).addAccount(sca);
			case "BusinessSavingsAccount":
				BusinessSavingsAccount bsa = new BusinessSavingsAccount(pinNumber, accountNumber, new BigDecimal(0), user, LocalDate.now(), null, status);
				userMap.get(user.getLicenseNumber()).addAccount(bsa);
			case "PersonalSavingsAccount":
				PersonalSavingsAccount psa = new PersonalSavingsAccount(pinNumber, accountNumber, new BigDecimal(0), user, LocalDate.now(), null, status);
				userMap.get(user.getLicenseNumber()).addAccount(psa);
			case "StudentSavingsAccount":
				StudentSavingsAccount ssa = new StudentSavingsAccount(pinNumber, accountNumber, new BigDecimal(0), user, LocalDate.now(), null, status);
				userMap.get(user.getLicenseNumber()).addAccount(ssa);
		}
	}
	
	
	/**
	 * @param licenseNumber
	 * @param accountNumber
	 * @return
	 */
	public Account getAccount(String licenseNumber, int accountNumber) {
		return userMap.get(licenseNumber).getAccount(accountNumber);
	}
	
	
	/**
	 * @param licenseNumber
	 * @param accountNumber
	 */
	public void closeAccount(String licenseNumber, int accountNumber) {
		User user = userMap.get(licenseNumber);
		user.getAccount(accountNumber).setStatus(Status.CLOSED);
	}
	
	
	/**
	 * @param licenseNumber
	 * @return
	 */
	public boolean doesUserExist(String licenseNumber) {
		boolean userExists = false;
		for(User user : userMap.values()) {
			if(user.getLicenseNumber() == licenseNumber)
				userExists = true;
		}
		return userExists;
	}
	

	/**
	 * @param account
	 * @return
	 */
	public boolean doesAccountExist(int accountNumber) {
		boolean accountExists = false;
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountNumber() == accountNumber)
				accountExists = true;
		}
		return accountExists;
	}
	
	
	/**
	 * @param t
	 */
	public void printReciept(Transaction t) {
		System.out.println("Remaining balance: ");
	}
	
	
	/**
	 * 
	 */
	public void saveData() {
		BankingInformationDAO.recordUsers(userList);
		BankingInformationDAO.recordAccounts(accounts);
		BankingInformationDAO.recordTransactions(transactions);
	}
}
