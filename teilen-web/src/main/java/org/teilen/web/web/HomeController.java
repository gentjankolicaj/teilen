package org.teilen.web.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(HomeController.URI)
public class HomeController {

  protected final static String URI = "/";


  public HomeController() {
    super();
    // TODO Auto-generated constructor stub
  }


  @RequestMapping(path = {"", "home", "index", "index.html", "index.xhtml"})
  public String showHome() {
    return "index";
  }


}
