package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "authentication_attempt")
@JsonIgnoreProperties({"user"})
@Data
@NoArgsConstructor
public class AuthenticationAttempt implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -3600473251128792825L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;


  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "email_or_username")
  private String emailOrUsername;

  @Column(name = "password")
  private String password;

  @Column(name = "status")
  private String status;

  @Column(name = "platform")
  private String platform;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;


}
