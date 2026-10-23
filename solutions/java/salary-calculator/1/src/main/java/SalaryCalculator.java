public class SalaryCalculator {
    private final int baseSalary = 1000;
    private final int maxSalary = 2000;
    public double salaryMultiplier(int daysSkipped) {
        return daysSkipped >= 5 ? 0.85 : 1;
    }

    public int bonusMultiplier(int productsSold) {
        return productsSold >= 20 ? 13 : 10;
    }

    public double bonusForProductsSold(int productsSold) {
        return productsSold * this.bonusMultiplier(productsSold);
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        double salary = this.salaryMultiplier(daysSkipped) * this.baseSalary + this.bonusForProductsSold(productsSold);
        return salary > this.maxSalary ? this.maxSalary : salary;
    } 
}
