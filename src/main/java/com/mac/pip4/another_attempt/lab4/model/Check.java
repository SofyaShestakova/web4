package com.mac.pip4.another_attempt.lab4.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "spring_check")
public class Check implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="check_id")
    private Long checkId;

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Double r;

    @NotNull
    @Column(name = "is_in_area")
    private Boolean isInArea;

    @NotNull
    String shooter;
}

