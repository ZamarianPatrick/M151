package ch.patrick.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Klasse der Applikation
 *
 * @author Patrick
 * @version 1.0
 */
@SpringBootApplication
public class ShopApplication {

    /**
     * Startet die Applikation
     *
     * @param args Java Argumente
     */

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
