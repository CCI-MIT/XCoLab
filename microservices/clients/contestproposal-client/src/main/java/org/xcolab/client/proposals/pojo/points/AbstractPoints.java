package org.xcolab.client.proposals.pojo.points;


import java.io.Serializable;

class AbstractPoints implements Serializable {

        private static final long serialVersionUID = 1890682318;

        private Long   id_;
        private Long   proposalid;
        private Long   userid;
        private Double materializedpoints;
        private Double hypotheticalpoints;
        private Long   pointssourceid;
        private Long   originatingcontestpk;
        private Long   originatingproposalid;

        public AbstractPoints() {}

        public AbstractPoints(AbstractPoints value) {
            this.id_ = value.id_;
            this.proposalid = value.proposalid;
            this.userid = value.userid;
            this.materializedpoints = value.materializedpoints;
            this.hypotheticalpoints = value.hypotheticalpoints;
            this.pointssourceid = value.pointssourceid;
            this.originatingcontestpk = value.originatingcontestpk;
            this.originatingproposalid = value.originatingproposalid;
        }

        public AbstractPoints(
                Long   id_,
                Long   proposalid,
                Long   userid,
                Double materializedpoints,
                Double hypotheticalpoints,
                Long   pointssourceid,
                Long   originatingcontestpk,
                Long   originatingproposalid
        ) {
            this.id_ = id_;
            this.proposalid = proposalid;
            this.userid = userid;
            this.materializedpoints = materializedpoints;
            this.hypotheticalpoints = hypotheticalpoints;
            this.pointssourceid = pointssourceid;
            this.originatingcontestpk = originatingcontestpk;
            this.originatingproposalid = originatingproposalid;
        }

        public Long getId_() {
            return this.id_;
        }

        public void setId_(Long id_) {
            this.id_ = id_;
        }

        public Long getProposalId() {
            return this.proposalid;
        }

        public void setProposalId(Long proposalid) {
            this.proposalid = proposalid;
        }

        public Long getUserId() {
            return this.userid;
        }

        public void setUserId(Long userid) {
            this.userid = userid;
        }

        public Double getMaterializedPoints() {
            return this.materializedpoints;
        }

        public void setMaterializedPoints(Double materializedpoints) {
            this.materializedpoints = materializedpoints;
        }

        public Double getHypotheticalPoints() {
            return this.hypotheticalpoints;
        }

        public void setHypotheticalPoints(Double hypotheticalpoints) {
            this.hypotheticalpoints = hypotheticalpoints;
        }

        public Long getPointsSourceId() {
            return this.pointssourceid;
        }

        public void setPointsSourceId(Long pointssourceid) {
            this.pointssourceid = pointssourceid;
        }

        public Long getOriginatingContestPK() {
            return this.originatingcontestpk;
        }

        public void setOriginatingContestPK(Long originatingcontestpk) {
            this.originatingcontestpk = originatingcontestpk;
        }

        public Long getOriginatingProposalId() {
            return this.originatingproposalid;
        }

        public void setOriginatingProposalId(Long originatingproposalid) {
            this.originatingproposalid = originatingproposalid;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final AbstractPoints other = (AbstractPoints) obj;
            if (id_ == null) {
                if (other.id_ != null)
                    return false;
            }
            else if (!id_.equals(other.id_))
                return false;
            if (proposalid == null) {
                if (other.proposalid != null)
                    return false;
            }
            else if (!proposalid.equals(other.proposalid))
                return false;
            if (userid == null) {
                if (other.userid != null)
                    return false;
            }
            else if (!userid.equals(other.userid))
                return false;
            if (materializedpoints == null) {
                if (other.materializedpoints != null)
                    return false;
            }
            else if (!materializedpoints.equals(other.materializedpoints))
                return false;
            if (hypotheticalpoints == null) {
                if (other.hypotheticalpoints != null)
                    return false;
            }
            else if (!hypotheticalpoints.equals(other.hypotheticalpoints))
                return false;
            if (pointssourceid == null) {
                if (other.pointssourceid != null)
                    return false;
            }
            else if (!pointssourceid.equals(other.pointssourceid))
                return false;
            if (originatingcontestpk == null) {
                if (other.originatingcontestpk != null)
                    return false;
            }
            else if (!originatingcontestpk.equals(other.originatingcontestpk))
                return false;
            if (originatingproposalid == null) {
                if (other.originatingproposalid != null)
                    return false;
            }
            else if (!originatingproposalid.equals(other.originatingproposalid))
                return false;
            return true;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
            result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
            result = prime * result + ((userid == null) ? 0 : userid.hashCode());
            result = prime * result + ((materializedpoints == null) ? 0 : materializedpoints.hashCode());
            result = prime * result + ((hypotheticalpoints == null) ? 0 : hypotheticalpoints.hashCode());
            result = prime * result + ((pointssourceid == null) ? 0 : pointssourceid.hashCode());
            result = prime * result + ((originatingcontestpk == null) ? 0 : originatingcontestpk.hashCode());
            result = prime * result + ((originatingproposalid == null) ? 0 : originatingproposalid.hashCode());
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Points (");

            sb.append(id_);
            sb.append(", ").append(proposalid);
            sb.append(", ").append(userid);
            sb.append(", ").append(materializedpoints);
            sb.append(", ").append(hypotheticalpoints);
            sb.append(", ").append(pointssourceid);
            sb.append(", ").append(originatingcontestpk);
            sb.append(", ").append(originatingproposalid);

            sb.append(")");
            return sb.toString();
        }
    }

