package br.com.qualidade;

/**
 * Converte temperaturas entre as escalas Celsius, Fahrenheit e Kelvin.
 *
 * <p>Todas as conversões rejeitam valores abaixo do zero absoluto
 * (-273,15 °C / -459,67 °F / 0 K), que não existem fisicamente.</p>
 */
public class ConversorTemperatura {

    public static final double ZERO_ABSOLUTO_CELSIUS = -273.15;
    public static final double ZERO_ABSOLUTO_FAHRENHEIT = -459.67;
    public static final double ZERO_ABSOLUTO_KELVIN = 0.0;

    public double celsiusParaFahrenheit(double celsius) {
        validar(celsius, ZERO_ABSOLUTO_CELSIUS, "°C");
        return celsius * 9 / 5 + 32;
    }

    public double fahrenheitParaCelsius(double fahrenheit) {
        validar(fahrenheit, ZERO_ABSOLUTO_FAHRENHEIT, "°F");
        return (fahrenheit - 32) * 5 / 9;
    }

    public double celsiusParaKelvin(double celsius) {
        validar(celsius, ZERO_ABSOLUTO_CELSIUS, "°C");
        return celsius - ZERO_ABSOLUTO_CELSIUS;
    }

    public double kelvinParaCelsius(double kelvin) {
        validar(kelvin, ZERO_ABSOLUTO_KELVIN, "K");
        return kelvin + ZERO_ABSOLUTO_CELSIUS;
    }

    public double fahrenheitParaKelvin(double fahrenheit) {
        return celsiusParaKelvin(fahrenheitParaCelsius(fahrenheit));
    }

    public double kelvinParaFahrenheit(double kelvin) {
        return celsiusParaFahrenheit(kelvinParaCelsius(kelvin));
    }

    private static void validar(double valor, double zeroAbsoluto, String unidade) {
        if (Double.isNaN(valor) || Double.isInfinite(valor)) {
            throw new IllegalArgumentException("Temperatura inválida: " + valor);
        }
        // Pequena tolerância para erros de arredondamento de ponto flutuante.
        if (valor < zeroAbsoluto - 1e-9) {
            throw new IllegalArgumentException(
                    "Temperatura abaixo do zero absoluto: " + valor + " " + unidade);
        }
    }
}
