package com.ext.utils.iptranslation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.util.portlet.PortletProps;

public class IpTranslationServiceImpl implements IpTranslationService, InvokableLocalService {
	
	private final static Log _log = LogFactoryUtil.getLog(IpTranslationServiceImpl.class);
	private final static String GEO_LITE_CITY_BLOCKS_PROPERTY = "geolite.blocks";
	private final static String GEO_LITE_CITY_LOCATION_PROPERTY = "geolite.location";
	
	private Map<Integer, Location> locations = new HashMap<>();
	private ArrayList<IpBlock> blocks = new ArrayList<IpBlock>();
	
	public IpTranslationServiceImpl() throws Exception {
		reloadLocationAndBlockData();
	}

	@Override
	public synchronized Location getLocationForIp(String ip) throws Exception {
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
		
		Location location = null;
		if (matchingBlock != null) {
			location = locations.get(matchingBlock.locId);
		}
		
		return location;
		
	}

	@Override
	public void reloadLocationAndBlockData() throws Exception {
		_log.info("Reloading ip to location translation data");
		
		String blocksFilePath = PortletProps.get(GEO_LITE_CITY_BLOCKS_PROPERTY);
		if (StringUtils.isBlank(blocksFilePath)) {
			_log.error("Geolite blocks property has to be set in portlet.properties: " + GEO_LITE_CITY_BLOCKS_PROPERTY);
			return;
		}
		File ipBlocksFile = new File(blocksFilePath);
		if (!ipBlocksFile.exists()) {
			_log.error("Can't find ip blocks file: " + ipBlocksFile.getAbsolutePath());
			return;
		}
		
		String locationsFilePath = PortletProps.get(GEO_LITE_CITY_LOCATION_PROPERTY);
		if (StringUtils.isBlank(locationsFilePath)) {
			_log.error("Geolite locations property has to be set in portlet.properties: " + GEO_LITE_CITY_LOCATION_PROPERTY);
			return;
		}
		File locationsFile = new File(locationsFilePath);
		if (!locationsFile.exists()) {
			_log.error("Can't find ip locations file: " + locationsFile.getAbsolutePath());
			return;
		}
		

		BufferedReader bufferedBlocksReader = new BufferedReader(new FileReader(ipBlocksFile));
		CSVReader csvBlocksReader = new CSVReader(bufferedBlocksReader, 
				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 2);
		
		String[] line = null;
		ArrayList<IpBlock> blocks = new ArrayList<IpBlock>(1900000);
		while ((line = csvBlocksReader.readNext()) != null) {
			blocks.add(new IpBlock(Long.parseLong(line[0]), Long.parseLong(line[1]), Integer.parseInt(line[2])));
		}
		csvBlocksReader.close();

		BufferedReader bufferedLocationsReader = new BufferedReader(new FileReader(locationsFile));
		CSVReader csvLocationsReader = new CSVReader(bufferedLocationsReader, 
				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 2);
		
		Map<Integer, Location> locations = new HashMap<Integer, Location>(510000);
		while ((line = csvLocationsReader.readNext()) != null) {
			int locId = Integer.parseInt(line[0]);
			locations.put(locId, new Location(locId, line[1], line[2], line[3], line[4], 
					Double.parseDouble(line[5]), Double.parseDouble(line[6]), line[7], line[8]));
		}
		csvLocationsReader.close();
		
		Collections.sort(blocks);
		
		synchronized(this) {
			this.locations = locations;
			this.blocks = blocks;
			
		}
		_log.info("Ip to location data reloaded");
		
	}
	
	private static long convertStringIpToLong(String ipAddress) {
		String[] parts = ipAddress.split("\\.");
		
		long ipNumber = 0;
		for (int i = 0; i < 4; i++) {
		    ipNumber += Integer.parseInt(parts[i]) << (24 - (8 * i));
		}
		return ipNumber;
	}
	
	private static class IpBlock implements Comparable<IpBlock> {
		final long ipFrom;
		final long ipTo;
		final int locId;
		public IpBlock(long ipFrom, long ipTo, int locId) {
			this.ipFrom = ipFrom;
			this.ipTo = ipTo;
			this.locId = locId;
		}
		@Override
		public int compareTo(IpBlock o) {
			long val = ipFrom - o.ipFrom;
			
			return val == 0 ? 0 : val < 0 ? -1 : 1;
		}
		@Override
		public String toString() {
			return "IpBlock [ipFrom=" + ipFrom + ", ipTo=" + ipTo + ", locId="
					+ locId + "]";
		}
		
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
			Object[] arguments) throws Throwable {
		Class[] parameterTypesClass = new Class[parameterTypes.length];
		for (int i=0; i < parameterTypes.length; i++) {
			parameterTypesClass[i] = getClass().getClassLoader().loadClass(parameterTypes[i]);
		}
		
		Method m = this.getClass().getMethod(name, parameterTypesClass);
		Object ret = m.invoke(this, arguments);
		return ret;
	}

}
