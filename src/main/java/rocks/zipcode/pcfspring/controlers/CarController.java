package rocks.zipcode.pcfspring.controlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rocks.zipcode.pcfspring.entities.CarEntity;
import rocks.zipcode.pcfspring.requests.AddNewCarRequest;
import rocks.zipcode.pcfspring.requests.UpdateCarRequest;
import rocks.zipcode.pcfspring.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/api/car")
public class CarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public @ResponseBody
    List<CarEntity> findAll() {

        List<CarEntity> cars = null;

        try {
            cars = carService.findAll();
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }

        return cars;
    }

    @GetMapping("/make")
    public @ResponseBody
    List<CarEntity> findByMake(@RequestParam String make) {

        List<CarEntity> cars = null;

        try {
            cars = carService.findByMake(make);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }

        return cars;
    }

    @GetMapping("/model")
    public @ResponseBody
    List<CarEntity> findByModel(@RequestParam String model) {

        List<CarEntity> cars = null;

        try {
            cars = carService.findByModel(model);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }

        return cars;
    }

    @GetMapping("/makeAndModel")
    public @ResponseBody
    List<CarEntity> findByMakeAndModel(@RequestParam String make, @RequestParam String model) {

        List<CarEntity> cars = null;

        try {
            cars = carService.findByMakeAndModel(make, model);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }

        return cars;
    }

    @PostMapping("addCar")
    public @ResponseBody CarEntity addNewCar(@RequestBody AddNewCarRequest request) {

        CarEntity carEntity = null;
        try {
            carEntity = carService.addNewCar(request);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }

        return carEntity;
    }

    @PostMapping("updateCar")
    public @ResponseBody CarEntity addNewCar(@RequestBody UpdateCarRequest request) {

        CarEntity carEntity = null;
        try {
            carEntity = carService.updateCar(request);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }

        return carEntity;
    }

    @DeleteMapping("deleteCar")
    public void addNewCar(@RequestParam Long carId) {

        try {
            carService.deleteCar(carId);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
    }


}