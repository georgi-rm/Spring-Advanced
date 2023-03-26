package com.example.airlinetickets.models.entities;

import com.example.airlinetickets.models.enums.ClubCardType;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "club_cards")
public class ClubCardEntity extends BaseEntity {

    @Column(nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClubCardType type;

    @Column(name = "traveled_distance")
    private BigInteger traveledDistance;
}
