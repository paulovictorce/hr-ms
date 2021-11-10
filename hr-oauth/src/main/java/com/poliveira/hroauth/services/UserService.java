package com.poliveira.hroauth.services;

import com.poliveira.hroauth.entities.User;
import com.poliveira.hroauth.feignclients.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private static Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserFeignClient userFeignClient;

  public User searchByEmail(String email) {
    User user = userFeignClient.findByEmail(email).getBody();
    if (user == null) {
      logger.error("EMAIL NOT FOUND: " + email);
      throw new IllegalArgumentException("EMAIL NOT FOUND");
    }
    logger.info("EMAIL FOUND: " + email);
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userFeignClient.findByEmail(username).getBody();
    if (user == null) {
      logger.error("EMAIL NOT FOUND: " + username);
      throw new UsernameNotFoundException("EMAIL NOT FOUND");
    }
    logger.info("EMAIL FOUND: " + username);
    return user;
  }
}
