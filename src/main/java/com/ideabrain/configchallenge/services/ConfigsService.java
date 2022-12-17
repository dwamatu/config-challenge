package com.ideabrain.configchallenge.services;

import com.ideabrain.configchallenge.dtos.*;
import com.ideabrain.configchallenge.entity.Configs;
import com.ideabrain.configchallenge.repository.ConfigsRepository;
import com.ideabrain.configchallenge.utils.Utilities;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ideabrain.configchallenge.utils.Constants.*;

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

        Configs c = configsRepository.findByName(name);
        if (c != null) {
            configsRepository.deleteByNameEquals(name);
        }
    }

    //TODO FIX UPDATE BY CONFIG NAME - WHERE RECORD IS NOT FOUND

    public Request updateByConfigName(String name, Request request) {


        Configs c = configsRepository.findByName(name);

        if (c.getId() != null) {
            c.setName(request.getName());
            c.setMonitoringEnabled(request.getMetadata().getMonitoring().isEnabled());
            c.setCpuEnabled(request.getMetadata().getLimits().getCpu().isEnabled());
            c.setCpuValue(request.getMetadata().getLimits().getCpu().getValue());
            // Save
            configsRepository.save(c);
            return request;
        } else {
            return null;
        }

    }

    public List<Request> performAdvancedQuery(Map<String, String> queryParams) {

        List<Configs> configsList = new ArrayList<>();

        if (queryParams.containsKey(METADATA_MONITORING_ENABLED)) {
            configsList = configsRepository.findAllByMonitoringEnabled(Boolean.parseBoolean(queryParams.get(METADATA_MONITORING_ENABLED).trim()));
        }
        if (queryParams.containsKey(METADATA_LIMITS_CPU_ENABLED))
            configsList = configsRepository.findAllByCpuEnabled(Boolean.parseBoolean(queryParams.get(METADATA_LIMITS_CPU_ENABLED).trim()));
        if ((queryParams.containsKey(METADATA_LIMITS_CPU_VALUE)))
            configsList = configsRepository.findAllByCpuValueEquals(queryParams.get(METADATA_LIMITS_CPU_VALUE).trim());

        if (!configsList.isEmpty()) {

            return configsList.stream().map(x -> {
                // Map Everything to Requests
                Request y = new Request();
                return utilities.mapConfigToRequest(x, y);
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }


    }
}
