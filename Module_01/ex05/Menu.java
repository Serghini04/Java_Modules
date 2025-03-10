/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Menu.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:57:41 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:57:42 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;
import java.util.UUID;

enum Modes
{
	Dev,
	Production	
}

public class Menu
{
	Modes mode;
	TransactionsService transactionsService;

	private final int	addUser = 1;
	private final int	viewUserbalances = 2;
	private final int	performTransfer = 3;
	private final int	viewTransactionsUser = 4;
	private final int	removeTransfer = 5;
	private final int	transferValidity = 6;
	private final int	finishExecution = 7;

	public Menu(String input)
	{
		if (input.equals("--profile=dev"))
			mode = Modes.Dev;
		else
			mode = Modes.Production;
		transactionsService = new TransactionsService();
	}

	public void	printMenu()
	{
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		if (mode.equals(Modes.Dev))
		{
			System.out.println("5. Dev - remove a transfer by ID");
			System.out.println("6. Dev - check transfer validity");
			System.out.println("7. Finish execution");
		}
		else
			System.out.println("5. Finish execution");
	}


	private void addUser()
	{
		do
		{
			try
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a user name and a balance :");
				if (scanner.hasNextLine())
				{
					String[] input = scanner.nextLine().trim().split("\\s+");
					if (input.length != 2)
						throw new IllegalArgumentException();
					String name = input[0];
					int balance = Integer.parseInt(input[1]);
					transactionsService.addUser(name, balance);
					break ;
				}
				else {
					System.err.println("* returning to the menu ↩ ");
					break ;
				}
				
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}

		} while (true);
	}

	private void	getUserBalance()
	{
		do
		{
			try
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a user ID :");
				if (!scanner.hasNextLine())
				{
					System.err.println("* returning to the menu ↩ ");
					break ;
				}
				int id = Integer.parseInt(scanner.nextLine().trim());
				transactionsService.getUserBalance(id);
				break ;
				
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}

		} while (true);
	}

	private void	transferAmount()
	{
		do
		{
			try
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a sender ID, a recipient ID, and a transfer amount :");
				if (!scanner.hasNextLine())
				{
					System.err.println("* returning to the menu ↩ ");
					break ;
				}
				String[] input = scanner.nextLine().trim().split("\\s+");
				if (input.length != 3)
					throw new IllegalArgumentException();
				int senderId = Integer.parseInt(input[0]);
				int recipienterId = Integer.parseInt(input[1]);
				int amount = Integer.parseInt(input[2]);
				transactionsService.transferAmount(senderId, recipienterId, amount);
				System.out.println("The transfer is completed");
				break ;
				
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}

		} while (true);
	}
	
	private	void	findUserTransactions()
	{
		do
		{
			try
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a user ID :");
				if (!scanner.hasNextLine())
				{
					System.err.println("* returning to the menu ↩ ");
					break ;
				}
				int id = Integer.parseInt(scanner.nextLine().trim());
				Transaction[] tr = transactionsService.findUserTransactions(id);
				if (tr.length == 0)
					System.out.println("Empty Transactions.");
				for (int i = 0; i < tr.length; i++)
					System.out.println("To (" + tr[i].getRecipient().getName() + " = " + tr[i].getRecipient().getId() + ") " + tr[i].getAmount() + " with id = " + tr[i].getId());
				break ;
			} catch (Exception e) {
				System.err.println("Invalid Input");
			}

		} while (true);
	}

	private void removedUserTransaction()
	{
		do
		{
			try
			{
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter a user ID and a transfer ID :");
				if (!scanner.hasNextLine())
				{
					System.err.println("* returning to the menu ↩ ");
					break ;
				}
				String[] input = scanner.nextLine().trim().split("\\s+");
				if (input.length != 2)
					throw new IllegalArgumentException();
				int id = Integer.parseInt(input[0]);
				UUID transactionId = UUID.fromString(input[1]);
				System.err.println("Id (" +id+ ")==>(" + transactionId.toString()+ ").");
				transactionsService.removedUserTransaction(id, transactionId);
				break ;
				
			} catch (Exception e) {
				System.err.println("Invalid Input1");
				if (e.getMessage() != null);
				System.err.println(e.getMessage());
			}

		} while (true);
		System.err.println("Fuck");
	}

	public void transferValidity()
	{
		Transaction[] trs = transactionsService.getUnpairedTransactions();
		if (trs.length == 0)
			System.out.println("Empty Unpaired Transactions");
		for (int i = 0; i < trs.length; i++)
		{
			System.out.println(trs[i].getRecipient().getName() + "(id = " + trs[i].getRecipient().getId() + ") as an unacknowledged transfer id = " + trs[i].getId() + " from " + trs[i].getSender().getName() + "(id = " + trs[i].getSender().getId() + ") for " + trs[i].getAmount() + ".");
		}
	}

	public void applyInput(int input)
	{
		switch (input)
		{
			case addUser :
				addUser();
				break ;
			case viewUserbalances :
				getUserBalance();
				break ;
			case performTransfer :
				transferAmount();
				break ;
			case viewTransactionsUser :
				findUserTransactions();
				break ;
			case removeTransfer :
				removedUserTransaction();
				break ;
			case transferValidity :
				transferValidity();
				break;
			case finishExecution :
				System.err.println("Exiting...");
				System.exit(0);
			default:
				break;
		}
	}

	public void readInput()
	{
		int input;
		
		do
		{
			// try
			// {
				Scanner scanner = new Scanner(System.in);
				printMenu();
				if (!scanner.hasNextLine())
					break;
				else
				{
					input = Integer.parseInt(scanner.nextLine().trim());
					if (!((mode.equals(Modes.Dev) && input >= 1 && input <= 7) || (mode.equals(Modes.Production) && input >= 1 && input <= 5)))
						throw new IllegalArgumentException();
					applyInput(input);
				}
			// }
			// catch(Exception e)
			// {
			// 	System.err.println("Invalid Input");
			// }
			System.out.println("---------------------------------------------------------");
		} while (true);

		System.err.println("Exiting...");
	}
}