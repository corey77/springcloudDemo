package com.corey.scheduler;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.MethodInvoker;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/27</li>\n
 */

public class MyMethodInvokingJobDetailFactoryBean implements FactoryBean, BeanNameAware, InitializingBean {

    private Logger logger= LoggerFactory.getLogger( MyMethodInvokingJobDetailFactoryBean.class );

    /**
     * The JobDetail produced by the <code>afterPropertiesSet</code> method of this Class will be assigned to the Group specified by this property.  Default: Scheduler.DEFAULT_GROUP
     * @see #afterPropertiesSet()
     * @see Scheduler#DEFAULT_GROUP
     */
    private String group = Scheduler.DEFAULT_GROUP;

    /**
     * Indicates whether or not the Bean Method should be invoked by more than one Scheduler at the specified time (like when deployed to a cluster, and/or when there are multiple Spring ApplicationContexts in a single JVM<i> - Tomcat 5.5 creates 2 or more instances of the DispatcherServlet (a pool), which in turn creates a separate Spring ApplicationContext for each instance of the servlet</i>)
     * <p>
     * Used by <code>afterPropertiesSet</code> to set the JobDetail.jobClass to MethodInvokingJob.class or StatefulMethodInvokingJob.class when true or false, respectively.  Default: true
     * @see #afterPropertiesSet()
     */
    private boolean concurrent = true;

    /** Used to set the JobDetail.durable property.  Default: false
     * <p>Durability - if a job is non-durable, it is automatically deleted from the scheduler once there are no longer any active triggers associated with it.
     * @see <a href="http://www.opensymphony.com/quartz/wikidocs/TutorialLesson3.html">http://www.opensymphony.com/quartz/wikidocs/TutorialLesson3.html</a>
     * @see #afterPropertiesSet()
     */
    private boolean durable = true;

    /**
     * Used by <code>afterPropertiesSet</code> to set the JobDetail.volatile property.  Default: false
     * <p>Volatility - if a job is volatile, it is not persisted between re-starts of the Quartz scheduler.
     * <p>I set the default to false to be the same as the default for a Quartz Trigger.  An exception is thrown
     * when the Trigger is non-volatile and the Job is volatile.  If you want volatility, then you must set this property, and the Trigger's volatility property, to true.
     * @see <a href="http://www.opensymphony.com/quartz/wikidocs/TutorialLesson3.html">http://www.opensymphony.com/quartz/wikidocs/TutorialLesson3.html</a>
     * @see #afterPropertiesSet()
     */
    private boolean volatility = false;

    /**
     * Used by <code>afterPropertiesSet</code> to set the JobDetail.requestsRecovery property.  Default: false<BR>
     * <p>RequestsRecovery - if a job "requests recovery", and it is executing during the time of a 'hard shutdown' of the scheduler (i.e. the process it is running within crashes, or the machine is shut off), then it is re-executed when the scheduler is started again. In this case, the JobExecutionContext.isRecovering() method will return true.
     * @see <a href="http://www.opensymphony.com/quartz/wikidocs/TutorialLesson3.html">http://www.opensymphony.com/quartz/wikidocs/TutorialLesson3.html</a>
     * @see #afterPropertiesSet()
     */
    private boolean shouldRecover = false;

    /**
     * A list of names of JobListeners to associate with the JobDetail object created by this FactoryBean.
     *
     * @see #afterPropertiesSet()
     **/
    private String[] jobListenerNames;

    /** The name assigned to this bean in the Spring ApplicationContext.
     * Used by <code>afterPropertiesSet</code> to set the JobDetail.name property.
     **/
    private String beanName;

    /**
     * The JobDetail produced by the <code>afterPropertiesSet</code> method, and returned by the <code>getObject</code> method of the Spring FactoryBean interface.
     * @see #afterPropertiesSet()
     * @see #getObject()
     * @see FactoryBean
     **/
    private JobDetailImpl jobDetail;

    /**
     * The name of the Class to invoke.
     **/
    private String targetClass;

