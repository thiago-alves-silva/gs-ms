package br.com.fiap.stellantis.model.dto;

import br.com.fiap.stellantis.model.Environment;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {

    private String brand;
    private String model;
    private String plate;
    private String km;
    private Environment environment;
}
