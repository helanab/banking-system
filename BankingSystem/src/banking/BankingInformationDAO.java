package banking;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import accounts.Account;
import users.Address;
import users.Teller;
import users.User;

class BankingInformationDAO {
	List<User> users;
	List<Account> accounts;
	List<Transaction> transactions;

	private BankingInformationDAO() {}

	public static void recordTransaction(User user, Account account, BigDecimal amount, Teller teller) {
		Transaction transaction = new Transaction(account.getClass().getSimpleName(), amount, user, teller, account.getAccountNumber());
	}

	public static List<User> loadUsers(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile(); // create the file if it does not exist
		Scanner fileReader = new Scanner(file);
		List<User> users = new ArrayList<User>();

		while(fileReader.hasNextLine()) {
			String[] userInformation = fileReader.nextLine().split(",");
			LocalDate ld = LocalDate.parse(userInformation[2]);
			Address address = new Address(userInformation[5], userInformation[6], userInformation[7], Integer.parseInt(userInformation[8]));
			User user = new User(userInformation[0], userInformation[1], ld, userInformation[3], userInformation[4], address);
			users.add(user);
		}
		fileReader.close();
		return users;
	}

	public static List<Account> loadAccounts(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile(); // create the file if it does not exist
		Scanner fileReader = new Scanner(file);
		List<Account> accounts = new ArrayList<Account>();

		while(fileReader.hasNextLine()) {
			String[] accountInfo = fileReader.nextLine().split(",");
//			Account account = new StudentCheckingAccount(accountInfo[0], accountInfo[1], Integer.parseInt(accountInfo[2]), Integer.parseInt(accountInfo[3]), null, accountInfo[5], null);
//			account.add(account);
		}
		fileReader.close();
		return accounts;
	}

	public static List<Transaction> loadTransactions(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile(); // create the file if it does not exist
		Scanner fileReader = new Scanner(file);
		List<Transaction> transactions = new ArrayList<Transaction>();

		while(fileReader.hasNextLine()) {
			String[] transactionInfo = fileReader.nextLine().split(",");
//			Account account = new StudentCheckingAccount(accountInfo[0], accountInfo[1], Integer.parseInt(accountInfo[2]), Integer.parseInt(accountInfo[3]), null, accountInfo[5], null);
//			account.add(account);
		}
		fileReader.close();
		return transactions;
	}

	public void saveData() {
		// this does a full save of all data in their respective files. only runs when the program is terminated.
	}

}
