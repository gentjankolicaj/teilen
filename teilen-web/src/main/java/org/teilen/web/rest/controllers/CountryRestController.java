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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.teilen.web.exception.IdException;
import org.teilen.web.exception.NullReferenceException;
import org.teilen.web.model.Country;
import org.teilen.web.service.CountryService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(CountryRestController.URI)
class CountryRestController {

  protected static final String URI = "/api/countries";

  private final CountryService countryService;

  @Autowired
  public CountryRestController(CountryService countryService) {
    super();
    this.countryService = countryService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Country> getAllCountries() throws Exception {
    return countryService.getAll();

  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Country getCountryById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long countryId = Long.parseLong(id);
      return countryService.getById(countryId);
    } else {
      throw new IdException("Country id " + id + " is not parsable to long.Must be integer !!!");
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Country saveCountry(@RequestBody Country newCountry) throws Exception {
    if (newCountry == null) {
      throw new NullReferenceException("Country reference binded is null.");
    }

    return countryService.create(newCountry);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Country editCountry(@PathVariable("id") String id, @RequestBody Country country)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long countryId = Long.parseLong(id);
      if (country == null) {
        throw new NullReferenceException("Country reference binded is null.");
      }
      country.setId(countryId);
      return countryService.edit(country);

    } else {
      throw new IdException("Country id" + id + " is not parsable to long.Must be integer");
    }

  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteCountry(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long countryId = Long.parseLong(id);
      countryService.deleteById(countryId);
    } else {
      throw new IdException("Country id" + id + " is not parsable to long.Must be integer");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("*")
  public ResponseEntity<Object> unMappedRequests() {
    return new ResponseEntity<>("Wrong URI or wrong request method", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }
}
