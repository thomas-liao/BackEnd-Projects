package hw1.service.impl;

import hw1.domain.RunningInformation;
import hw1.domain.RunningInformationRepository;
import hw1.service.InformationAnaylsisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vagrant on 7/17/17.
 */
@Service
public class InformationAnalysisImpl implements InformationAnaylsisService {
    //Autowire to repository..
    /*  method 1, not recommended
    @Autowired
    RunningInformationRepository runningInformationRepository;
    */
    //method 2: user constructor, recommended
    private RunningInformationRepository runningInformationRepository;
    @Autowired
    public InformationAnalysisImpl(RunningInformationRepository runningInformationRepository) {
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformations) {
        return runningInformationRepository.save(runningInformations);
    }

    @Override
    public void deleteAll() {
        runningInformationRepository.deleteAll();
    }

    @Override
    public Page<RunningInformation> findByHealthWarningLevel(String healthWarningLevel, Pageable pageable) {
        return runningInformationRepository.findByHealthWarningLevel(RunningInformation.HealthWarningLevel.valueOf(healthWarningLevel), pageable);
    }
}
