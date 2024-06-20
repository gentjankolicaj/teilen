package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "message")
@JsonIgnoreProperties({"sender", "receiver"})
@Data
@NoArgsConstructor
public class Message implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -1206420511651841025L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "team_id")
  private Team team;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sender")
  private User sender;


  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "receiver")
  private User receiver;


  @Column(name = "message")
  private String message;


  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "deletion_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date deletionDate;


  @Column(name = "modification_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationDate;


  @Column(name = "modified_by")
  private Long modifiedBy;


}
