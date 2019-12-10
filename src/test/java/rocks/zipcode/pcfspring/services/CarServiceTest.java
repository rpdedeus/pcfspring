package rocks.zipcode.pcfspring.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rocks.zipcode.pcfspring.entities.CarEntity;
import rocks.zipcode.pcfspring.repository.CarRepository;
import rocks.zipcode.pcfspring.requests.AddNewCarRequest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void findAllTest() {

        when(carService.findAll()).thenReturn(stubCarEntities());

        carService.findAll();

        verify(carRepository, times(1)).findAll();
    }

    private List<CarEntity> stubCarEntities() {

        CarEntity f150 = new CarEntity("FORD", "F-150", "BLACK", 1975);
        CarEntity colorado = new CarEntity("CHEVY", "COLORADO", "SILVER", 1989);

        return Arrays.asList(f150, colorado);
    }

    @Test
    public void addNewCarTest() {

        CarEntity expected = new CarEntity("TOYOTA", "TACOMA", "RED", 1996);
        doReturn(expected).when(carRepository).save(any(CarEntity.class));

        CarEntity actual = carService.addNewCar(stubAddNewCarRequest());

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void addNewCarWithNoReturnTest() {

        CarEntity expected = new CarEntity("TOYOTA", "TACOMA", "RED", 1996);
        doReturn(expected).when(carRepository).save(any(CarEntity.class));

        carService.addNewCar(stubAddNewCarRequest());

        ArgumentCaptor<CarEntity> captor = ArgumentCaptor.forClass(CarEntity.class);
        verify(carRepository, times(1)).save(captor.capture());

        Assert.assertEquals(expected.toString(), captor.getValue().toString());
    }

    private AddNewCarRequest stubAddNewCarRequest() {

        AddNewCarRequest addNewCarRequest = new AddNewCarRequest();
        addNewCarRequest.setMake("TOYOTA");
        addNewCarRequest.setModel("TACOMA");
        addNewCarRequest.setColor("RED");
        addNewCarRequest.setYear(1996);

        return addNewCarRequest;
    }

}
