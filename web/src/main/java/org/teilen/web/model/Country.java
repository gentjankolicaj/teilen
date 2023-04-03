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
@Table(name = "country")
@JsonIgnoreProperties({"userAddressList", "currencies", "languages"})//just for testing
@Data
@NoArgsConstructor
public class Country implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8846734276269089568L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "iso_codes")
    private String isoCodes;

    @Column(name = "phone_prefix")
    private String phonePrefix;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
    private List<UserAddress> userAddressList;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "country_currency",
            joinColumns = {@JoinColumn(name = "country_id")},
            inverseJoinColumns = {@JoinColumn(name = "currency_id")})
    private List<Currency> currencies;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_language",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;


}
