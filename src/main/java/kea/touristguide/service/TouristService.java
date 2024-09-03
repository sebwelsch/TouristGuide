package kea.touristguide.service;

import kea.touristguide.model.TouristAttraction;
import kea.touristguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public void addTouristAttraction(String name, String description) {
        touristRepository.addTouristAttraction(name, description);
    }
}