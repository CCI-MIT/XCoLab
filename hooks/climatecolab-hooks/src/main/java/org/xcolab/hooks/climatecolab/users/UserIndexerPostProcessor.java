package org.xcolab.hooks.climatecolab.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Region;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.DefaultFullNameGenerator;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;

public class UserIndexerPostProcessor extends BaseIndexerPostProcessor {

	private final static Log _log = LogFactoryUtil
			.getLog(UserIndexerPostProcessor.class);

	public static final String[] CLASS_NAMES = { User.class.getName() };

	public static final String PORTLET_ID = PortletKeys.USERS_ADMIN;

	private static Map<Long, MemberCategory> roleIdToCategoryMap;
	private final static long DEFAULT_COMPANY_ID = 10112L;
	public void postProcessDocument(Document document, Object object)
			throws Exception {
		
		User user = (User) object;
		document.addDate("joinDate", user.getCreateDate());
		document.addNumber("activities",
				ActivityUtil.getActivitiesCount(user.getUserId()));
		document.addKeyword("memberCategory", getUserCategories(user));
		document.addKeyword("realName", new DefaultFullNameGenerator()
				.getFullName(user.getFirstName(), user.getMiddleName(),
						user.getLastName()));
        document.addKeyword(Field.ENTRY_CLASS_NAME, User.class.getName());

		populateAddresses(document, user.getAddresses(), 0, 0);
	}

	protected void populateAddresses(Document document,
			List<Address> addresses, long regionId, long countryId)
			throws PortalException, SystemException {

		List<String> cities = new ArrayList<String>();

		List<String> countries = new ArrayList<String>();

		if (countryId > 0) {
			try {
				Country country = CountryServiceUtil.getCountry(countryId);

				countries.addAll(getLocalizedCountryNames(country));
			} catch (NoSuchCountryException nsce) {
				if (_log.isWarnEnabled()) {
					_log.warn(nsce.getMessage());
				}
			}
		}

		List<String> regions = new ArrayList<String>();

		if (regionId > 0) {
			try {
				Region region = RegionServiceUtil.getRegion(regionId);

				regions.add(StringUtil.toLowerCase(region.getName()));
			} catch (NoSuchRegionException nsre) {
				if (_log.isWarnEnabled()) {
					_log.warn(nsre.getMessage());
				}
			}
		}

		List<String> streets = new ArrayList<String>();
		List<String> zips = new ArrayList<String>();

		for (Address address : addresses) {
			cities.add(StringUtil.toLowerCase(address.getCity()));
			countries.addAll(getLocalizedCountryNames(address.getCountry()));
			regions.add(StringUtil.toLowerCase(address.getRegion().getName()));
			streets.add(StringUtil.toLowerCase(address.getStreet1()));
			streets.add(StringUtil.toLowerCase(address.getStreet2()));
			streets.add(StringUtil.toLowerCase(address.getStreet3()));
			zips.add(StringUtil.toLowerCase(address.getZip()));
		}

		document.addText("city", cities.toArray(new String[cities.size()]));
		document.addText("country",
				countries.toArray(new String[countries.size()]));
		document.addText("region", regions.toArray(new String[regions.size()]));
		document.addText("street", streets.toArray(new String[streets.size()]));
		document.addText("zip", zips.toArray(new String[zips.size()]));
	}

	protected Set<String> getLocalizedCountryNames(Country country) {
		Set<String> countryNames = new HashSet<String>();

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (Locale locale : locales) {
			String countryName = country.getName(locale);

			countryName = StringUtil.toLowerCase(countryName);

			countryNames.add(countryName);
		}

		return countryNames;
	}

	private static final boolean _PERMISSION_AWARE = true;

	private static String[] getUserCategories(User user) throws SystemException {
		initRoleIdToCategoryMap();
		List<String> categories = new ArrayList<String>();

		for (Long roleId : user.getRoleIds()) {

			if (roleIdToCategoryMap.containsKey(roleId)) {
				categories.add(roleIdToCategoryMap.get(roleId).name()
						.toLowerCase());
			}
		}
		String[] ret = new String[categories.size()];
		return categories.toArray(ret);
	}

	private static synchronized void initRoleIdToCategoryMap() {
		roleIdToCategoryMap = new HashMap<Long, MemberCategory>();

		for (MemberCategory category : MemberCategory.values()) {
			try {
				if (category.equals(MemberCategory.ALL)
						|| category.equals(MemberCategory.DEFAULT))
					continue;
				for (String roleName : category.getRoleNames()) {
					Role role = RoleLocalServiceUtil.getRole(
							DEFAULT_COMPANY_ID, roleName);
					roleIdToCategoryMap.put(role.getRoleId(), category);
				}
			} catch (PortalException e) {
				_log.error("Can't find role for user category " + category, e);
			} catch (SystemException e) {
				_log.error("Can't find role for user category " + category, e);
			}
		}
	}

}
