package org.teilen.web.web.sign;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.teilen.web.model.Country;
import org.teilen.web.model.PasswordModel;
import org.teilen.web.model.User;
import org.teilen.web.model.UserAddress;
import org.teilen.web.model.UserContact;
import org.teilen.web.service.CountryService;
import org.teilen.web.service.SignService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(SignUpController.URI)
public class SignUpController {

  protected final static String URI = "/signup";

  private final CountryService countryService;
  private final SignService signService;

  @Autowired
  public SignUpController(CountryService countryService, SignService signService) {
    super();
    this.countryService = countryService;
    this.signService = signService;
  }

  @RequestMapping(path = {"", "/", "/**"}, method = RequestMethod.GET)
  public String showSignUpPage(Model model) throws Exception {

    User user = new User();

    PasswordModel passwordModel = new PasswordModel();

    UserContact userContact = new UserContact();

    UserAddress userAddress = new UserAddress();

    List<Country> countries = countryService.getAll();

    model.addAttribute("user", user);

    model.addAttribute("passwordModel", passwordModel);

    model.addAttribute("userAddress", userAddress);

    model.addAttribute("userContact", userContact);

    model.addAttribute("countries", countries);

    return "signup";

  }

}
