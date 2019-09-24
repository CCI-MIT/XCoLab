package org.xcolab.service.tracking.service.iptranslation;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.client.tracking.pojo.ILocation;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class IpTranslationService {

    private final Map<Integer, ILocation> locations;
    private final List<IpBlock> blocks;

    @Autowired
    public IpTranslationService(GeoLiteCityConfiguration geoLiteCityConfiguration)
            throws IOException, NumberFormatException {
        locations = geoLiteCityConfiguration.getLocations();
        blocks = geoLiteCityConfiguration.getBlocks();
    }

    public Optional<ILocation> getLocationForIp(String ip) throws IpFormatException {
        Validate.notBlank(ip, "ip is required");
        long ipToLookFor = convertStringIpToLong(ip);
        int val = Collections.binarySearch(blocks, new IpBlock(ipToLookFor, ipToLookFor, 0));

        IpBlock matchingBlock = null;
        if (val >= 0) {
            matchingBlock = blocks.get(val);
        }
        else {
            // according to binarySearch documentation:

			/* the index of the search key, if it is contained in the list;
			 * otherwise, (-(insertion point) - 1). The insertion point is
			 * defined as the point at which the key would be inserted into
			 * the list: the index of the first element greater than the
			 * key, or list.size()
			 */
            // we need to substract 2 from returned value (1 to move back to "insertion point"
            // as insertion point has value greater than what we have provided
            // (so it's start ip is greater) we need to move one more position back to find block that has
            // startIp lesser than what we have provided and now it's enough to check if
            // endIp is greater than our ip
            int candidate = Math.abs(val)-2;
            if (candidate >= 0) {
                if (blocks.size() > candidate &&
                        blocks.get(candidate).ipFrom < ipToLookFor &&
                        blocks.get(candidate).ipTo > ipToLookFor) {
                    matchingBlock = blocks.get(candidate);
                }
            }
        }

        ILocation location = null;
        if (matchingBlock != null) {
            location = locations.get(matchingBlock.locId);
        }

        return Optional.ofNullable(location);
    }

    private static long convertStringIpToLong(String ipAddress) throws IpFormatException {
        String[] parts = ipAddress.split("\\.");
        if (parts.length != 4) {
            throw new IpFormatException(ipAddress);
        }

        try {
            long ipNumber = 0;
            for (int i = 0; i < 4; i++) {
                ipNumber += Integer.parseInt(parts[i]) << (24 - (8 * i));
            }
            return ipNumber;
        } catch (NumberFormatException e) {
            throw new IpFormatException(ipAddress);
        }
    }

    public static class IpFormatException extends Exception {
        IpFormatException(String ipAddress) {
            super("Not a valid IPv4 address: " + ipAddress);
        }

    }
}
