package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberCategoryWrapper extends org.xcolab.client.user.pojo.tables.pojos.MemberCategory implements Serializable, Comparable<MemberCategoryWrapper> {

    private static final long serialVersionUID = -2052172539;



    public MemberCategoryWrapper() {
    }

    public MemberCategoryWrapper(MemberCategoryWrapper value) {
        this.setRoleId(value.getRoleId());
        this.setDisplayName(value.getDisplayName());
        this.setCategoryName(value.getCategoryName());
        this.setSortOrder(value.getSortOrder());
        this.setShowInList(value.isShowInList());
        this.setImageName(value.getImageName());
        this.setDescription(value.getDescription());
    }

    public MemberCategoryWrapper(
            Long roleid,
            String displayname,
            String categoryname,
            Long sortorder,
            Boolean showinlist,
            String imagename
    ) {

        this.setRoleId(roleid);
        this.setDisplayName(displayname);
        this.setCategoryName(categoryname);
        this.setSortOrder(sortorder);
        this.setShowInList(showinlist);
        this.setImageName(imagename);
    }



    @Override
    public String toString() {
        String sb = "MemberCategory (" + getRoleId() + ", " + getDisplayName() + ", " + getCategoryName() + ", "
                + getSortOrder() + ", " + isShowInList() + ", " + getImageName() + ")";

        return sb;
    }

    @Override
    public int compareTo(MemberCategoryWrapper o) {
        return getSortOrder().compareTo(o.getSortOrder());
    }
}
