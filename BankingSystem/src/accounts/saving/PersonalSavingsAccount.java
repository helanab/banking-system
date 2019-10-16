package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class PersonalSavingsAccount extends SavingsAccount {
	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal WAIVE_AMOUNT = new BigDecimal(300);

	public PersonalSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwnerLicenseNumber, dateCreated);
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

	
}
