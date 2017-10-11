package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.xcolab.client.members.pojo.Member;

import java.util.List;
import java.util.Random;

public class PlatformTeam {

    private Long id_;
    private String name;
    private List<Member> members;

    public PlatformTeam(Long id) {
        this.id_ = id;
    }

    public PlatformTeam() {
        this(new Random().nextLong());
    }

    public Long getId_() {
        return id_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}


