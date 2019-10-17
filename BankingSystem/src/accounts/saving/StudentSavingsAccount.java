package accounts.saving;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import exceptions.InvalidAgeException;
import users.User;


public class StudentSavingsAccount extends SavingsAccount {

	private final BigDecimal MONTHLY_FEE = new BigDecimal(5);
	private final BigDecimal WAIVE_AMOUNT = new BigDecimal(150);

	public StudentSavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
			LocalDate dateCreated, LocalDate dateClosed, Status status) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status);
		
		try {
			verifyAge();
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
	
	public void monthlyFee() {
		if(accountBalance.compareTo(WAIVE_AMOUNT) == -1) {
			accountBalance.add(MONTHLY_FEE);
		}
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
