package accounts.saving;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;

import accounts.Account;
import users.User;

/**
 * Savings account class. Cannot be over-drawn, and it has compound interest.
 * 
 * @author Helana Brock
 */
public abstract class SavingsAccount extends Account implements InterestGaining {
	final double COMPOUND_INTEREST_RATE = 0.01;

	/**
	 * @param pinNumber // Unique number user has chosen.
	 * @param accountNumber // Savings accounts always start with 1
	 * @param accountBalance
	 * @param accountOwner
	 * @param dateCreated
	 * @param dateClosed
	 * @param status
	 */
	public SavingsAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwnerLicenseNumber, dateCreated);
	}
	
	@Override
	public BigDecimal deposit(BigDecimal amountToDeposit) {
		if(amountToDeposit.compareTo(new BigDecimal(0)) != -1) {
			System.out.println("The amount of $" + amountToDeposit + " has been deposited into your savings account.");
			this.accountBalance = accountBalance.add(amountToDeposit);
		} else {
			System.out.println("You cannot deposit a negative number or 0.");
		}
		return this.accountBalance;
	}
	
	@Override
	public BigDecimal withdraw(BigDecimal amountToWithdraw) {
		boolean withdrawApproved = verify(amountToWithdraw);
		
		if(withdrawApproved) {
			this.accountBalance = accountBalance.subtract(amountToWithdraw);
		}
		return this.accountBalance;
	}
	
	/** 
	 * NO PARAMS. Does not take in any numbers, just takes the variable values as they are.
	 * 
	 * @return This method returns the compound interest earned per day with an annual interest 
	 * rate of 1% using BigDecimal.
	 */
	public BigDecimal calculateCompoundInterest() {
		BigDecimal num = new BigDecimal(Math.pow((1+(COMPOUND_INTEREST_RATE/365)), 1)-1);
		return accountBalance.multiply(num);
	}

	@Override
	public void showAccountInfo() {
		System.out.println("This is " + this.primaryAccountOwnerLicenseNumber + "'s savings account!\n" + "Current balance: " 
				+ NumberFormat.getCurrencyInstance().format(this.accountBalance) + "\nAccount number: " + 
				this.accountNumber + "\nPin number (do not share!): " + this.pinNumber);
		if(accountBalance.equals(new BigDecimal(0))) {
			System.out.println("Status: You do not have any money in your account! Consider making a deposit to take advantage of your account here.");
		} else {
			System.out.println("Status: Your account is in good standing. Remember to keep your account positive to prevent overdraft fees.");
		}
		
	}

	@Override
	public boolean verify(BigDecimal amount) {
		boolean approved = false;
		if(amount.compareTo(new BigDecimal(0)) == -1) {
			System.out.println("You cannot withdraw negative amounts or 0.");
		} else if(accountBalance.compareTo(amount) == -1) {
			System.out.println("You do not have enough in your account to withdraw this much.");
		} else {
			System.out.println("The amount of $" + amount + " has been withdrawn from your savings account.");
			approved = true;
		}
		return approved;
	}
}
