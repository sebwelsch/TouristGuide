package kea.touristguide.repository;

import kea.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        touristAttractions.add(new TouristAttraction("Copenhagen", "Denmarks capital"));
        touristAttractions.add(new TouristAttraction("Oslo", "Norways capital"));
        touristAttractions.add(new TouristAttraction("Stockholm", "Swedens capital"));
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

    public void addTouristAttraction(TouristAttraction newTouristAttraction) {
        touristAttractions.add(newTouristAttraction);
    }

    public void updateTouristAttraction(String name, TouristAttraction updatedTouristAttraction) {
        for (TouristAttraction touristAttraction : touristAttractions) {
            if (touristAttraction.getName().equalsIgnoreCase(name)) {
                touristAttraction.setName(updatedTouristAttraction.getName());
                touristAttraction.setDescription(updatedTouristAttraction.getDescription());
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