package com.example.airlinetickets.seeders;

import com.example.airlinetickets.models.entities.*;
import com.example.airlinetickets.models.enums.UserRoleEnum;
import com.example.airlinetickets.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRoleRepository userRoleRepository;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;

    private final AirplaneRepository airplaneRepository;
    private final FlightRepository flightRepository;

    private final String defaultAdminPass;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseSeeder(UserRoleRepository userRoleRepository,
                          UserRepository userRepository,
                          CityRepository cityRepository,
                          AirportRepository airportRepository,
                          AirplaneRepository airplaneRepository,
                          FlightRepository flightRepository,
                          @Value("${airline-tickets.admin.default-pass}") String defaultAdminPass, PasswordEncoder passwordEncoder) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
        this.airplaneRepository = airplaneRepository;
        this.flightRepository = flightRepository;
        this.defaultAdminPass = defaultAdminPass;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        List<UserRoleEntity> userRoleEntities = Arrays.stream(UserRoleEnum.values())
                .map(UserRoleEntity::new)
                .toList();

        if (this.userRoleRepository.count() == 0) {
            this.userRoleRepository.saveAll(userRoleEntities);
        }

        if (this.userRepository.count() == 0) {
            UserEntity userAdmin = new UserEntity()
                    .setUsername("admin")
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setPassword(passwordEncoder.encode(defaultAdminPass))
                    .setEmail("admin@test.com")
                    .setRoles(userRoleEntities);

            this.userRepository.save(userAdmin);
        }

        if (this.cityRepository.count() != 0) {
            return;
        }

        List<City> cities = new ArrayList<>();

        City sofiaCity = new City("Sofia", "Bulgaria");
        cities.add(sofiaCity);

        City newYorkCity = new City("New York", "USA");
        cities.add(newYorkCity);

        City viennaCity = new City("Vienna", "Austria");
        cities.add(viennaCity);

        this.cityRepository.saveAll(cities);

        if (this.airportRepository.count() != 0) {
            return;
        }

        List<AirportEntity> airportEntities = new ArrayList<>();

        AirportEntity sofiaAirport = new AirportEntity("Sofia Airport", "SOF", sofiaCity);
        AirportEntity jfkAirport = new AirportEntity("John F. Kennedy International Airport", "JFK", newYorkCity);
        AirportEntity viennaAirport = new AirportEntity("Vienna Airport", "VIE", viennaCity);

        airportEntities.add(sofiaAirport);
        airportEntities.add(jfkAirport);
        airportEntities.add(new AirportEntity("LaGuardia Airport", "LGA", newYorkCity));
        airportEntities.add(new AirportEntity("Newark International Airport", "EWR", newYorkCity));
        airportEntities.add(viennaAirport);

        this.airportRepository.saveAll(airportEntities);

        if (this.airplaneRepository.count() != 0) {
            return;
        }

        List<AirplaneEntity> airplaneEntities = new ArrayList<>();

        AirplaneEntity boeing747AirplaneEntity = new AirplaneEntity("Boeing 747-400",
                "https://www.lufthansa.com/content/dam/lh/images/local_images/discover-lufthansa/fleet/B747-400_1144x490.jpg",
                400,
                70.66,
                64.44,
                19.40,
                394,
                920,
                13700,
                12200,
                4
        );

        airplaneEntities.add(boeing747AirplaneEntity);

        this.airplaneRepository.saveAll(airplaneEntities);

        if (this.flightRepository.count() != 0) {
            return;
        }

        List<Flight> flights = new ArrayList<>();

        Flight flight1 = new Flight().setDepartureDateTime(LocalDateTime.now().plusDays(1))
                .setArrivalDateTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .setOriginAirport(sofiaAirport)
                .setDestinationAirport(viennaAirport)
                .setDistance(2000)
                .setPremiumTicketPrice(BigDecimal.valueOf(850.00))
                .setBusinessTicketPrice(BigDecimal.valueOf(500.00))
                .setEconomyTicketPrice(BigDecimal.valueOf(230.56))
                .setAirplane(boeing747AirplaneEntity)
                .setPremiumSeats(14)
                .setBusinessSeats(26)
                .setEconomySeats(352)
                .setTerminal("T2");

        flights.add(flight1);

        Flight flight2 = new Flight().setDepartureDateTime(LocalDateTime.now().plusDays(2))
                .setArrivalDateTime(LocalDateTime.now().plusDays(2).plusHours(6))
                .setOriginAirport(viennaAirport)
                .setDestinationAirport(jfkAirport)
                .setDistance(14564)
                .setPremiumTicketPrice(BigDecimal.valueOf(4858.28))
                .setBusinessTicketPrice(BigDecimal.valueOf(2502.54))
                .setEconomyTicketPrice(BigDecimal.valueOf(1434.83))
                .setAirplane(boeing747AirplaneEntity)
                .setPremiumSeats(16)
                .setBusinessSeats(23)
                .setEconomySeats(357)
                .setTerminal("T1");

        flights.add(flight2);

        this.flightRepository.saveAll(flights);
    }
}