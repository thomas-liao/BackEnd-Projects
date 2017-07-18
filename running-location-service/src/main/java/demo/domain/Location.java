package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

// @Entity: means we need to store the class into a table

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
// @Table (name = "RUNNING_LOCATIONS")   yes you can do it, later, teacher's demo shows us how to use JsonInclude
// to handle data conversion (from front end to back end)
public class Location {
    enum GpsStatus {
        Excellent, OK, UNRELIABLE, BAD, NOFIXX, UNKNOWN;
    }
    public enum RunnerMovementType {
        STOPPED, IN_MOTION;

        public boolean isMoving() {
            return this != STOPPED;
        }
    }

    @Id // java generate and provide ID
    @GeneratedValue
    private Long id;

    // UnitInfo is a class containing different field.. to deal with this, we use @Embedded
    // then the Embedded will locate the fields in UnitInfo (which are runningId, bandmake, customerName, unitName
    @Embedded
    // supposed we want to map bandMake to a different column, which has label (unit_band_make), then we do AttributeOverride
    @AttributeOverride(name = "bandMake", column = @Column(name = "unit_band_make"))  // note: no ";" char
    private final UnitInfo unitInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi")),
            @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr"))
    })


    private MedicalInfo medicalInfo;

    private double latitude;
    private double longitude;

    private String heading;
    private double gpsSpeed;
    private GpsStatus gpsStatus;

    private double odometer;
    private double totalRunningTime;
    private double totalIdleTime;
    private double totalCaloriesBurnt;
    private String address;
    private Date timestamp = new Date();
    private String gearProvider;
    private RunnerMovementType runnerMovementType = RunnerMovementType.STOPPED;
    private String serviceType; //if encountered problem need serice

    public Location() {this.unitInfo = null;}  //default constructor

    @JsonCreator // get something from Json and use it to pass value to running ID, for this constructor
    public Location(@JsonProperty("runningId") String runningId) { //this sentence tells Json that if you see
        //a property in Json (JsonProperty) named by "runningID", then use that value for tihs constructor
        //pass runningId from location to UnitInfo
        this.unitInfo = new UnitInfo(runningId);
    }

    public Location(UnitInfo unitInfo) { //if you have UnitInfo type input, just use it directly, hooray!
        this.unitInfo = unitInfo;
    }

    // here although in the root class has @Data (lambok), if you define a getter setter method, then it will use the getter and setter you defined
    // instead of the getter/setter generated automatically;
    public String getRunningId() {return this.unitInfo == null ? null : this.unitInfo.getRunningId();}


}
