package kr.kro.finalone.domain.category;

import jakarta.persistence.*;
import kr.kro.finalone.domain.budget.Budget;
import kr.kro.finalone.domain.expense.Expense;

import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Budget> budgets;

    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;
}
