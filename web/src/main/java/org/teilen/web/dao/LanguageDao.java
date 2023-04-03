package org.teilen.web.dao;

import org.teilen.web.model.Language;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface LanguageDao extends CrudDao<Language, Long> {

    public abstract List<Language> findByLangLike(String lang) throws Exception;

}
