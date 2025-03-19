/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersArrayList.java                                :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:56:06 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:56:08 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public class UsersArrayList implements UsersList
{
	
	User[]	users;
	int		size;

	public UsersArrayList(int size)
	{
		if (size < 0)
			throw new ArrayStoreException();
		this.users = new User[size];
		this.size = 0;
	}
	public UsersArrayList()
	{
		users = new User[10];
		size = 0;	
	}
	
	public static class UserNotFoundException extends RuntimeException 
	{
		public UserNotFoundException()
		{
			super("I can't Find User.");
		}
	}

	@Override
	public void	addUser(User user) throws NullPointerException
	{
		if (user == null)
			throw new NullPointerException();
		if (size == users.length)
		{
			User []newUsers	= new User[size + (size / 2)];
			for (int i = 0; i < size; i++)
			newUsers[i] = users[i];
			users = newUsers;
		}
		users[size] = user;
		size++;
	}
	
	@Override
	public User findUserById(int id)
	{
		for (int i = 0; i < size; i++)
		{
			if (users[i].getId() == id)
				return users[i];
		}
		throw new UserNotFoundException();
	}
	
	
	@Override
	public User	findUserByIndex(int index)
	{
		if (index < 0 && index >= size)
			throw new ArrayIndexOutOfBoundsException();
		return users[index];
	}

	@Override
	public int usersNumbers()
	{
		return size;
	}
}
