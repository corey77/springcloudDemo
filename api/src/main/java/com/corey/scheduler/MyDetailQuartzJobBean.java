package com.corey.scheduler;

import com.corey.SpringBeanFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/28</li>\n
 */

public class MyDetailQuartzJobBean implements Serializable {

    protected final Logger logger = LoggerFactory.getLogger( MyDetailQuartzJobBean.class );

    private String targetObject;

    private String targetMethod;

    protected void execute () {

//        logger.info( "execute [" + targetObject + "] at once>>>>>>" );
        Object otargetObject = SpringBeanFactoryUtil.getBean( targetObject );
        Method m = null;

        try {
            //以反射形式调用otargetObject中的方法，方法的参数是JobExecutionContext类型
            m = otargetObject.getClass().getMethod( targetMethod );
            m.invoke( otargetObject );
        } catch ( SecurityException e ) {
            logger.error( e.getMessage() ,e);
        } catch ( NoSuchMethodException e ) {
            logger.error( e.getMessage() ,e);
        }catch ( Exception e ){
            logger.error( e.getMessage(),e );
        }
    }

    public void setTargetObject ( String targetObject ) {

        this.targetObject = targetObject;
    }

    public void setTargetMethod ( String targetMethod ) {

        this.targetMethod = targetMethod;
    }
}
