package com.corey;

import java.io.Serializable;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/14</li>\n
 */

public class RoleDTO implements Serializable {
    private String name;

    public String getName () {

        return name;
    }

    public void setName ( String name ) {

        this.name = name;
    }

    public RoleDTO ( String name ) {

        this.name = name;
    }

    public RoleDTO () {

    }
}
