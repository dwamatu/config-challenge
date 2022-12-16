package com.ideabrain.configchallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Configs {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private boolean monitoringEnabled;
    private boolean cpuEnabled;
    private String cpuValue;
}
