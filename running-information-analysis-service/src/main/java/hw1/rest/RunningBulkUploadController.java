package hw1.rest;

import hw1.domain.RunningInformation;
import hw1.service.InformationAnaylsisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningBulkUploadController {
    @Autowired
    private InformationAnaylsisService informationAnaylsisService;

    @RequestMapping(value = "/running", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformations) {
        informationAnaylsisService.saveRunningInformation(runningInformations);
    }

    @RequestMapping(value = "/running", method = RequestMethod.DELETE)
    public void purge() {
        informationAnaylsisService.deleteAll();
    }

    @RequestMapping(value = "/running", method = RequestMethod.GET)
    public Page<RunningInformation> findByHealthWarningLevel(@PathVariable String healthWarningLevel,
                                                             @RequestParam(name = "page") int page,
                                                             @RequestParam(name = "size") int size) {
        return this.informationAnaylsisService.findByHealthWarningLevel(healthWarningLevel, new PageRequest(page, size));
    }

}
