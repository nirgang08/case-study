package driver;

import dao.Employee;
import dao.EmployeeData;
import operators.CSVDataExtractor;
import operators.CSVFileReader;
import operators.DataExtractor;

import java.io.IOException;
import java.util.List;

public class Driver {

    private String filePath = "src/main/resources/COMPANY.csv";

    public Driver(String filePath) {
        this.filePath = filePath;
    }

    public void displayEmployeeDataFromFIle() {

        try {
            List<Employee> employees = CSVFileReader.getRecords(filePath);

            DataExtractor dataExtractor = new CSVDataExtractor(employees);
            System.out.println("Employees earning less than 20% of the average of his subordinates : ");
            List<EmployeeData> eds = dataExtractor.findManagersEarningLess();
            for (EmployeeData ed : eds) {
                System.out.println(ed.getEmployee().getFirstName() + " " + ed.getEmployee().getLastName());
                System.out.println("Difference in salary : " + ed.getInfo());
            }

            System.out.println();
            System.out.println("Employees earning more than 50% of the average of his subordinates : ");
            eds = dataExtractor.findManagersEarningMore();
            for (EmployeeData ed : eds) {
                System.out.println(ed.getEmployee().getFirstName() + " " + ed.getEmployee().getLastName());
                System.out.println("Difference in salary : " + ed.getInfo());
            }

            System.out.println();
            System.out.println("Employees with reporting line too long : ");
            eds = dataExtractor.findManagersWithLongReportingLine();
            for (EmployeeData ed : eds) {
                System.out.println(ed.getEmployee().getFirstName() + " " + ed.getEmployee().getLastName());
                System.out.println("Length of hierarchy : " + ed.getInfo());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