    /**
     * The Object to invoke.
     * <p>
     * {@link #targetClass} or targetObject must be set, but not both.
     * <p>
     * This object must be Serializable when {@link #concurrent} is set to false.
     */
    private Object targetObject;

    /**
     * The instance method to invoke on the Class or Object identified by the targetClass or targetObject property, respectfully.
     * <p>
     * targetMethod or {@link #staticMethod} should be set, but not both.
     **/
    private String targetMethod;

    /**
     * The static method to invoke on the Class or Object identified by the targetClass or targetObject property, respectfully.
     * <p>
     * {@link #targetMethod} or staticMethod should be set, but not both.
     */
    private String staticMethod;

    /**
     * Method arguments provided to the {@link #targetMethod} or {@link #staticMethod} specified.
     * <p>
     * All arguments must be Serializable when {@link #concurrent} is set to false.
     * <p>
     * I strongly urge you not to provide arguments until Quartz 1.6.1 has been released if you are using a JDBCJobStore with
     * Microsoft SQL Server. There is a bug in version 1.6.0 that prevents Quartz from Serializing the Objects in the JobDataMap
     * to the database.  The workaround is to set the property "org.opensymphony.quaryz.useProperties = true" in your quartz.properties file,
     * which tells Quartz not to serialize Objects in the JobDataMap, but to instead expect all String compliant values.
     */
    private Object[] arguments;

    /**
     * Get the targetClass property.
     * @see #targetClass
     * @return targetClass
     */
    public String getTargetClass()
    {
        return targetClass;
    }

    /**
     * Set the targetClass property.
     * @see #targetClass
     */
    public void setTargetClass(String targetClass)
    {
        this.targetClass = targetClass;
    }

    /**
     * Get the targetMethod property.
     * @see #targetMethod
     * @return targetMethod
     */
    public String getTargetMethod()
    {
        return targetMethod;
    }

    /**
     * Set the targetMethod property.
     * @see #targetMethod
     */
    public void setTargetMethod(String targetMethod)
    {
        this.targetMethod = targetMethod;
    }

    /**
     * @return jobDetail - The JobDetail that is created by the afterPropertiesSet method of this FactoryBean
     * @see #jobDetail
     * @see #afterPropertiesSet()
     * @see FactoryBean#getObject()
     */
    public Object getObject() throws Exception
    {
        return jobDetail;
    }

    /**
     * @return JobDetail.class
     * @see FactoryBean#getObjectType()
     */
    public Class getObjectType()
    {
        return JobDetail.class;
    }

    /**
     * @return true
     * @see FactoryBean#isSingleton()
     */
    public boolean isSingleton()
    {
        return true;
    }

    /**
     * Set the beanName property.
     * @see #beanName
     * @see BeanNameAware#setBeanName(String)
     */
    public void setBeanName(String beanName)
    {
        this.beanName = beanName;
    }

