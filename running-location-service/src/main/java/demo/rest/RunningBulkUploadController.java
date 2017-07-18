package demo.rest;
// this package is used for writting rest controller

import demo.domain.Location;
import demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//rest controller: return rest response
//controller: not return : not necessarily a rest response

@RestController
public class RunningBulkUploadController {
    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)  //this means creation is successful : POST: create a new thing, put: update
    public void upload(@RequestBody List<Location> locations) { //@RequestBody, come from front end, we store it, that's it
        locationService.saveRunningLocations(locations); //use defined saveRunningLocations method to save location(from
        // front end)(to LocationRepository)
    }

    @RequestMapping(value = "/running", method = RequestMethod.DELETE)
    public void purge() {
        locationService.deleteAll();
    }

    @RequestMapping(value = "/running/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType,
                                             // @RequestParam(name = "page") int page,
                                             // @RequestParam(name = "size") int size) {
                                             @RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "size", required = false) Integer size) {
        // new PageRequest(page, size) :
        // writing like this, you have to pass a page parameter otherwise will cause 400 error (whitelabel page....)
        // however, it doesn't quite make sense that you have to give a page parameter whenever you request to get the information
        // e.g. a single person. so we need to change the above page input a little bit (default: required = true, we set the required as false)
        return this.locationService.findByRunnerMovementType(movementType, new PageRequest(page, size));

    }

}


