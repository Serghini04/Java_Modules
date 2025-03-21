/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersRepository.java                               :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/21 14:12:34 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/21 14:12:34 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.repositories;
import java.util.List;
import fr._42.chat.models.User;

public interface UsersRepository {
	public List<User>	findAll(int page, int size);
}
