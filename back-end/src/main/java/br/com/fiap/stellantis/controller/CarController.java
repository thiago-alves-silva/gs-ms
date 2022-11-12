package br.com.fiap.stellantis.controller;

import br.com.fiap.stellantis.model.Car;
import br.com.fiap.stellantis.model.dto.CarDTO;
import br.com.fiap.stellantis.service.CarService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    private CarService carService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> insert(@RequestBody CarDTO carDTO){

       Car car = carService.insert(carDTO);

       return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.findAll();

        return ResponseEntity.ok(cars);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Object> delete(@PathVariable int id){

        carService.deleteById(id);

        return ResponseEntity.ok("Dados Excluidos");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> getById(@PathVariable int id){

        Car car = carService.findById(id);

        return ResponseEntity.ok(car);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Car> update(@PathVariable int id, @RequestBody CarDTO carDTO){

        Car car = carService.update(carDTO, id);

        return ResponseEntity.ok(car);
    }
}
