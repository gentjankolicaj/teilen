package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "industry")
@JsonIgnoreProperties({"organizations"})
@Data
@NoArgsConstructor
public class Industry implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8487382726403224343L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;


  @Column(name = "name")
  private String name;


  @Column(name = "description")
  private String description;


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "industry")
  private List<Organization> organizations;


}
