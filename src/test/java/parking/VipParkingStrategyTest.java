package parking;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock(name = "carDao")
    CarDao carDao;

    @InjectMocks
    VipParkingStrategy injectMocksVipParkingStrategy = new VipParkingStrategy();

    //3
	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */

        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Car car = mock(Car.class);

        when(parkingLot.isFull()).thenReturn(true);

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);

        vipParkingStrategy.park(parkingLots,car);
        verify(vipParkingStrategy,times(1)).createReceipt(any(),any());
        verify(vipParkingStrategy,times(0)).createNoSpaceReceipt(any());


    }

    //3
    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */


        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        Car car = mock(Car.class);

        when(parkingLot.isFull()).thenReturn(true);

        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);

        vipParkingStrategy.park(parkingLots,car);
        verify(vipParkingStrategy,times(0)).createReceipt(any(),any());
        verify(vipParkingStrategy,times(1)).createNoSpaceReceipt(any());

    }


    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */

	    Car car = createMockCar("BAA");
	    when(carDao.isVip(any())).thenReturn(true);

	    Boolean result = injectMocksVipParkingStrategy.isAllowOverPark(car);

	    Assert.assertEquals(true,result);





    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){


        Car car = createMockCar("BBV");
        when(carDao.isVip(any())).thenReturn(true);

        Boolean result = injectMocksVipParkingStrategy.isAllowOverPark(car);

        Assert.assertEquals(false,result);

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){


        Car car = createMockCar("AAA");
        when(carDao.isVip(any())).thenReturn(false);

        Boolean result = injectMocksVipParkingStrategy.isAllowOverPark(car);

        Assert.assertEquals(false,result);

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not JMockit) and @InjectMocks
         */


        Car car = createMockCar("BB");
        when(carDao.isVip(any())).thenReturn(false);

        Boolean result = injectMocksVipParkingStrategy.isAllowOverPark(car);

        Assert.assertEquals(false,result);

    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
