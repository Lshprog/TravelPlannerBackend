package com.example.TravelPlanner.common.exceptions.custom;

public class TravelPlanNotFoundException extends EntityNotFoundException{
    public TravelPlanNotFoundException(Long id){
        super("Travel Plan not found for ID: " + id);
    }
}
