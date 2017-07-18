package hw1.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by vagrant on 7/17/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
@Data
class UserInfo {
    // we still need need Long id, but we just ignore it in Json..

    // this userInfo is created from Json input, and in Json input, in userInfo field we have:
    // username and address (not userName)... note the difference.


    private String username;
    private String address;


    public UserInfo() {
        this.username = null; // actually you do not need to write it
        this.address = null; // same, you do not need to write it
    }
    @JsonCreator
    public UserInfo(@JsonProperty("userName") String userName, @JsonProperty("address") String address) {
        this.username = userName;
        this.address = address;
    }
}
