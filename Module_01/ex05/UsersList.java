/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersList.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:58:34 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:58:36 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

public interface UsersList
{
	public void	addUser(User user);
	public User	findUserById(int id);
	public User	findUserByIndex(int index);
	public int	usersNumbers();
}
