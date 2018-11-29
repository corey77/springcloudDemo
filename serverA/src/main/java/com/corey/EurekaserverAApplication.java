package com.corey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/13</li>\n
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaserverAApplication {
    private  static Logger logger= LoggerFactory.getLogger( EurekaserverAApplication.class );

    public  String getVal () {

        return val;
    }
    @Value( "${val1}" )
    public  void setVal ( String val1 ) {

        val = val1;
    }


    private static String val="1";

    public static void main(String[] args) {
//        logger.debug( "dddd" );
        new SpringApplicationBuilder(EurekaserverAApplication.class).web( true).run(args);
//        SpringApplication.run( EurekaserverAApplication.class, args);
    }
}
