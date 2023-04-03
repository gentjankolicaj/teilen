package org.teilen.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
