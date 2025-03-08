package operators;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import dao.Employee;
import dao.EmployeeData;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {

    public static List<Employee> getRecords(String file) throws IOException {

            FileReader filereader = new FileReader(file);

            //csvReader object without header line.
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> alldata = csvReader.readAll();
            List<Employee> employees = new ArrayList<>();
            for (String[] row : alldata) {
                if(row[4].isBlank()){
                    row[4] = "0";
                }
                employees.add(new Employee(Integer.parseInt(row[0]), row[1], row[2], Integer.parseInt(row[3]), Integer.parseInt(row[4])));
            }
            return employees;
    }
}
