package accounts;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import banking.Transaction;
import exceptions.InvalidMonetaryAmountException;
import exceptions.InsufficientFundsException;
import users.User;

/**
 * @author Helana Brock
 *
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
	protected List<User> authorizedUsers;
	protected List<Transaction> transactions;
	protected LocalDate dateCreated;
	protected LocalDate dateClosed;
	protected Status status;
	protected BigDecimal amountSpent;
	
	
	/**
	 * @param pinNumber
	 * @param accountNumber
	 * @param accountBalance
	 * @param primaryAccountOwner
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
	 * 
	 * @param amountToDeposit: The amount we want to add to accountBalance.
	 */
	public BigDecimal deposit(BigDecimal amount) {
		BigDecimal amountToDeposit = verify(amount);
		accountBalance.add(amountToDeposit);
		return accountBalance;
	}
	
	
	/**
	 * 
	 * @param amountToWithdraw: The amount we want to subtract from accountBalance.
	 */
	public abstract BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException;
	
	
	/**
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
