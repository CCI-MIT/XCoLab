ALTER TABLE members_Member ENGINE=InnoDB;

CREATE TABLE xcolab_PlatformTeam
(
    id_ BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(256) DEFAULT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE xcolab_PlatformTeamMember
(
    userId BIGINT(20) NOT NULL,
    teamId BIGINT(20) NOT NULL,
    CONSTRAINT xcolab_PlatformTeamMember_userId_teamId_pk PRIMARY KEY (userId, teamId),
    CONSTRAINT xcolab_PlatformTeamMember_members_Member_id__fk FOREIGN KEY (userId) REFERENCES members_Member (id_) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT xcolab_PlatformTeamMember_xcolab_PlatformTeam_id__fk FOREIGN KEY (teamId) REFERENCES xcolab_PlatformTeam (id_) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;