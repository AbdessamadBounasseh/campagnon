package com.afaaq.campagnon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
}
