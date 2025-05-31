/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   App.java                                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 09:06:49 by meserghi          #+#    #+#             */
/*   Updated: 2025/05/31 14:52:06 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.app;

import fr._42.orm.OrmManager;

public class App 
{
    //docker run --name my-postgres -e POSTGRES_DB=mydb -e POSTGRES_USER=user -e POSTGRES_PASSWORD=**** -p 5432:5432 -d postgres
    private static String  _url = "jdbc:postgresql://localhost:5432/mydb";
    private static String  _username = "user";
    private static String  _password = "****";
    
    public static void main( String[] args )
    {
        OrmManager  orm = new OrmManager(_url, _username, _password);
        System.exit(0);
    }
}
