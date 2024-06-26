package com.example.TravelPlanner.travelplanning.entities;

import com.example.TravelPlanner.auth.entities.User;
import com.example.TravelPlanner.travelplanning.common.enums.PlaceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@Table(name = "votings")
public class Voting implements Serializable {

    public Voting(){
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now().plusDays(2);
        this.votes = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User creator;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToOne()
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @OneToMany(mappedBy = "voting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<Vote> votes = new ArrayList<>();

}
