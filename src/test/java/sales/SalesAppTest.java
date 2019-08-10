package sales;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SalesAppTest {

	@Test
	public void testGenerateReport() {
		SalesApp salesApp = new SalesApp();
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
	}

	@Test
	public void should_return_sales_when_call_getSales_given_right_time(){
        SalesApp salesApp = spy(new SalesApp());
        Date today = new Date();
        Date yesterday = new Date(today.getTime()-86400000L);
        Date tomorrow = new Date(today.getTime()+86400000L);

        Sales mockSales= mock(Sales.class);
        when(mockSales.getEffectiveFrom()).thenReturn(yesterday);
        when(mockSales.getEffectiveTo()).thenReturn(tomorrow);
        SalesDao salesDao=mock(SalesDao.class);
        when(salesDao.getSalesBySalesId(any())).thenReturn(mockSales);
        doReturn(salesDao).when(salesApp).getSalesDao();

        Sales sales=salesApp.getSales(any());

        assertNotNull(sales);
    }


}
