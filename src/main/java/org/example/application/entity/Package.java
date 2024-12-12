package org.example.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.application.service.PackageService;

import java.util.ArrayList;
import java.util.List;

public class Package {

    @JsonProperty("Id")
    private int id;

    @JsonProperty("Cards")
    List<Card> cards = new ArrayList();

    public Package() {
        cards = new ArrayList();
    }
}
