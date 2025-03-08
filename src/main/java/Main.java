import driver.Driver;

public class Main {
    public static void main(String[] args) {
        Driver driver = new Driver("src/main/resources/COMPANY.csv");
        driver.displayEmployeeDataFromFIle();
    }
}
