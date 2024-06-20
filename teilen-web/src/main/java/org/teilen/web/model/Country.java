package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "country")
@JsonIgnoreProperties({"userAddressList", "currencies", "languages"})//just for testing
@Data
@NoArgsConstructor
public class Country implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -8846734276269089568L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "country_name")
  private String countryName;

  @Column(name = "iso_codes")
  private String isoCodes;

  @Column(name = "phone_prefix")
  private String phonePrefix;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
  private List<UserAddress> userAddressList;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "country_currency",
      joinColumns = {@JoinColumn(name = "country_id")},
      inverseJoinColumns = {@JoinColumn(name = "currency_id")})
  private List<Currency> currencies;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "country_language",
      joinColumns = @JoinColumn(name = "country_id"),
      inverseJoinColumns = @JoinColumn(name = "language_id"))
  private List<Language> languages;


}
