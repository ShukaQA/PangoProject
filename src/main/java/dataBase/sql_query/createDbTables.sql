CREATE TABLE IF NOT EXISTS weatherFrom_OpenWeather (
    city VARCHAR(255),
    temp_metric DECIMAL(5, 2),
    feels_like DECIMAL(5, 2),
    temp_avg DECIMAL(8, 2),
    CONSTRAINT unique_weather_data UNIQUE (city)
);