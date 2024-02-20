package hidden;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) throws Exception {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        System.out.println("Define Class Test");
        byte[] data = Files.readAllBytes(new File("data/Hello.class.bytes").toPath());
        Class helloClz = lookup.defineClass(data);
        Object value = helloClz.getMethod("simpleMessage").invoke(null);
        System.out.printf("method return %s\n", value);
        Class sameClass = App.class.getClassLoader().loadClass("hidden.Hello");
        System.out.printf("found %s\n", sameClass);

        System.out.println("--------------");
        System.out.println("Define Hidden Class Test");
        byte[] discreteData = Files.readAllBytes(new File("data/DiscreteHello.class.bytes").toPath());
        Class discreteHello = lookup.defineHiddenClass(discreteData, true).lookupClass();
        Object discreteValue = discreteHello.getMethod("simpleMessage").invoke(null);
        System.out.printf("method return %s\n", discreteValue);

        try {
            Class discreteClassLookup = App.class.getClassLoader().loadClass("hidden.DiscreteHello");
            System.out.printf("discrete class in class loader %s\n", discreteClassLookup);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("hidden.DiscreteHello was not found as expected");
        }
    }
}
