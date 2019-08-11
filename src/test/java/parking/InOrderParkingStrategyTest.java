package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

    //1
	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        when(parkingLot.getName()).thenReturn("AAA");
        when(car.getName()).thenReturn("BBB");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        Receipt result = inOrderParkingStrategy.createReceipt(parkingLot,car);

        Assert.assertEquals("AAA",result.getParkingLotName());
        Assert.assertEquals("BBB",result.getCarName());


    }

    //1
    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {


        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */


        Car car = mock(Car.class);
        when(car.getName()).thenReturn("BBB");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
        Assert.assertEquals("BBB",receipt.getCarName());

    }

    //2
    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){


	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.
	    verify to test the situation for no available parking lot */

	    List<ParkingLot> parkingLots = new ArrayList<>();
	    Car car = mock(Car.class);

	    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
	    Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);

	    Assert.assertEquals(NO_PARKING_LOT,receipt.getParkingLotName());

	    verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(any());
	    verify(inOrderParkingStrategy,times(0)).createReceipt(any(),any());


    }

    //2
    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */


        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        parkingLots.add(parkingLot1);
        Car car = mock(Car.class);

        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot1.getName()).thenReturn("AAA");

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);

        Assert.assertEquals("AAA",receipt.getParkingLotName());

        Mockito.verify(inOrderParkingStrategy,times(1)).createReceipt(any(),any());
        Mockito.verify(inOrderParkingStrategy,times(0)).createNoSpaceReceipt(any());

    }

    //2
    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

        List<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        parkingLots.add(parkingLot1);
        Car car = mock(Car.class);

        when(parkingLot1.isFull()).thenReturn(true);
        when(parkingLot1.getName()).thenReturn("AAA");

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = inOrderParkingStrategy.park(parkingLots,car);

        Assert.assertEquals(NO_PARKING_LOT,receipt.getParkingLotName());

        Mockito.verify(inOrderParkingStrategy,times(0)).createReceipt(any(),any());
        Mockito.verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(any());


    }




    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
