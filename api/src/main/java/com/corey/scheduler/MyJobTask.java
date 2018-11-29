package com.corey.scheduler;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/27</li>\n
 */

public class MyJobTask {
    private MyJobTaskDetail jobTaskDetail;

    private String cronexpression;

    public MyJobTaskDetail getJobTaskDetail () {

        return jobTaskDetail;
    }

    public void setJobTaskDetail ( MyJobTaskDetail jobTaskDetail ) {

        this.jobTaskDetail = jobTaskDetail;
    }

    public String getCronexpression () {

        return cronexpression;
    }

    public void setCronexpression ( String cronexpression ) {

        this.cronexpression = cronexpression;
    }
}
