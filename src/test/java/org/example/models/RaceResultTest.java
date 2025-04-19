package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RaceResultTest {
    private RaceResult raceResult;
    private Driver driver;
    private Team team;

    // Este método se ejecutará antes de cada test
    @BeforeEach
    void setUp() {
        // Inicializamos los objetos necesarios antes de cada test
        driver = new Driver("Max Verstappen", "Netherlands", 33);
        team = new Team("Red Bull Racing", "Milton Keynes"); // Creamos el equipo
        raceResult = new RaceResult(driver, team, 1, true, false); // Inicializamos RaceResult
    }

    @Test
    void testDefaultConstructor() {
        RaceResult defaultResult = new RaceResult();
        assertNull(defaultResult.getDriver());
        assertNull(defaultResult.getTeam());
        assertEquals(0, defaultResult.getPosition());
        assertFalse(defaultResult.hasFastestLap());
        assertFalse(defaultResult.isDnf());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(driver, raceResult.getDriver());
        assertEquals(team, raceResult.getTeam());
        assertEquals(1, raceResult.getPosition());
        assertTrue(raceResult.hasFastestLap());
        assertFalse(raceResult.isDnf());
    }

    @Test
    void testSettersAndGetters() {
        Driver newDriver = new Driver("Lewis Hamilton", "United Kingdom", 44);
        Team newTeam = new Team("Mercedes", "Brackley");

        raceResult.setDriver(newDriver);
        raceResult.setTeam(newTeam);
        raceResult.setPosition(2);
        raceResult.setFastestLap(false);
        raceResult.setDnf(true);

        assertEquals(newDriver, raceResult.getDriver());
        assertEquals(newTeam, raceResult.getTeam());
        assertEquals(2, raceResult.getPosition());
        assertFalse(raceResult.hasFastestLap());
        assertTrue(raceResult.isDnf());
    }

    @Test
    void testPointsCalculation() {
        // Test con vuelta rápida (fastestLap = true)
        raceResult.setFastestLap(true);

        // 1er lugar + vuelta rápida (25 + 1)
        raceResult.setPosition(1);
        assertEquals(26, raceResult.getPoints(), "Debería dar 26 puntos para P1 con vuelta rápida");

        // 2do lugar + vuelta rápida (18 + 1)
        raceResult.setPosition(2);
        assertEquals(19, raceResult.getPoints(), "Debería dar 19 puntos para P2 con vuelta rápida");

        // 3er lugar + vuelta rápida (15 + 1)
        raceResult.setPosition(3);
        assertEquals(16, raceResult.getPoints(), "Debería dar 16 puntos para P3 con vuelta rápida");

        // 4to lugar + vuelta rápida (12 + 1)
        raceResult.setPosition(4);
        assertEquals(13, raceResult.getPoints(), "Debería dar 13 puntos para P4 con vuelta rápida");

        // 5to lugar + vuelta rápida (10 + 1)
        raceResult.setPosition(5);
        assertEquals(11, raceResult.getPoints(), "Debería dar 11 puntos para P5 con vuelta rápida");

        // 6to lugar + vuelta rápida (8 + 1)
        raceResult.setPosition(6);
        assertEquals(9, raceResult.getPoints(), "Debería dar 9 puntos para P6 con vuelta rápida");

        // 7mo lugar + vuelta rápida (6 + 1)
        raceResult.setPosition(7);
        assertEquals(7, raceResult.getPoints(), "Debería dar 7 puntos para P7 con vuelta rápida");

        // 8vo lugar + vuelta rápida (4 + 1)
        raceResult.setPosition(8);
        assertEquals(5, raceResult.getPoints(), "Debería dar 5 puntos para P8 con vuelta rápida");

        // 9no lugar + vuelta rápida (2 + 1)
        raceResult.setPosition(9);
        assertEquals(3, raceResult.getPoints(), "Debería dar 3 puntos para P9 con vuelta rápida");

        // 10mo lugar + vuelta rápida (1 + 1)
        raceResult.setPosition(10);
        assertEquals(2, raceResult.getPoints(), "Debería dar 2 puntos para P10 con vuelta rápida");

        // Test sin vuelta rápida (fastestLap = false)
        raceResult.setFastestLap(false);

        // 1er lugar sin vuelta rápida (25)
        raceResult.setPosition(1);
        assertEquals(25, raceResult.getPoints(), "Debería dar 25 puntos para P1 sin vuelta rápida");

        // 2do lugar sin vuelta rápida (18)
        raceResult.setPosition(2);
        assertEquals(18, raceResult.getPoints(), "Debería dar 18 puntos para P2 sin vuelta rápida");

        // 3er lugar sin vuelta rápida (15)
        raceResult.setPosition(3);
        assertEquals(15, raceResult.getPoints(), "Debería dar 15 puntos para P3 sin vuelta rápida");

        // 4to lugar sin vuelta rápida (12)
        raceResult.setPosition(4);
        assertEquals(12, raceResult.getPoints(), "Debería dar 12 puntos para P4 sin vuelta rápida");

        // 5to lugar sin vuelta rápida (10)
        raceResult.setPosition(5);
        assertEquals(10, raceResult.getPoints(), "Debería dar 10 puntos para P5 sin vuelta rápida");

        // 6to lugar sin vuelta rápida (8)
        raceResult.setPosition(6);
        assertEquals(8, raceResult.getPoints(), "Debería dar 8 puntos para P6 sin vuelta rápida");

        // 7mo lugar sin vuelta rápida (6)
        raceResult.setPosition(7);
        assertEquals(6, raceResult.getPoints(), "Debería dar 6 puntos para P7 sin vuelta rápida");

        // 8vo lugar sin vuelta rápida (4)
        raceResult.setPosition(8);
        assertEquals(4, raceResult.getPoints(), "Debería dar 4 puntos para P8 sin vuelta rápida");

        // 9no lugar sin vuelta rápida (2)
        raceResult.setPosition(9);
        assertEquals(2, raceResult.getPoints(), "Debería dar 2 puntos para P9 sin vuelta rápida");

        // 10mo lugar sin vuelta rápida (1)
        raceResult.setPosition(10);
        assertEquals(1, raceResult.getPoints(), "Debería dar 1 punto para P10 sin vuelta rápida");

        // Test DNF (Did Not Finish) - No importa la posición, los puntos deben ser 0
        raceResult.setDnf(true);
        raceResult.setPosition(5);  // Puede ser cualquier posición, pero debería dar 0
        assertEquals(0, raceResult.getPoints(), "Debería dar 0 puntos cuando el piloto no termina la carrera (DNF)");

        // Test posición fuera de los puntos (posición 11 o mayor)
        raceResult.setDnf(false); // Asegurarse de que DNF es false
        raceResult.setPosition(11); // Posición fuera de los primeros 10
        assertEquals(0, raceResult.getPoints(), "Debería dar 0 puntos para posición fuera de los puntos (P11 o más)");

        // Test con más de 10 (p.ej. posición 15)
        raceResult.setPosition(15);
        assertEquals(0, raceResult.getPoints(), "Debería dar 0 puntos para posiciones fuera de los puntos");
    }


    @Test
    void testToString() {
        assertEquals("1. Max Verstappen (Red Bull Racing) [FL]", raceResult.toString());

        raceResult.setDnf(true);
        assertEquals("1. Max Verstappen (Red Bull Racing) [FL] [DNF]", raceResult.toString());

        raceResult.setFastestLap(false);
        assertEquals("1. Max Verstappen (Red Bull Racing) [DNF]", raceResult.toString());
    }
}
