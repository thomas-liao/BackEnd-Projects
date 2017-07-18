package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

// JpaRepository<mainClass, Long (ID))
public interface LocationRepository extends JpaRepository<Location, Long> {
    //springdata return a type which contains pagination
    //pageable: what is current page, which page are you requesting..and spring data will deal with it to
    // generate page and return page
    Page<Location> findByRunnerMovementType(@Param("movementType") Location.RunnerMovementType movementType,
                                            Pageable pageable);
}
