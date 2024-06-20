package org.teilen.web.service;

import java.util.List;
import org.teilen.web.model.Country;
import org.teilen.web.model.User;
import org.teilen.web.model.UserAddress;

/**
 * @author gentjan kolicaj
 */
public interface UserAdressService {

  List<UserAddress> getAll() throws Exception;

  List<UserAddress> getAllById(List<Long> ids) throws Exception;

  UserAddress getById(Long id) throws Exception;

  UserAddress create(UserAddress entity) throws Exception;

  List<UserAddress> createAll(List<UserAddress> entities) throws Exception;

  UserAddress edit(UserAddress entity) throws Exception;

  List<UserAddress> editAll(List<UserAddress> entities) throws Exception;

  void deleteById(Long id) throws Exception;

  void delete(UserAddress entity) throws Exception;

  void deleteAll(List<UserAddress> entities) throws Exception;

  boolean existById(Long id) throws Exception;


  //=============================================================================
  List<UserAddress> getByCityLike(String cityName) throws Exception;

  List<UserAddress> getByStreetLike(String street) throws Exception;

  List<UserAddress> getByCountryId(Long countryId) throws Exception;

  UserAddress createAdress(User user, String street, String city, Country country)
      throws Exception;

  UserAddress editAdress(Long adressId, String street, String city, Country country)
      throws Exception;

  UserAddress editAdress(UserAddress adress, String street, String city,
      Country country) throws Exception;

  void deleteAdress(User user) throws Exception;

}
