package com.corey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/13</li>\n
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
//@ImportResource("application.yml")
//@EnableFeignClients
public class ClientCApplication {


    public  String getVal () {

        return val;
    }
    @Value( "${val1}" )
    public  void setVal ( String val1 ) {

        val = val1;
    }


    @RequestMapping("/user")
    String home() {

        return "helloC :"+val;
    }
    @RequestMapping("/")
    String home1() {

        return "helloC :"+val;
    }


    private static String val="1";

    public static void main(String[] args) {

//        MyTest myTest=new MyTest();
//        MyTest myTest1=new MyTest();
//        MyTest myTest2=new MyTest();
//        myTest.start();
//        myTest1.start();
//        myTest2.start();

        SpringApplication.run( ClientCApplication.class, args);
    }
}
