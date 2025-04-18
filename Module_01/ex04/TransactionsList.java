/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionsList.java                              :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:57:04 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:57:05 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

public interface TransactionsList
{
	public void	addTransaction(Transaction transaction);
	public void removeTransaction(UUID id);
	public Transaction getTransactionById(UUID id);
	Transaction[] toArray();
}
