package kea.touristguide.repository;

import kea.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        touristAttractions.add(new TouristAttraction("Copenhagen", "Denmarks capital"));
        touristAttractions.add(new TouristAttraction("Oslo", "Norways capital"));
    }

    public ArrayList<TouristAttraction> getAllTouristAttractions() {
        return touristAttractions;
    }

    public TouristAttraction getTouristAttraction(String name) {
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equals(name)) {
                return touristAttraction;
            }
        }
        return null;
    }
}
