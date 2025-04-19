package org.example.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircuitTest {

    @Test
    void testConstructorConParametros() {
        Circuit circuit = new Circuit("Monza", "Italia", 5.8);

        assertEquals("Monza", circuit.getName());
        assertEquals("Italia", circuit.getCountry());
        assertEquals(5.8, circuit.getLength(), 0.001);
    }

    @Test
    void testConstructorPorDefectoYSetters() {
        Circuit circuit = new Circuit();

        circuit.setName("Silverstone");
        circuit.setCountry("Reino Unido");
        circuit.setCity("Northamptonshire");
        circuit.setLength(5.9);
        circuit.setLaps(52);
        circuit.setTotalDistance(306.198);
        circuit.setTrackType("Permanente");
        circuit.setCorners(18);
        circuit.setRaces(1);

        assertEquals("Silverstone", circuit.getName());
        assertEquals("Reino Unido", circuit.getCountry());
        assertEquals("Northamptonshire", circuit.getCity());
        assertEquals(5.9, circuit.getLength(), 0.001);
        assertEquals(52, circuit.getLaps());
        assertEquals(306.198, circuit.getTotalDistance(), 0.001);
        assertEquals("Permanente", circuit.getTrackType());
        assertEquals(18, circuit.getCorners());
        assertEquals(1, circuit.getRaces());
    }

    @Test
    void testToString() {
        Circuit circuit = new Circuit("Spa-Francorchamps", "Bélgica", 7.0);
        assertEquals("Spa-Francorchamps (Bélgica)", circuit.toString());
    }
}
