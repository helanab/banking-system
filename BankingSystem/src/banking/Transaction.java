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
	private Teller teller;
	private int accountNumber;
	
	public Transaction(String type, BigDecimal amount, User user, Teller teller, int accountNumber) {
		this.type = type;
		this.date = LocalDate.now();
		this.time = LocalTime.now();
		this.amount = amount;
		this.user = user;
		this.teller = teller;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Teller getTeller() {
		return teller;
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
