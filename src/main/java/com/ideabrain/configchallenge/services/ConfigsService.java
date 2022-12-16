package com.ideabrain.configchallenge.services;

import com.ideabrain.configchallenge.dtos.*;
import com.ideabrain.configchallenge.entity.Configs;
import com.ideabrain.configchallenge.repository.ConfigsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConfigsService {

    public final ConfigsRepository configsRepository;

    public List<Request> fetchAllConfigs() {

        List<Configs> configs = configsRepository.findAll();
        // Set Parameters on the fly.
        List<Request> requests = configs.stream().map(x -> {

            // Map Everything to Requests
            Request y = new Request();
            return getRequest(x, y);
        }).collect(Collectors.toList());


        return requests;
    }

    private Request getRequest(Configs x, Request y) {

        y.setName(x.getName());
        Metadata metadata = new Metadata();
        Monitoring monitoring = new Monitoring();
        Limits limits = new Limits();
        Cpu cpu = new Cpu();


        cpu.setEnabled(x.isCpuEnabled());
        cpu.setValue(x.getCpuValue());
        limits.setCpu(cpu);

        monitoring.setEnabled(x.isMonitoringEnabled());

        metadata.setLimits(limits);
        metadata.setMonitoring(monitoring);

        y.setMetadata(metadata);
        return y;
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
        return getRequest(c, request);
    }

    public Configs deleteByConfigName(String name) {
        return configsRepository.deleteByName(name);
    }

    public Configs updateByConfigName(String name, Configs configs) {
        return configsRepository.save(configs);
    }


}
