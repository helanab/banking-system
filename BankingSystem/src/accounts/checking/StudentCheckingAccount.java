package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import accounts.Account;
import exceptions.InvalidAgeException;
import users.User;

public class StudentCheckingAccount extends CheckingAccount {

	private final BigDecimal OVER_DRAFT_FEE = new BigDecimal(35);
	private final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(500);

	public StudentCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
			LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status, overdrawn);
		
		try {
			this.verifyAge();
		} catch (InvalidAgeException e) {
			e.printStackTrace();
		}
	}

	public void verifyAge() throws InvalidAgeException {
		Period age = Period.between(primaryAccountOwner.getBirthDate(), LocalDate.now());
		if(age.getYears() > 12 && age.getYears() <= 17) {
			System.out.println("User is underrage, try again with an authorized user.");
		} else {
			throw new InvalidAgeException("User is not with the age constraints.");
		}
	}
	
	public BigDecimal getOverDraftFee() {
		return this.OVER_DRAFT_FEE;
	}
	
	public BigDecimal getOverDrawLimit() {
		return this.OVER_DRAW_LIMIT;
	}

	@Override
	public void monthlyFee() {
		System.out.println("No monthly fee.");
	}
}
