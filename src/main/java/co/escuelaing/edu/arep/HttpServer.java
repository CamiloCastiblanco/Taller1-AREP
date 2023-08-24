package co.escuelaing.edu.arep;
import static spark.Spark.*;
import spark.Request;
import java.io.*;


public class HttpServer {

   
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/static");
        get("/movies", (req, res) -> {
            res.type("application/json");
            return identifyMovie(req);
        });
        
    }

    
    private static String identifyMovie(Request req) throws IOException {
        String movie = new Movie(req.queryParams("movie")).getJson();
        return movie;
    }

    
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}



