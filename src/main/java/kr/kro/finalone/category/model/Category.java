package kr.kro.finalone.category.model;

import kr.kro.finalone.budget.model.Budget;
import kr.kro.finalone.expense.model.Expense;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Budget> budgets;

    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;
}
