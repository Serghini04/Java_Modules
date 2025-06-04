/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   User.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 10:35:24 by meserghi          #+#    #+#             */
/*   Updated: 2025/06/04 18:48:23 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.models;

import fr._42.annotations.OrmEntity;
import fr._42.annotations.OrmColumn;
import fr._42.annotations.OrmColumnId;

@OrmEntity(table = "ana")
public class User {
    @OrmColumnId
    private int id;
    @OrmColumn(name = "first_name", length = 10)
    private String _firstName;
    @OrmColumn(name = "last_name", length = 10)
    private String _lastName;
    @OrmColumn(name = "age")
    private Integer _age;
    
    public User() {}

    public User(String _firstName, String _lastName, Integer _age) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._age = _age;
    }
    
    public void setId(int _id) {
        id = _id; 
    }
    public int getId() {
        return id;
    }
    
    public String getFirstName() {
        return _firstName;
    }
    
    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }
    
    public String getLastName() {
        return _lastName;
    }
    
    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }
    
    public Integer getAge() {
        return _age;
    }
    
    public void setAge(Integer _age) {
        this._age = _age;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", _firstName=" + _firstName + ", _lastName=" + _lastName + ", _age=" + _age + "]";
    }
}