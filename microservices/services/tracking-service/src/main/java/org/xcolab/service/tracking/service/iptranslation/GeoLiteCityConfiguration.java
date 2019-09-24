package org.xcolab.service.tracking.service.iptranslation;

import org.apache.commons.lang3.StringUtils;
import org.jooq.tools.csv.CSVParser;
import org.jooq.tools.csv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.client.tracking.pojo.tables.pojos.Location;
import org.xcolab.service.tracking.exceptions.GeoLiteCityConfigurationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
@PropertySource({"file:${user.home}/.xcolab.application.properties"})
public class GeoLiteCityConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GeoLiteCityConfiguration.class);

    private static final String GEO_LITE_CITY_BLOCKS_PROPERTY = "geolite.blocks.path";
    private static final String GEO_LITE_CITY_LOCATION_PROPERTY = "geolite.location.path";

    private Map<Integer, ILocation> locations;
    private List<IpBlock> blocks;

    private long initializationStartTime;

    @Autowired
    public GeoLiteCityConfiguration(Environment env) {
        final String blocksFilePath = env.getProperty(GEO_LITE_CITY_BLOCKS_PROPERTY);
        final String locationsFilePath = env.getProperty(GEO_LITE_CITY_LOCATION_PROPERTY);

        final boolean blocksFilePathGiven = StringUtils.isNotEmpty(blocksFilePath);
        final boolean locationsFilePathGiven = StringUtils.isNotEmpty(locationsFilePath);

        if (blocksFilePathGiven && locationsFilePathGiven) {
            loadFromFilePaths(blocksFilePath, locationsFilePath);
        } else {
            log.warn("Configuration file locations are not set, falling back to NoOp IpLocationTranslator");
            locations = Collections.emptyMap();
            blocks = Collections.emptyList();
        }
    }

    private void loadFromFilePaths(String blocksFilePath, String locationsFilePath) {
        final File ipBlocksFile = new File(blocksFilePath);
        final File locationsFile = new File(locationsFilePath);

        final boolean ipBlocksFileExists = ipBlocksFile.exists();
        final boolean locationsFileExists = locationsFile.exists();

        if (ipBlocksFileExists && locationsFileExists) {
            log.info("Configuration detected, loading configuration files...");
            initializationStartTime = System.nanoTime();
            loadFromFiles(ipBlocksFile, locationsFile);
        } else {
            throw new GeoLiteCityConfigurationException("Given configuration files don't exist.",
                    blocksFilePath, locationsFilePath);
        }
    }

    private void loadFromFiles(File ipBlocksFile, File locationsFile) {
        try (BufferedReader bufferedBlocksReader =
                    new BufferedReader(new FileReader(ipBlocksFile));
                BufferedReader bufferedLocationsReader =
                        new BufferedReader(new FileReader(locationsFile))) {

            CSVReader csvBlocksReader = new CSVReader(bufferedBlocksReader,
                    CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
                    CSVParser.DEFAULT_ESCAPE_CHARACTER, 2);

            String[] line;
            ArrayList<IpBlock> blocks = new ArrayList<>(1900000);
            while ((line = csvBlocksReader.readNext()) != null) {
                blocks.add(new IpBlock(Long.parseLong(line[0]), Long.parseLong(line[1]),
                        Integer.parseInt(line[2])));
            }

            CSVReader csvLocationsReader = new CSVReader(bufferedLocationsReader,
                    CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
                    CSVParser.DEFAULT_ESCAPE_CHARACTER, 2);

            Map<Integer, ILocation> locations = new HashMap<>(510000);
            while ((line = csvLocationsReader.readNext()) != null) {
                int locId = Integer.parseInt(line[0]);
                String countryName = new Locale("", line[1]).getDisplayCountry();
                locations.put(locId, new Location(locId, line[1], countryName, line[2], line[3], line[4],
                        Double.parseDouble(line[5]), Double.parseDouble(line[6]), line[7], line[8]));
            }

            Collections.sort(blocks);

            this.locations = locations;
            this.blocks = blocks;
            final String initializationLoadTime = String.format("%.3f",
                    (System.nanoTime() - initializationStartTime) / 1_000_000_000.0);
            log.info("Configuration loaded successfully in {} seconds", initializationLoadTime);
        } catch (IOException e) {
            log.error("Error while reading GeoLite City configuration files", e);
            locations = Collections.emptyMap();
            blocks = Collections.emptyList();
        }
    }

    public Map<Integer, ILocation> getLocations() {
        return locations;
    }

    public List<IpBlock> getBlocks() {
        return blocks;
    }
}
