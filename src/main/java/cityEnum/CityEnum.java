package cityEnum;

import lombok.Getter;

@Getter
public enum CityEnum {
    TBILISI("Tbilisi"),
    NEW_YORK("New York"),
    LONDON("London"),
    PARIS("Paris"),
    TOKYO("Tokyo"),
    BEIJING("Beijing"),
    MOSCOW("Moscow"),
    ISTANBUL("Istanbul"),
    DUBAI("Dubai"),
    ROME("Rome"),
    SEOUL("Seoul"),
    MUMBAI("Mumbai"),
    SYDNEY("Sydney"),
    CAIRO("Cairo"),
    BUENOS_AIRES("Buenos Aires"),
    RIO_DE_JANEIRO("Rio de Janeiro"),
    MEXICO_CITY("Mexico City"),
    BERLIN("Berlin"),
    TORONTO("Toronto"),
    SAO_PAULO("São Paulo");

    // Getter method
    private final String cityName;

    // Constructor
    CityEnum(String cityName) {
        this.cityName = cityName;
    }

}

