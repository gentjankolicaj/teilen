package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "user_contact")
@Data
@NoArgsConstructor
public class UserContact implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 7917770997880409954L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @JsonManagedReference
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "email")
  private String email;

  @Column(name = "telephone")
  private Long telephone;

  @Column(name = "mobile")
  private Long mobile;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "modification_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;


}
