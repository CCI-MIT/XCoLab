package org.xcolab.commons.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;

public final class MetricsUtil {

    private static final String REGISTRY_NAME = "XCoLab";

    public static final MetricRegistry REGISTRY = SharedMetricRegistries.getOrCreate(REGISTRY_NAME);

    private MetricsUtil() {
    }

    public static String getUniqueInstanceIdentifier(Object identifyingObject) {
        return "instance" + Integer.toString(System.identityHashCode(identifyingObject), 16).toUpperCase();
    }
}
