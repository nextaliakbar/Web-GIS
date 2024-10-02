package com.web.apps.service;

import com.web.apps.entity.Marker;
import com.web.apps.model.MarkerModel;
import com.web.apps.repository.MarkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkerService {

    @Autowired
    private MarkerRepository markerRepository;

    @Transactional(readOnly = true)
    public List<MarkerModel> list() {
        List<Marker> markers = markerRepository.findAll();
        return markers.stream().map(this::toMarkerModel).collect(Collectors.toList());
    }

    @Transactional
    public MarkerModel create(MarkerModel markerModel) {
        Marker marker = new Marker();
        marker.setName(markerModel.getName());
        marker.setLatitude(markerModel.getLatitude());
        marker.setLongitude(markerModel.getLongitude());
        marker.setDescription(markerModel.getDescription());
        marker.setImage(markerModel.getImage());
        markerRepository.save(marker);
        return toMarkerModel(marker);
    }

    @Transactional
    public MarkerModel update(MarkerModel markerModel) {
        Marker marker = new Marker();
        marker.setId(markerModel.getId());
        marker.setName(markerModel.getName());
        marker.setLatitude(markerModel.getLatitude());
        marker.setLongitude(markerModel.getLongitude());
        marker.setDescription(markerModel.getDescription());
        marker.setImage(markerModel.getImage());
        markerRepository.save(marker);
        return toMarkerModel(marker);
    }

    @Transactional
    public void delete(Integer id) {
        Marker marker = markerRepository.findFirstById(id);
        markerRepository.delete(marker);
    }

    @Transactional
    public MarkerModel getById(Integer id) {
        Marker marker = markerRepository.findFirstById(id);
        return toMarkerModel(marker);
    }

    private MarkerModel toMarkerModel(Marker marker) {
        return MarkerModel.builder()
                .id(marker.getId()).name(marker.getName())
                .latitude(marker.getLatitude())
                .longitude(marker.getLongitude())
                .description(marker.getDescription())
                .image(marker.getImage()).build();
    }
}
