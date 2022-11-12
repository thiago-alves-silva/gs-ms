package br.com.fiap.stellantis.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String district;
    private String city;
    private String state;

    @OneToOne(mappedBy = "location")
    @JsonBackReference
    private Environment environment;

    @JoinColumn(name = "regional_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Regional regional;

}
