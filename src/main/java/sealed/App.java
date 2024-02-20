package sealed;


import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;

sealed abstract class Planet permits Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune {
    private final double diameter;
    private final double distanceFromSun;
    private final double daysToRevolveAroundSun;

    private final Duration rotationDuration;
    private final double axialTilt;

    public Planet(double diameter, double distanceFromSun, double daysToRevolveAroundSun, Duration rotationDuration, double axialTilt) {
        this.diameter = diameter;
        this.distanceFromSun = distanceFromSun;
        this.daysToRevolveAroundSun = daysToRevolveAroundSun;
        this.rotationDuration = rotationDuration;
        this.axialTilt = axialTilt;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getDistanceFromSun() {
        return distanceFromSun;
    }

    public double getDaysToRevolveAroundSun() {
        return daysToRevolveAroundSun;
    }

    public Duration getRotationDuration() {
        return rotationDuration;
    }

    public double getAxialTilt() {
        return axialTilt;
    }
}

non-sealed class Mercury extends Planet {
    public Mercury() {
        super(4879.4, 57.9, 88.0, Duration.ofDays(58).plusHours(14L).plusMinutes(24L), 0.034);
    }
}

non-sealed class Venus extends Planet {
    public Venus() {
        super(12104, 108.2, 224.7, Duration.ofDays(243), 177.0);
    }
}

non-sealed class Earth extends Planet {
    public Earth() {
        super(12742, 149.6, 365.25, Duration.ofHours(24), 23.5);
    }
}

non-sealed class Mars extends Planet {
    public Mars() {
        super(6779, 227.9, 687.0, Duration.ofHours(24).plusMinutes(36), 25.2);
    }
}

non-sealed class Jupiter extends Planet {
    public Jupiter() {
        super(139820, 778.5, 4331, Duration.ofHours(9).plusMinutes(54L), 3.1);
    }
}


non-sealed class Saturn extends Planet {
    public Saturn() {
        super(116460, 1433.5, 10747, Duration.ofHours(10).plusMinutes(42L), 26.7);
    }
}

non-sealed class Uranus extends Planet {
    public Uranus() {
        super(50724, 2872.5, 30589, Duration.ofHours(17).plusMinutes(12L), 97.8);
    }
}

non-sealed class Neptune extends Planet {
    public Neptune() {
        super(49244, 4495.1, 59800, Duration.ofHours(16).plusMinutes(6L), 28.3);
    }
}

public class App {
    public static void main(String[] args) {
        List<Planet> p = List.of(
                new Mercury(), new Venus(), new Earth(), new Mars(), new Jupiter(), new Saturn(),
                new Neptune()
        );
        int value = new SecureRandom().nextInt(7);
        Planet planet = p.get(value);
        switch (planet) {
            case Mercury m:
                System.out.println("The Swift Planet");
                break;
            case Venus v:
                System.out.println("The Morning Star");
                break;
            case Earth ignored:
                System.out.println("The Blue Planet");
                break;
            case Mars m:
                System.out.println("The Red Planet");
                break;
            case Jupiter j:
                System.out.println("The Giant Planet");
                break;
            case Saturn s:
                System.out.println("The Ringed Planet");
                break;
            case Uranus u:
                System.out.println("The Ice Giant");
                break;
            case Neptune n:
                System.out.println("The Windy Planet");
        }
        System.out.printf("""
                        Name:                             %s
                        Diameter in Kms:                  %f
                        Distance from Sun in million kms: %f
                        Axial Tilt:                       %f
                        Rotation Duration:                %s
                        """,
                planet.getClass().getSimpleName(),
                planet.getDiameter(),
                planet.getDistanceFromSun(), planet.getAxialTilt(), planet.getRotationDuration().toString());
    }
}
