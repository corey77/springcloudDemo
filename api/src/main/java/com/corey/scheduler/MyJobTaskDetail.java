package com.corey.scheduler;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/27</li>\n
 */

public class MyJobTaskDetail {
    private String targetBeanName;
    private String targetMethodName;

    public String getTargetBeanName () {

        return targetBeanName;
    }

    public void setTargetBeanName ( String targetBeanName ) {

        this.targetBeanName = targetBeanName;
    }

    public String getTargetMethodName () {

        return targetMethodName;
    }

    public void setTargetMethodName ( String targetMethodName ) {

        this.targetMethodName = targetMethodName;
    }
}
