package com.ccit.test;

import com.ccit.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestLogin {
   @Inject
   private LoginService loginService;
    @Test
    public void testLogin(){
      loginService.login("tom","123123","228.128.222.1");
    }
}
