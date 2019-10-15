package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class StudentSavingsAccount extends SavingsAccount {
	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal WAIVE_AMOUNT = new BigDecimal(150);

	public StudentSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwnerLicenseNumber, dateCreated);
	}

	public BigDecimal get_MONTHLY_FEE() {
		return this.MONTHLY_FEE;
	}

	public BigDecimal get_WAIVE_AMOUNT() {
		return this.WAIVE_AMOUNT;
	}

	
}
