package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContestList {

    private final List<ContestWrapper> list;

    private ContestList(Collection<ContestWrapper> collection) {
        list = new ArrayList<>(collection);
    }

    public ContestList(Collection<ContestWrapper> collection, Comparator<ContestWrapper> comparator) {
        this(collection);
        list.sort(comparator);
    }

    public ContestList getFiltered(Predicate<ContestWrapper> contestPredicate) {
        return new ContestList(list.stream().filter(contestPredicate)
                .collect(Collectors.toList()));
    }

    public ContestList getFeatured() {
        return new ContestList(list.stream().filter(ContestWrapper::isFeatured)
                .collect(Collectors.toList()));
    }

    public ContestList getNotFeatured() {
        return new ContestList(list.stream().filter(contest -> !contest.isFeatured())
                .collect(Collectors.toList()));
    }

    public ContestList getInTileView() {
        return new ContestList(list.stream().filter(ContestWrapper::isShowInTileView)
                .collect(Collectors.toList()));
    }

    public ContestList getInOutlineView() {
        return new ContestList(list.stream()
                .filter(ContestWrapper::isShowInOutlineView)
                .collect(Collectors.toList()));
    }

    public ContestList getInListView() {
        return new ContestList(list.stream().filter(ContestWrapper::isShowInListView)
                .collect(Collectors.toList()));
    }

    public boolean getIsEmpty() {
        return list.isEmpty();
    }

    public boolean getIsNotEmpty() {
        return !list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public List<ContestWrapper> getList() {
        return new ArrayList<>(list);
    }
}
