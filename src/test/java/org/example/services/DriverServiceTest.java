package org.example.services;

import org.example.models.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DriverServiceTest {
    private DriverService driverService;
    private List<Driver> testDrivers;
    
    @BeforeEach
    void setUp() {
        testDrivers = new ArrayList<>();
        
        Driver driver1 = new Driver();
        driver1.setName("Lewis Hamilton");
        driver1.setTeamName("Mercedes");
        driver1.setPoints(223);
        
        Driver driver2 = new Driver();
        driver2.setName("Max Verstappen");
        driver2.setTeamName("Red Bull");
        driver2.setPoints(437);
        
        testDrivers.add(driver1);
        testDrivers.add(driver2);
        
        driverService = new DriverService(testDrivers);
    }
    
    @Test
    void testGetAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        assertEquals(2, drivers.size());
        assertTrue(drivers.contains(testDrivers.get(0)));
        assertTrue(drivers.contains(testDrivers.get(1)));
    }
    
    @Test
    void testGetDriverByName() {
        Optional<Driver> driver = driverService.getDriverByName("Lewis Hamilton");
        assertTrue(driver.isPresent());
        assertEquals("Lewis Hamilton", driver.get().getName());
        assertEquals("Mercedes", driver.get().getTeamName());
    }
    
    @Test
    void testGetDriverByNameNotFound() {
        Optional<Driver> driver = driverService.getDriverByName("Fernando Alonso");
        assertFalse(driver.isPresent());
    }
    
    @Test
    void testGetDriversSortedByPoints() {
        List<Driver> sortedDrivers = driverService.getDriversSortedByPoints();
        assertEquals(2, sortedDrivers.size());
        assertEquals("Max Verstappen", sortedDrivers.get(0).getName());
        assertEquals("Lewis Hamilton", sortedDrivers.get(1).getName());
    }
}