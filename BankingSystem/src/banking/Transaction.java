package banking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import users.Teller;
import users.User;

public class Transaction {
	private String type;
	private LocalDate date;
	private LocalTime time;
	private BigDecimal amount;
	private User user;
	private String userLicenseNumber;
	private Teller teller;
	private String tellerLicenseNumber;
	private int accountNumber;

	public Transaction(String type, BigDecimal amount, String userLicenseNumber, String tellerLicenseNumber, int accountNumber, LocalDate date, LocalTime time) {
		this.type = type;
		this.date = date;
		this.time = time;
		this.amount = amount;
		this.userLicenseNumber = userLicenseNumber;
		this.tellerLicenseNumber = tellerLicenseNumber;
		this.accountNumber = accountNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUserLicenseNumber() {
		return userLicenseNumber;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTellerLicenseNumber() {
		return tellerLicenseNumber;
	}

	public void setTeller(Teller teller) {
		this.teller = teller;
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber() {
		this.accountNumber = accountNumber;
	}

}
