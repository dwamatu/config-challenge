package com.ideabrain.configchallenge.services;

import com.ideabrain.configchallenge.dtos.*;
import com.ideabrain.configchallenge.entity.Configs;
import com.ideabrain.configchallenge.repository.ConfigsRepository;
import com.ideabrain.configchallenge.utils.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConfigsService {

    public final ConfigsRepository configsRepository;
    public final Utilities utilities;

    public List<Request> fetchAllConfigs() {

        List<Configs> configs = configsRepository.findAll();
        // Set Parameters on the fly.
        List<Request> requests = configs.stream().map(x -> {

            // Map Everything to Requests
            Request y = new Request();
            return utilities.mapConfigToRequest(x, y);
        }).collect(Collectors.toList());

        return requests;
    }


    public Request createConfig(Request request) {
        Configs configs = new Configs();
        configs.setName(request.getName());
        configs.setMonitoringEnabled(request.getMetadata().getMonitoring().isEnabled());
        configs.setCpuEnabled(request.getMetadata().getLimits().getCpu().isEnabled());
        configs.setCpuValue(request.getMetadata().getLimits().getCpu().getValue());
        // Save
        configsRepository.save(configs);
        // Return Saved Request
        return request;
    }

    public Request fetchConfigByName(String name) {
        Configs c = configsRepository.findByName(name);
        Request request = new Request();
        return utilities.mapConfigToRequest(c, request);
    }

    public void deleteByConfigName(String name) {
        configsRepository.deleteByNameEquals(name);
    }

    //TODO FIX UPDATE BY CONFIG NAME

    public Request updateByConfigName(String name, Request request) {
        Configs c = configsRepository.findByName(name);

        c.setName(request.getName());
        c.setMonitoringEnabled(request.getMetadata().getMonitoring().isEnabled());
        c.setCpuEnabled(request.getMetadata().getLimits().getCpu().isEnabled());
        c.setCpuValue(request.getMetadata().getLimits().getCpu().getValue());
        // Save
        configsRepository.save(c);

        return request;
    }


}
