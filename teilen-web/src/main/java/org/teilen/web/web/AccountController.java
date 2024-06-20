package org.teilen.web.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.teilen.web.model.Message;
import org.teilen.web.model.Team;
import org.teilen.web.model.User;
import org.teilen.web.service.MessageService;
import org.teilen.web.service.TeamService;
import org.teilen.web.service.UserService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(AccountController.URI)
class AccountController {

  protected static final String URI = "/account";

  private User user;

  private final UserService userService;
  private final MessageService messageService;
  private final TeamService teamService;

  @Autowired
  public AccountController(UserService userService, MessageService messageService,
      TeamService teamService) {
    super();
    this.userService = userService;
    this.messageService = messageService;
    this.teamService = teamService;

  }

  @RequestMapping(path = {"/in"}, method = RequestMethod.GET)
  public String showAccountIn(Model model) throws Exception {
    try {

      this.user = (User) model.asMap().get("user");

      List<User> uList = userService.getAllUnDeleted();

      List<Team> tList = teamService.getAll();

      List<Message> sentMessages = messageService.getBySenderId(user.getId());

      List<Message> receivedMessages = messageService.getByReceiverId(user.getId());

      model.addAttribute("user", user);

      model.addAttribute("tList", tList);

      model.addAttribute("uList", uList);

      model.addAttribute("sentMessages", sentMessages);

      model.addAttribute("receivedMessages", receivedMessages);


    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/signin";

    }

    return "account";
  }

  @RequestMapping(path = {"/up"}, method = RequestMethod.GET)
  public String showAccountUp(Model model) throws Exception {
    try {

      this.user = (User) model.asMap().get("signedUpUser");

      List<User> uList = userService.getAllUnDeleted();

      List<Team> tList = teamService.getAll();

      List<Message> sentMessages = messageService.getBySenderId(user.getId());

      List<Message> receivedMessages = messageService.getByReceiverId(user.getId());

      model.addAttribute("user", user);

      model.addAttribute("tList", tList);

      model.addAttribute("uList", uList);

      model.addAttribute("sentMessages", sentMessages);

      model.addAttribute("receivedMessages", receivedMessages);


    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/signup";

    }

    return "account";
  }

  @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
  public String showAccount(Model model) throws Exception {
    try {

      List<User> uList = userService.getAllUnDeleted();

      List<Team> tList = teamService.getAll();

      List<Message> sentMessages = messageService.getBySenderId(user.getId());

      List<Message> receivedMessages = messageService.getByReceiverId(user.getId());

      model.addAttribute("user", user);

      model.addAttribute("tList", tList);

      model.addAttribute("uList", uList);

      model.addAttribute("sentMessages", sentMessages);

      model.addAttribute("receivedMessages", receivedMessages);


    } catch (Exception e) {
      e.printStackTrace();
      return "redirect:/signin";

    }

    return "account";
  }

  @RequestMapping(path = {"/sent"}, method = RequestMethod.GET)
  public String sentMessage(@RequestParam("receiverId") String receiverId,
      @RequestParam("message") String message)
      throws Exception {

    messageService.sendMessage(user, receiverId, message);

    return "redirect:/account/";

  }

}
