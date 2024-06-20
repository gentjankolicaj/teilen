package org.teilen.web.web.end;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AccessDeniedController.URL)
public class AccessDeniedController {

  protected final static String URL = "/access-denied";


  @RequestMapping({"", "/", "/**"})
  public String showAccessDenied() {

    return "/end/access-denied"; //both /end/access-denied && end/access-denied work ok to resolve the view resource
  }
}
