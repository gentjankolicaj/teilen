package org.teilen.web.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.dao.UserAdressDao;
import org.teilen.web.exception.UserAddressException;
import org.teilen.web.exception.UserException;
import org.teilen.web.model.Country;
import org.teilen.web.model.User;
import org.teilen.web.model.UserAddress;
import org.teilen.web.service.UserAdressService;

/**
 * @author gentjan kolicaj
 */
@Service
@Transactional
public class UserAdressServiceImpl implements UserAdressService {

  private final UserAdressDao userAdressDao;

  @Autowired
  public UserAdressServiceImpl(UserAdressDao userAdressDao) {
    super();
    this.userAdressDao = userAdressDao;
  }

  @Override
  public List<UserAddress> getAll() throws Exception {
    return userAdressDao.findAll();
  }

  @Override
  public List<UserAddress> getAllById(List<Long> ids) throws Exception {
    return userAdressDao.findAllById(ids);
  }

  @Override
  public UserAddress getById(Long id) throws Exception {
    return userAdressDao.findById(id);
  }

  @Override
  public UserAddress create(UserAddress entity) throws Exception {
    return userAdressDao.save(entity);
  }

  @Override
  public List<UserAddress> createAll(List<UserAddress> entities) throws Exception {
    return userAdressDao.saveAll(entities);
  }

  @Override
  public UserAddress edit(UserAddress entity) throws Exception {
    return userAdressDao.update(entity);
  }

  @Override
  public List<UserAddress> editAll(List<UserAddress> entities) throws Exception {
    return userAdressDao.updateAll(entities);
  }

  @Override
  public void deleteById(Long id) throws Exception {
    userAdressDao.deleteById(id);

  }

  @Override
  public void delete(UserAddress entity) throws Exception {
    userAdressDao.delete(entity);

  }

  @Override
  public void deleteAll(List<UserAddress> entities) throws Exception {
    userAdressDao.deleteAll(entities);

  }

  @Override
  public boolean existById(Long id) throws Exception {
    return userAdressDao.existById(id);
  }

  @Override
  public List<UserAddress> getByCityLike(String cityName) throws Exception {
    return userAdressDao.findByCityLike(cityName);
  }

  @Override
  public List<UserAddress> getByStreetLike(String street) throws Exception {
    return userAdressDao.findByStreetLike(street);
  }

  @Override
  public List<UserAddress> getByCountryId(Long countryId) throws Exception {
    return userAdressDao.findByCountryId(countryId);
  }

  @Override
  public UserAddress createAdress(User user, String street, String city, Country country)
      throws Exception {
    UserAddress userAddress = new UserAddress();
    userAddress.setUser(user);
    userAddress.setStreet(street);
    userAddress.setCity(city);
    userAddress.setCountry(country);
    userAddress.setCreationDate(new Date());
    userAddress.setModificationDate(null);

    return userAdressDao.save(userAddress);
  }

  @Override
  public UserAddress editAdress(Long adressId, String street, String city, Country country)
      throws Exception {
    UserAddress userAddress = userAdressDao.findById(adressId);
    if (userAddress == null) {
      throw new UserAddressException("User adress with id " + adressId + " doesn't exists.");
    }

    userAddress.setStreet(street);
    userAddress.setCity(city);
    userAddress.setCountry(country);
    userAddress.setModificationDate(new Date());
    return userAdressDao.update(userAddress);
  }

  @Override
  public UserAddress editAdress(UserAddress userAddress, String street, String city,
      Country country) throws Exception {
    if (userAddress == null) {
      throw new UserAddressException("User adress doesn't exists.Reference is null");
    }

    userAddress.setStreet(street);
    userAddress.setCity(city);
    userAddress.setCountry(country);
    userAddress.setModificationDate(new Date());
    return userAdressDao.update(userAddress);

  }

  @Override
  public void deleteAdress(User user) throws Exception {
    if (user == null) {
      throw new UserException("User dosen't exists.Reference is null");
    }

    UserAddress userAddress = user.getUserAddress();
    userAdressDao.delete(userAddress);

  }


}
