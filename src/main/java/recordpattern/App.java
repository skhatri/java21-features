package recordpattern;


import java.security.SecureRandom;
import java.util.List;

record GeoLocation(double lat, double lng) {
}

enum Category {FOOD, DRINKS, LEISURE, TECHNOLOGY, OTHERS};

record Merchant(String name, GeoLocation location, Category category) {
}

public class App {
    public static void main(String[] args) {
        Merchant one = new Merchant("Woolies", new GeoLocation(-33.8731247, 151.2072211), Category.FOOD);
        Merchant two = new Merchant("Event Cinemas", new GeoLocation(-33.8731174, 151.1762141), Category.LEISURE);
        Merchant three = new Merchant("Apple Store Miranda", new GeoLocation(-34.0345697, 151.0982815), Category.TECHNOLOGY);
        List<Merchant> src = List.of(one, two, three);
        Object item = src.get(new SecureRandom().nextInt(src.size()));


        if (item instanceof Merchant(String name, GeoLocation(var lat, var lng), Category cat)) {
            System.out.printf("merchant: %s, decomposed lat lng: %f,%f\n", name, lat, lng);
        }

    }
}
