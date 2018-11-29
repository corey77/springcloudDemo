package com.corey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/13</li>\n
 */
@SpringBootApplication
@EnableEurekaServer
//@RestController
public class EurekaserverBApplication {


    public  String getVal () {

        return val;
    }
    @Value( "${val1}" )
    public  void setVal ( String val1 ) {

        val = val1;
    }

    @RequestMapping("/")
    String home() {
        return "hello :"+val;
    }

    private static String val="1";

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaserverBApplication.class).web( true).run(args);
//        SpringApplication.run( EurekaserverBApplication.class, args);
    }
}
