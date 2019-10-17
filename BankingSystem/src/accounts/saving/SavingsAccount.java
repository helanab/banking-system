package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account;
import exceptions.InsufficientFundsException;
import users.User;

public abstract class SavingsAccount extends Account implements InterestGaining {
	
	final double COMPOUND_INTEREST_RATE = 0.06;

	public SavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
			LocalDate dateCreated, LocalDate dateClosed, Status status) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status);
	}

	
	@Override
	public BigDecimal getInterestAccrued() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public BigDecimal calculateCompoundInterest(int timeInterval) {
		return accountBalance.multiply(new BigDecimal((Math.pow((1 + COMPOUND_INTEREST_RATE/365), timeInterval)) - 1));
	}
	

	@Override
	public BigDecimal deposit(BigDecimal amount) {
		return super.deposit(amount);
	}
	

	@Override
	public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException {

		BigDecimal amountToWithdraw = verify(amount);
		
		if(amountToWithdraw.subtract(accountBalance).compareTo(new BigDecimal(0)) == -1 ) {
			
			throw new InsufficientFundsException("Insufficient funds; transaction denied.");
			
		} else {
			accountBalance.subtract(amountToWithdraw);
		}
		return null;
	}

	
	@Override
	public void monthlyFee() {};
}
