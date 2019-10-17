package accounts.saving;

import java.math.BigDecimal;

/**
 * Interface for bacnk accounts that earn interest to ensure that they have methods to supply information regarding that.
 * 
 * @author Helana Brock
 */
public interface InterestGaining {
	
	
	/**
	 * A method that calculates all the interest an account has accrued over its lifetime
	 * 
	 * @return BigDecimal amount of all interest a given account have earned over its life span
	 */
	public BigDecimal getInterestAccrued();
	
	
	/**
	 * A method that calculates all the interest an account will accrue over a certain time span.
	 * 
	 * @param timeInterval: use to calculate how much you will earn in interest over this time period.
	 * @return BigDecimal amount of how much an account will earn in interest over a given time interval.
	 */
	public BigDecimal calculateCompoundInterest(int timeInterval);
}
