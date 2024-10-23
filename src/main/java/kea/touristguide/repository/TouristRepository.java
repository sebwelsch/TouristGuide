package kea.touristguide.repository;

import kea.touristguide.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String db_username;
    @Value("${spring.datasource.password}")
    private String db_password;

    public TouristRepository() {
        touristAttractions.add(new TouristAttraction("SMK", "Museum for Kunst", "København", List.of("Kunst", "Museum")));
        touristAttractions.add(new TouristAttraction("Odense Zoo", "Europas bedste zoo", "Odense", List.of("Børnevenlig")));
        touristAttractions.add(new TouristAttraction("Dyrehaven", "Naturpark med skovområder", "Kongens Lyngby", List.of("Natur", "Gratis")));
        touristAttractions.add(new TouristAttraction("Tivoli", "Forlystelsespark i Københavns centrum", "København", List.of("Børnevenlig")));
        try (Connection dbConnection = DriverManager.getConnection(db_url, db_username, db_password)) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TouristAttraction> getAllTouristAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getTouristAttraction(String name) {
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                return touristAttraction;
            }
        }
        return null;
    }

    public TreeSet<String> getCities() {
        TreeSet<String> cities = new TreeSet<>();
        for (TouristAttraction touristAttraction : touristAttractions) {
            cities.add(touristAttraction.getCity());
        }
        return cities;
    }

    public List<String> getTags() {
        Set<String> uniqueTags = new HashSet<>();
        for (TouristAttraction touristAttraction : touristAttractions) {
            uniqueTags.addAll(touristAttraction.getTags());
        }
        return new ArrayList<>(uniqueTags);
    }

    public void saveTouristAttraction(TouristAttraction newTouristAttraction) {
        touristAttractions.add(newTouristAttraction);
    }

    public void updateTouristAttraction(String name, TouristAttraction updatedTouristAttraction) {
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                touristAttraction.setName(updatedTouristAttraction.getName());
                touristAttraction.setDescription(updatedTouristAttraction.getDescription());
                touristAttraction.setCity(updatedTouristAttraction.getCity());
                touristAttraction.setTags(updatedTouristAttraction.getTags());
            }
        }
    }

    public TouristAttraction deleteTouristAttraction(String name) {
        Iterator<TouristAttraction> iterator = touristAttractions.iterator();
        while (iterator.hasNext()) {
            TouristAttraction touristAttraction = iterator.next();
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                iterator.remove();
                return touristAttraction;
            }
        }
        return null;
    }
}
