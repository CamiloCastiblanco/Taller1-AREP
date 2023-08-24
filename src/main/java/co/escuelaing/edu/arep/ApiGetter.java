package co.escuelaing.edu.arep;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class ApiGetter {
    private static final String USER_AGENT = "Mozilla/5.0";
    private String URL = " http://www.omdbapi.com/?t=Green+Lantern";
    private String PLOT = "&plot=full";
    protected ArrayList<String> parameters = new ArrayList<>();
    protected ArrayList<String> input = new ArrayList<>();
    private StringBuilder query;
    private Cache cache = Cache.getInstance();
    
    public ApiGetter() {
    }
    public ApiGetter(String URL, String PLOT) {
        this.URL = URL;
        this.PLOT = PLOT;

    }
    public abstract void buildParameters();
    public void buildQuery() {
        query = new StringBuilder();
        byte counter = 0;
        query.append(URL);
        for (String parameter : parameters) {
            query.append(parameter);
            try {
                query.append(input.get(counter));
            } catch (IndexOutOfBoundsException indexBounds) {
                System.out.println("Index out of bounds");
            }
            counter++;
        }
        query.append(PLOT);
    }
    public String getJson() throws IOException {
        if (cache.contains(query.toString())) {
            return cache.get(query.toString());
        } else {
            URL obj = new URL(query.toString());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                cache.insert(query.toString(), response.toString());
                System.out.println("GET DONE");
                return response.toString();
            } else {
                return "GET request didn't worked";
            }
        }
    }
}
