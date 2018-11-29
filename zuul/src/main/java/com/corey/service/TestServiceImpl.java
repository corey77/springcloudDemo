package com.corey.service;

import org.springframework.stereotype.Service;

/**
 * \n
 * <p>\n
 * <li>Description: TODO </li>\n
 * <li>@author: Corey </li>\n
 * <li>@date: 2018/11/26</li>\n
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    public void test () {
        System.out.println("=====================test");
    }
}
