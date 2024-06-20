package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Currency;


/**
 * @author gentjan kolicaj
 */
public interface CurrencyDao extends CrudDao<Currency, Long> {

  List<Currency> findByNameLike(String name) throws Exception;

}
