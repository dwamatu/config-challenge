package com.ideabrain.configchallenge.repository;

import com.ideabrain.configchallenge.entity.Configs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigsRepository extends JpaRepository<Configs, Long> {

    Configs findByName(String name);

    Configs deleteByNameEquals(String name);
}