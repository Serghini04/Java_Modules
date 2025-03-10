/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersList.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <serghini@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:56:14 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:56:15 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public interface UsersList
{
	public void	addUser(User user);
	public User	findUserById(int id);
	public User	findUserByIndex(int index);
	public int	usersNumbers();
}
