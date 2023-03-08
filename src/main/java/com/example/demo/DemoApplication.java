package com.example.demo;

import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
//@PWA(
//		name = "NBA Tracker",
//		shortName = "NBA",
//		offlinePath = "offline.html",
//		offlineResources = {"images/offline.png"}
//)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
