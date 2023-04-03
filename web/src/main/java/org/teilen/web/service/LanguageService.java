package org.teilen.web.service;

import org.teilen.web.model.Language;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface LanguageService {

    public abstract List<Language> getAll() throws Exception;

    public abstract List<Language> getAllById(List<Long> ids) throws Exception;

    public abstract Language getById(Long id) throws Exception;

    public abstract Language create(Language entity) throws Exception;

    public abstract List<Language> createAll(List<Language> entities) throws Exception;

    public abstract Language edit(Language entity) throws Exception;

    public abstract List<Language> editAll(List<Language> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Language entity) throws Exception;

    public abstract void deleteAll(List<Language> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    public abstract List<Language> getByLangLike(String lang) throws Exception;


}
