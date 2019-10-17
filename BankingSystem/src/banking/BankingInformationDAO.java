package banking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import accounts.Account;
import accounts.Account.Status;
import accounts.checking.BusinessCheckingAccount;
import accounts.checking.PersonalCheckingAccount;
import accounts.checking.StudentCheckingAccount;
import accounts.saving.BusinessSavingsAccount;
import accounts.saving.PersonalSavingsAccount;
import accounts.saving.StudentSavingsAccount;
import users.Address;
import users.Teller;
import users.User;

/**
 * @author Helana Brock
 *
 */
class BankingInformationDAO {
	List<User> users;
	List<Account> accounts;
	List<Transaction> transactions;

	/**
	 * 
	 */
	private BankingInformationDAO() {}

	
	/**
	 * @param user
	 */
	public static void recordNewUser(User user) {
		
		try(FileWriter fw = new FileWriter("users.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {
				out.println(user.getFirstName() + "," + user.getLastName() + "," + user.getBirthDate() + "," + user.getLicenseNumber() + "," + user.getOccupation() + ","
							+ user.getAddress().getStreetAddress() + "," + user.getAddress().getCity() + "," + user.getAddress().getState() + "," + user.getAddress().getZipCode());
		} catch (IOException e) {
	    System.out.println("File doesn't exist.");
		}
	}
	
	
	/**
	 * @param account
	 */
	public static void recordNewAccount(Account account) {
		LocalDate dateClosed = LocalDate.of(0000, 00, 00);
		
		if(account.getDateClosed() != null) 
			dateClosed = account.getDateClosed();
		
		try(FileWriter fw = new FileWriter("accounts.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {
				out.println(account.getClass().getSimpleName() + "," + account.getPinNumber() + "," + account.getAccountNumber() + "," + account.getAccountBalance() + ","
							+ account.getPrimaryAccountOwner().getLicenseNumber() + "," + account.getDateCreated() + "," + dateClosed + "," + account.getStatus());
		} catch (IOException e) {
	    System.out.println("File doesn't exist.");
		}
	}
	
	
	/**
	 * @param user
	 * @param account
	 * @param amountToWithdraw
	 * @param teller
	 * @return
	 */
	public static Transaction recordTransaction(User user, Account account, BigDecimal amountToWithdraw, Teller teller) {
		Transaction transaction = new Transaction(account.getClass().getSimpleName(), amountToWithdraw.toString(), user.getLicenseNumber(), teller.getLicenseNumber(), account.getAccountNumber(), LocalDate.now(), LocalTime.now());
		try(FileWriter fw = new FileWriter("transactions.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {
				out.println(transaction.getType() + "," + transaction.getAmount() + "," + transaction.getUserLicenseNumber() + "," + transaction.getTellerLicenseNumber() + ","
							+ transaction.getAccountNumber() + "," + transaction.getDate() + "," + transaction.getTime());
		} catch (IOException e) {
	    System.out.println("File doesn't exist.");
		}
		return transaction;
	}
	
	
	/**
	 * @param users
	 */
	public static void recordUsers(List<User> users) {
		try(FileWriter fw = new FileWriter("users.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {
				for(int i = 0; i < users.size(); i++) {
					out.println(users.get(i).getFirstName() + "," + users.get(i).getLastName() + "," + users.get(i).getBirthDate() + "," + users.get(i).getLicenseNumber() + "," + users.get(i).getOccupation() + ","
								+ users.get(i).getAddress().getStreetAddress() + "," + users.get(i).getAddress().getCity() + "," + users.get(i).getAddress().getState() + "," + users.get(i).getAddress().getZipCode());
		    }
		} catch (IOException e) {
	    System.out.println("File doesn't exist.");
		}
	}
	
	
	/**
	 * @param accounts
	 */
	public static void recordAccounts(List<Account> accounts) {
		try(FileWriter fw = new FileWriter("accounts.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {
				for(int i = 0; i < accounts.size(); i++) {
					LocalDate dateClosed = LocalDate.of(0000, 00, 00);
					
					if(accounts.get(i).getDateClosed() != null) 
						dateClosed = accounts.get(i).getDateClosed();
					
					out.println(accounts.get(i).getClass().getSimpleName() + "," + accounts.get(i).getPinNumber() + "," + accounts.get(i).getAccountNumber() + "," + accounts.get(i).getAccountBalance() + ","
								+ accounts.get(i).getPrimaryAccountOwner().getLicenseNumber() + "," + accounts.get(i).getDateCreated() + "," + dateClosed + "," + accounts.get(i).getStatus());
				}
		} catch (IOException e) {
	    System.out.println("File doesn't exist.");
		}
	}
	
	
	/**
	 * @param transactions
	 */
	public static void recordTransactions(List<Transaction> transactions) {
		try(FileWriter fw = new FileWriter("transactions.txt", true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter out = new PrintWriter(bw)) {
				for(int i = 0; i < transactions.size(); i++) {
					out.println(transactions.get(i).getType() + "," + transactions.get(i).getAmount() + "," + transactions.get(i).getUserLicenseNumber() + "," + transactions.get(i).getTellerLicenseNumber() + ","
											+ transactions.get(i).getAccountNumber() + "," + transactions.get(i).getDate() + "," + transactions.get(i).getTime());
				}
		} catch (IOException e) {
	    System.out.println("File doesn't exist.");
		}
	}
	
	
	/**
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<User> loadUsers(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile(); 
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
	
	
	/**
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static List<Account> loadAccounts(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile(); // create the file if it does not exist
		Scanner fileReader = new Scanner(file);
		List<Account> accounts = new ArrayList<Account>();
		
		while(fileReader.hasNextLine()) {
			String[] accountInfo = fileReader.nextLine().split(",");
			
			LocalDate dateClosed;
			if(accountInfo[5].contentEquals(new StringBuffer("000-00-00"))) {
				dateClosed = null;
			} else {
				dateClosed = LocalDate.parse(accountInfo[5]);
			}
			
			switch(accountInfo[0]) {
				case "BusinessCheckingAccount":
					BusinessCheckingAccount bca = new BusinessCheckingAccount(Integer.parseInt(accountInfo[1]), Integer.parseInt(accountInfo[2]), new BigDecimal(Integer.parseInt(accountInfo[3])), null, LocalDate.parse(accountInfo[5]), dateClosed, Enum.valueOf(Status.class, accountInfo[6]), Boolean.parseBoolean(accountInfo[7]));
					accounts.add(bca);
				case "PersonalCheckingAccount":
					PersonalCheckingAccount pca = new PersonalCheckingAccount(Integer.parseInt(accountInfo[1]), Integer.parseInt(accountInfo[2]), new BigDecimal(Integer.parseInt(accountInfo[3])), null, LocalDate.parse(accountInfo[5]), dateClosed, Enum.valueOf(Status.class, accountInfo[6]), Boolean.parseBoolean(accountInfo[7]));
					accounts.add(pca);
				case "StudentCheckingAccount":
					StudentCheckingAccount sca = new StudentCheckingAccount(Integer.parseInt(accountInfo[1]), Integer.parseInt(accountInfo[2]), new BigDecimal(Integer.parseInt(accountInfo[3])), null, LocalDate.parse(accountInfo[5]), dateClosed, Enum.valueOf(Status.class, accountInfo[6]), Boolean.parseBoolean(accountInfo[7]));
					accounts.add(sca);
				case "BusinessSavingsAccount":
					BusinessSavingsAccount bsa = new BusinessSavingsAccount(Integer.parseInt(accountInfo[1]), Integer.parseInt(accountInfo[2]), new BigDecimal(Integer.parseInt(accountInfo[3])), null, LocalDate.parse(accountInfo[5]), dateClosed, Enum.valueOf(Status.class, accountInfo[6]));
					accounts.add(bsa);
				case "PersonalSavingsAccount":
					PersonalSavingsAccount psa = new PersonalSavingsAccount(Integer.parseInt(accountInfo[1]), Integer.parseInt(accountInfo[2]), new BigDecimal(Integer.parseInt(accountInfo[3])), null, LocalDate.parse(accountInfo[5]), dateClosed, Enum.valueOf(Status.class, accountInfo[6]));
					accounts.add(psa);
				case "StudentSavingsAccount":
					StudentSavingsAccount ssa = new StudentSavingsAccount(Integer.parseInt(accountInfo[1]), Integer.parseInt(accountInfo[2]), new BigDecimal(Integer.parseInt(accountInfo[3])), null, LocalDate.parse(accountInfo[5]), dateClosed, Enum.valueOf(Status.class, accountInfo[6]));
					accounts.add(ssa);
			}
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
			Transaction transaction = new Transaction(transactionInfo[0], transactionInfo[1], transactionInfo[2], transactionInfo[3], Integer.parseInt(transactionInfo[4]), LocalDate.parse(transactionInfo[5]), LocalTime.parse(transactionInfo[6]));
			transactions.add(transaction);
		}
		fileReader.close();
		return transactions;
	}
}
