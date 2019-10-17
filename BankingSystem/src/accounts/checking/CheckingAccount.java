package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import exceptions.InsufficientFundsException;
import users.User;

/**
 * Basic checking account. It can be overdrawn up to a certain point and have more fees than a savings in most cases
 * 
 * @author Helana Brock
 */
public abstract class CheckingAccount extends Account {
	
	protected static final BigDecimal OVER_DRAFT_FEE = new BigDecimal(35);
	protected boolean overdrawn;
	protected boolean optedIn;
	
	public CheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
			LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn, boolean optedIn) {
	
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status);
	
		this.overdrawn = overdrawn;
		this.optedIn = optedIn;
	}
	
	@Override
	public BigDecimal deposit(BigDecimal amount) {
		return super.deposit(amount);
	}
	
	
	@Override
	public abstract BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException;	
	
	
	@Override
	public void monthlyFee() {};
	
	
	public boolean isOverdrawn() {
		return overdrawn;
	}

	public void setOverdrawn(boolean overdrawn) {
		this.overdrawn = overdrawn;
	}
	
	public boolean isOptedIn() {
		return this.optedIn;
	}
	
	public void optIn() {
		this.optedIn = true;
	}
	
	public void optOut() {
		this.optedIn = false;
	}
}