    /**
     * Invoked by the Spring container after all properties have been set.
     * <p>
     * Sets the <code>jobDetail</code> property to a new instance of JobDetail
     * <ul>
     * <li>jobDetail.name is set to <code>beanName</code><br>
     * <li>jobDetail.group is set to <code>group</code><br>
     * <li>jobDetail.jobClass is set to MethodInvokingJob.class or StatefulMethodInvokingJob.class depending on whether the <code>concurrent</code> property is set to true or false, respectively.<br>
     * <li>jobDetail.durability is set to <code>durable</code>
     * <li>jobDetail.volatility is set to <code>volatility</code>
     * <li>jobDetail.requestsRecovery is set to <code>shouldRecover</code>
     * <li>jobDetail.jobDataMap["targetClass"] is set to <code>targetClass</code>
     * <li>jobDetail.jobDataMap["targetMethod"] is set to <code>targetMethod</code>
     * <li>Each JobListener name in <code>jobListenerNames</code> is added to the <code>jobDetail</code> object.
     * </ul>
     * <p>
     * Logging occurs at the DEBUG and INFO levels; 4 lines at the DEBUG level, and 1 line at the INFO level.
     * <ul>
     * <li>DEBUG: start
     * <li>DEBUG: Creating JobDetail <code>{beanName}</code>
     * <li>DEBUG: Registering JobListener names with JobDetail object <code>{beanName}</code>
     * <li>INFO: Created JobDetail: <code>{jobDetail}</code>; targetClass: <code>{targetClass}</code>; targetMethod: <code>{targetMethod}</code>;
     * <li>DEBUG: end
     * </ul>
     * @see InitializingBean#afterPropertiesSet()
     * @see JobDetail
     * @see #jobDetail
     * @see #beanName
     * @see #group
     * @see MethodInvokingJob
     * @see StatefulMethodInvokingJob
     * @see #durable
     * @see #volatility
     * @see #shouldRecover
     * @see #targetClass
     * @see #targetMethod
     * @see #jobListenerNames
     */
    public void afterPropertiesSet() throws Exception
    {
        try
        {
            logger.debug("start");

            logger.debug("Creating JobDetail "+beanName);
            jobDetail = new JobDetailImpl();
            jobDetail.setName(beanName);
            jobDetail.setGroup(group);
            jobDetail.setJobClass(concurrent ? MethodInvokingJob.class : StatefulMethodInvokingJob.class);
            jobDetail.setDurability(durable);
//            jobDetail.setVolatility(volatility);
            jobDetail.setRequestsRecovery(shouldRecover);
            if(targetClass!=null)
                jobDetail.getJobDataMap().put("targetClass", targetClass);
            if(targetObject!=null)
                jobDetail.getJobDataMap().put("targetObject", targetObject);
            if(targetMethod!=null)
                jobDetail.getJobDataMap().put("targetMethod", targetMethod);
            if(staticMethod!=null)
                jobDetail.getJobDataMap().put("staticMethod", staticMethod);
            if(arguments!=null)
                jobDetail.getJobDataMap().put("arguments", arguments);

//            logger.debug("Registering JobListener names with JobDetail object "+beanName);
//            if (this.jobListenerNames != null) {
//                for (int i = 0; i < this.jobListenerNames.length; i++) {
//                    this.jobDetail.addJobListener(this.jobListenerNames[i]);
//                }
//            }
            logger.info("Created JobDetail: "+jobDetail+"; targetClass: "+targetClass+"; targetObject: "+targetObject+"; targetMethod: "+targetMethod+"; staticMethod: "+staticMethod+"; arguments: "+arguments+";");
        }
        finally
        {
            logger.debug("end");
        }
    }

    /**
     * Setter for the concurrent property.
     *
     * @param concurrent
     * @see #concurrent
     */
    public void setConcurrent(boolean concurrent)
    {
        this.concurrent = concurrent;
    }

    /**
     * setter for the durable property.
     *
     * @param durable
     *
     * @see #durable
     */
    public void setDurable(boolean durable)
    {
        this.durable = durable;
    }

    /**
     * setter for the group property.
     *
     * @param group
     *
     * @see #group
     */
    public void setGroup(String group)
    {
        this.group = group;
    }

    /**
     * setter for the {@link #jobListenerNames} property.
     *
     * @param jobListenerNames
     * @see #jobListenerNames
     */
    public void setJobListenerNames(String[] jobListenerNames)
    {
        this.jobListenerNames = jobListenerNames;
    }

    /**
     * setter for the {@link #shouldRecover} property.
     *
     * @param shouldRecover
     * @see #shouldRecover
     */
    public void setShouldRecover(boolean shouldRecover)
    {
        this.shouldRecover = shouldRecover;
    }

    /**
     * setter for the {@link #volatility} property.
     *
     * @param volatility
     * @see #volatility
     */
    public void setVolatility(boolean volatility)
    {
        this.volatility = volatility;
    }

