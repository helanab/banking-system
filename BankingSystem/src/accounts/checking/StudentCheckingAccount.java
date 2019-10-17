package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import accounts.Account;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAgeException;
import users.User;

public class StudentCheckingAccount extends CheckingAccount {

	private final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(500);

	public StudentCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, User primaryAccountOwner,
			LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn, boolean optedIn) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status, overdrawn, optedIn);
		
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
			
		} else if (age.getYears() <= 12) {
			
			throw new InvalidAgeException("User is not within the age constraints.");
		}
	}
	
	@Override
	public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException {
		
		BigDecimal amountToWithdraw = verify(amount);
		
		int compareToZero = amountToWithdraw.subtract(accountBalance).compareTo(new BigDecimal(0));
		int compareToOverdrawLimit = amountToWithdraw.subtract(accountBalance).compareTo(OVER_DRAW_LIMIT);
		
		if(compareToZero == 1 || compareToZero == 0) {
			
			accountBalance.subtract(amountToWithdraw);
			System.out.println("The amount of $" + amountToWithdraw.toString() + " has been withdrawn from your student checking account.");
			super.amountSpent.add(amountToWithdraw);
			
		} else if(compareToZero == -1 && (compareToOverdrawLimit ==  1 || compareToOverdrawLimit == 0) && optedIn == true) {
			
			accountBalance.subtract(amountToWithdraw).subtract(OVER_DRAFT_FEE);
			System.out.println("The amount of $" + amountToWithdraw.toString() + " has been withdrawn, but an over draft fee has been charged." + 
			" If you wish to opt out of this program, please be sure to do so before your next purchase, or you will be at risk of being charged again.");
			super.amountSpent.add(amountToWithdraw);
			return amountToWithdraw.add(OVER_DRAFT_FEE);
			
		} else if(compareToZero == -1 && (compareToOverdrawLimit ==  1 || compareToOverdrawLimit == 0) && optedIn == false) {
			
			throw new InsufficientFundsException("Insufficient funds; transaction denied. If you use to opt in to allow future purchases without the " +
			"sufficient funds (at the cost of a small fee), remember to opt in before your next purchase, or you will be at risk of being charged again.");
			
		} else if(compareToOverdrawLimit == -1) {
			
			throw new InsufficientFundsException("Insufficient funds; transaction denied. You have currently reach your overdraw limit. To make more purchases" +
			" you need to pay off the remaining debt on your account. Any future attempts to make a purchase and your account will be flagged for review and may" +
			" be closed.");
			
		} else {
			
			System.out.println("Amount is invalid or something went wrong, please try again.");
			return null;
			
		}
		return amountToWithdraw;
	}
	
	@Override
	public void monthlyFee() {
		System.out.println("No monthly fee.");
	}
	
	public BigDecimal getOverDraftFee() {
		return this.OVER_DRAFT_FEE;
	}
	
	public BigDecimal getOverDrawLimit() {
		return this.OVER_DRAW_LIMIT;
	}
}
