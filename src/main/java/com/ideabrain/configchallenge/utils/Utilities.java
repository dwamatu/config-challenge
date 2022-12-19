package com.ideabrain.configchallenge.utils;

import com.ideabrain.configchallenge.dtos.*;
import com.ideabrain.configchallenge.dtos.subdtos.Cpu;
import com.ideabrain.configchallenge.dtos.subdtos.Limits;
import com.ideabrain.configchallenge.dtos.subdtos.Metadata;
import com.ideabrain.configchallenge.dtos.subdtos.Monitoring;
import com.ideabrain.configchallenge.entity.Configs;
import org.springframework.stereotype.Component;

@Component
public class Utilities {

    public   Request mapConfigToRequest(Configs x, Request y) {

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

    public Configs getRequest(Request request, Configs c) {
        c.setName(request.getName());
        c.setMonitoringEnabled(request.getMetadata().getMonitoring().isEnabled());
        c.setCpuEnabled(request.getMetadata().getLimits().getCpu().isEnabled());
        c.setCpuValue(request.getMetadata().getLimits().getCpu().getValue());
        // Save
        return  c;

    }
}
