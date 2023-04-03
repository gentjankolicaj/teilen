package org.teilen.web.service;

import org.teilen.web.model.Privilege;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface PrivilegeService {


    public abstract List<Privilege> getAll() throws Exception;

    public abstract List<Privilege> getAllById(List<Long> ids) throws Exception;

    public abstract Privilege getById(Long id) throws Exception;

    public abstract Privilege create(Privilege entity) throws Exception;

    public abstract List<Privilege> createAll(List<Privilege> entities) throws Exception;

    public abstract Privilege edit(Privilege entity) throws Exception;

    public abstract List<Privilege> editAll(List<Privilege> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Privilege entity) throws Exception;

    public abstract void deleteAll(List<Privilege> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;


    public abstract List<Privilege> getByIdentifierLike(String identifier) throws Exception;

}
