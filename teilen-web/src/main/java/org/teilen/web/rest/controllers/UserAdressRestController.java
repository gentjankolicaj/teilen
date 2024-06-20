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
import org.teilen.web.model.UserAddress;
import org.teilen.web.service.UserAdressService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(UserAdressRestController.URI)
class UserAdressRestController {

  protected final static String URI = "/api/user_adresses";

  private final UserAdressService userAdressService;

  @Autowired
  public UserAdressRestController(UserAdressService userAdressService) {
    super();
    this.userAdressService = userAdressService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserAddress> getAllUserAdresses() throws Exception {
    return userAdressService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public UserAddress getUserAdressById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userAdressId = Long.parseLong(id);
      return userAdressService.getById(userAdressId);
    } else {
      throw new IdException("User adress id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"street"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserAddress> getUserAdressesByStreetLike(@RequestParam("street") String street)
      throws Exception {
    return userAdressService.getByStreetLike(street);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"city"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserAddress> getUserAdressesByCity(@RequestParam("city") String city)
      throws Exception {
    return userAdressService.getByCityLike(city);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"countryId"}, method = RequestMethod.GET, produces = "application/json")
  public List<UserAddress> getUserAdressesByCountryId(@RequestParam("countryId") String countryId)
      throws Exception {
    if (NumberUtils.isParsable(countryId)) {
      Long id = Long.parseLong(countryId);
      return userAdressService.getByCountryId(id);
    } else {
      throw new IdException("Country id " + countryId + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public UserAddress saveUserAdress(@RequestBody UserAddress userAddress) throws Exception {
    if (userAddress == null) {
      throw new NullReferenceException("User adress reference binded is null.");
    }

    return userAdressService.create(userAddress);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public UserAddress editUserAdress(@PathVariable("id") String id,
      @RequestBody UserAddress userAddress)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userAdressId = Long.parseLong(id);
      if (userAddress == null) {
        throw new NullReferenceException("User adress reference binded is null.");
      }
      userAddress.setId(userAdressId);

      return userAdressService.edit(userAddress);
    } else {
      throw new IdException("User adress id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteUserAdress(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long userAdressId = Long.parseLong(id);
      userAdressService.deleteById(userAdressId);
    } else {
      throw new IdException("User adress id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
