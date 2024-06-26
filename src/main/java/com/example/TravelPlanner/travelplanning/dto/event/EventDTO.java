package com.example.TravelPlanner.travelplanning.dto.event;

import com.example.TravelPlanner.travelplanning.common.enums.PlaceStatus;
import com.example.TravelPlanner.travelplanning.dto.voting.VotingDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

// To show every even on the front page for every travel plan

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String creator;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;

    private String description;
    @NotNull
    private PlaceStatus placeStatus;
    @NotNull
    private String location;

    private Integer minCost;
    private Integer maxCost;
}
