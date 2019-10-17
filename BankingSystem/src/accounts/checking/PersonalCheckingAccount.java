package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import users.User;

public class PersonalCheckingAccount extends CheckingAccount {

	protected final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(-1500);
	protected final BigDecimal MONTHLY_FEE = new BigDecimal(10);
	protected final BigDecimal WAIVE_AMOUNT = new BigDecimal(1500);
	protected BigDecimal directDepositAmount;

	public PersonalCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance,
			User primaryAccountOwner, LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status, overdrawn);
	}

	
	@Override
	public void monthlyFee() {
		if(accountBalance.compareTo(WAIVE_AMOUNT) == -1 || directDepositAmount.compareTo(new BigDecimal(500)) == -1 ||
				getPrimaryAccountOwner().getCombinedAverageOfAccounts().compareTo(new BigDecimal(5000)) == -1) {
			accountBalance.add(MONTHLY_FEE);
		}
	}
	
	public void setUpDirectDeposit(BigDecimal amount) {
		this.directDepositAmount = amount;
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
}
