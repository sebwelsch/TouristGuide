package kea.touristguide.repository;

import kea.touristguide.model.Tag;
import kea.touristguide.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class TouristRepository {
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String db_username;
    @Value("${spring.datasource.password}")
    private String db_password;

    private Connection getDBConnection() throws SQLException {
        return DriverManager.getConnection(db_url, db_username, db_password);
    }

    public ArrayList<TouristAttraction> getAllTouristAttractions() {
        ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();
        String query = "SELECT name, description, city, tags FROM attractions";

        try (Connection dbConnection = getDBConnection()) {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                TouristAttraction attraction = new TouristAttraction(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("city"),
                        List.of(rs.getString("tags").split(","))
                );
                touristAttractions.add(attraction);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all tourist attractions", e);
        }
        return touristAttractions;
    }

    public TouristAttraction getTouristAttraction(String name) {
        String query = "SELECT name, description, city, tags FROM attractions WHERE name = ?";
        try (Connection dbConnection = getDBConnection()) {
            PreparedStatement pstmt = dbConnection.prepareStatement(query);

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TouristAttraction(
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("city"),
                            List.of(rs.getString("tags").split(","))
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching the tourist attraction", e);
        }
        return null;
    }

    public TreeSet<String> getCities() {
        TreeSet<String> cities = new TreeSet<>();
        String query = "SELECT name FROM cities";

        try (Connection dbConnection = getDBConnection()) {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                cities.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching cities", e);
        }
        return cities;
    }

    public List<String> getTags() {
        List<String> tagNames = new ArrayList<>();
        for (Tag tag : Tag.values()) {
            tagNames.add(tag.getDisplayName());
        }
        return tagNames;
    }


    public void saveTouristAttraction(TouristAttraction newTouristAttraction) {
        String query = "INSERT INTO attractions (name, description, city, tags) VALUES (?, ?, ?, ?)";
        try (Connection dbConnection = getDBConnection()) {
            PreparedStatement pstmt = dbConnection.prepareStatement(query);

            pstmt.setString(1, newTouristAttraction.getName());
            pstmt.setString(2, newTouristAttraction.getDescription());
            pstmt.setString(3, newTouristAttraction.getCity());
            pstmt.setString(4, String.join(",", newTouristAttraction.getTags()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving the tourist attraction", e);
        }
    }

    public void updateTouristAttraction(String name, TouristAttraction updatedTouristAttraction) {
        String query = "UPDATE attractions SET name = ?, description = ?, city = ?, tags = ? WHERE name = ?";
        try (Connection dbConnection = getDBConnection()) {
            PreparedStatement pstmt = dbConnection.prepareStatement(query);

            pstmt.setString(1, updatedTouristAttraction.getName());
            pstmt.setString(2, updatedTouristAttraction.getDescription());
            pstmt.setString(3, updatedTouristAttraction.getCity());
            pstmt.setString(4, String.join(",", updatedTouristAttraction.getTags()));
            pstmt.setString(5, name);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the tourist attraction", e);
        }
    }

    public TouristAttraction deleteTouristAttraction(String name) {
        String query = "DELETE FROM attractions WHERE name = ?";
        try (Connection dbConnection = getDBConnection()) {
            PreparedStatement pstmt = dbConnection.prepareStatement(query);

            pstmt.setString(1, name);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return new TouristAttraction(name, null, null, null);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting the tourist attraction", e);
        }
        return null;
    }
}
