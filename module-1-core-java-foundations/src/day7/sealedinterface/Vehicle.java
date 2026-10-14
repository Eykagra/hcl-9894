package day7.sealedinterface;

/**
 * Day 7 - Sealed interface: only the types listed in permits may implement it.
 * The compiler therefore knows every possible kind of Vehicle.
 */
public sealed interface Vehicle permits Car, Bike {
}
