package demo.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by vagrant on 7/14/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
@Data
// @RequiredArgsConstructor  // @required.... means once stated cannot change, and it is required!
// Ross decided not to set bfr and fmi as final so. Otherwise public MeidalInfo() {}
// constructor cannot pass IDE error check
public class MedicalInfo {

    private long bfr; // blood flow rate
    private long fmi; // first-aid medical index

    //missing a default constructor... missed by Ross lol
    public MedicalInfo() {

    }
    public MedicalInfo(long bfr, long fmi) {
        this.bfr = bfr;
        this.fmi = fmi;
    }
}
