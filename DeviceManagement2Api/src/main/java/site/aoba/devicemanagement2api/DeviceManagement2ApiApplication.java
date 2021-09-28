package site.aoba.devicemanagement2api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DeviceManagement2ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagement2ApiApplication.class, args);
    }

}
