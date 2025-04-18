/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersServiceImpl.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/05 14:16:13 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/05 16:20:08 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.services;
import fr._42.repositories.UsersRepository;
import fr._42.models.User;
import fr._42.exceptions.AlreadyAuthenticatedException;

public class UsersServiceImpl {
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) {
        User user = usersRepository.findByLogin(login);

        if (user.isStatus())
            throw new AlreadyAuthenticatedException();

        if (user.getPassword().equals(password)) {
            user.setStatus(true);
            usersRepository.update(user);
            return true;
        }

        return false;
    }
}

