package org.teilen.web.service;

import org.teilen.web.model.Organization;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface OrganizationService {


    public abstract List<Organization> getAll() throws Exception;

    public abstract List<Organization> getAllById(List<Long> ids) throws Exception;

    public abstract Organization getById(Long id) throws Exception;

    public abstract Organization create(Organization entity) throws Exception;

    public abstract List<Organization> createAll(List<Organization> entities) throws Exception;

    public abstract Organization edit(Organization entity) throws Exception;

    public abstract List<Organization> editAll(List<Organization> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Organization entity) throws Exception;

    public abstract void deleteAll(List<Organization> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;
}
