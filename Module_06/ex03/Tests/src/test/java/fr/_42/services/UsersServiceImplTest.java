/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   UsersServiceImplTest.java                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/05 15:43:19 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/05 16:22:06 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.services;

import fr._42.exceptions.AlreadyAuthenticatedException;
import fr._42.models.User;
import fr._42.repositories.UsersRepository;
import fr._42.services.UsersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceImplTest {
    private UsersRepository usersRepository;
    private UsersServiceImpl usersService;

    @BeforeEach
    void init() {
        usersRepository = mock(UsersRepository.class);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Test
    void testCorrectLoginAndPassword() {
        User user = new User(1L, "john", "1234", false);

        when(usersRepository.findByLogin("john")).thenReturn(user);
        boolean result = usersService.authenticate("john", "1234");

        assertTrue(result);
        assertTrue(user.isStatus());
        verify(usersRepository).update(user);
    }

    @Test
    void testIncorrectPassword() {
        User user = new User(2L, "jane", "pass", false);

        when(usersRepository.findByLogin("jane")).thenReturn(user);

        boolean result = usersService.authenticate("jane", "wrong");

        assertFalse(result);
        assertFalse(user.isStatus());
        verify(usersRepository, never()).update(any());
    }

    @Test
    void testAlreadyAuthenticated() {
        User user = new User(3L, "bob", "secure", true);

        when(usersRepository.findByLogin("bob")).thenReturn(user);

        assertThrows(AlreadyAuthenticatedException.class, () -> {
            usersService.authenticate("bob", "secure");
        });

        verify(usersRepository, never()).update(any());
    }
}
