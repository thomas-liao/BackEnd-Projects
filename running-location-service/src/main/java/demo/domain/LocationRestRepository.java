package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "locations", collectionResourceRel = "locations")
// add this otherwise the name will automaticall
// be .... as xxxsRepository.... so that's not so right
public interface LocationRestRepository extends PagingAndSortingRepository<Location, Long> {
    @RestResource(path = "runningId", rel =  "by-runningId")
    Page<Location> findByUnitInfoRunningId(@Param("runningId") String runningId, Pageable pageable);
}
