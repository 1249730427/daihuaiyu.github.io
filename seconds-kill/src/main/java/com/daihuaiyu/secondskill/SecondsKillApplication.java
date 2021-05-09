package com.daihuaiyu.secondskill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(value = "com.daihuaiyu.secondskill.config")
public class SecondsKillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondsKillApplication.class, args);
    }

}
