package co.escuelaing.edu.arep;

import java.util.HashMap;

public class Cache {
    private static final Cache _instance = new Cache();
    private HashMap<String, String> cache = new HashMap<>();
    /**
     * Constructor for Cache
     */
    private Cache() {
    }

    /**
     * Metodo que inserta una valor en el cache
     * 
     * @param key   Clave del valor a insertar
     * @param value Valor a insertar
     */
    public void insert(String key, String value) {
        cache.put(key, value);
    }

    /**
     * Metodo que obtiene el valor asociado a una key
     * 
     * @param key Key a buscar
     * @return Valor asociado a la key
     */
    public String get(String key) {
        return cache.get(key);
    }

    /**
     * Metodo que devuelve si el cache contiene la key
     * 
     * @param key la key a buscar
     * @return true si el cache contiene la key, false en caso contrario
     */
    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    /**
     * Metodo que devuelve la instancia del cache
     * 
     * @return the _instance
     */
    public static Cache getInstance() {
        return _instance;
    }
}
