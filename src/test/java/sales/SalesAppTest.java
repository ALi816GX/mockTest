package sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SalesAppTest {


	@Mock
	SalesDao salesDao;

	@InjectMocks
	SalesApp salesApp = new SalesApp();

	@Test
	public void testGenerateReport() {
		
		SalesApp salesApp = new SalesApp();
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
		
	}


	@Test
	public void shoule_return_Sales_when_call_getSalesBySalesId_given_SalesId(){

		String salesId = "Test";
		Sales sales = new Sales();
        sales.setSupervisor(true);
		when(salesDao.getSalesBySalesId(salesId)).thenReturn(sales);

		Sales result = salesApp.getSalesBySalesId(salesId);

		assertTrue(result.isSupervisor() == sales.isSupervisor());

	}


    @Test
    public void should_return_true_when_call_isNotDuringEffectiveDate_given_the_first_day_and_the_last_day_in_current_month() {

		Sales mockSales = mock(Sales.class);

		Calendar from =Calendar.getInstance();
		from.add(Calendar.MONTH, 1);// the first day of current month
		Date fromDate = from.getTime();

		Calendar to = Calendar.getInstance();
		to.add(Calendar.DAY_OF_MONTH, to.getActualMaximum(Calendar.DAY_OF_MONTH));// the last day of current month
		Date toDate = to.getTime();

        when(mockSales.getEffectiveFrom()).thenReturn(fromDate);
        when(mockSales.getEffectiveTo()).thenReturn(toDate);

        boolean result = salesApp.isNotDuringEffectiveDate(mockSales);

        assertTrue(result == true);

    }










}
