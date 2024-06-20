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
import org.teilen.web.exception.ParamException;
import org.teilen.web.model.UserContact;
import org.teilen.web.service.UserContactService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(UserContactRestController.URI)
class UserContactRestController {

  protected final static String URI = "/api/user_contacts";

  private final UserContactService userContactService;

  @Autowired
  public UserContactRestController(UserContactService userContactService) {
    super();
    this.userContactService = userContactService;
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserContact> getAllUserContact() throws Exception {
    return userContactService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public UserContact getUserContactById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userContactId = Long.parseLong(id);
      return userContactService.getById(userContactId);
    } else {
      throw new IdException("User contact id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"email"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserContact> getUserContactByEmailLike(@RequestParam("email") String email)
      throws Exception {
    return userContactService.getByEmailLike(email);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"tel"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserContact> getUserContactByTelephone(@RequestParam("tel") String tel)
      throws Exception {
    if (NumberUtils.isParsable(tel)) {
      Long telephone = Long.parseLong(tel);
      return userContactService.getByTelephone(telephone);
    } else {
      throw new ParamException("Tel " + tel + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"cel"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserContact> getUserContactByCel(@RequestParam("cel") String cel) throws Exception {
    if (NumberUtils.isParsable(cel)) {
      Long celular = Long.parseLong(cel);
      return userContactService.getByMobile(celular);
    } else {
      throw new ParamException("Cel " + cel + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public UserContact saveUserContact(@RequestBody UserContact userContact) throws Exception {
    if (userContact == null) {
      throw new NullReferenceException("User contact reference binded is null.");
    }

    return userContactService.create(userContact);
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public UserContact editUserContact(@PathVariable("id") String id,
      @RequestBody UserContact userContact)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userContactId = Long.parseLong(id);
      if (userContact == null) {
        throw new NullReferenceException("User contact reference binded is null.");
      }
      userContact.setId(userContactId);

      return userContactService.edit(userContact);
    } else {
      throw new IdException("User contact id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteUserContact(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userContactId = Long.parseLong(id);
      userContactService.deleteById(userContactId);
    } else {
      throw new IdException("User contact id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }


}
