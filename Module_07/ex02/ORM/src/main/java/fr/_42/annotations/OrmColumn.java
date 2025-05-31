/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   OrmColumn.java                                     :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: meserghi <meserghi@student.1337.ma>        +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2025/05/31 09:09:20 by meserghi          #+#    #+#             */
/*   Updated: 2025/05/31 10:24:53 by meserghi         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package fr._42.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrmColumn {
    String  name();
    long    length() default 255;
}