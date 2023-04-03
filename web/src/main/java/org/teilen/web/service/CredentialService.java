package org.teilen.web.service;

import org.teilen.web.model.Credential;
import org.teilen.web.model.User;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface CredentialService {

    public abstract List<Credential> getAll() throws Exception;

    public abstract List<Credential> getAllById(List<Long> ids) throws Exception;

    public abstract Credential getById(Long id) throws Exception;

    public abstract Credential create(Credential entity) throws Exception;

    public abstract List<Credential> createAll(List<Credential> entities) throws Exception;

    public abstract Credential edit(Credential entity) throws Exception;

    public abstract List<Credential> editAll(List<Credential> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(Credential entity) throws Exception;

    public abstract void deleteAll(List<Credential> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;


    //==============================================================
    public abstract Credential getByUserId(Long userId) throws Exception;

    public abstract Credential changePassword(User user, String newPassword) throws Exception;

}
