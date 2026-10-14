package day7.sealedclass;

/**
 * Day 7 - Sealed class: only Circle, Rectangle and Triangle may extend Shape.
 * Every permitted subclass must say what happens next: final (no more subclasses),
 * sealed (again a fixed list) or non-sealed (open for anyone).
 */
public abstract sealed class Shape permits Circle, Rectangle, Triangle {

    public abstract double area();
}
