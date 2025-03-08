package operators;

import dao.EmployeeData;

import java.util.List;

public interface DataExtractor {
    public List<EmployeeData> findManagersEarningLess();

    public List<EmployeeData> findManagersEarningMore();

    public List<EmployeeData> findManagersWithLongReportingLine();
}
