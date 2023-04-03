package org.teilen.web.service;

import org.teilen.web.model.Industry;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface IndustryService {

    public abstract List<Industry> getAll() throws Exception;

    public abstract List<Industry> getAllById(List<Long> ids) throws Exception;

    public abstract Industry getById(Long id) throws Exception;

    public abstract Industry create(Industry entity) throws Exception;

    public abstract List<Industry> createAll(List<Industry> entities) throws Exception;

    public abstract Industry edit(Industry entity) throws Exception;

    public abstract List<Industry> editAll(List<Industry> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Industry entity) throws Exception;

    public abstract void deleteAll(List<Industry> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    //=================================
    public abstract List<Industry> getIndustryByNameLike(String name) throws Exception;

}
