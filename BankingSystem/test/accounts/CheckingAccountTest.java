package accounts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import accounts.checking.CheckingAccount;

import java.math.BigDecimal;

public class CheckingAccountTest {
	
	@Test
	public void withdraw_amount180000_result3920() {
		assertEquals(new BigDecimal(3920), new CheckingAccount(1234, 731924, new BigDecimal(183920), "Helana Brock").withdraw(new BigDecimal(180000)));
	}
	
	@Test
	public void withdraw_amount200_resultnegative100() {
		assertEquals(new BigDecimal(-100), new CheckingAccount(1234, 731924, new BigDecimal(100), "Helana Brock").withdraw(new BigDecimal(200)));
	}
	
	@Test
	public void deposit_amount920_result3920() {
		assertEquals(new BigDecimal(3920), new CheckingAccount(1234, 731924, new BigDecimal(3000), "Helana Brock").deposit(new BigDecimal(920)));
	}
	
	@Test
	public void withdraw_amount5000_resultsnotnegative100() {
		assertNotEquals(new BigDecimal(-100), new CheckingAccount(1234, 731924, new BigDecimal(100), "Helana Brock").withdraw(new BigDecimal(5000)));
	}
	
	@Test
	public void deposit_amountnegative100_resultnotnegative100() {
		assertNotEquals(new BigDecimal(-100), new CheckingAccount(1234, 731924, new BigDecimal(0), "Helana Brock").deposit(new BigDecimal(-100)));
	}
}
