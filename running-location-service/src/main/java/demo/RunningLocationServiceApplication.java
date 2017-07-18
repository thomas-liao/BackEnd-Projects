package demo;


import demo.domain.UnitInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//ctr + shift + f, reformat
//ctr + shift + o, optimize import (delete useless imports)
//you have to create a class before you creating
// an nested package in demo... otherwise they(outer and innter nested packagess) will bindtogether
@SpringBootApplication
public class RunningLocationServiceApplication {
    public static void main(String[] args) {
        UnitInfo unitInfo = new UnitInfo();
        unitInfo.setBandMake("FitBit");
        SpringApplication.run(RunningLocationServiceApplication.class);
    }
}
