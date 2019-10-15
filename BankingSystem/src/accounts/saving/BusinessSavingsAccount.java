package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class BusinessSavingsAccount extends SavingsAccount {
	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal TRANSACTION_FEE = new BigDecimal(0.25);

	public BusinessSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner, User authorizedUser, LocalDate dateCreated, LocalDate dateClosed, Status status) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, authorizedUser, dateCreated, dateClosed, status);
	}

	public BigDecimal get_MONTHLY_FEE() {
		return this.MONTHLY_FEE;
	}

	public BigDecimal get_TRANSACTION_FEE() {
		return this.TRANSACTION_FEE;
	}

	
}
