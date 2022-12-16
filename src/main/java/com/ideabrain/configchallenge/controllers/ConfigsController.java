package com.ideabrain.configchallenge.controllers;

import com.ideabrain.configchallenge.dtos.Request;
import com.ideabrain.configchallenge.entity.Configs;
import com.ideabrain.configchallenge.services.ConfigsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Request updateByConfigName(@PathVariable String name, @RequestBody Request request) {
        return configsService.updateByConfigName(name, request);
    }

    @DeleteMapping("/configs/{name}")
    public void deleteByConfigName(@PathVariable String name) {
        configsService.deleteByConfigName(name);
    }

    //TODO Add Implementation for Query Data
    @GetMapping("/search")
    public Request queryParamImplemenation(@RequestParam(required = false) Map<String, String> queryParams) {

        return configsService.performAdvancedQuery(queryParams);

    }

}