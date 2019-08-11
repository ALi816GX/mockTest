package sales;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
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
		Sales sales1 = new Sales();
		sales1.setSupervisor(true);
		when(salesDao.getSalesBySalesId(salesId)).thenReturn(sales1);

		Sales sales = salesApp.getSalesBySalesId(salesId);

		assertTrue(sales.isSupervisor());

	}







}
