package hw1.service;

import hw1.domain.RunningInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by vagrant on 7/17/17.
 */
public interface InformationAnaylsisService {
    List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformations);

    void deleteAll();

    Page<RunningInformation> findByHealthWarningLevel (String healthWarningLevel, Pageable pageable);
}
