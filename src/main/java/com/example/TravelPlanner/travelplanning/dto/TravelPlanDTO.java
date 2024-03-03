package com.example.TravelPlanner.travelplanning.dto;

import com.example.TravelPlanner.travelplanning.entities.UserPlanRoles;
import com.example.TravelPlanner.travelplanning.entities.Voting;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPlanDTO implements Serializable {

    private Long id;

    private String ownerUsername;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<EventDTO> events;

    private String joinCode;
}
