package com.corey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/13</li>\n
 */
@EnableZuulProxy
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class ZuulApplication {



    private static String val = "1";

    public static void main ( String[] args ) {
        SpringApplication.run( ZuulApplication.class, args );
    }
}
