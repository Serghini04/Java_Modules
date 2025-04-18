/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MacroShell.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:59:03 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:59:04 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.Scanner;
import java.io.File;

public class MacroShell
{
	private File path;

	public MacroShell(String arg)
	{
		if (!arg.startsWith("--current-folder="))
			throw new RuntimeException("Usage: java Program --current-folder=<Path>");
		this.path = new File(arg.substring("--current-folder=".length()));
		if (!this.path.exists() || !this.path.isDirectory())
			throw new RuntimeException("Usage: invalid Path");
	}

	private void	lsCmd()
	{
		File[] files = this.path.listFiles();

    	if (files != null) {
			for (File file : files) {
                System.out.printf("%-20s %10.2f KB\n", file.getName(), file.length() / 1024.0);
            }
        }
		else
            System.out.println("Failed to list files.");
	}

	private void cdCmd(String[] line) {
		File newDir = null;
	
		if (line.length == 1)
		{
			System.out.println("Usage: cd <FOLDER_NAME>");
			return ;
		}
		else if (line[1].equals("..")) {
			newDir = path.getParentFile();
			if (newDir == null) {
				System.err.println("cd: already at the root directory");
				return;
			}
		} 
		else
			newDir = new File(path, line[1]);

		if (newDir != null && newDir.exists() && newDir.isDirectory())
			path = newDir;
		else
			System.err.println("cd: no such file or directory");
	}

	private void mvCmd(String[] line)
	{
		if (line.length < 3) {
			System.err.println("mv: missing operand");
			return;
		}
	
		File source = new File(path, line[1]);
		File destination = new File(line[2]);
	
		if (!source.exists()) {
			System.err.println("mv: cannot move '" + line[1] + "': No such file or directory");
			return;
		}

		if (!destination.isAbsolute())
			destination = new File(path, line[2]);

		if (destination.isDirectory())
			destination = new File(destination, source.getName());

		if (!source.renameTo(destination))
			System.err.println("mv: failed to move '" + source.getName() + "'");
	}
	
	
	public void startReadInput()
	{
		Scanner scanner = new Scanner(System.in);

		while (true)
		{
			System.out.print(path.getAbsolutePath() + ">> ");
			if (!scanner.hasNextLine())
				break ;
			String []line = scanner.nextLine().trim().split("\\s+");
			switch (line[0]) {
				case "ls":
					lsCmd();
					break;
			
				case "cd":
					cdCmd(line);
					break;
				
				case "mv":
					mvCmd(line);
					break;

				default:
					System.out.println("Command not found : " + line[0]);
					break;
			}
		}

	}
}
