package org.teilen.web.service;

import org.teilen.web.model.Country;
import org.teilen.web.model.Currency;
import org.teilen.web.model.Language;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface CountryService {

    public abstract List<Country> getAll() throws Exception;

    public abstract List<Country> getAllById(List<Long> ids) throws Exception;

    public abstract Country getById(Long id) throws Exception;

    public abstract Country create(Country entity) throws Exception;

    public abstract List<Country> createAll(List<Country> entities) throws Exception;

    public abstract Country edit(Country entity) throws Exception;

    public abstract List<Country> editAll(List<Country> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Country entity) throws Exception;

    public abstract void deleteAll(List<Country> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    public abstract Country setCurrency(Country country, Currency currency) throws Exception;

    public abstract Country setLanguage(Country country, Language language) throws Exception;


}
