package sales;

import org.junit.Assert;
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

	@Mock
	SalesReportDao salesReportDao;

	@InjectMocks
	SalesApp salesApp = new SalesApp();

	@Test
	public void testGenerateReport() {
		
		//SalesApp salesApp = new SalesApp();
		//salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
		
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

	@Test
	public void should_return_salesReportDatas_when_call_getSalesReportDatasBySales_given_sales() {

		Sales sales = mock(Sales.class);

		SalesReportData salesReportData = mock(SalesReportData.class);
		when(salesReportData.isConfidential()).thenReturn(true);
		when(salesReportData.getType()).thenReturn("Test");

		ArrayList<SalesReportData> salesReportDatas = new ArrayList<>();
		salesReportDatas.add(salesReportData);

		when(salesReportDao.getReportData(sales)).thenReturn(salesReportDatas);

		List<SalesReportData> salesReportDataBySales = salesApp.getSalesReportDatasBySales(sales);
		SalesReportData result = salesReportDataBySales.get(0);


		assertTrue(salesReportDataBySales.size() == 1);
		Assert.assertEquals("Test",result.getType());
		Assert.assertEquals(true,result.isConfidential());

	}

    @Test
    public void should_return_containsTime_when_call_getHeaders_given_true() {

        boolean isNatTrade = true;

        List<String> headers = salesApp.getHeaders(isNatTrade);

        assertTrue(headers.contains("Time"));
    }

    @Test
    public void should_return_containsLocalTime_when_call_getHeaders_given_false() {

        boolean isNatTrade = false;

        List<String> headers = salesApp.getHeaders(isNatTrade);

        assertTrue(headers.contains("Local Time"));
    }










}
