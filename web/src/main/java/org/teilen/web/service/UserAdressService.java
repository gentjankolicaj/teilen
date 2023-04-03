package org.teilen.web.service;

import org.teilen.web.model.Country;
import org.teilen.web.model.User;
import org.teilen.web.model.UserAddress;

import java.util.List;

/**
 * @author gentjan kolicaj
 */
public interface UserAdressService {

    public abstract List<UserAddress> getAll() throws Exception;

    public abstract List<UserAddress> getAllById(List<Long> ids) throws Exception;

    public abstract UserAddress getById(Long id) throws Exception;

    public abstract UserAddress create(UserAddress entity) throws Exception;

    public abstract List<UserAddress> createAll(List<UserAddress> entities) throws Exception;

    public abstract UserAddress edit(UserAddress entity) throws Exception;

    public abstract List<UserAddress> editAll(List<UserAddress> entities) throws Exception;

    public abstract void deleteById(Long id) throws Exception;

    public abstract void delete(UserAddress entity) throws Exception;

    public abstract void deleteAll(List<UserAddress> entities) throws Exception;

    public abstract boolean existById(Long id) throws Exception;


    //=============================================================================
    public abstract List<UserAddress> getByCityLike(String cityName) throws Exception;

    public abstract List<UserAddress> getByStreetLike(String street) throws Exception;

    public abstract List<UserAddress> getByCountryId(Long countryId) throws Exception;

    public abstract UserAddress createAdress(User user, String street, String city, Country country) throws Exception;

    public abstract UserAddress editAdress(Long adressId, String street, String city, Country country) throws Exception;

    public abstract UserAddress editAdress(UserAddress adress, String street, String city, Country country) throws Exception;

    public abstract void deleteAdress(User user) throws Exception;

}
