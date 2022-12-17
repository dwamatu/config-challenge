package com.ideabrain.configchallenge.repository;

import com.ideabrain.configchallenge.entity.Configs;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigsRepository extends JpaRepository<Configs, Long> {

    Configs findByName(String name);

    @Transactional
    Long deleteByNameEquals(String name);

    List <Configs> findAllByMonitoringEnabled(boolean value);
    List <Configs> findAllByCpuEnabled(boolean value);
    List <Configs> findAllByCpuValueEquals(String cpuValue);

}