    /**
     * This is a cluster safe Job designed to invoke a method on any bean defined within the same Spring
     * ApplicationContext.
     * <p>
     * The only entries this Job expects in the JobDataMap are "targetClass" and "targetMethod".<br>
     * - It uses the value of the <code>targetClass</code> entry to get the desired bean from the Spring ApplicationContext.<br>
     * - It uses the value of the <code>targetMethod</code> entry to determine which method of the Bean (identified by targetClass) to invoke.
     * <p>
     * It uses the static ApplicationContext in the MethodInvokingJobDetailFactoryBean,
     * which is ApplicationContextAware, to get the Bean with which to invoke the method.
     * <p>
     * All Exceptions thrown from the execute method are caught and wrapped in a JobExecutionException.
     *
     * @see #execute(JobExecutionContext)
     *
     * @author Stephen M. Wick
     */
    public static class MethodInvokingJob implements Job
    {
        private Logger logger= LoggerFactory.getLogger( MethodInvokingJob.class );

        /**
         * When invoked by a Quartz scheduler, <code>execute</code> invokes a method on a Class or Object in the JobExecutionContext provided.
         * <p>
         * <b>Implementation</b><br>
         * The Class is identified by the "targetClass" entry in the JobDataMap of the JobExecutionContext provided.  If targetClass is specified, then targetMethod must be a static method.<br>
         * The Object is identified by the 'targetObject" entry in the JobDataMap of the JobExecutionContext provided.  If targetObject is provided, then targetClass will be overwritten.  This Object must be Serializable when <code>concurrent</code> is set to false.<br>
         * The method is identified by the "targetMethod" entry in the JobDataMap of the JobExecutionContext provided.<br>
         * The "staticMethod" entry in the JobDataMap of the JobExecutionContext can be used to specify a Class and Method in one entry (ie: "example.ExampleClass.someStaticMethod")<br>
         * The method arguments (an array of Objects) are identified by the "arguments" entry in the JobDataMap of the JobExecutionContext.  All arguments must be Serializable when <code>concurrent</code> is set to false.
         * <p>
         * Logging is provided at the DEBUG and INFO levels; 8 lines at the DEBUG level, and 1 line at the INFO level.
         * @see Job#execute(JobExecutionContext)
         */
        public void execute( JobExecutionContext context) throws JobExecutionException
        {
            try
            {
                logger.debug("start");
                String targetClass = context.getMergedJobDataMap().getString("targetClass");
                logger.debug("targetClass is "+targetClass);
                Class targetClassClass = null;
                if(targetClass!=null)
                {
                    targetClassClass = Class.forName(targetClass); // Could throw ClassNotFoundException
                }
                Object targetObject = context.getMergedJobDataMap().get("targetObject");
                logger.debug("targetObject is "+targetObject);
                String targetMethod = context.getMergedJobDataMap().getString("targetMethod");
                logger.debug("targetMethod is "+targetMethod);
                String staticMethod = context.getMergedJobDataMap().getString("staticMethod");
                logger.debug("staticMethod is "+staticMethod);
                Object[] arguments = (Object[])context.getMergedJobDataMap().get("arguments");
                logger.debug("arguments are "+arguments);

                logger.debug("creating MethodInvoker");
                MethodInvoker methodInvoker = new MethodInvoker();
                methodInvoker.setTargetClass(targetClassClass);
                methodInvoker.setTargetObject(targetObject);
                methodInvoker.setTargetMethod(targetMethod);
                methodInvoker.setStaticMethod(staticMethod);
                methodInvoker.setArguments(arguments);
                methodInvoker.prepare();
//                logger.info("Invoking: "+methodInvoker.getPreparedMethod().toGenericString());
                methodInvoker.invoke();
            }
            catch(Exception e)
            {
                throw new JobExecutionException(e);
            }
            finally
            {
                logger.debug("end");
            }
        }
    }

    public static class StatefulMethodInvokingJob extends MethodInvokingJob implements StatefulJob
    {
        // No additional functionality; just needs to implement StatefulJob.
    }

    public Object[] getArguments()
    {
        return arguments;
    }

    public void setArguments(Object[] arguments)
    {
        this.arguments = arguments;
    }

    public String getStaticMethod()
    {
        return staticMethod;
    }

    public void setStaticMethod(String staticMethod)
    {
        this.staticMethod = staticMethod;
    }

    public void setTargetObject(Object targetObject)
    {
        this.targetObject = targetObject;
    }
}
