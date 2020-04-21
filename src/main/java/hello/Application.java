package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class Application {
    private static final Logger logger
            = LoggerFactory.getLogger(PersonController.class);

    public static void main(String[] args) {
        logger.info("!!!DEBUG: started application");
        SpringApplication.run(Application.class, args);
    }

}