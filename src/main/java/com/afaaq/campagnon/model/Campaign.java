package com.afaaq.campagnon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double target;

    private Double currentAmount;

    @Setter(AccessLevel.NONE)
    private Double restAmount;

    private String image;

    @OneToMany(mappedBy = "campaign")
    @JsonIgnore
    private List<Transaction> transactions;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    @Builder
    public Campaign(String name, String description, Double target, Double currentAmount,
                    String image, List<Transaction> transactions, Instant lastUpdatedOn) {
        this.name = name;
        this.description = description;
        this.target = target;
        this.currentAmount = currentAmount;
        this.image = image;
        this.transactions = transactions;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @PrePersist
    public void calculateRestAmountAndInitCurrentAmount() {
        currentAmount = (double) 0;
        restAmount = target - currentAmount;
    }

    @PreUpdate
    public void updateRestAmount() {
        restAmount = target - currentAmount;
    }
}
