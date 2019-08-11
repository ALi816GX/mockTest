package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

    private SalesDao salesDao;
    private SalesReportDao salesReportDao;
    private EcmService ecmService;

    public SalesApp() {
        this.salesDao = new SalesDao();
        this.salesReportDao = new SalesReportDao();
        this.ecmService = new EcmService();
    }

    public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {

		if (salesId == null) {
			return;
		}

		Sales sales = salesDao.getSalesBySalesId(salesId);

		if (isNotDuringEffectiveDate(sales)) {
            return;
        }

		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);
		List<SalesReportData> filteredReportDataList = getFilteredReportDataList(isSupervisor, reportDataList);
		filteredReportDataList = getSalesReportData(maxRow, reportDataList);
		List<String> headers = getHeaders(isNatTrade);
		SalesActivityReport report = this.generateReport(headers, reportDataList);
		ecmService.uploadDocument(report.toXml());
		
	}

    private boolean isNotDuringEffectiveDate(Sales sales) {
        Date today = new Date();
        return today.after(sales.getEffectiveTo()) || today.before(sales.getEffectiveFrom());
    }

    private List<SalesReportData> getSalesReportData(int maxRow, List<SalesReportData> reportDataList) {
        List<SalesReportData> tempList = new ArrayList<SalesReportData>();
        for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
            tempList.add(reportDataList.get(i));
        }
        return tempList;
    }

    private List<SalesReportData> getFilteredReportDataList(boolean isSupervisor, List<SalesReportData> reportDataList) {
        List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
        for (SalesReportData data : reportDataList) {
            if ("SalesActivity".equalsIgnoreCase(data.getType())) {
                if (data.isConfidential()) {
                    if (isSupervisor) {
                        filteredReportDataList.add(data);
                    }
                }else {
                    filteredReportDataList.add(data);
                }
            }
        }
        return filteredReportDataList;
    }

    private List<String> getHeaders(boolean isNatTrade) {
        if (isNatTrade) {
            return Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
        }

        return Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
    }


    private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

}
