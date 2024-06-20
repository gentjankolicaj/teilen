package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Language;

/**
 * @author gentjan kolicaj
 */
public interface LanguageService {

  List<Language> getAll() throws Exception;

  List<Language> getAllById(List<Long> ids) throws Exception;

  Language getById(Long id) throws Exception;

  Language create(Language entity) throws Exception;

  List<Language> createAll(List<Language> entities) throws Exception;

  Language edit(Language entity) throws Exception;

  List<Language> editAll(List<Language> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Language entity) throws Exception;

  void deleteAll(List<Language> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  List<Language> getByLangLike(String lang) throws Exception;


}
