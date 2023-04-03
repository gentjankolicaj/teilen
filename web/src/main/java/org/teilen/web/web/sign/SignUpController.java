package org.teilen.web.web.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teilen.web.model.*;
import org.teilen.web.service.CountryService;
import org.teilen.web.service.SignService;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(SignUpController.URI)
public class SignUpController {

    protected final static String URI = "/signup";

    private CountryService countryService;
    private SignService signService;

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

    @RequestMapping(path = {"/new"}, method = RequestMethod.GET)
    public String saveDetails(@ModelAttribute User user, @ModelAttribute PasswordModel passwordModel,
                              @ModelAttribute("userAddress") UserAddress userAddress, @ModelAttribute("userContact") UserContact userContact, @RequestParam("countryId") Long countryId, RedirectAttributes redirectAttributes) throws Exception {
        if (passwordModel.getPassword().equals(passwordModel.getRePassword())) {

            try {

                User tmp = signService.signUp(user, userContact, userAddress, passwordModel, countryId);

                redirectAttributes.addFlashAttribute("signedUpUser", tmp); //added as an attribute to be carried during redirect,this is the only way not (model.addAttribute)

            } catch (Exception e) {
                e.printStackTrace();

                return "redirect:/signup";
            }

            return "redirect:/account/up";

        } else
            return "redirect:/signup";

    }

}
