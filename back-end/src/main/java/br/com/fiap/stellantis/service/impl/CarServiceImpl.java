package br.com.fiap.stellantis.service.impl;

import br.com.fiap.stellantis.model.Car;
import br.com.fiap.stellantis.model.Regional;
import br.com.fiap.stellantis.model.dto.CarDTO;
import br.com.fiap.stellantis.repository.CarRepository;
import br.com.fiap.stellantis.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        List<Car> cars = carRepository.findAll();
        return  cars;
    }

    @Override
    public Car findById(int id) {
        return carRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car insert(CarDTO carDTO) {
        Car car = new Car(carDTO);

        Regional regional = new Regional();
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        regional.setLocation(car.getEnvironment().getLocation());
        regional.setOperationDate(formatador.format(data));

        car.getEnvironment().getLocation().setRegional(regional);

        return carRepository.save(car);
    }

    @Override
    public Car update(CarDTO carDTO, int id){
        Car car = new Car(carDTO);

        car.setId(id);
        return carRepository.save(car);
    }
}
