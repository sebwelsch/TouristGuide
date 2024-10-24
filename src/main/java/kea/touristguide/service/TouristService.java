package kea.touristguide.service;

import kea.touristguide.model.TouristAttraction;
import kea.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class TouristService {
    private final TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public ArrayList<TouristAttraction> getAllTouristAttractions() {
        return touristRepository.getAllTouristAttractions();
    }

    public TouristAttraction getTouristAttraction(String name) {
        return touristRepository.getTouristAttraction(name);
    }

    public TreeSet<String> getCities() {
        return touristRepository.getCities();
    }

    public List<String> getTags() {
        return touristRepository.getTags();
    }

    public void saveTouristAttraction(TouristAttraction newTouristAttraction) {
        touristRepository.saveTouristAttraction(newTouristAttraction);
    }

    public void updateTouristAttraction(String name, TouristAttraction updatedTouristAttraction) {
        touristRepository.updateTouristAttraction(name, updatedTouristAttraction);
    }

    public void deleteTouristAttraction(String name) {
        touristRepository.deleteTouristAttraction(name);
    }
}
