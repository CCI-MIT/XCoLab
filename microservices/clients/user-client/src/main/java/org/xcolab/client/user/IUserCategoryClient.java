package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.MemberCategoryNotFoundException;
import org.xcolab.client.user.pojo.wrapper.MemberCategoryWrapper;

import java.util.List;

;

@FeignClient("xcolab-user-service")
public interface IUserCategoryClient {

    @RequestMapping(value = "/membercategories/{roleId}", method = RequestMethod.GET)
    MemberCategoryWrapper getMemberCategory(@PathVariable long roleId) throws MemberCategoryNotFoundException;

    @RequestMapping(value = "/membercategories", method = RequestMethod.GET)
    List<MemberCategoryWrapper> getMemberCategories(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String displayName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Boolean showInList);

    default List<MemberCategoryWrapper> listMemberCategories() {
        return getMemberCategories(0,Integer.MAX_VALUE, null,null,null,null);
    }



    default MemberCategoryWrapper getMemberCategory(String displayName) {
        List<MemberCategoryWrapper> list = getMemberCategories(
                0,Integer.MAX_VALUE, null,displayName,null,null);

        if (list != null && ! list.isEmpty()) {
            return list.get(0);
        }

        throw new MemberCategoryNotFoundException("Category with name " + displayName + " not found.");
    }

    default List<MemberCategoryWrapper> getVisibleMemberCategories() {

        List<MemberCategoryWrapper> list = getMemberCategories(
                0,Integer.MAX_VALUE, null,null,null,true);

        return list;
    }
}
