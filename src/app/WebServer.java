package app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * Small HTTP layer around the existing SHMS console program (app.FinalP).
 * It lets the project be reached from a browser at http://SERVER_IP:PORT/
 *
 *   GET /        -> HTML page showing the output of the hotel workflow
 *   GET /run     -> same output as plain text (API-style response)
 *   GET /health  -> "OK" (used for health checks)
 */
public class WebServer {

    /** Runs FinalP.main and returns everything it prints. */
    private static synchronized String runWorkflow() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            FinalP.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        return buffer.toString(StandardCharsets.UTF_8);
    }

    private static String escapeHtml(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static void send(HttpExchange ex, int status, String contentType, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", contentType + "; charset=utf-8");
        ex.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        String env = System.getenv("PORT");
        if (env != null && !env.isBlank()) {
            port = Integer.parseInt(env.trim());
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/health", ex -> send(ex, 200, "text/plain", "OK"));

        server.createContext("/run", ex -> send(ex, 200, "text/plain", runWorkflow()));

        server.createContext("/", ex -> {
            if (!ex.getRequestURI().getPath().equals("/")) {
                send(ex, 404, "text/plain", "Not found");
                return;
            }
            String html = "<!DOCTYPE html><html><head><meta charset=\"utf-8\">"
                    + "<title>Smart Hotel Management System</title>"
                    + "<style>body{font-family:Arial,sans-serif;max-width:900px;margin:30px auto;padding:0 16px;}"
                    + "h1{color:#1F4E9C;}pre{background:#111;color:#eee;padding:16px;border-radius:8px;overflow:auto;}"
                    + "a{color:#1F4E9C;}</style></head><body>"
                    + "<h1>Smart Hotel Management System (SHMS)</h1>"
                    + "<p>Java application using 7 design patterns, running in Docker on a VPS.</p>"
                    + "<p><a href=\"/run\">/run</a> (plain text) | <a href=\"/health\">/health</a></p>"
                    + "<pre>" + escapeHtml(runWorkflow()) + "</pre>"
                    + "</body></html>";
            send(ex, 200, "text/html", html);
        });

        server.start();
        System.out.println("SHMS web server started on port " + port);
    }
}
