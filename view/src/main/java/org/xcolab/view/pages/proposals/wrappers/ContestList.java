package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.contest.pojo.Contest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContestList {

    private final List<Contest> list;

    private ContestList(Collection<Contest> collection) {
        list = new ArrayList<>(collection);
    }

    public ContestList(Collection<Contest> collection, Comparator<Contest> comparator) {
        this(collection);
        list.sort(comparator);
    }

    public ContestList getFiltered(Predicate<Contest> contestPredicate) {
        return new ContestList(list.stream().filter(contestPredicate)
                .collect(Collectors.toList()));
    }

    public ContestList getFeatured() {
        return new ContestList(list.stream().filter(Contest::isFeatured)
                .collect(Collectors.toList()));
    }

    public ContestList getNotFeatured() {
        return new ContestList(list.stream().filter(contest -> !contest.isFeatured())
                .collect(Collectors.toList()));
    }

    public ContestList getInTileView() {
        return new ContestList(list.stream().filter(Contest::getShowInTileView)
                .collect(Collectors.toList()));
    }

    public ContestList getInOutlineView() {
        return new ContestList(list.stream()
                .filter(Contest::getShowInOutlineView)
                .collect(Collectors.toList()));
    }

    public ContestList getInListView() {
        return new ContestList(list.stream().filter(Contest::getShowInListView)
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

    public List<Contest> getList() {
        return new ArrayList<>(list);
    }
}
