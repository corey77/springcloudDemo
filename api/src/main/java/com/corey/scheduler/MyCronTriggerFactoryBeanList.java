package com.corey.scheduler;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/27</li>\n
 */
@Component
@ConfigurationProperties(prefix = "jobTasks")
public class MyCronTriggerFactoryBeanList {

    private List< MyJobTask > jobTask;

    public List< MyJobTask > getJobTask () {

        return jobTask;
    }

    public void setJobTask ( List< MyJobTask > jobTask ) {

        this.jobTask = jobTask;
    }
}
