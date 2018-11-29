package com.corey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
public class ClientAApplication {


    public  String getVal () {

        return val;
    }
    @Value( "${val1}" )
    public  void setVal ( String val1 ) {

        val = val1;
    }

    @RequestMapping("/user")
    UserDTO home() {

        List<RoleDTO> list=new ArrayList< RoleDTO >(  );
        list.add( new RoleDTO( "管理员" ) );
        list.add( new RoleDTO( "安全员" ) );
        UserDTO userDTO=new UserDTO( 1,"张三",list ,new RoleDTO( "审计员" ));
        return userDTO;
    }
    @RequestMapping("/")
    String home1() {

        return "hello11 :"+val;
    }


    private static String val="1";

    public static void main(String[] args) {

        SpringApplication.run( ClientAApplication.class, args);
    }
}
