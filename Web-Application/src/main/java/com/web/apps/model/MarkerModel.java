package com.web.apps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkerModel {

    private Integer id;

    private String latitude;

    private String longitude;

    private String name;

    private String description;

    private String image;
}
