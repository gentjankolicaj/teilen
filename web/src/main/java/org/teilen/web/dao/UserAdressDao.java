package org.teilen.web.dao;

import org.teilen.web.model.UserAddress;

import java.util.List;


/**
 * @author gentjan kolicaj
 */
public interface UserAdressDao extends CrudDao<UserAddress, Long> {

    public abstract List<UserAddress> findByCityLike(String cityName) throws Exception;

    public abstract List<UserAddress> findByStreetLike(String street) throws Exception;

    public abstract List<UserAddress> findByCountryId(Long countryId) throws Exception;

}
