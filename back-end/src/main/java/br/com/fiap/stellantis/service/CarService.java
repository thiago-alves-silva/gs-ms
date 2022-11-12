package br.com.fiap.stellantis.service;

import br.com.fiap.stellantis.model.Car;
import br.com.fiap.stellantis.model.dto.CarDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {


    List<Car> findAll();

    Car findById(int id);

    void deleteById(int id);

    Car insert(CarDTO carDTO);

    Car update(CarDTO carDTO, int id);


}
