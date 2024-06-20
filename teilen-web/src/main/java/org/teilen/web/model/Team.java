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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
public class Team implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -5617465605585772145L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "department_id")
  private Department department;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "creator_id")
  private User creator;


  @Column(name = "name")
  private String name;


  @Column(name = "description")
  private String description;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_teams",
      joinColumns = @JoinColumn(name = "team_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
  private List<Message> messages;


  @Column(name = "creation_date")
  private Date creationDate;


  @Column(name = "deletion_date")
  private Date deletionDate;

  @Column(name = "modification_date")
  private Date modificationDate;

}

