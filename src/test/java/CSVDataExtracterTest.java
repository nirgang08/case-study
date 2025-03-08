import dao.Employee;
import dao.EmployeeData;
import operators.CSVDataExtractor;
import operators.DataExtractor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVDataExtracterTest {

    List<Employee> employees = new ArrayList<>();

    @Before
    public void before() throws Exception {
        employees.add(new Employee(123, "Joe", "Doe", 60000, 0));
        employees.add(new Employee(124, "Martin", "Chekov", 45000, 123));
        employees.add(new Employee(125, "Bob", "Ronstad", 47000, 123));
        employees.add(new Employee(300, "Alice", "Hasacat", 50000, 124));
        employees.add(new Employee(305, "Brett", "Hardleaf", 34000, 300));
        employees.add(new Employee(306, "Bob", "Codd", 20000, 305));
        employees.add(new Employee(307, "Jill", "Andrews", 15000, 306));
    }

    @Test
    public void findManagersEarningLessTest() {
        DataExtractor dataExtractor = new CSVDataExtractor(employees);
        List<EmployeeData> eds = dataExtractor.findManagersEarningLess();
        assertEquals(eds.size(), 1);
        EmployeeData ed = eds.get(0);
        assertEquals(ed.getEmployee().getId(), 124);
    }

    @Test
    public void findManagersEarningMoreTest() {
        DataExtractor dataExtractor = new CSVDataExtractor(employees);
        List<EmployeeData> eds = dataExtractor.findManagersEarningMore();
        assertEquals(eds.size(), 3);
        EmployeeData ed1 = eds.get(0);
        assertEquals(ed1.getEmployee().getId(), 125);
        EmployeeData ed2 = eds.get(1);
        assertEquals(ed2.getEmployee().getId(), 305);
        EmployeeData ed3 = eds.get(2);
        assertEquals(ed3.getEmployee().getId(), 307);
    }

    @Test
    public void findManagersWithLongReportingLineTest() {
        DataExtractor dataExtractor = new CSVDataExtractor(employees);
        List<EmployeeData> eds = dataExtractor.findManagersWithLongReportingLine();
        assertEquals(eds.size(), 1);
        EmployeeData ed = eds.get(0);
        assertEquals(ed.getEmployee().getId(), 307);
    }
}
