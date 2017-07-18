// not quite sure how to write MySQL Repository... need to learn
package hw1.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by vagrant on 7/17/17.
 */
public interface RunningInformationRepository extends JpaRepository<RunningInformation, Long>{
   Page<RunningInformation> findByHealthWarningLevel(@Param("healthWarningLevel")RunningInformation.HealthWarningLevel healthWarningLevel, Pageable pageable);
}
