package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class StudentCheckingAccount extends CheckingAccount {
	private final BigDecimal OVER_DRAFT_FEE = new BigDecimal(35);
	private final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(500);

	public StudentCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwnerLicenseNumber, dateCreated);
	}

	public BigDecimal getOverDraftFee() {
		return this.OVER_DRAFT_FEE;
	}
	
	public BigDecimal getOverDrawLimit() {
		return this.OVER_DRAW_LIMIT;
	}

}
