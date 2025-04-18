/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   AlreadyAuthenticatedException.java                 :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/04/05 15:12:45 by meserghi          #+#    #+#             */
/*   Updated: 2025/04/05 16:20:56 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.exceptions;

public class AlreadyAuthenticatedException extends RuntimeException{
    public AlreadyAuthenticatedException() {
        super("Already authenticated");
    }

    public AlreadyAuthenticatedException(String message) {
        super(message);
    }

    public AlreadyAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyAuthenticatedException(Throwable cause) {
        super(cause);
    }
}