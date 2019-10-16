package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class BusinessSavingsAccount extends SavingsAccount {
	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal TRANSACTION_FEE = new BigDecimal(0.25);

	public BusinessSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
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

	public BigDecimal get_TRANSACTION_FEE() {
		return this.TRANSACTION_FEE;
	}

	
}
