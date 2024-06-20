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
import org.teilen.web.model.Industry;
import org.teilen.web.service.IndustryService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(IndustryRestController.URI)
class IndustryRestController {

  protected static final String URI = "/api/industries";

  private final IndustryService industryService;

  @Autowired
  public IndustryRestController(IndustryService industryService) {
    super();
    this.industryService = industryService;
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Industry> getAllIndustries() throws Exception {
    return industryService.getAll();
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Industry getIndustryById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long industryId = Long.parseLong(id);
      return industryService.getById(industryId);
    } else {
      throw new IdException("Industry id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = "application/json")
  public List<Industry> getIndustryByNameLike(@RequestParam("name") String name)
      throws Exception { // /api/industries?name=industryName
    return industryService.getIndustryByNameLike(name);
  }


  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Industry saveIndustry(@RequestBody Industry industry) throws Exception {
    if (industry == null) {
      throw new NullReferenceException("Industry reference binded is null.");
    }

    return industryService.create(industry);
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Industry editIndustry(@PathVariable("id") String id, @RequestBody Industry industry)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long industryId = Long.parseLong(id);
      if (industry == null) {
        throw new NullReferenceException("Industry reference binded is null.");
      }
      industry.setId(industryId);

      return industryService.edit(industry);
    } else {
      throw new IdException("Industry id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteIndustry(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long industryId = Long.parseLong(id);
      industryService.deleteById(industryId);
    } else {
      throw new IdException("Industry id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }


}
