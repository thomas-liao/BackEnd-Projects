package demo.service;

import demo.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface LocationService {
    //store running locations... name is pretty self explanatory
    List<Location> saveRunningLocations(List<Location> runningLocations);

    void deleteAll();


    // why we use string as movementType? instead of type (RunnerMovementType in domain.Location)? because this service level
    // because the parse variable, to service, is actually passing a string instead of a class, to our service..
    // so for upperlevel infrastructure, we filler out the specific details about movementType type, instead we use concrete String

    Page<Location> findByRunnerMovementType(String movementType, Pageable pageable);



}
