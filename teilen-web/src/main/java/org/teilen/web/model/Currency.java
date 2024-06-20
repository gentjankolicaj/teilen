package org.teilen.web.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "currency")
@Data
@NoArgsConstructor
public class Currency implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7368579814206603090L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "currency_name")
  private String currencyName;

  @Column(name = "currency_code")
  private String currencyCode;

  @Column(name = "currency_symbol")
  private String currencySymbol;


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "currencies")
//currencies is the field identifier on class country
  private List<Country> countries;

}
