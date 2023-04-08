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
    public void run(String... args) {

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

        if (this.cityRepository.count() != 0
                || this.airportRepository.count() != 0
                || this.airplaneRepository.count() != 0
                || this.flightRepository.count() != 0) {
            return;
        }

        List<CityEntity> cities = new ArrayList<>();

        CityEntity sofiaCity = new CityEntity().setName("Sofia").setCountry("Bulgaria");
        cities.add(sofiaCity);

        CityEntity newYorkCity = new CityEntity().setName("New York").setCountry("USA");
        cities.add(newYorkCity);

        CityEntity viennaCity = new CityEntity().setName("Vienna").setCountry("Austria");
        cities.add(viennaCity);

        CityEntity istanbulCity = new CityEntity().setName("Istanbul").setCountry("Turkey");
        cities.add(istanbulCity);

        this.cityRepository.saveAll(cities);

        List<AirportEntity> airportEntities = new ArrayList<>();

        AirportEntity sofiaAirport = new AirportEntity()
                .setName("Sofia Airport")
                .setAbbreviation("SOF")
                .setImageUrl("https://www.airport-technology.com/wp-content/uploads/sites/14/2019/07/Sofia-Airport.jpg")
                .setCity(sofiaCity)
                .setDescription("Sofia Airport (IATA: SOF, ICAO: LBSF) (Bulgarian: Летище София, romanized: Letishte Sofiya) is the main international airport of Bulgaria, located 10 km (6.2 mi) east of the centre of the capital Sofia.[3] In 2019 the airport surpassed 7 milli...");

        AirportEntity jfkAirport = new AirportEntity()
                .setName("John F. Kennedy International Airport")
                .setAbbreviation("JFK")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/JFK_Aerial_Nov_14_2018.jpg/1280px-JFK_Aerial_Nov_14_2018.jpg")
                .setCity(newYorkCity)
                .setDescription("""
                        John F. Kennedy International Airport (IATA: JFK, ICAO: KJFK, FAA LID: JFK) (colloquially referred to as JFK Airport, Kennedy Airport, New York-JFK, or simply JFK) is the main international airport serving New York City. The airport is the busiest of the seven airports in the New York airport system, the 13th-busiest airport in the United States, and the busiest international air passenger gateway into North America.[5] Over 90 airlines operate from the airport, with nonstop or direct flights to destinations in all six inhabited continents.[6][7]

                        JFK is located in the Jamaica neighborhood of Queens,[8] 16 miles (26 km) southeast of Midtown Manhattan. The airport features six passenger terminals and four runways. It is primarily accessible via car, bus, shuttle, or other vehicle transit via the JFK Expressway or Interstate 678 (Van Wyck Expressway), or via train. JFK is a hub for both American Airlines and Delta Air Lines, as well as the primary operating base for JetBlue.[9] JFK is also a former hub for Braniff, Eastern, National, Northeast, Pan Am, Tower Air, and TWA.

                        The facility opened in 1948 as New York International Airport[10][11] and was commonly known as Idlewild Airport.[12] Following the assassination of John F. Kennedy in 1963, the airport was renamed John F. Kennedy International Airport as a tribute to the 35th President of the United States.[13][14][15]""");

        AirportEntity laGuardiaAirport = new AirportEntity()
                .setName("LaGuardia Airport")
                .setAbbreviation("LGA")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Laguardia_American_terminal_2021_Overhead.png/1280px-Laguardia_American_terminal_2021_Overhead.png")
                .setCity(newYorkCity)
                .setDescription("""
                        LaGuardia Airport (IATA: LGA, ICAO: KLGA, FAA LID: LGA) /ləˈɡwɑːrdiə/ is a civil airport in East Elmhurst, Queens, New York City. Covering 680 acres (280 ha) as of January 1, 2023,[2] the facility was established in 1929 and began operating as a public airport in 1939. It is named after former New York City mayor Fiorello La Guardia.

                        The airport primarily accommodates airline service to domestic (and limited international) destinations. As of 2019, it was the third-busiest airport in the New York metropolitan area, behind Kennedy and Newark airports, and the twenty-first busiest in the United States by passenger volume.[3] The airport is located directly to the north of the Grand Central Parkway, the airport's primary access highway. While the airport is a hub for both American Airlines and Delta Air Lines, commercial service is strictly governed by unique regulations including a curfew, a slot system, and a "perimeter rule" prohibiting most non-stop flights to or from destinations greater than 1,500 mi (2,400 km).[4]

                        Throughout the 2000s and 2010s, LaGuardia was notable for having obsolete and dirty facilities, inefficient air operations, and poor customer service metrics.[5][6] Responding to these criticisms, the Port Authority of New York and New Jersey (PANYNJ) in 2015 announced a multibillion-dollar reconstruction of the airport's passenger infrastructure, which is expected to be complete by 2025.[7]""");

        AirportEntity newarkAirport = new AirportEntity()
                .setName("Newark International Airport")
                .setAbbreviation("EWR")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Vienna_airport_terminal_3.jpg/1280px-Vienna_airport_terminal_3.jpg")
                .setCity(newYorkCity)
                .setDescription("Vienna International Airport (German: Flughafen Wien-Schwechat; IATA: VIE, ICAO: LOWW) is the international airport of Vienna, the capital of Austria, located in Schwechat, 18 km (11 mi) southeast of central Vienna and 57 kilometres (35 mi) west of Bratislava, the capital of Slovakia. It is the country's largest airport and serves as the hub for Austrian Airlines as well as a base for low-cost carriers Wizz Air and Ryanair. It is capable of handling wide-body aircraft up to the Airbus A380. The airport features a dense network of European destinations as well as long-haul flights to Asia, North America and Africa.");


        AirportEntity viennaAirport = new AirportEntity()
                .setName("Vienna Airport")
                .setAbbreviation("VIE")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Vienna_airport_terminal_3.jpg/1280px-Vienna_airport_terminal_3.jpg")
                .setCity(viennaCity)
                .setDescription("Vienna International Airport (German: Flughafen Wien-Schwechat; IATA: VIE, ICAO: LOWW) is the international airport of Vienna, the capital of Austria, located in Schwechat, 18 km (11 mi) southeast of central Vienna and 57 kilometres (35 mi) west of Bratislava, the capital of Slovakia. It is the country's largest airport and serves as the hub for Austrian Airlines as well as a base for low-cost carriers Wizz Air and Ryanair. It is capable of handling wide-body aircraft up to the Airbus A380. The airport features a dense network of European destinations as well as long-haul flights to Asia, North America and Africa.");

        AirportEntity istanbulAirport = new AirportEntity()
                .setName("Istanbul Atatürk Airport")
                .setAbbreviation("ISL")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/3/3f/Ataturk_Airport_Karakas-1.jpg")
                .setCity(istanbulCity)
                .setDescription("Atatürk Airport (IATA: ISL, ICAO: LTBA) (former IATA code: IST) (Turkish: Atatürk Havalimanı) was a general aviation airport in Istanbul. It formerly served as the main intercontinental passenger airport and cargo hub for Istanbul until it was closed to commercial passenger flights on 6 April 2019. From that point, all flights were transferred to the new Istanbul Airport.[4][5] All freight operations subsequently relocated as well by 5 February 2022.[6]");

        airportEntities.add(sofiaAirport);
        airportEntities.add(jfkAirport);
        airportEntities.add(laGuardiaAirport);
        airportEntities.add(newarkAirport);
        airportEntities.add(viennaAirport);
        airportEntities.add(istanbulAirport);

        this.airportRepository.saveAll(airportEntities);

        List<AirplaneEntity> airplaneEntities = new ArrayList<>();

        AirplaneEntity boeing747AirplaneEntity = new AirplaneEntity()
                .setModel("Boeing 747-400")
                .setImageUrl("https://www.lufthansa.com/content/dam/lh/images/local_images/discover-lufthansa/fleet/B747-400_1144x490.jpg")
                .setMaximumSeats(400)
                .setLength(70.66)
                .setWingspan(64.44)
                .setHeight(19.40)
                .setMaximumWeight(394)
                .setMaximumCruisingSpeed(920)
                .setMaximumCruisingAltitude(13700)
                .setRange(12200)
                .setEngines(4);

        AirplaneEntity Boeing7478AirplaneEntity = new AirplaneEntity()
                .setModel("Boeing 747-8")
                .setImageUrl("https://www.lufthansa.com/content/dam/lh/images/local_images/discover-lufthansa/fleet/B747-8.jpg.transform/lh-dcep-transform-width-1440/img.jpg")
                .setMaximumSeats(364)
                .setLength(76.30)
                .setWingspan(68.40)
                .setHeight(19.40)
                .setMaximumWeight(442)
                .setMaximumCruisingSpeed(920)
                .setMaximumCruisingAltitude(13100)
                .setRange(13100)
                .setEngines(4);

        AirplaneEntity AirbusA330300AirplaneEntity = new AirplaneEntity()
                .setModel("Airbus A330-300")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Turkish_Airlines%2C_Airbus_A330-300_TC-JNL_NRT_%2823708073592%29.jpg/1024px-Turkish_Airlines%2C_Airbus_A330-300_TC-JNL_NRT_%2823708073592%29.jpg")
                .setMaximumSeats(255)
                .setLength(63.66)
                .setWingspan(60.30)
                .setHeight(16.83)
                .setMaximumWeight(233)
                .setMaximumCruisingSpeed(870)
                .setMaximumCruisingAltitude(12500)
                .setRange(10000)
                .setEngines(2);

        AirplaneEntity AirbusA380800AirplaneEntity = new AirplaneEntity()
                .setModel("Airbus A330-300")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/A6-EDY_A380_Emirates_31_jan_2013_jfk_%288442269364%29_%28cropped%29.jpg/1024px-A6-EDY_A380_Emirates_31_jan_2013_jfk_%288442269364%29_%28cropped%29.jpg")
                .setMaximumSeats(509)
                .setLength(72.73)
                .setWingspan(79.80)
                .setHeight(24.10)
                .setMaximumWeight(560)
                .setMaximumCruisingSpeed(907)
                .setMaximumCruisingAltitude(13100)
                .setRange(12400)
                .setEngines(4);

        AirplaneEntity AirbusA320200AirplaneEntity = new AirplaneEntity()
                .setModel("Airbus A320-200")
                .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Jetstar_Airbus_A320_in_flight_%286768081241%29_crop.jpg/1024px-Jetstar_Airbus_A320_in_flight_%286768081241%29_crop.jpg")
                .setMaximumSeats(168)
                .setLength(37.57)
                .setWingspan(34.10)
                .setHeight(12.0)
                .setMaximumWeight(73)
                .setMaximumCruisingSpeed(840)
                .setMaximumCruisingAltitude(11900)
                .setRange(3200)
                .setEngines(2);

        airplaneEntities.add(boeing747AirplaneEntity);
        airplaneEntities.add(Boeing7478AirplaneEntity);
        airplaneEntities.add(AirbusA330300AirplaneEntity);
        airplaneEntities.add(AirbusA380800AirplaneEntity);
        airplaneEntities.add(AirbusA320200AirplaneEntity);

        this.airplaneRepository.saveAll(airplaneEntities);

        List<FlightEntity> flights = new ArrayList<>();

        FlightEntity flight1 = new FlightEntity()
                .setFlightNumber("LZ934")
                .setDepartureDateTime(LocalDateTime.now().plusDays(1))
                .setArrivalDateTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .setOriginAirportEntity(sofiaAirport)
                .setDestinationAirportEntity(viennaAirport)
                .setAirplaneEntity(boeing747AirplaneEntity)
                .setDistance(2000)
                .setPremiumTicketPrice(BigDecimal.valueOf(850.00))
                .setBusinessTicketPrice(BigDecimal.valueOf(500.00))
                .setEconomyTicketPrice(BigDecimal.valueOf(230.56))
                .setPremiumSeats(14)
                .setBusinessSeats(26)
                .setEconomySeats(352)
                .setTerminal("T2");

        flights.add(flight1);

        FlightEntity flight2 = new FlightEntity().setDepartureDateTime(LocalDateTime.now().plusDays(2))
                .setFlightNumber("LZ003")
                .setArrivalDateTime(LocalDateTime.now().plusDays(2).plusHours(6))
                .setOriginAirportEntity(viennaAirport)
                .setDestinationAirportEntity(jfkAirport)
                .setAirplaneEntity(AirbusA380800AirplaneEntity)
                .setDistance(10564)
                .setPremiumTicketPrice(BigDecimal.valueOf(4858.28))
                .setBusinessTicketPrice(BigDecimal.valueOf(2502.54))
                .setEconomyTicketPrice(BigDecimal.valueOf(1434.83))
                .setPremiumSeats(16)
                .setBusinessSeats(23)
                .setEconomySeats(357)
                .setTerminal("T1");

        flights.add(flight2);

        this.flightRepository.saveAll(flights);
    }
}