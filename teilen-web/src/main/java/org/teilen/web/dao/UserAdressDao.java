package org.teilen.web.dao;

import java.util.List;
import org.teilen.web.model.UserAddress;


/**
 * @author gentjan kolicaj
 */
public interface UserAdressDao extends CrudDao<UserAddress, Long> {

  List<UserAddress> findByCityLike(String cityName) throws Exception;

  List<UserAddress> findByStreetLike(String street) throws Exception;

  List<UserAddress> findByCountryId(Long countryId) throws Exception;

}
