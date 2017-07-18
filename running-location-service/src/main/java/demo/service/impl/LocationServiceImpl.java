package demo.service.impl;

import demo.domain.Location;
import demo.domain.LocationRepository;
import demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

// best practise suggested by Ross (it might vary across companies)
// service package with 1) iii interface 2) xx package(implementation package) (named as iii + impl)
// this package contains class which implements iii

@Service
// in rest package, runningbulk..., if you do not label this class as service @Service, error : no qualifying
// bean of type [demo.service.LocationService] found for dependency;
public class LocationServiceImpl implements LocationService {
    /* Method#1, field injection (one of 3 ways of dependencies injection)
    @Autowired  //springframework..  this method is call field injection
    private LocationRepository locationRepository; //you can't and dont need to new..., @autowired and stated it then you can use it
    //already
    */

    // Method#2, Ross recommended, constructor injection! 1 generate an repository field, then constructor construct

    private LocationRepository locationRepository;
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Location> saveRunningLocations(List<Location> runningLocations) {
        //now, we need to use save location data in domain - LocationRepository (an Interface), what shoud we do - > 3 methods
        return locationRepository.save(runningLocations);
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByRunnerMovementType(String movementType, Pageable pageable) {
        //convert string - > enum  type(RunnerMovementType is an enum type)
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable);
    }
}
