package kr.kro.finalone.auditlog.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "AuditLog")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String tableName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Operation operation;

    private Integer changedByUserId;

    @Column(columnDefinition = "TEXT")
    private String changedData;

    @Column(nullable = false)
    private Timestamp timestamp;

    public enum Operation {
        INSERT, UPDATE, DELETE
    }

}
