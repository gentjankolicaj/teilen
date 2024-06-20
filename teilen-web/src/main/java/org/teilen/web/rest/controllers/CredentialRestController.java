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
import org.teilen.web.model.Credential;
import org.teilen.web.service.CredentialService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(CredentialRestController.URI)
class CredentialRestController {

  protected static final String URI = "/api/credentials";

  private final CredentialService credentialService;

  @Autowired
  public CredentialRestController(CredentialService credentialService) {
    super();
    this.credentialService = credentialService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Credential> getAllCredentials() throws Exception {
    return credentialService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Credential getCredentialById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long credentialId = Long.parseLong(id);
      return credentialService.getById(credentialId);
    } else {
      throw new IdException("Credential id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"userId"}, method = RequestMethod.GET, produces = "application/json")
  public Credential getCredentialByUserId(@RequestParam("userId") String userId)
      throws Exception { // /api/credentials?userId=example
    if (NumberUtils.isParsable(userId)) {
      Long userID = Long.parseLong(userId);
      return credentialService.getByUserId(userID);
    } else {
      throw new IdException("User id " + userId + " is not parsable.Must be an integer.");
    }

  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Credential saveCredential(@RequestBody Credential credential) throws Exception {
    if (credential == null) {
      throw new NullReferenceException("Credential reference binded is null.");
    }

    return credentialService.create(credential);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Credential editCredential(@PathVariable("id") String id,
      @RequestBody Credential credential)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long credentialId = Long.parseLong(id);
      if (credential == null) {
        throw new NullReferenceException("Credential reference binded is null.");
      }
      credential.setId(credentialId);
      return credentialService.edit(credential);
    } else {
      throw new IdException("Credential id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteCredential(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long credentialId = Long.parseLong(id);
      credentialService.deleteById(credentialId);
    } else {
      throw new IdException("Credential id" + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
