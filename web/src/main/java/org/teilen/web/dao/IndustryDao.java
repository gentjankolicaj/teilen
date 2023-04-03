package org.teilen.web.dao;

import org.teilen.web.model.Industry;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface IndustryDao extends CrudDao<Industry, Long> {

    public abstract List<Industry> findByNameLike(String name) throws Exception;

}
