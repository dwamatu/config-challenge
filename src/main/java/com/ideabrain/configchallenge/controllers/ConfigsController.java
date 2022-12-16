package com.ideabrain.configchallenge.controllers;

import com.ideabrain.configchallenge.dtos.Request;
import com.ideabrain.configchallenge.entity.Configs;
import com.ideabrain.configchallenge.services.ConfigsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ConfigsController {

    private final ConfigsService configsService;


    @PostMapping("/configs")
    public Request createConfigs(@RequestBody Request request) {
        return configsService.createConfig(request);
    }

    @GetMapping("/configs")
    public List<Request> getConfigs() {
        return configsService.fetchAllConfigs();
    }


    @GetMapping("/configs/{name}")
    public Request getConfigByName(@PathVariable String name) {
        return configsService.fetchConfigByName(name);
    }


    @PutMapping("/configs/{name}")
    public Configs updateByConfigName(@PathVariable String name, @RequestBody Configs configs) {
        return configsService.updateByConfigName(name, configs);
    }

    @DeleteMapping("/configs/{name}")
    public Configs deleteByConfigName(@PathVariable String name) {
        return configsService.deleteByConfigName(name);
    }

}