package accounts.checking;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;

import accounts.Account;
import users.User;

/**
 * @author Helana Brock
 * Checking account abstract class.
 */
public abstract class CheckingAccount extends Account {
	protected boolean overdrawn = false;
	public CheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance, String primaryAccountOwnerLicenseNumber, LocalDate dateCreated) {
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwnerLicenseNumber, dateCreated);
	}
	
	@Override
	public BigDecimal deposit(BigDecimal amountToDeposit) {
		if(amountToDeposit.compareTo(new BigDecimal(0)) != -1) {
			System.out.println("The amount of $" + amountToDeposit + " has been deposited into your checking account.");
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
	
	@Override
	public void showAccountInfo() {
		System.out.println("This is " + this.primaryAccountOwnerLicenseNumber + "'s checking account!\n" + "Current balance: " 
				+ NumberFormat.getCurrencyInstance().format(this.accountBalance) + "\nAccount number: " + 
				this.accountNumber + "\nPin number (do not share!): " + this.pinNumber);
		if(accountBalance.equals(new BigDecimal(0))) {
			System.out.println("Status: You do not have any money in your account! Consider making a deposit to take advantage of your account here.");
		} else if(overdrawn) {
			System.out.println("Status: Your account is overdraft. To prevent future charges to your account bring your account out of the negative.");
		} else {
			System.out.println("Status: Your account is in good standing. Remember to keep your account positive to prevent overdraft fees.");
		}
		
	}
}
