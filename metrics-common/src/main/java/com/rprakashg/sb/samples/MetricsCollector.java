package com.rprakashg.sb.samples;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by rprakashg on 6/28/17.
 */
@Aspect
@Component
public class MetricsCollector {
    private static final Logger LOG = LoggerFactory.getLogger(MetricsCollector.class);

    @Autowired
    private ExtendedMetricRegistry metricRegistry;

    @Pointcut("@annotation(collectMetrics)")
    public void collectable(CollectMetrics collectMetrics){}

    @Around("collectable(collectMetrics)")
    public Object collectMetrics(ProceedingJoinPoint pjp, CollectMetrics collectMetrics) throws Throwable {
        Object targetObject;
        final String methodName = pjp.getSignature().getName();

        // start  timer
        final Timer.Context timerContext = metricRegistry.timer(MetricRegistry.name(methodName, ExtendedMetricRegistry.DURATION)).time();

        //increment total requests meter
        metricRegistry.meter(MetricRegistry.name(methodName, ExtendedMetricRegistry.REQUESTS)).mark();

        try {
            // log arguments
            logArguments(pjp, methodName);
            targetObject = pjp.proceed();
        } finally {
            final long elapsed = timerContext.stop();
            metricRegistry.recordTime(MetricRegistry.name(methodName, metricRegistry.DURATION), elapsed);
        }
        return targetObject;
    }

    @AfterThrowing(value = "@annotation(com.rprakashg.sb.samples.CollectMetrics)", throwing = "e")
    public void handleException(final JoinPoint jp, final Exception e){
        final String methodName = jp.getSignature().getName();
        metricRegistry.meter(MetricRegistry.name(methodName, metricRegistry.ERRORS)).mark();
    }

    private void logArguments(final JoinPoint joinPoint, final String methodName) {
        String arguments = Arrays.toString(joinPoint.getArgs());
        if (LOG.isDebugEnabled()) {
            LOG.debug("Executing method: [ {} ] with arguments: {}. ", methodName, arguments);
        }
    }
}