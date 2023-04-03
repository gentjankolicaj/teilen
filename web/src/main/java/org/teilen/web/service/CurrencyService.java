package org.teilen.web.service;

import org.teilen.web.model.Currency;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface CurrencyService {

    public abstract List<Currency> getAll() throws Exception;

    public abstract List<Currency> getAllById(List<Long> ids) throws Exception;

    public abstract Currency getById(Long id) throws Exception;

    public abstract Currency create(Currency entity) throws Exception;

    public abstract List<Currency> createAll(List<Currency> entities) throws Exception;

    public abstract Currency edit(Currency entity) throws Exception;

    public abstract List<Currency> editAll(List<Currency> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Currency entity) throws Exception;

    public abstract void deleteAll(List<Currency> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    //======================================================

    public abstract List<Currency> getCurrencyByNameLike(String name) throws Exception;

}
