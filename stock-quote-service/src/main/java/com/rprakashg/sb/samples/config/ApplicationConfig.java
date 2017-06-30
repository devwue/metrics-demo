package com.rprakashg.sb.samples.config;

import com.rprakashg.sb.samples.ExtendedMetricRegistry;
import com.rprakashg.sb.samples.MetricsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by rprakashg on 6/28/17.
 */
@Configuration
public class ApplicationConfig {
    private ExtendedMetricRegistry emr;

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public ExtendedMetricRegistry extendedMetricRegistry(final MetricsConfig metricsConfig) {
        emr = new ExtendedMetricRegistry(appName, metricsConfig.getMetricRegistry());
        emr.registerGCMetricSet();
        emr.registerBufferPoolMetricSet();
        emr.registerMemoryUsageGuageSet();
        emr.registerThreadStatesGuageSet();

        return emr;
    }
}
