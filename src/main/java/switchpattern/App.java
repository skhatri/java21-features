package switchpattern;

import java.math.BigDecimal;

public class App {

    /**
     * Uses switch expression to return value as well as switch pattern matching to identify numeric values
     * @param args
     */
    public static void main(String[] args) {
        Object[] inputs = new Object[]{1, "name", 10, 5, 100034, false, BigDecimal.ZERO, 0.2f};
        int numbers = 0;
        for (Object input : inputs) {
            boolean isNumber = switch (input) {
                case String s when s.isEmpty() -> {
                    System.out.println("empty string");
                    yield false;
                }
                case Integer i when i > 1000 -> {
                    System.out.println("Big number");
                    yield true;
                }
                case Integer i -> true;
                case Double d -> true;
                case BigDecimal bd -> true;
                case Number n -> {
                    System.out.println("caught by number match");
                    yield true;
                }
                default -> {
                    System.out.println(input);
                    yield false;
                }
            };
            if (isNumber) {
                numbers++;
            }

        }
        System.out.printf("Numbers: %d\n", numbers);

    }
}
