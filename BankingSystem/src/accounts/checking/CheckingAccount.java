package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import exceptions.InsufficientFundsException;
import users.User;

/**
 * @author Helana Brock
 *
 */
public abstract class CheckingAccount extends Account {
	
	private static final BigDecimal OVER_DRAFT_FEE = new BigDecimal(35);
	private static final BigDecimal OVER_DRAFT_LIMIT = null;
	protected boolean overdrawn;
	
	public CheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
			LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn) {
	
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status);
	
		this.overdrawn = overdrawn;
	}
	
	
	@Override
	public BigDecimal deposit(BigDecimal amount) {
		return super.deposit(amount);
	}
	
	
	@Override
	public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException {
		
		BigDecimal amountToWithdraw = verify(amount);
		
		if(accountBalance.subtract(amountToWithdraw).compareTo(new BigDecimal(0)) == -1) {
			accountBalance.subtract(amountToWithdraw);
			accountBalance.subtract(OVER_DRAFT_FEE);
			
		} else if(accountBalance.subtract(amountToWithdraw).compareTo(OVER_DRAFT_LIMIT) == -1) {
			
			throw new InsufficientFundsException("Insufficient funds; transaction denied.");
			
		} else {
			accountBalance.subtract(amountToWithdraw);
		}
		return amountToWithdraw;
	}
	
	
	@Override
	public void monthlyFee() {};
	
	
	public boolean isOverdrawn() {
		return overdrawn;
	}

	public void setOverdrawn(boolean overdrawn) {
		this.overdrawn = overdrawn;
	}
}
