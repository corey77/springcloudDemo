package com.corey;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * \n
 * <p>\n
 * <li>Description: TODO</li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2017/11/14</li>\n
 */
@FeignClient(name = "corey-provider-user")
public interface ClientAService {
    @RequestMapping(value = "/user/user")
    UserDTO hello();
}
