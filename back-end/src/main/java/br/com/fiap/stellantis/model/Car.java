package br.com.fiap.stellantis.model;

import br.com.fiap.stellantis.model.dto.CarDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String brand;
    private String model;
    private String plate;
    private String km;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "environment_id")
    @JsonManagedReference
    private Environment environment;

    public Car(CarDTO carDTO){
        this.brand = carDTO.getBrand();
        this.model = carDTO.getModel();
        this.plate = carDTO.getPlate();
        this.km = carDTO.getKm();
        this.environment = carDTO.getEnvironment();
    }

    public Car(){
    }


}
