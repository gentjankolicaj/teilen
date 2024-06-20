package org.teilen.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "credential")
@Data
@NoArgsConstructor
public class Credential implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -6088590874742961507L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;


  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "password")
  private String password;


  @Column(name = "hash_function")
  private String hashFunction;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "modification_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;


}
