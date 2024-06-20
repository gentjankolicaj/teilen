package org.teilen.web.web.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teilen.web.model.AuthData;
import org.teilen.web.service.AuthenticationService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(SignInController.URI)
public class SignInController {

  protected final static String URI = "/signin";

  private final AuthenticationService authenticationService;

  @Autowired
  public SignInController(AuthenticationService authenticationService) {
    super();
    this.authenticationService = authenticationService;
  }

  @RequestMapping(path = {"", "/", "/**"})
  public String showSignInPage(Model model) {

    AuthData authData = new AuthData();

    model.addAttribute("authData", authData);

    return "signin";

  }


}
