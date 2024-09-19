package kea.touristguide.controller;

import kea.touristguide.model.TouristAttraction;
import kea.touristguide.service.TouristService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/attractions")
public class TouristController {
    private final TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public String getAllTouristAttractions(Model model) {
        model.addAttribute("touristAttractions", touristService.getAllTouristAttractions());
        return "attractionList";
    }

    @GetMapping("/{name}")
    public ResponseEntity<TouristAttraction> getTouristAttraction(@PathVariable String name) {
        return ResponseEntity.ok(touristService.getTouristAttraction(name));
    }

    @GetMapping("/{name}/tags")
    public String getTouristAttractionTags(@PathVariable String name, Model model) {
        model.addAttribute("touristAttractions", touristService.getTouristAttraction(name));
        return "tags";
    }

    @GetMapping("/add")
    public String addTouristAttraction(Model model) {
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getTags());
        return "addTouristAttraction";
    }

    @PostMapping("/save")
    public String saveTouristAttraction(@ModelAttribute TouristAttraction newTouristAttraction) {
        touristService.saveTouristAttraction(newTouristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editTouristAttraction(Model model, @PathVariable String name) {
        model.addAttribute("touristAttraction", touristService.getTouristAttraction(name));
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getTags());
        return "editTouristAttraction";
    }

    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction updateTouristAttraction) {
        touristService.updateTouristAttraction(updateTouristAttraction.getName(), updateTouristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/{name}/delete")
    public String deleteTouristAttraction(@PathVariable String name) {
        touristService.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }
}
