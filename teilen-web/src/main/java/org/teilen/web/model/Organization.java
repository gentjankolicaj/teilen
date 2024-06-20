package org.teilen.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "organization")
@Data
@NoArgsConstructor
public class Organization implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 4715687820277496646L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "industry_id")
  private Industry industry;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id")
  private User creator;

  @Column(name = "name")
  private String name;

  @Column(name = "city")
  private String city;

  @Column(name = "adress")
  private String adress;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country_id")
  private Country country;

  @Column(name = "url")
  private String url;

  @Column(name = "email")
  private String organizationEmail;

  @Column(name = "phone")
  private String phone;

  @Column(name = "description")
  private String description;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
  private List<Department> departments;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "deletion_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletionDate;

}
