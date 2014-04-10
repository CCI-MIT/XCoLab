package org.xcolab.libs.iptolocationtranslator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

public class IpToLocationTranslator {
	
	
	
	public static void main(String[] args) throws IOException {
		
		File ipBlocksFile = new File("/home/janusz/Pobrane/GeoLiteCity_20140304/GeoLiteCity-Blocks.csv");
		File locationsFile = new File("/home/janusz/Pobrane/GeoLiteCity_20140304/GeoLiteCity-Location.csv");
		

		BufferedReader bufferedBlocksReader = new BufferedReader(new FileReader(ipBlocksFile));
		CSVReader csvBlocksReader = new CSVReader(bufferedBlocksReader, 
				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 2);
		
		String[] line = null;
		ArrayList<IpBlock> blocks = new ArrayList<IpBlock>();
		while ((line = csvBlocksReader.readNext()) != null) {
			//System.out.println(Arrays.toString(line));
			blocks.add(new IpBlock(Long.parseLong(line[0]), Long.parseLong(line[1]), Integer.parseInt(line[2])));
		}
		csvBlocksReader.close();

		BufferedReader bufferedLocationsReader = new BufferedReader(new FileReader(locationsFile));
		CSVReader csvLocationsReader = new CSVReader(bufferedLocationsReader, 
				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 2);
		
		Map<Integer, Location> locations = new HashMap<Integer, Location>();
		while ((line = csvLocationsReader.readNext()) != null) {
			//System.out.println(Arrays.toString(line));
			//blocks.add(new IpBlock(Long.parseLong(line[0]), Long.parseLong(line[1]), Integer.parseInt(line[2])));
			int locId = Integer.parseInt(line[0]);
			locations.put(locId, new Location(locId, line[1], line[2], line[3], line[4], 
					Double.parseDouble(line[5]), Double.parseDouble(line[6]), line[7], line[8]));
		}
		csvLocationsReader.close();
		
		Collections.sort(blocks);
		
		
		long ipToLookFor = convertStringIpToLong("18.4.70.53");//89.67.113.30");
		
		int val = Collections.binarySearch(blocks, new IpBlock(ipToLookFor, ipToLookFor, 0));
		
		
		System.out.println("Looking for ip: " + ipToLookFor);
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
		System.out.println("matching block: " + String.valueOf(matchingBlock) + "\tcandidate: " + val + "\tsize: " + blocks.size());
		Location location = null;
		if (matchingBlock != null) {
			location = locations.get(matchingBlock.locId);
		}
		
		if (location != null) {
			System.out.println(location);
		}
		else {
			System.out.println("not found");
		}
		
		
		
		
		
		
	}
	
	private static long convertStringIpToLong(String ipAddress) {
		String[] parts = ipAddress.split("\\.");
		
		long ipNumber = 0;
		for (int i = 0; i < 4; i++) {
		    ipNumber += Integer.parseInt(parts[i]) << (24 - (8 * i));
		}
		return ipNumber;
	}
	
	//locId,country,region,city,postalCode,latitude,longitude,metroCode,areaCode
	public static class Location {

		int locId;
		String country;
		String countryName;
		String region;
		
		String city;
		String postalCode;
		double latitude;
		double longitude;
		String metroCode;
		String areaCode;
		public Location(int locId, String country, String region, String city,
				String postalCode, double latitude, double longitude,
				String metroCode, String areaCode) {
			super();
			this.locId = locId;
			this.country = country;
			this.region = region;
			this.city = city;
			this.postalCode = postalCode;
			this.latitude = latitude;
			this.longitude = longitude;
			this.metroCode = metroCode;
			this.areaCode = areaCode;
			
			Locale l = new Locale("", country);
			countryName = l.getDisplayCountry();
		}
		@Override
		public String toString() {
			return "Location [locId=" + locId + ", country=" + country
					+ ", countryName=" + countryName + ", region=" + region
					+ ", city=" + city + ", postalCode=" + postalCode
					+ ", latitude=" + latitude + ", longitude=" + longitude
					+ ", metroCode=" + metroCode + ", areaCode=" + areaCode
					+ "]";
		}
		
		
	}

	public static class IpBlock implements Comparable<IpBlock> {
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
}
