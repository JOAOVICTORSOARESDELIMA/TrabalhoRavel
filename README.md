[![Java CI with Maven](https://github.com/JOAOVICTORSOARESDELIMA/TrabalhoRavel/actions/workflows/maven.yml/badge.svg)](https://github.com/JOAOVICTORSOARESDELIMA/TrabalhoRavel/actions/workflows/maven.yml)

# Conversor de Temperatura com testes automatizados e CI

Trabalho da disciplina de **Qualidade de Software** do curso de Sistemas para Internet (UNIESP, 2026.1).

O objetivo era praticar **testes unitários** e **integração contínua**: uma classe Java simples, testada com **JUnit 5** e verificada automaticamente pelo **GitHub Actions** a cada `push` ou pull request. O selo no topo mostra se a última versão passou nos testes.

## O que o conversor faz

| Método | Conversão |
|---|---|
| `celsiusParaFahrenheit` / `fahrenheitParaCelsius` | °C ↔ °F |
| `celsiusParaKelvin` / `kelvinParaCelsius` | °C ↔ K |
| `fahrenheitParaKelvin` / `kelvinParaFahrenheit` | °F ↔ K |

Todas as conversões **recusam temperaturas abaixo do zero absoluto** (-273,15 °C, -459,67 °F ou 0 K) e valores que não são números (`NaN`, infinito). Nesses casos, lançam `IllegalArgumentException`.

```java
ConversorTemperatura conversor = new ConversorTemperatura();
conversor.celsiusParaFahrenheit(100);  // 212.0
conversor.fahrenheitParaCelsius(98.6); // 37.0
conversor.celsiusParaKelvin(0);        // 273.15
conversor.kelvinParaCelsius(-1);       // IllegalArgumentException
```

## Testes

Os 12 testes em `ConversorTemperaturaTest` cobrem:

- pontos de referência conhecidos (congelamento e ebulição da água, temperatura do corpo, -40 °C = -40 °F);
- conversões com Kelvin;
- ida e volta: converter e desconverter devolve o valor original;
- casos de erro: abaixo do zero absoluto, exatamente no zero absoluto, `NaN` e infinito.

## Como executar

Requisitos: **Java 17** e **Maven**.

```bash
mvn test      # roda os testes
mvn package   # compila, testa e gera o .jar em target/
```

## Integração contínua

O workflow [`.github/workflows/maven.yml`](.github/workflows/maven.yml) roda `mvn package` no GitHub Actions a cada push e pull request na branch `main`. Se algum teste falhar, o build fica vermelho e o selo do README mostra a falha.

## Estrutura

```
src/
├── main/java/br/com/qualidade/ConversorTemperatura.java      # código
└── test/java/br/com/qualidade/ConversorTemperaturaTest.java  # testes JUnit 5
```

## Autores

- **João Victor Soares de Lima**: [GitHub](https://github.com/JOAOVICTORSOARESDELIMA)
- Chrystian
