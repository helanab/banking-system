package accounts.saving;

import java.math.BigDecimal;

public interface InterestGaining {
	public BigDecimal getInterestAccrued();
	public BigDecimal calculateCompoundInterest(int timeInterval);
}
