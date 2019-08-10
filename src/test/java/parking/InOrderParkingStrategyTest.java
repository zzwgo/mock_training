package parking;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static parking.ParkingStrategy.NO_PARKING_LOT;

public class InOrderParkingStrategyTest {

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);

//        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.getName()).thenReturn("ParkingLot");

        when(car.getName()).thenReturn("CarName");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);

        assertEquals("ParkingLot", receipt.getParkingLotName());
        assertEquals("CarName", receipt.getCarName());

    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */

        String carName = "CarName";
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);

        assertEquals(carName, receipt.getCarName());
        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());


    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        ParkingLot fullParkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);

        when(fullParkingLot.isFull()).thenReturn(true);
        when(fullParkingLot.getName()).thenReturn("FullParkingLot");

        when(car.getName()).thenReturn("CarName");

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(fullParkingLot), car);

//        assertEquals("CarName", receipt.getCarName());
//        assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(any());
        verify(inOrderParkingStrategy, times(0)).createReceipt(any(), any());

    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);

        when(parkingLot.isFull()).thenReturn(false);
        when(parkingLot.getName()).thenReturn("ParkingLot");

        when(car.getName()).thenReturn("CarName");

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);

        assertEquals("CarName", receipt.getCarName());
        assertEquals("ParkingLot", receipt.getParkingLotName());
        verify(inOrderParkingStrategy, times(1)).createReceipt(any(), any());
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */


//        Car car = mock(Car.class);
//
//        ParkingLot parkingLot = mock(ParkingLot.class);
//        when(parkingLot.isFull()).thenReturn(false);
//        when(parkingLot.getName()).thenReturn("ParkingLot");
//
//        ParkingLot parkingLot1 = mock(ParkingLot.class);
//        when(parkingLot.isFull()).thenReturn(false);
//        when(parkingLot.getName()).thenReturn("ParkingLot");
//
//        when(car.getName()).thenReturn("CarName");
//
//        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
//
//        Receipt receipt = inOrderParkingStrategy.park(Collections.singletonList(parkingLot), car);
//
//        assertEquals("CarName", receipt.getCarName());
//        assertEquals("ParkingLot", receipt.getParkingLotName());
//        verify(inOrderParkingStrategy, times(1)).createReceipt(any(), any());
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
