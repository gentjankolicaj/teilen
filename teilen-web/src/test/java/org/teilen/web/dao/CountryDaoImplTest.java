package org.teilen.web.dao;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.teilen.web.MyAbstractWebAppContext;
import org.teilen.web.model.Country;

@Transactional
//added this because at dao layer we don't have transaction help form spring.I have putted at service layer
@Slf4j
public class CountryDaoImplTest extends MyAbstractWebAppContext {

  @Autowired
  public CountryDao countryDao;


  @Test
  public void testFindAll() throws Exception {
    List<Country> list = countryDao.findAll();
    for (Country country : list) {
      System.out.println(country.toString());
    }

  }

  @Test
  public void testFindById() throws Exception {
    Country country = countryDao.findById(Long.valueOf(1));
    System.out.println(country.toString());

  }

  @Test
  public void testSave() throws Exception {
    Country country = new Country();
    country.setCountryName("Test country");
    country.setIsoCodes("TEST");
    country.setPhonePrefix("010101");
    countryDao.save(country);


  }

  @Test
  public void testUpdate() throws Exception {
    Country country = new Country();
    country.setCountryName("Test country");
    country.setIsoCodes("TEST");
    country.setPhonePrefix("010101");

    countryDao.save(country);


  }

  @Test
  public void testDelete() throws Exception {

  }

  @Test
  public void testDeleteById() throws Exception {
    countryDao.deleteById(Long.valueOf(1));

  }


}
