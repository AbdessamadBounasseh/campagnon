package com.afaaq.campagnon.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
@SQLDelete(sql = "UPDATE transactions SET deleted_on = now() WHERE ID = ?")
@SQLRestriction("deleted_on is NULL")
public class Transaction extends BaseEntity {

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
