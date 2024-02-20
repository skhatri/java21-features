package zgc;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

record Output(int code, byte[] data, Map<String, String> headers) {
}

public class App {

    private static final class HeaderSet {
        public static final Map<String, String> JSON = Map.of("Content-Type", "application/json");
    }

    private static class DefaultHandler implements HttpHandler {

        Output welcome() {
            return new Output(
                    200, """
                    {"message": "Welcome to Java 21 HttpServer"}""".getBytes(StandardCharsets.UTF_8),
                    HeaderSet.JSON
            );
        }

        Output status() {
            return new Output(200, """
                    {"status": "OK"}
                    """.getBytes(StandardCharsets.UTF_8), HeaderSet.JSON);
        }

        Output time() {
            String time = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return new Output(200, String.format("""
                    {"time": "%s"}""", time).getBytes(StandardCharsets.UTF_8), HeaderSet.JSON);
        }

        Output notFound() {
            return new Output(404, "Resource not found".getBytes(StandardCharsets.UTF_8), Map.of("Content-Type", "text/plain"));
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath().toLowerCase();
            String method = exchange.getRequestMethod();
            Output output = switch (path) {
                case "/" -> welcome();
                case "/status" -> status();
                case "/time" -> time();
                default -> notFound();
            };
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            OutputStream stream = exchange.getResponseBody();

            byte[] data = output.data();
            exchange.sendResponseHeaders(output.code(), data.length);
            long start = System.currentTimeMillis();
            stream.write(data);
            stream.flush();
            stream.close();
            long end = System.currentTimeMillis();
            System.out.printf("%s path: %-40s, time: %d ms\n", method, path, (end - start));
        }
    }

    public static void main(String[] args) throws IOException {
        HttpHandler httpHandler = new DefaultHandler();
        HttpServer server = HttpServer.create()
                .createContext("/", httpHandler).getServer();
        server.bind(new InetSocketAddress("0.0.0.0", 8040), 100);
        server.start();
    }
}
