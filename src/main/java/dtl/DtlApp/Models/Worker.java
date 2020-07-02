package dtl.DtlApp.Models;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "workers")
public class Worker extends User {

    @Column(name = "salary_balance")
    @Setter(value = AccessLevel.NONE)
    private Float salaryBalance = (float) 0;

    @Column(name = "salary_decrease_balance")
    @Setter(value = AccessLevel.NONE)
    private Float salaryDecreaseBalance = (float) 0;


    public void decreaseSalaryDecreaseBalance(float dec) {
        this.salaryDecreaseBalance -= dec;
    }

    public void increaseSalaryDecreaseBalance(float addition) {
        this.salaryDecreaseBalance += addition;
    }

    public void increaseSalaryBalance(float addition) {
        this.salaryBalance += addition;
    }

    public void decreaseSalaryBalance(float dec) {
        this.salaryBalance -= dec;
    }

    public void nullifySalaryBalance() {
        this.salaryBalance = (float) 0;
    }

    public void nullifySalaryDecreaseBalance() {

        this.salaryDecreaseBalance = (float) 0;
    }

}