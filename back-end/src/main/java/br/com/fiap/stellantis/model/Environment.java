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
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String temperature;
    private String airQuality;

    @OneToOne(mappedBy = "environment")
    @JsonBackReference
    private Car car;

    @JoinColumn(name = "location_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Location location;
}
