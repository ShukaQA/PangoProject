INSERT INTO weatherFrom_OpenWeather (city, temp_metric, feels_like, temp_avg)
VALUES (?, ?, ?, ?)
ON DUPLICATE KEY UPDATE
    temp_metric = VALUES(temp_metric),
    feels_like = VALUES(feels_like),
    temp_avg = VALUES(temp_avg);
