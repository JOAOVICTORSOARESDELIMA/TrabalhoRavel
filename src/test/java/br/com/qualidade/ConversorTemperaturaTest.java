package br.com.qualidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConversorTemperaturaTest {

    private static final double DELTA = 0.001;
    private ConversorTemperatura conversor;

    @BeforeEach
    void setUp() {
        conversor = new ConversorTemperatura();
    }

    // ---- Celsius <-> Fahrenheit -------------------------------------------

    @Test
    @DisplayName("0 °C = 32 °F (congelamento da água)")
    void celsiusParaFahrenheitNoCongelamento() {
        assertEquals(32.0, conversor.celsiusParaFahrenheit(0), DELTA);
    }

    @Test
    @DisplayName("100 °C = 212 °F (ebulição da água)")
    void celsiusParaFahrenheitNaEbulicao() {
        assertEquals(212.0, conversor.celsiusParaFahrenheit(100), DELTA);
    }

    @Test
    @DisplayName("-40 °C = -40 °F (ponto em que as escalas se encontram)")
    void celsiusEFahrenheitSeEncontramEmMenos40() {
        assertEquals(-40.0, conversor.celsiusParaFahrenheit(-40), DELTA);
        assertEquals(-40.0, conversor.fahrenheitParaCelsius(-40), DELTA);
    }

    @Test
    @DisplayName("32 °F = 0 °C")
    void fahrenheitParaCelsiusNoCongelamento() {
        assertEquals(0.0, conversor.fahrenheitParaCelsius(32), DELTA);
    }

    @Test
    @DisplayName("98,6 °F = 37 °C (temperatura do corpo)")
    void fahrenheitParaCelsiusTemperaturaCorporal() {
        assertEquals(37.0, conversor.fahrenheitParaCelsius(98.6), DELTA);
    }

    // ---- Kelvin -------------------------------------------------------------

    @Test
    @DisplayName("0 °C = 273,15 K")
    void celsiusParaKelvin() {
        assertEquals(273.15, conversor.celsiusParaKelvin(0), DELTA);
    }

    @Test
    @DisplayName("0 K = -273,15 °C")
    void kelvinParaCelsius() {
        assertEquals(-273.15, conversor.kelvinParaCelsius(0), DELTA);
    }

    @Test
    @DisplayName("212 °F = 373,15 K e volta")
    void fahrenheitEKelvin() {
        assertEquals(373.15, conversor.fahrenheitParaKelvin(212), DELTA);
        assertEquals(212.0, conversor.kelvinParaFahrenheit(373.15), DELTA);
    }

    @Test
    @DisplayName("Converter e desconverter devolve o valor original")
    void idaEVolta() {
        double[] valores = {-273.15, -10.5, 0, 25, 36.6, 1000};
        for (double c : valores) {
            assertEquals(c, conversor.fahrenheitParaCelsius(conversor.celsiusParaFahrenheit(c)), DELTA);
            assertEquals(c, conversor.kelvinParaCelsius(conversor.celsiusParaKelvin(c)), DELTA);
        }
    }

    // ---- Valores inválidos --------------------------------------------------

    @Test
    @DisplayName("Rejeita temperaturas abaixo do zero absoluto")
    void rejeitaAbaixoDoZeroAbsoluto() {
        assertThrows(IllegalArgumentException.class, () -> conversor.celsiusParaFahrenheit(-300));
        assertThrows(IllegalArgumentException.class, () -> conversor.fahrenheitParaCelsius(-500));
        assertThrows(IllegalArgumentException.class, () -> conversor.kelvinParaCelsius(-1));
    }

    @Test
    @DisplayName("Aceita exatamente o zero absoluto")
    void aceitaZeroAbsoluto() {
        assertEquals(-459.67, conversor.celsiusParaFahrenheit(-273.15), DELTA);
        assertEquals(0.0, conversor.fahrenheitParaKelvin(-459.67), DELTA);
    }

    @Test
    @DisplayName("Rejeita NaN e infinito")
    void rejeitaValoresNaoNumericos() {
        assertThrows(IllegalArgumentException.class, () -> conversor.celsiusParaFahrenheit(Double.NaN));
        assertThrows(IllegalArgumentException.class,
                () -> conversor.kelvinParaCelsius(Double.POSITIVE_INFINITY));
    }
}
