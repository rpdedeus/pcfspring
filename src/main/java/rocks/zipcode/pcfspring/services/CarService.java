package rocks.zipcode.pcfspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.zipcode.pcfspring.entities.CarEntity;
import rocks.zipcode.pcfspring.repository.CarRepository;
import rocks.zipcode.pcfspring.requests.AddNewCarRequest;
import rocks.zipcode.pcfspring.requests.UpdateCarRequest;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarEntity> findAll() {

        return carRepository.findAll();
    }

    public List<CarEntity> findByMake(String make) {

        return carRepository.findByMake(make);
    }

    public List<CarEntity> findByModel(String model) {

        return carRepository.findByModel(model);
    }

    public List<CarEntity> findByMakeAndModel(String make, String model) {

        return carRepository.findByMakeAndModel(make, model);
    }

    public CarEntity addNewCar(AddNewCarRequest request) {

        // todo validate request values

        String make = request.getMake();
        String model = request.getModel();
        String color = request.getColor();
        long year = request.getYear();

        CarEntity carEntity = new CarEntity(make, model, color, year);
        return carRepository.save(carEntity);
    }

    public CarEntity updateCar(UpdateCarRequest request) {

        // todo validate request values

        Long id = request.getId();
        String make = request.getMake();
        String model = request.getModel();
        String color = request.getColor();
        String year = request.getYear();

        Optional<CarEntity> carEntityOptional = carRepository.findById(id);

        CarEntity carEntity = null;

        if(carEntityOptional.isPresent()) {
            carEntity = carEntityOptional.get();
            carEntity.setColor(null == color ? carEntity.getColor() : color);
            carEntity.setMake(null == make ? carEntity.getMake() : make);
            carEntity.setModel(null == model ? carEntity.getModel() : model);
            carEntity.setYear(null == year ? carEntity.getYear() : Long.valueOf(year));
            carRepository.save(carEntity);
        }

        return carEntity;
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
