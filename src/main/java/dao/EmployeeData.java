package dao;

import dao.Employee;

public class EmployeeData {
    Employee employee;

    double info;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getInfo() {
        return info;
    }

    public void setInfo(double info) {
        this.info = info;
    }

    public EmployeeData(Employee employee, double info) {
        this.employee = employee;
        this.info = info;
    }
}
