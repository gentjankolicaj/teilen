package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.teilen.web.enums.Sex;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"sentMessages", "receivedMessages", "teams", "groups", "credential"})
//just for testing purposes
@Data
@NoArgsConstructor
public class User implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -5619598654091613554L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "sex")
  @Enumerated
  private Sex sex;


  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;


  @Column(name = "deletion_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletionDate;


  @Column(name = "modification_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;


  @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
  private Credential credential;

  @JsonBackReference
  @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
  private UserAddress userAddress;

  @JsonBackReference
  @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
  private UserContact userContact;


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
  private List<Team> teams;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
  private List<Message> sentMessages;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver")
  private List<Message> receivedMessages;


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
  private List<Group> groups;


}
