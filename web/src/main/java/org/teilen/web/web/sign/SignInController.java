package org.teilen.web.web.sign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.teilen.web.model.AuthData;
import org.teilen.web.model.User;
import org.teilen.web.service.AuthenticationService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(SignInController.URI)
public class SignInController {

    protected final static String URI = "/signin";

    private AuthenticationService authenticationService;

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

    @RequestMapping(path = {"/auth"}, method = RequestMethod.GET)
    public String showAccountPage(@ModelAttribute("authData") AuthData authData, RedirectAttributes redirectAttributes)
            throws Exception {

        try {

            User user = authenticationService.authenticate(authData.getEmail(), authData.getPassword());

            redirectAttributes.addFlashAttribute("user", user); // added as an attribute to be carried during
            // redirect,this is only way !!!,attribute can't be sent with usual model.addAttribute

        } catch (Exception e) {

            e.printStackTrace();

            return "redirect:/signin";
        }

        return "redirect:/account/in";

    }

}
