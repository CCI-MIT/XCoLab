package org.xcolab.service.tracking.service.iptranslation;

class IpBlock implements Comparable<IpBlock> {
    final long ipFrom;
    final long ipTo;
    final int locId;

    public IpBlock(long ipFrom, long ipTo, int locId) {
        this.ipFrom = ipFrom;
        this.ipTo = ipTo;
        this.locId = locId;
    }
    @Override
    public int compareTo(IpBlock o) {
        long val = ipFrom - o.ipFrom;

        return val == 0 ? 0 : val < 0 ? -1 : 1;
    }
    @Override
    public String toString() {
        return String.format("IpBlock [ipFrom=%d, ipTo=%d, locId=%d]", ipFrom, ipTo, locId);
    }

}
