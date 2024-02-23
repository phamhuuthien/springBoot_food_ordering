package com.IT.osahaneat;

import com.IT.osahaneat.entity.RatingRestaurant;
import com.IT.osahaneat.entity.Restaurant;
import com.IT.osahaneat.entity.Users;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class OsahaneatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsahaneatApplication.class, args);
	}

}
