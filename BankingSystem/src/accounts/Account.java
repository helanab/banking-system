package accounts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import banking.Transaction;
import users.User;

/**
 * Abstract account class for Checking and Savings. Provides all the setters and getters for the fields the two classes
 * will have alike. Also provides some methods that both classes will have but will each have a different implementation.
 * 
 * @author Helana Brock
 */
public abstract class Account {
	public enum Status {
		ACTIVE,
		FROZEN,
		CLOSED
	}
	
	// Basic account information.
	protected int pinNumber, accountNumber;
	protected BigDecimal accountBalance;
	protected User primaryAccountOwner;
	protected String primaryAccountOwnerLicenseNumber;
	protected List<User> authorizedUsers;
	protected List<Transaction> transactions;
	protected LocalDate dateCreated;
	protected LocalDate dateClosed;
	protected Status status;
	
	public Account(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		this.pinNumber = pinNumber;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.primaryAccountOwnerLicenseNumber = primaryAccountOwnerLicenseNumber;
		this.dateCreated = dateCreated;
	}

	/**
	 * All verification for the given amount should be done inside this method, as we only need to check and make sure
	 * the amount is positive.
	 * 
	 * @param amountToDeposit: The amount we want to add to accountBalance.
	 */
	public abstract BigDecimal deposit(BigDecimal amountToDeposit);
	
	/**
	 * Does not verify the amount within itself, rather, uses verify(double amount) to do it.
	 * 
	 * @param amountToWithdraw: The amount we want to subtract from accountBalance.
	 */
	public abstract BigDecimal withdraw(BigDecimal amountToWithdraw);
	
	
	/**
	 * This will print all of your account information to the screen. Does not taking any params or return anything.
	 */
	public abstract void showAccountInfo();
	
	/**
	 * This will print appropriate responses based on whether or not the amount can be withdrawn.\
	 * 
	 * @param amount: the amount of money we want to withdraw.
	 * @return approved: whether or not we can withdraw the amount.
	 * 
	 */
	public boolean verify(BigDecimal amount) {return false;};
	
	public void printAccountInformation() {
		System.out.println("Success");
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public void addAuthorizedUser(User u) {
		authorizedUsers.add(u);
	}
	
	public int getPinNumber() {
		return this.pinNumber;
	}
	
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public BigDecimal getAccountBalance() {
		return this.accountBalance;
	}
	
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public User getPrimaryAccountOwner() {
		return this.primaryAccountOwner;
	}
	
	public void setPrimaryAccountOwner(User u) {
		this.primaryAccountOwner = u;
	}
	
	public String getPrimaryAccountOwnerLicenseNumber() {
		return this.primaryAccountOwnerLicenseNumber;
	}

	public void setPrimaryAccountOwner(String primaryAccountOwnerLicenseNumber) {
		this.primaryAccountOwnerLicenseNumber = primaryAccountOwnerLicenseNumber;
	}

	public List<User> getAuthorizedUsers() {
		return this.authorizedUsers;
	}

	public void setAuthorizedUsers(List<User> authorizedUsers) {
		this.authorizedUsers = authorizedUsers;
	}

	public LocalDate getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateClosed() {
		return this.dateClosed;
	}

	public void setDateClosed(LocalDate dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
