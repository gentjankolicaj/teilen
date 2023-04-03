package org.teilen.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
