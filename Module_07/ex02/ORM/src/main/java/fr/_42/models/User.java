/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 10:35:24 by meserghi          #+#    #+#             */
/*   Updated: 2025/05/31 13:21:21 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.models;

import fr._42.annotations.OrmEntity;
import fr._42.annotations.OrmColumn;
import fr._42.annotations.OrmColumnId;

@OrmEntity(table = "")
public class Student {
    @OrmColumnId
    private int _id;
    @OrmColumn(name = "first_name", length = 10)
    private String _firstName;
    @OrmColumn(name = "last_name", length = 10)
    private String _lastName;
    @OrmColumn(name = "age")
    private Integer _age;
}