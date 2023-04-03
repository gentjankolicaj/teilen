package org.teilen.web.service;

import org.teilen.web.model.User;
import org.teilen.web.model.UserContact;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface UserContactService {

    public abstract List<UserContact> getAll() throws Exception;

    public abstract List<UserContact> getAllById(List<Long> ids) throws Exception;

    public abstract UserContact getById(Long id) throws Exception;

    public abstract UserContact create(UserContact entity) throws Exception;

    public abstract List<UserContact> createAll(List<UserContact> entities) throws Exception;

    public abstract UserContact edit(UserContact entity) throws Exception;

    public abstract List<UserContact> editAll(List<UserContact> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(UserContact entity) throws Exception;

    public abstract void deleteAll(List<UserContact> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;

    //======================================================================
    public abstract UserContact getByEmail(String email) throws Exception;

    public abstract List<UserContact> getByEmailLike(String email) throws Exception;

    public abstract List<UserContact> getByTelephone(Long telephone) throws Exception;

    public abstract List<UserContact> getByMobile(Long mobile) throws Exception;

    public abstract UserContact createContact(User user, String email, Long telephone, Long mobile, String postalCode) throws Exception;

    public abstract UserContact editContact(Long contactId, String email, Long telephone, Long mobile, String postalCode) throws Exception;

    public abstract UserContact editContact(UserContact userContact, String email, Long telephone, Long mobile, String postalCode) throws Exception;

    public abstract void deleteDeleteContact(User user) throws Exception;

}
