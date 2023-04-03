package org.teilen.web.dao;

import org.teilen.web.model.Currency;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface CurrencyDao extends CrudDao<Currency, Long> {

    public abstract List<Currency> findByNameLike(String name) throws Exception;

}
