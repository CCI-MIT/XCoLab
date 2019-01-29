package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.user.pojo.MemberCategory;

import java.util.List;

;

@FeignClient("xcolab-user-service")
public interface IUserCategoryClient {

    @RequestMapping(value = "/membercategories/{roleId}", method = RequestMethod.GET)
    MemberCategory getMemberCategory(@PathVariable long roleId) throws MemberCategoryNotFoundException;

    @RequestMapping(value = "/membercategories", method = RequestMethod.GET)
    List<MemberCategory> getMemberCategories(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Boolean showInList);

    default List<MemberCategory> listMemberCategories() {
        return getMemberCategories(0,Integer.MAX_VALUE, null,null,null,null);
    }



    default MemberCategory getMemberCategory(String displayName) {
        List<MemberCategory> list = getMemberCategories(
                0,Integer.MAX_VALUE, null,displayName,null,null);

        if (list != null && ! list.isEmpty()) {
            return list.get(0);
        }

        throw new MemberCategoryNotFoundException("Category with name " + displayName + " not found.");
    }

    default List<MemberCategory> getVisibleMemberCategories() {

        List<MemberCategory> list = getMemberCategories(
                0,Integer.MAX_VALUE, null,null,null,true);

        return list;
    }
}
