package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.Industry;


/**
 * @author gentjan kolicaj
 */
public interface IndustryDao extends CrudDao<Industry, Long> {

  List<Industry> findByNameLike(String name) throws Exception;

}
