package org.xcolab.service.tracking.exceptions;

public class GeoLiteCityConfigurationException extends RuntimeException {

    public GeoLiteCityConfigurationException(String message, String ipBlocksFilePath, String locationsFilePath) {
        super(message + " [ipBlocksFile Path = " + ipBlocksFilePath +
                ", locationsFilePath = " + locationsFilePath + "]");
    }
}
