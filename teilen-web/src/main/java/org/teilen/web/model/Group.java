package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gentjan kolicaj
 */
@Entity
@Table(name = "`group`")
@JsonIgnoreProperties({"privileges", "members"})
@Data
@NoArgsConstructor
public class Group implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 7503312750403163699L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "group_privilege",
      joinColumns = {@JoinColumn(name = "group_id")},
      inverseJoinColumns = {@JoinColumn(name = "privilege_id")})
  private List<Privilege> privileges;


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "group_members",
      joinColumns = {@JoinColumn(name = "group_id")},
      inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private List<User> members;


}
