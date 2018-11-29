package com.corey.scheduler;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * \n
 * <p,\n
 * <li,Description: TODO</li,\n
 * <li,@author: Corey </li,\n
 * <li,@date: 2018/11/27</li,\n
 */
@Configuration
public class SchedulerConfig {

    private Logger logger = LoggerFactory.getLogger( SchedulerConfig.class );

    @Autowired
    private MyCronTriggerFactoryBeanList triggerFactoryBeanList;

    @Value("${org.quartz.dataSource.quartzDS.driver}")
    private String driver;

    @Value("${org.quartz.dataSource.quartzDS.url}")
    private String url;

    @Value("${org.quartz.dataSource.quartzDS.user}")
    private String user;

    @Value("${org.quartz.dataSource.quartzDS.password}")
    private String password;

    @Value("${org.quartz.dataSource.quartzDS.maxConnections}")
    private String maxConnections;

    @Value("${org.quartz.scheduler.instancename}")
    private String instanceName;


    @Bean
    public SchedulerFactoryBean createSchedulerFactoryBean () throws Exception {

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setAutoStartup( true );


        List< MyJobTask > jobTasks = triggerFactoryBeanList.getJobTask();
        if ( !CollectionUtils.isEmpty( jobTasks ) ) {
            List< Trigger > triggers = new ArrayList< Trigger >();
            for ( MyJobTask jobTask : jobTasks ) {
                CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
                MyJobTaskDetail jobTaskDetail = jobTask.getJobTaskDetail();
                if ( jobTaskDetail != null ) {
                    MyMethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean = new MyMethodInvokingJobDetailFactoryBean();
                    if ( StringUtils.isEmpty( jobTaskDetail.getTargetBeanName() ) ) {
                        logger.error( "定时任务的对象名为空" );
                        continue;
                    }
                    if ( StringUtils.isEmpty( jobTaskDetail.getTargetMethodName() ) ) {
                        logger.error( "定时任务的方法名为空" );
                        continue;
                    }
                    MyDetailQuartzJobBean myDetailQuartzJobBean = new MyDetailQuartzJobBean();
                    myDetailQuartzJobBean.setTargetObject( jobTaskDetail.getTargetBeanName() );
                    myDetailQuartzJobBean.setTargetMethod( jobTaskDetail.getTargetMethodName()  );
                    methodInvokingJobDetailFactoryBean.setTargetObject(  myDetailQuartzJobBean);
                    methodInvokingJobDetailFactoryBean.setTargetMethod( "execute" );
                    if ( StringUtils.isEmpty( jobTask.getCronexpression() ) ) {
                        logger.error( "定时任务的执行周期为空" );
                        continue;
                    }
                    String name = jobTaskDetail.getTargetBeanName() + "_" + jobTaskDetail.getTargetMethodName();
                    methodInvokingJobDetailFactoryBean.setBeanName( "jobDetail_" + name );
                    cronTriggerFactoryBean.setCronExpression( jobTask.getCronexpression() );
                    methodInvokingJobDetailFactoryBean.afterPropertiesSet();
                    cronTriggerFactoryBean.setJobDetail( (JobDetail) methodInvokingJobDetailFactoryBean.getObject() );
                    cronTriggerFactoryBean.setName( "trigger_" + name );
                    cronTriggerFactoryBean.setGroup( instanceName );
                    cronTriggerFactoryBean.afterPropertiesSet();
                    triggers.add( cronTriggerFactoryBean.getObject() );
                    logger.debug( "定时任务加载任务:任务对象:{1}的{2}方法", jobTaskDetail.getTargetBeanName(), jobTaskDetail.getTargetMethodName() );
                }
            }
            Trigger[] triggersArray = triggers.toArray( new Trigger[triggers.size()] );
            schedulerFactoryBean.setTriggers( triggersArray );
        }
        logger.warn( "没有配置定时任务" );
        schedulerFactoryBean.setOverwriteExistingJobs( true );
        Properties properties = new Properties();
        properties.setProperty( "org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreCMT" );
        properties.setProperty( "org.quartz.scheduler.instanceName", instanceName );
        properties.setProperty( "org.quartz.scheduler.instanceId", "AUTO" );
        properties.setProperty( "org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool" );
        properties.setProperty( "org.quartz.threadPool.threadCount", "50" );
        properties.setProperty( "org.quartz.threadPool.threadPriority", "5" );
        properties.setProperty( "org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true" );
        properties.setProperty( "org.quartz.jobStore.misfireThreshold", "60000" );
        properties.setProperty( "org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate" );
        properties.setProperty( "org.quartz.jobStore.tablePrefix", "qrtz_" );
        properties.setProperty( "org.quartz.jobStore.maxMisfiresToHandleAtATime", "10" );
        properties.setProperty( "org.quartz.jobStore.isClustered", "true" );
        properties.setProperty( "org.quartz.jobStore.clusterCheckinInterval", "1500" );
        properties.setProperty( "org.quartz.jobStore.dontSetAutoCommitFalse", "true" );
        properties.setProperty( "org.quartz.jobStore.txIsolationLevelSerializable", "false" );
        properties.setProperty( "org.quartz.jobStore.dataSource", "quartzDS" );
        properties.setProperty( "org.quartz.jobStore.nonManagedTXDataSource", "quartzDS" );
        properties.setProperty( "org.quartz.jobStore.useProperties", "false" );
        properties.setProperty( "org.quartz.dataSource.quartzDS.driver", driver );
        properties.setProperty( "org.quartz.dataSource.quartzDS.URL", url );
        properties.setProperty( "org.quartz.dataSource.quartzDS.user", user );
        properties.setProperty( "org.quartz.dataSource.quartzDS.password", password );
        properties.setProperty( "org.quartz.dataSource.quartzDS.maxConnections", maxConnections );
        properties.setProperty( "org.quartz.dataSource.quartzDS.validationQuery", "select 0 from qrtz_locks" );
        schedulerFactoryBean.setQuartzProperties( properties );
        schedulerFactoryBean.setApplicationContextSchedulerContextKey( "applicationContext" );
        return schedulerFactoryBean;
    }


}
