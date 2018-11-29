package com.corey;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
@EnableFeignClients
public class ClientBApplication {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientAService clientAService;

    @Autowired
    private DiscoveryClient discoveryClient;



    public  String getVal () {

        return val;
    }
    @Value( "${val1}" )
    public  void setVal ( String val1 ) {

        val = val1;
    }

    @RequestMapping("/")
    String home() {

//        String result=this.restTemplate.getForObject("http://corey-provider-user:8801/get/user",String.class);
        Gson gson=new Gson();
        String result=gson.toJson( clientAService.hello(),UserDTO.class );
        return result;
    }

    @RequestMapping("/hello")
    String home1() {

//        List<ServiceInstance> servicesInstances=discoveryClient.getInstances( "COREY-PROVIDER-USER" );
//        URI uri=servicesInstances.get(0).getUri();
        String aa="ddd";
        try {
            aa= restTemplate.getForObject("http://COREY-PROVIDER-USER/user/user",String.class);
        }catch ( Exception e ){
            e.printStackTrace();
        }
        return aa;
    }

    private static String val="1";

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate( RestTemplateBuilder builder) {
        return builder.build();
    }


    public static void main(String[] args) {

        SpringApplication.run( ClientBApplication.class, args);
    }
}
