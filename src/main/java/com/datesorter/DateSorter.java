package com.datesorter;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Marking will be based upon producing a readable, well-engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 *
 * Implement in single class.
 */
public class DateSorter {

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates containing the sort string in the month,
     * sorted ascending (first to last),
     * then dates without the sort string in the month,
     * sorted descending (last to first).
     *
     * For instance, if sortString = "r":
     * October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2005-07-01, 2005-01-02, 2005-01-01, 2005-05-03)
     * would sort to
     * (2005-01-01, 2005-01-02, 2005-07-01, 2005-05-03)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    private final String sortString;

    public DateSorter(String sortString) {
        this.sortString = sortString;
    }

    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        if (unsortedDates == null || unsortedDates.contains(null)) {
            throw new IllegalArgumentException("Input data is null or contains null values!");
        }

        List<LocalDate> matchingDates = unsortedDates.stream()
                .filter(date -> isMonthContainSortString(date.getMonth()))
                .sorted()
                .collect(Collectors.toList());

        List<LocalDate> nonMatchingDates = unsortedDates.stream()
                .filter(date -> !isMonthContainSortString(date.getMonth()))
                .sorted(Comparator.reverseOrder())
                .toList();

        matchingDates.addAll(nonMatchingDates);
        return matchingDates;
    }

    private boolean isMonthContainSortString(Month month) {
        return month.toString().toLowerCase().contains(sortString.toLowerCase());
    }
}
