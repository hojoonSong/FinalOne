package kr.kro.finalone.domain.budget;

import jakarta.persistence.*;
import kr.kro.finalone.domain.category.Category;
import kr.kro.finalone.domain.member.Member;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date month;

}

