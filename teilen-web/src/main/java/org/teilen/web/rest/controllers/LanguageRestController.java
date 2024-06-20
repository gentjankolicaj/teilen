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
import org.teilen.web.model.Language;
import org.teilen.web.service.LanguageService;

/**
 * @author gentjan kolicaj
 */
@Controller
@RequestMapping(LanguageRestController.URI)
class LanguageRestController {

  protected static final String URI = "/api/languages";

  private final LanguageService languageService;

  @Autowired
  public LanguageRestController(LanguageService languageService) {
    this.languageService = languageService;
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"", "/", "/all",
      "/list"}, method = RequestMethod.GET, produces = "application/json")
  public List<Language> getAllLanguages() throws Exception {
    return languageService.getAll();
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET, produces = "application/json")
  public Language getLanguageById(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long languageId = Long.parseLong(id);
      return languageService.getById(languageId);
    } else {
      throw new IdException("Language id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(params = {"lang"}, method = RequestMethod.GET, produces = "application/json")
  public List<Language> getLanguageByLangLike(@RequestParam("lang") String lang)
      throws Exception { // /api/languages?lang=industryName
    return languageService.getByLangLike(lang);
  }


  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public Language saveLanguage(@RequestBody Language language) throws Exception {
    if (language == null) {
      throw new NullReferenceException("Language reference binded is null.");
    }

    return languageService.create(language);
  }


  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.PUT, produces = "application/json")
  public Language editLanguage(@PathVariable("id") String id, @RequestBody Language language)
      throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long languageId = Long.parseLong(id);
      if (language == null) {
        throw new NullReferenceException("Language reference binded is null.");
      }
      language.setId(languageId);

      return languageService.edit(language);
    } else {
      throw new IdException("Language id " + id + " is not parsable.Must be an integer.");
    }
  }


  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ResponseBody
  @RequestMapping(path = {"/{id}"}, method = RequestMethod.DELETE, produces = "application/json")
  public void deleteLanguage(@PathVariable("id") String id) throws Exception {
    if (NumberUtils.isParsable(id)) {
      Long languageId = Long.parseLong(id);
      languageService.deleteById(languageId);
    } else {
      throw new IdException("Language id " + id + " is not parsable.Must be an integer.");
    }
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @RequestMapping("/*")
  public ResponseEntity<Object> unMappedRequests() throws Exception {
    return new ResponseEntity<>("Wrong URI or request method.", new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }
}
