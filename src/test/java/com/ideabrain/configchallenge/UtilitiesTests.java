package com.ideabrain.configchallenge;


import com.ideabrain.configchallenge.utils.Constants;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class UtilitiesTests {

    @Test
    public void validateConstantsCpuEnabled() {
        assertEquals(Constants.METADATA_LIMITS_CPU_ENABLED, "metadata.limits.cpu.enabled");
    }

    @Test
    public void validateConstantsMonitoringEnabled() {
        assertEquals(Constants.METADATA_MONITORING_ENABLED, "metadata.monitoring.enabled");
    }


    @Test
    public void validateConstantsCpuValue() {
        assertEquals(Constants.METADATA_LIMITS_CPU_VALUE, "metadata.limits.cpu.value");
    }
}
