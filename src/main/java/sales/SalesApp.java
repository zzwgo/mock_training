package sales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SalesApp {

    public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {
        Sales sales = getSales(salesId);
        if (sales == null) {
            return;
        }
        List<SalesReportData> reportDataList = getSalesReportDao().getReportData(sales);
//        List<SalesReportData> filteredReportDataList = getFilterReportDataList(maxRow, isSupervisor, reportDataList);
        List<String> headers = getHeaders(isNatTrade);
        SalesActivityReport report = this.generateReport(headers, reportDataList);
        if (report == null) {
            return;
        }
        uploadDocument(report);
    }

    protected void uploadDocument(SalesActivityReport report) {
        getEcmService().uploadDocument(report.toXml());
    }

    protected Sales getSales(String salesId) {
        Sales sales = getSalesDao().getSalesBySalesId(salesId);
        Date today = new Date();
        if (sales==null||today.after(sales.getEffectiveTo())
                || today.before(sales.getEffectiveFrom())) {
            return null;
        }
        return sales;
    }

    protected List<String> getHeaders(boolean isNatTrade) {
        if (isNatTrade) {
            return Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
        }
        return Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
    }

    protected SalesDao getSalesDao() {
        return new SalesDao();
    }

    protected SalesReportDao getSalesReportDao() {
        return new SalesReportDao();
    }

    protected EcmService getEcmService() {
        return new EcmService();
    }

    private SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
        // TODO Auto-generated method stub
        return null;
    }

    protected List<SalesReportData> getFilterReportDataList(int maxRow, boolean isSupervisor, List<SalesReportData> reportDataList) {
//        List<SalesReportData> filteredReportDataList = new ArrayList<>();
//        for (SalesReportData data : reportDataList) {
//            if ("SalesActivity".equalsIgnoreCase(data.getType())) {
//                if (data.isConfidential() && isSupervisor) {
//                    filteredReportDataList.add(data);
//                } else {
//                    filteredReportDataList.add(data);
//                }
//            }
//        }
        List<SalesReportData> tempList = new ArrayList<>();
        for (int i = 0; i < reportDataList.size() || i < maxRow; i++) {
            tempList.add(reportDataList.get(i));
        }
//        filteredReportDataList = tempList;
//        return filteredReportDataList;
        return tempList;
    }
}
