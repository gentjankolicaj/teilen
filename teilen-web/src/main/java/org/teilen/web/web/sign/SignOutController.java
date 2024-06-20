package org.teilen.web.web.sign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SignOutController.URI)
public class SignOutController {

  protected final static String URI = "/signout";

  public SignOutController() {
    super();
    // TODO Auto-generated constructor stub
  }


  @RequestMapping(path = {"", "/", "/**"})
  public String showSignOut() {

    return "redirect:/signin";
  }


}
