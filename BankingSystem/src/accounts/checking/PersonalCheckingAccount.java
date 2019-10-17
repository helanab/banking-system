package accounts.checking;

import java.math.BigDecimal;
import java.time.LocalDate;

import exceptions.InsufficientFundsException;
import users.User;

public class PersonalCheckingAccount extends CheckingAccount {

	private final BigDecimal OVER_DRAW_LIMIT = new BigDecimal(-1500);
	private final BigDecimal MONTHLY_FEE = new BigDecimal(10);
	private final BigDecimal WAIVE_AMOUNT = new BigDecimal(1500);
	private final BigDecimal REWARD_LIMIT = new BigDecimal(3000);
	private boolean rewardsRedeemed;
	private BigDecimal directDepositAmount;

	public PersonalCheckingAccount(int pinNumber, int accountNumber, BigDecimal accountBalance,
			User primaryAccountOwner, LocalDate dateCreated, LocalDate dateClosed, Status status, boolean overdrawn, boolean optedIn) {
		
		super(pinNumber, accountNumber, accountBalance, primaryAccountOwner, dateCreated, dateClosed, status, overdrawn, optedIn);
	
		rewardsRedeemed = false;
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
		if(accountBalance.compareTo(WAIVE_AMOUNT) == -1 || directDepositAmount.compareTo(new BigDecimal(500)) == -1 ||
				getPrimaryAccountOwner().getCombinedAverageOfAccounts().compareTo(new BigDecimal(5000)) == -1) {
			accountBalance.add(MONTHLY_FEE);
		}
	}
	
	public BigDecimal checkRewardsBalance() {
		if(super.amountSpent.compareTo(REWARD_LIMIT) == 1) {
			return new BigDecimal(30);
		} else {
			return super.amountSpent.multiply(new BigDecimal(0.01));
		}
	}
	
	public void redeemRewards() {
		if(rewardsRedeemed = false) {
			BigDecimal rewardAmount = checkRewardsBalance();
			accountBalance.add(rewardAmount);
			rewardsRedeemed = true;
		} else {
			System.out.println("Rewards have already been redeemed!");
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
