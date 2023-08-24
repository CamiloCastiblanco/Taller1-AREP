package co.escuelaing.edu.arep;

public class MovieGetter extends ApiGetter {
    private static final String URL = "http://www.omdbapi.com/?t=Green+Lantern";
    private static final String PLOT = "&plot=full";


   
    public MovieGetter(String movie) {
        super(URL, PLOT);
        buildParameters();
        input.add(movie);
        buildQuery();
    }

  
    public void buildParameters() {
        parameters.add("");
    }
}
