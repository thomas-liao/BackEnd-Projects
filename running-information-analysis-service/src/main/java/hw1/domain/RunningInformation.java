package hw1.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "private")
public class RunningInformation {

    @Id
    @GeneratedValue
    private Long Id;

    private String runningId;
    private double latitude;
    private double longitude;
    private double totalRunningTime;
    private double runningDistance;
    private int heartRate;
    public enum HealthWarningLevel {
        LOW, NORMAL, HIGH;
    }

    // private Date timestamp = new Date(); // doesn't seem like a good practise. should declare in one constructor perhaps

    private Date timestamp;

    // enum + field
    private HealthWarningLevel healthWarningLevel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "user_Name")),
            @AttributeOverride(name = "address", column = @Column(name = "user_Address"))
    })
    private final UserInfo userInfo;

    public RunningInformation() {
        this.userInfo = null;
    }

    public RunningInformation(final String username, final String address) {
        this.userInfo = new UserInfo(username, address);
    }

    // this Json unit format is constructed for output, so set everything as string is best practise, except for heratRate
    @JsonCreator
   public RunningInformation(
           @JsonProperty("runningId") String runningId,
           @JsonProperty("totalRunningTime") String totalRunningTime,
           @JsonProperty("heartRate") String heartRate,
           @JsonProperty("timestamp") String timestamp,
           @JsonProperty("latitude") String latitude,
           @JsonProperty("longitude") String longitude,
           @JsonProperty("runningDistance") String runningDIstance,
           @JsonProperty("userInfo") UserInfo userInfo) {
        this.runningId = runningId;
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.timestamp = new Date();
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDIstance);
        this.userInfo = userInfo;

        //deal with heart rate
        this.heartRate = getRandomHeartRate(60, 200);

        // now decide which value is it for healthWarningLevel

        if (this.heartRate > 120) {
            this.healthWarningLevel = HealthWarningLevel.HIGH;
        } else if (this.heartRate > 75) {
            this.healthWarningLevel = HealthWarningLevel.NORMAL;
        } else if (this.heartRate >= 60) {
            this.healthWarningLevel = HealthWarningLevel.LOW;
        } else {
            // additional options.. like danger, exception, print warning
        }
    }

    // simle constructor for RunningInformation
    public RunningInformation(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
    // user name and user address: override lombok @Data annotation
    public String getUserName() {
        return this.userInfo == null ? null : userInfo.getUsername(); // corresponding to UserInfo field username
    }
    // similarly for address
    public String getUserAddress() {
        return this.userInfo == null ? null : userInfo.getAddress();
    }

    private int getRandomHeartRate(int min, int max) {
        Random rn = new Random();
        int addition = rn.nextInt(max - min + 1); // Random rn = new Ranodm(); int xx = rn.nextInt(10), generate value from 0 ~9
        return min + addition;
    }
}
