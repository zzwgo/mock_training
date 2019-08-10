package parking;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class VipParkingStrategyJMockitTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice() {

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using JMockit to mock static method */

        new MockUp<Calendar>() {
//            @Mock
//            public Calendar getInstance() {
//                return new Calendar.Builder().setWeekDate(2019, 1, Calendar.SUNDAY).build();
//            }

            @Mock
            public int get(int a){
                return Calendar.SUNDAY;
            }
        };

        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        assertEquals(Integer.valueOf(50), vipParkingStrategy.calculateHourlyPrice());
    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice() {

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using JMockit to mock static method */


    }
}
