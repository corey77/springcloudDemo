package com.corey.service;

import com.corey.dao.UserRepository;
import com.corey.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/28</li>\n
 */
@Service("testTask")
public class TestTask  {

    @Autowired
    private UserRepository userRepository;


    private static final long serialVersionUID = -2723435504831637336L;

    public void test () {

        System.out.println( "=====================testTask" );
        User users=userRepository.findOne( 1l );
////        Gson gson=new Gson();
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout( 5000 );
//        httpRequestFactory.setConnectTimeout( 5000 );
//        httpRequestFactory.setReadTimeout( 5000 );
//        RestTemplate restTemplate = new RestTemplate( httpRequestFactory );
//        UserDTO result=restTemplate.getForObject( "http://COREY-PROVIDER-USER/user/user",UserDTO.class );
//        List<String> services=discoveryClient.getServices();
//        User user=userRepository.get(  );
        System.out.println(users.getEmail()+",,,,");
    }
}
