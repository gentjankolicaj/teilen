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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "language")
@JsonIgnoreProperties({"countries"})
@Data
@NoArgsConstructor
public class Language implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 8519210466884197198L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "language")
  private String language;


  @Column(name = "code")
  private String code;


  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "languages")
  private List<Country> countries;


}
