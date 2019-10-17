package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class BusinessSavingsAccount extends SavingsAccount {

	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal WAIVE_AMOUNT = new BigDecimal(3500);
	private final BigDecimal TRANSACTION_FEE = new BigDecimal(0.25);

	public BusinessSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
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

	public BigDecimal get_TRANSACTION_FEE() {
		return this.TRANSACTION_FEE;
	}

	@Override
	public void monthlyFee() {
		if(accountBalance.compareTo(WAIVE_AMOUNT) == -1) {
			accountBalance.add(MONTHLY_FEE);
		}
	}
}
