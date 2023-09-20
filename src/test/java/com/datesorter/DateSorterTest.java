package com.datesorter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateSorterTest {
    private DateSorter dateSorter;
    private List<LocalDate> exampleListToSort;
    private List<LocalDate> exampleSortedList;
    private List<LocalDate> listToSort;
    private List<LocalDate> sortedList;

    @BeforeEach
    void setUp() {
        dateSorter = new DateSorter("r");

        exampleListToSort = Arrays.asList(
                LocalDate.of(2005, 7, 1),
                LocalDate.of(2005, 1, 2),
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 5, 3)
        );

        exampleSortedList = Arrays.asList(
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 1, 2),
                LocalDate.of(2005, 7, 1),
                LocalDate.of(2005, 5, 3)
        );

        listToSort = Arrays.asList(
                LocalDate.of(2023, Month.JANUARY, 25),
                LocalDate.of(2024, Month.JANUARY, 25),
                LocalDate.of(2023, Month.APRIL, 10),
                LocalDate.of(2023, Month.SEPTEMBER, 5),
                LocalDate.of(2023, Month.DECEMBER, 5),
                LocalDate.of(2023, Month.MAY, 10),
                LocalDate.of(2023, Month.AUGUST, 20),
                LocalDate.of(2024, Month.MAY, 10)
        );

        sortedList = Arrays.asList(
                LocalDate.of(2023, Month.JANUARY, 25),
                LocalDate.of(2023, Month.APRIL, 10),
                LocalDate.of(2023, Month.SEPTEMBER, 5),
                LocalDate.of(2023, Month.DECEMBER, 5),
                LocalDate.of(2024, Month.JANUARY, 25),
                LocalDate.of(2024, Month.MAY, 10),
                LocalDate.of(2023, Month.AUGUST, 20),
                LocalDate.of(2023, Month.MAY, 10)
        );
    }

    @Test
    void sortDates_ExampleData_Ok() {
        List<LocalDate> actual = (List<LocalDate>) dateSorter.sortDates(exampleListToSort);
        assertEquals(exampleSortedList, actual);
    }

    @Test
    void sortDates_ComplexTest_Ok() {
        List<LocalDate> actual = (List<LocalDate>) dateSorter.sortDates(listToSort);
        assertEquals(sortedList, actual);
    }

    @Test
    void sortDates_NullInput_NotOk() {
        assertThrows(IllegalArgumentException.class, () -> dateSorter.sortDates(null));
    }

    @Test
    void sortDates_SomeDatesAreNull_NotOk() {
        List<LocalDate> datesWithNull = new ArrayList<>(exampleListToSort);
        datesWithNull.add(null);
        assertThrows(IllegalArgumentException.class, () -> dateSorter.sortDates(datesWithNull));
    }

    @Test
    void sortEmptyList() {
        List<LocalDate> result = (List<LocalDate>) dateSorter.sortDates(new ArrayList<>());
        assertTrue(result.isEmpty());
    }
}
