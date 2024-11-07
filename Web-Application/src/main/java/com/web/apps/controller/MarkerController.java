package com.web.apps.controller;

import com.web.apps.model.MarkerModel;
import com.web.apps.service.MarkerService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

@Controller
@RequestMapping(path = "/marker")
public class MarkerController {

    @Autowired
    private MarkerService markerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("markers", markerService.list());
        markerService.list().forEach(System.out::println);
        model.addAttribute("title", "Marker");
        return "marker/index";
    }

    @GetMapping(path = "/create")
    public String showCreate(Model model) {
        model.addAttribute("marker", new MarkerModel());
        model.addAttribute("title", "Tambah Marker");
        return "marker/create";
    }

    @GetMapping(path = "/edit")
    public String showEdit(Model model, @RequestParam("id") Integer id) {
        MarkerModel markerModel = markerService.getById(id);
        model.addAttribute("marker", markerModel);
        model.addAttribute("title", "Perbarui Marker");
        return "marker/edit";
    }

    @SneakyThrows
    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String create(@ModelAttribute("marker") MarkerModel markerModel,
    @RequestPart("file")MultipartFile file) {

        String filename = file.getOriginalFilename();
        Path path = Path.of("src/main/resources/static/assets/img/" + filename);

        MarkerModel marker = new MarkerModel();
        marker.setName(markerModel.getName());
        marker.setLatitude(markerModel.getLatitude());
        marker.setLongitude(markerModel.getLongitude());
        marker.setDescription(markerModel.getDescription());
        marker.setImage(filename);
        file.transferTo(path);

        markerService.create(marker);

        return "redirect:/marker";
    }

    @SneakyThrows
    @PostMapping(path = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String edit(@ModelAttribute("marker") MarkerModel markerModel,
    @RequestParam("id") Integer id , @RequestPart("file") MultipartFile file) {
        MarkerModel marker = new MarkerModel();
        marker.setId(id);
        marker.setName(markerModel.getName());
        marker.setLatitude(markerModel.getLatitude());
        marker.setLongitude(markerModel.getLongitude());
        marker.setDescription(markerModel.getDescription());

        String filename = file.getOriginalFilename();
        Path path = Path.of("src/main/resources/static/assets/img/" + filename);

        marker.setImage(filename);
        markerService.update(marker);
        file.transferTo(path);

        return "redirect:/marker";
    }

    @GetMapping(path = "/delete")
    public String delete(@RequestParam("id") Integer id) {
        markerService.delete(id);
        return "redirect:/marker";
    }
}
