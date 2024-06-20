package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Industry;


/**
 * @author gentjan kolicaj
 */
public interface IndustryService {

  List<Industry> getAll() throws Exception;

  List<Industry> getAllById(List<Long> ids) throws Exception;

  Industry getById(Long id) throws Exception;

  Industry create(Industry entity) throws Exception;

  List<Industry> createAll(List<Industry> entities) throws Exception;

  Industry edit(Industry entity) throws Exception;

  List<Industry> editAll(List<Industry> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(Industry entity) throws Exception;

  void deleteAll(List<Industry> entities) throws Exception;

  boolean existById(Long id) throws Exception;

  //=================================
  List<Industry> getIndustryByNameLike(String name) throws Exception;

}
