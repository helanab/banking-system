package accounts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import banking.Transaction;
import exceptions.InvalidMonetaryAmountException;
import exceptions.InsufficientFundsException;
import users.User;

/**
 * Abstract that provides all the fields for a basic bank account (with setters and getters), a deposit function, and a basic verify function
 * 
 * @author Helana Brock
 */
public abstract class Account {
	public enum Status {
		ACTIVE,
		FROZEN,
		CLOSED
	}
	
	protected int pinNumber, accountNumber;
	protected BigDecimal accountBalance;
	protected User primaryAccountOwner;
	protected List<User> authorizedUsers; // Mainly for those authorized to use a given account
	protected List<Transaction> transactions; // To save data from bank transactions
	protected LocalDate dateCreated;
	protected LocalDate dateClosed;
	protected Status status;
	protected BigDecimal amountSpent; // The total amount that's been withdrawn from date created to present
	
	
	/**
	 * @param pinNumber: User given code to use at ATM's or for other bank transactions
	 * @param accountNumber: The unique code to a specific instance of an account
	 * @param accountBalance: How money money is in the account (can be negative for checking accounts)
	 * @param primaryAccountOwner: The main person who controls the account
	 * @param dateCreated
	 * @param dateClosed
	 * @param status
	 */
	public Account(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner, 
			LocalDate dateCreated, LocalDate dateClosed, Status status) {
		this.pinNumber = pinNumber;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.primaryAccountOwner = primaryAccountOwner;
		this.dateCreated = dateCreated;
		this.dateClosed = dateClosed;
		this.status = status;
	}
	
	
	/**
	 * The user can deposit as much money as they have/want, however, it cannot be negative
	 * 
	 * @param amountToDeposit: The amount we want to add to accountBalance.
	 */
	public BigDecimal deposit(BigDecimal amount) {
		BigDecimal amountToDeposit = verify(amount);
		accountBalance.add(amountToDeposit);
		return accountBalance;
	}
	
	
	/**
	 * The user can withdraw only up until what the given account allows; there are different implementations
	 * 
	 * @param amountToWithdraw: The amount we want to subtract from accountBalance.
	 */
	public abstract BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException;
	
	
	/**
	 * Verifies a given monetary amount to make sure there are no letters, negative signs, etc.
	 * 
	 * @param amount: the amount of money we want to withdraw.
	 * @return approved: whether or not we can withdraw the amount.
	 */
	public BigDecimal verify(BigDecimal amount) throws InvalidMonetaryAmountException {
		if (amount.toString().matches("\\d*\\.?\\d+")) {
		  return amount;
		 }
		throw new InvalidMonetaryAmountException("Amount invalid");
	}
	
	
	/**
	 * Determines whether or not a monthly fee needs to be applied based on certain criteria.
	 */
	public abstract void monthlyFee();
	
	
	public void addAuthorizedUser(User user) {
		this.authorizedUsers.add(user);
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public User getPrimaryAccountOwner() {
		return primaryAccountOwner;
	}

	public void setPrimaryAccountOwner(User primaryAccountOwner) {
		this.primaryAccountOwner = primaryAccountOwner;
	}

	public List<User> getAuthorizedUsers() {
		return authorizedUsers;
	}

	public void setAuthorizedUsers(List<User> authorizedUsers) {
		this.authorizedUsers = authorizedUsers;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(LocalDate dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BigDecimal getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(BigDecimal amountSpent) {
		this.amountSpent = amountSpent;
	}

	@Override
	public String toString() {
		return "Account_v2 [pinNumber=" + pinNumber + ", accountNumber=" + accountNumber + ", accountBalance="
				+ accountBalance + ", primaryAccountOwner=" + primaryAccountOwner + ", authorizedUsers="
				+ authorizedUsers + ", transactions=" + transactions + ", dateCreated=" + dateCreated + ", dateClosed="
				+ dateClosed + ", status=" + status + ", amountSpent=" + amountSpent + "]";
	}
}
