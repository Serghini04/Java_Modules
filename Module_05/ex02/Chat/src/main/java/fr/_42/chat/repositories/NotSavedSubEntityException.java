/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   NotSavedSubEntityException.java                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/20 01:14:00 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/20 01:14:00 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.repositories;
// package fr._42.chat.repositories;

public class NotSavedSubEntityException extends RuntimeException {

    public NotSavedSubEntityException(String message) {
        super(message);
    }


    public NotSavedSubEntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
