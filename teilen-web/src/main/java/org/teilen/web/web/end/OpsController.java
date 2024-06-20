package org.teilen.web.web.end;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(OpsController.URI)
public class OpsController {

  protected final static String URI = "/ops";

  public OpsController() {
    super();
    // TODO Auto-generated constructor stub
  }

  @RequestMapping({"", "/", "/**"})
  public String showOpsPage() {
    return "end/ops"; //both /end/ops && end/ops work ok to resolve the view resource
  }


}
