package org.teilen.web.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.teilen.web.enums.HashFunction;

@EnableWebSecurity
public class WebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  //how to hash user passwords
  public static final HashFunction PASSWORD_HASHING_FUNCTION = HashFunction.SHA_256;


  private static final String[] FREE_URI = {
      "/home", "/home/**",
      "/about", "/about/**",
      "/contact", "/contact/**",

      "/signin", "/signin/**",
      "/signout", "/signout/**",
      "/signup", "/signup/**",

      "/ops", "/access-denied",
      "/api/**",
      "/css/**", "/fonts/**", "/images/**"
  };

  private static final String[] SECURED_URI = {
      "/account", "/account/**"
  };


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeRequests().antMatchers(FREE_URI).permitAll();

  }


}
