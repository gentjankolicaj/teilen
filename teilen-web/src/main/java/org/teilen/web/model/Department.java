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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
public class Department implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 5098269253740722343L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "organization_id")
  private Organization organization;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id")
  private User creator;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "deletion_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletionDate;

  @Column(name = "modification_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
  //join is done at field department in class Team,with column Team.department_id
  private List<Team> teams;


}
