package org.teilen.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ContactController.URI)
public class ContactController {

  protected final static String URI = "/contact";

  public ContactController() {
    super();
    // TODO Auto-generated constructor stub
  }


  @RequestMapping(path = {"", "/", "/**"})
  public String showContactPage() {
    return "contact";

  }
}
