package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
