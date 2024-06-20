package org.teilen.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.teilen.web.model.User;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(ControlPanelController.URI)
public class ControlPanelController {

  protected final static String URI = "/cpanel";

  public ControlPanelController() {
    super();
    // TODO Auto-generated constructor stub
  }


  @RequestMapping(path = {"", "/", "/**"})
  public String showControlPanel(Model model) {

    User user = new User();

    model.addAttribute("user", user);

    return "control-panel";
  }


  @RequestMapping(path = {"/out"})
  public String logoutFromControlPanel() {
    System.out.println("Redirecting at signin");
    return "redirect:/signin";
  }
}
