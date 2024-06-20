package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Language;


/**
 * @author gentjan kolicaj
 */
public interface LanguageDao extends CrudDao<Language, Long> {

  List<Language> findByLangLike(String lang) throws Exception;

}
