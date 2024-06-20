package org.teilen.web.rest.controllers;

import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.teilen.web.exception.IdException;
import org.teilen.web.exception.NullReferenceException;
import org.teilen.web.model.Message;
import org.teilen.web.service.MessageService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(MessageRestController.URI)
class MessageRestController {

  protected final static String URI = "/api/messages";

  private final MessageService messageService;

  @Autowired
  public MessageRestController(MessageService messageService) {
    super();
    this.messageService = messageService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Message> getAllMessages() throws Exception {
    return messageService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = "deleted", method = RequestMethod.GET, produces = "application/json")
  public List<Message> getAllDeletedMessages(@RequestParam("deleted") String deleted)
      throws Exception {
    if (deleted.equalsIgnoreCase("true")) {
      return messageService.getAllDeleted();
    } else {
      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = "undeleted", method = RequestMethod.GET, produces = "application/json")
  public List<Message> getAllUnDeletedMessages(@RequestParam("undeleted") String undeleted)
      throws Exception {
    if (undeleted.equalsIgnoreCase("true")) {
      return messageService.getAllUnDeleted();
    } else {
      return null;
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Message getMessageById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long messageId = Long.parseLong(id);
      return messageService.getById(messageId);
    } else {
      throw new IdException("Message id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"teamId"}, method = RequestMethod.GET, produces = "application/json")
  public List<Message> getMessagesByTeamId(@RequestParam("teamId") String teamId)
      throws Exception { // /api/messages?teamId=12
    if (NumberUtils.isParsable(teamId)) {
      Long id = Long.parseLong(teamId);
      return messageService.getByTeamId(id);
    } else {
      throw new IdException(
          "Team id " + teamId + " is not valid and parsable.It must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"senderId"}, method = RequestMethod.GET, produces = "application/json")
  public List<Message> getMessagesBySenderId(@RequestParam("senderId") String senderId)
      throws Exception { // /api/messages?senderId=12
    if (NumberUtils.isParsable(senderId)) {
      Long id = Long.parseLong(senderId);
      return messageService.getBySenderId(id);
    } else {
      throw new IdException(
          "Sender id " + senderId + " is not valid and parsable.It must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {
      "receiverId"}, method = RequestMethod.GET, produces = "application/json")
  public List<Message> getMessagesByReceiverId(@RequestParam("receiverId") String receiverId)
      throws Exception { // /api/messages?receiverId=12
    if (NumberUtils.isParsable(receiverId)) {
      Long id = Long.parseLong(receiverId);
      return messageService.getByReceiverId(id);
    } else {
      throw new IdException(
          "Receiver id " + receiverId + " is not valid and parsable.It must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Message saveMessage(@RequestBody Message message) throws Exception {
    if (message == null) {
      throw new NullReferenceException("Message reference binded is null.");
    }

    return messageService.create(message);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Message editMessage(@PathVariable("id") String id, @RequestBody Message message)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long messageId = Long.parseLong(id);
      if (message == null) {
        throw new NullReferenceException("Message reference binded is null.");
      }
      message.setId(messageId);

      return messageService.edit(message);
    } else {
      throw new IdException("Message id " + id + " is not parsable.Must be an integer.");
    }
  }

  // =====================================================================

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(params = {"teamId",
      "delete"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteMessagesByTeamId(@RequestParam("teamId") String teamId,
      @RequestParam("delete") String delete)
      throws Exception { // /api/messages?teamId=12&delete=true
    if (NumberUtils.isParsable(teamId)) {
      Long id = Long.parseLong(teamId);
      if (delete.equalsIgnoreCase("true")) {
        messageService.deleteByTeamId(id);
      }

    } else {
      throw new IdException(
          "Team id " + teamId + " is not valid and parsable.It must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(params = {"senderId",
      "delete"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteMessagesBySenderId(@RequestParam("senderId") String senderId,
      @RequestParam("delete") String delete)
      throws Exception { // /api/messages?senderId=12&delete=true
    if (NumberUtils.isParsable(senderId)) {
      Long id = Long.parseLong(senderId);
      if (delete.equalsIgnoreCase("true")) {
        messageService.deleteBySenderId(id);
      }
    } else {
      throw new IdException(
          "Sender id " + senderId + " is not valid and parsable.It must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(params = {"receiverId",
      "delete"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteMessagesByReceiverId(@RequestParam("receiverId") String receiverId,
      @RequestParam("delete") String delete)
      throws Exception { // /api/messages?receiverId=12&delete=true
    if (NumberUtils.isParsable(receiverId)) {
      Long id = Long.parseLong(receiverId);

      if (delete.equalsIgnoreCase("true")) {
        messageService.deleteByReceiverId(id);
      }
    } else {
      throw new IdException(
          "Receiver id " + receiverId + " is not valid and parsable.It must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteMessage(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long messageId = Long.parseLong(id);
      messageService.deleteById(messageId);
    } else {
      throw new IdException("Message id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
