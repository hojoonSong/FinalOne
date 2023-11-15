package kr.kro.finalone.domain.member;

import jakarta.persistence.*;
import kr.kro.finalone.domain.budget.Budget;
import kr.kro.finalone.domain.expense.Expense;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String memberName;

    @Column(nullable = false, length = 255)
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Budget> budgets;

    @OneToMany(mappedBy = "member")
    private List<Expense> expenses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

}
