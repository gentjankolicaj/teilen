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
