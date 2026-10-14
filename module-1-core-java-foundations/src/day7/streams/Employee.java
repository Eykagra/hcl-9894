package day7.streams;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Day 7 - A record: a short way to write an immutable data class.
 * Java generates the constructor, the getters (name(), salary(), ...), equals, hashCode and toString.
 */
public record Employee(int id, String name, String department, BigDecimal salary, LocalDate hireDate, Set<String> skills) {
}
