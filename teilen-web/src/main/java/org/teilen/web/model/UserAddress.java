package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "user_address")
@Data
@NoArgsConstructor
public class UserAddress implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 3964805147362067167L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @JsonManagedReference
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "city")
  private String city;

  @Column(name = "street")
  private String street;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country_id")
  private Country country;


  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "modification_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;


}
