package com.corey;

import java.io.Serializable;
import java.util.List;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/14</li>\n
 */
public class UserDTO implements Serializable {
    private long id;
    private String name;
    private List<RoleDTO> roles;
    private RoleDTO role;

    public long getId () {

        return id;
    }

    public void setId ( long id ) {

        this.id = id;
    }

    public String getName () {

        return name;
    }

    public void setName ( String name ) {

        this.name = name;
    }

    public List< RoleDTO > getRoles () {

        return roles;
    }

    public void setRoles ( List< RoleDTO > roles ) {

        this.roles = roles;
    }

    public RoleDTO getRole () {

        return role;
    }

    public void setRole ( RoleDTO role ) {

        this.role = role;
    }

    public UserDTO ( long id, String name, List< RoleDTO > roles, RoleDTO role ) {

        this.id = id;
        this.name = name;
        this.roles = roles;
        this.role = role;
    }

    public UserDTO () {

    }
}
