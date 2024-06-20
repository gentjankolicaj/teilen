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
@Table(name = "privilege")
@Data
@NoArgsConstructor
public class Privilege implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -7114873212619445315L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "identifier")
  private String identifier;

  @Column(name = "description")
  private String description;


  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "privileges")
  private List<Group> groups;


}
