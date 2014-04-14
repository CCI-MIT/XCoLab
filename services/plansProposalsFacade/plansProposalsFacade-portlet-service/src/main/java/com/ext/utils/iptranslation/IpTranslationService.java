package com.ext.utils.iptranslation;


public interface IpTranslationService {
	public Location getLocationForIp(String ip) throws Exception;
	public void reloadLocationAndBlockData() throws Exception;
}
