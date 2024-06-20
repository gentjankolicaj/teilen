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
import org.teilen.web.model.Currency;
import org.teilen.web.service.CurrencyService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(CurrencyRestController.URI)
class CurrencyRestController {

  protected final static String URI = "/api/currencies";

  private final CurrencyService currencyService;

  @Autowired
  public CurrencyRestController(CurrencyService currencyService) {
    super();
    this.currencyService = currencyService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Currency> getAllCurrencies() throws Exception {
    return currencyService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Currency getCurrencyById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long currencyId = Long.parseLong(id);
      return currencyService.getById(currencyId);
    } else {
      throw new IdException("Currency id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = "application/json")
  public List<Currency> getCurrencyByNameLike(@RequestParam("name") String name)
      throws Exception { // /api/currencies?name=example

    return currencyService.getCurrencyByNameLike(name);
  }


  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Currency saveCurrency(@RequestBody Currency currency) throws Exception {
    if (currency == null) {
      throw new NullReferenceException("Currency reference binded is null.");
    }

    return currencyService.create(currency);
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Currency editCurrency(@PathVariable("id") String id, @RequestBody Currency currency)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long currencyId = Long.parseLong(id);
      if (currency == null) {
        throw new NullReferenceException("Currency reference binded is null.");
      }
      currency.setId(currencyId);
      return currencyService.edit(currency);
    } else {
      throw new IdException("Currency id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteCurrency(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long credentialId = Long.parseLong(id);
      currencyService.deleteById(credentialId);
    } else {
      throw new IdException("Currency id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }


}
