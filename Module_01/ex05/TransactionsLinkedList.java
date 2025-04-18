/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   TransactionsLinkedList.java                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: serghini <meserghi@student.1337.ma>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/03/08 00:57:56 by serghini          #+#    #+#             */
/*   Updated: 2025/03/08 00:57:57 by serghini         ###   ########.fr       */
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
        if (header == null) {
            throw new TransactionNotFoundException();
        }
        
        if (header.data.getId().equals(id)) {
            header = header.next;
            size--;
            if (header == null) {
                lastNode = null;
            }
            return ;
        }
        
        Node tmp = header;
        while (tmp.next != null) {
            if (tmp.next.data.getId().equals(id)) {
                tmp.next = tmp.next.next;
                size--;
                if (tmp.next == null)
                    lastNode = tmp;
                return ;
            }
            tmp = tmp.next;
        }
        System.err.println("Not delete");
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

    @Override
    public Transaction getTransactionById(UUID id)
    {
        Node tmp = header;
        while (tmp != null)
        {
            if (tmp.data.getId().equals(id))
            {
                System.err.println("Is Here");
                return tmp.data;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    public class TransactionNotFoundException extends RuntimeException
    {
        public TransactionNotFoundException() {
            super("Transaction not found.");
        }
    }
}