package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class BusinessCheckingAccount extends CheckingAccount {

	protected final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(-7500);
	protected final BigDecimal MONTHLY_FEE = new BigDecimal(25);
	protected final BigDecimal WAIVE_AMOUNT = new BigDecimal(2500);
	protected final BigDecimal TRANSACTION_FEE = new BigDecimal(0.50);
	protected final BigDecimal DEPOSIT_FEE = new BigDecimal(0.30);

	public BusinessCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance,
			User primaryAccountOwner, LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status, overdrawn);
	}

	@Override
	public void monthlyFee() {
		if(accountBalance.compareTo(WAIVE_AMOUNT) == -1) {
			accountBalance.add(MONTHLY_FEE);
		}
	}
	
	public BigDecimal getOverDrawLimit() {
		return this.OVER_DRAW_LIMIT;
	}
	
	public BigDecimal getServiceFee() {
		return this.MONTHLY_FEE;
	}

	public BigDecimal getWaiveAmount() {
		return this.WAIVE_AMOUNT;
	}

	public BigDecimal getTransactionFee() {
		return this.TRANSACTION_FEE;
	}

	public BigDecimal getDopsitFee() {
		return this.DEPOSIT_FEE;
	}
}
