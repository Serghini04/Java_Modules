/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   MessagesRepository.java                            :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/19 03:06:06 by meserghi          #+#    #+#             */
/*   Updated: 2025/03/19 03:06:06 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.chat.repositories;

import java.util.Optional;
import fr._42.chat.models.Message;

public interface MessagesRepository {
    public Optional<Message> findById(long id);
}