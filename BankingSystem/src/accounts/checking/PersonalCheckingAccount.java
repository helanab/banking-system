package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class PersonalCheckingAccount extends CheckingAccount {
	protected final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(-1500);
	protected final BigDecimal SERVICE_FEE = new BigDecimal(10);
	protected final BigDecimal WAIVE_AMOUNT = new BigDecimal(1500);

	public PersonalCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwnerLicenseNumber, dateCreated);
	}

	public BigDecimal getOverDrawLimit() {
		return this.OVER_DRAW_LIMIT;
		
	}

	public BigDecimal getServiceFee() {
		return this.SERVICE_FEE;
	}

	public BigDecimal getWaiveAmount() {
		return this.WAIVE_AMOUNT;
	}
	
}
