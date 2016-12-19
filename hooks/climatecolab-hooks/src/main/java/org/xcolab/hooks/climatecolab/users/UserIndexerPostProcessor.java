package org.xcolab.hooks.climatecolab.users;

import com.ext.portlet.Activity.ActivityUtil;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.NoSuchRoleException;
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
import org.xcolab.entity.utils.enums.MemberRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class UserIndexerPostProcessor extends BaseIndexerPostProcessor {

	private final static Log _log = LogFactoryUtil
			.getLog(UserIndexerPostProcessor.class);

	private static Map<Long, MemberRole> roleIdToCategoryMap;
	private final static long DEFAULT_COMPANY_ID = 10112L;
	@Override
	public void postProcessDocument(Document document, Object object)
			throws SystemException, PortalException {
		
		User user = (User) object;
		document.addDate("joinDate", user.getCreateDate());
        long activities = ActivityUtil.getActivitiesCount(user.getUserId());
		document.addNumber("activities",
				activities > 0 ? activities : 1); // Fix if joined colab activity is not in DB/cache yet
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

		List<String> cities = new ArrayList<>();

		List<String> countries = new ArrayList<>();

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

		List<String> regions = new ArrayList<>();

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

		List<String> streets = new ArrayList<>();
		List<String> zips = new ArrayList<>();

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
		Set<String> countryNames = new HashSet<>();

		Locale[] locales = LanguageUtil.getAvailableLocales();

		for (Locale locale : locales) {
			String countryName = country.getName(locale);

			countryName = StringUtil.toLowerCase(countryName);

			countryNames.add(countryName);
		}

		return countryNames;
	}

	private static String[] getUserCategories(User user) throws SystemException {
		initRoleIdToCategoryMap();
		List<String> categories = new ArrayList<>();

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
		roleIdToCategoryMap = new HashMap<>();

		for (MemberRole category : MemberRole.values()) {
			try {
				if (category.equals(MemberRole.ALL)
						|| category.equals(MemberRole.DEFAULT)) {
					continue;
				}
				for (String roleName : category.getRoleNames()) {
					try {
						Role role = RoleLocalServiceUtil.getRole(
								DEFAULT_COMPANY_ID, roleName);
						roleIdToCategoryMap.put(role.getRoleId(), category);
					} catch(NoSuchRoleException ignored) { }
				}
			} catch (PortalException | SystemException e) {
				_log.error("Can't find role for user category " + category, e);
			}
		}
	}

}
