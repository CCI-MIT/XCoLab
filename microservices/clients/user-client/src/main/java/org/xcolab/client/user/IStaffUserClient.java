package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.pojo.wrapper.StaffUserWrapper;

import java.util.List;

@FeignClient("xcolab-user-service")
public interface IStaffUserClient {

    @GetMapping("staffMembers")
    List<StaffUserWrapper> listStaffMembers(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long categoryId);

    default List<StaffUserWrapper> getStaffUsersByCategoryId(long categoryId) {
        return listStaffMembers(0,Integer.MAX_VALUE, null, categoryId);
    }
}
