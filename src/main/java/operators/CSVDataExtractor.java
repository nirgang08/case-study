package operators;

import dao.Employee;
import dao.EmployeeData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVDataExtractor implements DataExtractor {

    List<Employee> employees;

    Map<Integer, Integer> employeeManagerMap = new HashMap<>();

    public CSVDataExtractor(List<Employee> employees) {
        this.employees = employees;
        loadManagerRelationship();
    }

    public List<EmployeeData> findManagersEarningLess() {
        return employees.stream().map(employee -> {
                    List<Employee> subordinates = findSubordinates(employee);
                    double avg = getAverage(subordinates);
                    return new EmployeeData(employee, 1.2 * avg - employee.getSalary());
                }
        ).filter(empData -> empData.getInfo() > 0).collect(Collectors.toList());
    }

    public List<EmployeeData> findManagersEarningMore() {
        return employees.stream().map(employee -> {
                    List<Employee> subordinates = findSubordinates(employee);
                    double avg = getAverage(subordinates);
                    return new EmployeeData(employee, employee.getSalary() - 1.5 * avg);
                }
        ).filter(empData -> empData.getInfo() > 0).collect(Collectors.toList());
    }

    public List<EmployeeData> findManagersWithLongReportingLine(){
        return employees.stream().map(employee->{
            int count = 0;
            int currentId = employee.getId();
            while(employeeManagerMap.get(currentId) != 0){
                count++;
                currentId = employeeManagerMap.get(currentId);
            }
            return new EmployeeData(employee, count);
        }).filter(empData -> empData.getInfo() >= 5).collect(Collectors.toList());
    }

    private List<Employee> findSubordinates(Employee employee){
        return employees.stream().filter(emp -> emp.getManagerId() == employee.getId())
                .collect(Collectors.toList());
    }

    private double getAverage(List<Employee> subordinates){
        double salarySumSubordinates = 0;
        for (Employee sub : subordinates) {
            salarySumSubordinates += sub.getSalary();
        }
        double avg = 0;
        if (subordinates.size() > 0)
            avg = salarySumSubordinates / subordinates.size();
        return avg;
    }

    private void loadManagerRelationship() {
        employeeManagerMap = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getManagerId));
    }
}
