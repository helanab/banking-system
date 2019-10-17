package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import accounts.Account.Status;
import users.User;


public class PersonalSavingsAccount extends SavingsAccount {

	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal WAIVE_AMOUNT = new BigDecimal(300);
	
	public PersonalSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
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
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal get_MONTHLY_FEE() {
		return this.MONTHLY_FEE;
	}

	public BigDecimal get_WAIVE_AMOUNT() {
		return this.WAIVE_AMOUNT;
	}

	@Override
	public void monthlyFee() {
		if(accountBalance.compareTo(WAIVE_AMOUNT) == -1) {
			accountBalance.add(MONTHLY_FEE);
		}
	}

	
}
