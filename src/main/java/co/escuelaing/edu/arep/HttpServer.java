package co.escuelaing.edu.arep;

import static spark.Spark.*;
import spark.Request;
import java.io.*;
import co.escuelaing.edu.arep.Movie;

public class HttpServer {

   
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/static");
        get("/movie", (req, res) -> {
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

// http://www.omdbapi.com/?i=tt3896198&apikey=760fda9b


