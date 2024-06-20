package org.teilen.web.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:mysql.properties")
@Slf4j
public class DatabaseConfig {

  private final Environment env;

  @Autowired
  public DatabaseConfig(Environment env) {
    this.env = env;
  }

  // define a bean for our datasource
  @Bean
  public DataSource getPooledDataSource() {
    // create connection pool
    ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();

    // set the jdbc driver
    try {
      pooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
    } catch (PropertyVetoException exc) {
      throw new RuntimeException(exc);
    }

    // for sanity's sake, let's log url and user ... just to make sure we are reading the data
    log.info("--------------------------------------------------------------------");
    log.info("jdbc.driver=" + env.getProperty("jdbc.driver"));
    log.info("jdbc.url=" + env.getProperty("jdbc.url"));
    log.info("jdbc.username=" + env.getProperty("jdbc.username"));
    log.info("jdbc.password=" + env.getProperty("jdbc.password"));
    log.info("--------------------------------------------------------------------");

    // set database connection props
    pooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
    pooledDataSource.setUser(env.getProperty("jdbc.username"));
    pooledDataSource.setPassword(env.getProperty("jdbc.password"));

    // set connection pool props
    pooledDataSource.setInitialPoolSize(
        getIntProperty("connection.pool.initialPoolSize"));

    pooledDataSource.setMinPoolSize(
        getIntProperty("connection.pool.minPoolSize"));

    pooledDataSource.setMaxPoolSize(
        getIntProperty("connection.pool.maxPoolSize"));

    pooledDataSource.setMaxIdleTime(
        getIntProperty("connection.pool.maxIdleTime"));

    return pooledDataSource;
  }

  // need a helper method
  // read environment property and convert to int
  private int getIntProperty(String propName) {
    // now convert to int
    return Integer.parseInt(env.getProperty(propName));
  }

  private Properties getHibernateProperties() {
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
    props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    return props;
  }


  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    // create session factory's
    LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();

    // set the properties
    localSessionFactory.setDataSource(getPooledDataSource());
    localSessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
    localSessionFactory.setHibernateProperties(getHibernateProperties());

    return localSessionFactory;
  }


  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    // setup transaction manager based on session factory
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);

    return txManager;
  }
}
