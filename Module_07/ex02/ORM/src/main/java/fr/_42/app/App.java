/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   App.java                                           :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 09:06:49 by meserghi          #+#    #+#             */
/*   Updated: 2025/06/05 16:19:08 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.app;

import fr._42.models.User;
import fr._42.orm.OrmManager;

public class App 
{
    private static String  _url = "jdbc:postgresql://localhost:5432/mydb";
    private static String  _username = "user";
    private static String  _password = "****";
    
    //docker run --name my-postgres -e POSTGRES_DB=mydb -e POSTGRES_USER=user -e POSTGRES_PASSWORD='****' -p 5432:5432 -d postgres
    // mvn compile exec:java -Dexec.mainClass="fr._42.app.App"
    public static void main( String[] args )
    {
        OrmManager  orm = new OrmManager(_url, _username, _password);

        User    user1 = new User("Mehdi", "Serghini", 20);
        orm.save(user1);
        user1.setId(1);
        user1.setAge(21);
        orm.update(user1);
        User student = orm.findById(1L, User.class);
        System.out.println(student);
        System.exit(0);
    }
}
