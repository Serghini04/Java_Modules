/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionsLinkedList.java                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:56:30 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:56:31 by serghini         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList 
{
    private Node header;
    private Node lastNode;
    private int size;

    public TransactionsLinkedList()
    {
        header = null;
        lastNode = null;
        size = 0;
    }

    private class Node
    {
        Transaction data;
        Node next;
    }

    @Override
    public void addTransaction(Transaction transaction)
    {
        Node newNode = new Node();
        newNode.data = transaction;
        newNode.next = null;
        size++;

        if (header == null)
        {
            header = newNode;
            lastNode = newNode;
        }
        else
        {
            lastNode.next = newNode;
            lastNode = newNode;
        }
    }

    @Override
    public void removeTransaction(UUID id) throws TransactionNotFoundException {
        if (header == null)
            throw new TransactionNotFoundException();
        if (header.data.getId().equals(id))
        {
            header = header.next;
            size--;
            if (header == null)
                lastNode = null;
            System.out.println("Transaction ID = [" + id + "] removed successfully.");
            return;
        }
        Node tmp = header;
        while (tmp.next != null) {
            if (tmp.next.data.getId().equals(id))
            {
                tmp.next = tmp.next.next;
                size--;
                if (tmp.next == null)
                    lastNode = tmp;
                System.out.println("Transaction ID = [" + id + "] removed successfully.");
                return;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray()
    {
        Transaction[] res = new Transaction[size];
        int i = 0;
        Node tmp = header;
        while (tmp != null)
        {
            res[i] = tmp.data;
            tmp = tmp.next;
            i++;
        }
        return res;
    }

    public class TransactionNotFoundException extends RuntimeException
    {
        public TransactionNotFoundException() {
            super("Transaction not found.");
        }
    }
}