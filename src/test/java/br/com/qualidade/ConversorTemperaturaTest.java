package br.com.qualidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ConversorTemperaturaTest {

    @Test
    public void testCelsiusParaFahrenheit() {
        ConversorTemperatura conversor = new ConversorTemperatura();
        assertEquals(32.0, conversor.celsiusParaFahrenheit(0), 0.001); 
    }

    @Test
    public void testFahrenheitParaCelsius() {
        ConversorTemperatura conversor = new ConversorTemperatura();
        assertEquals(0.0, conversor.fahrenheitParaCelsius(32), 0.001);
    }
}