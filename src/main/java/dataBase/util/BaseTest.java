package dataBase.util;

import dataBase.dao.GetWeatherDataDAO;
import dataBase.dao.OpenWeatherDAO;
import org.testng.annotations.BeforeMethod;

import java.util.logging.Logger;

public class BaseTest {
    protected static final Logger LOGGER = Logger.getLogger(PropertyLoader.class.getName());
    protected OpenWeatherDAO openWeatherDAO;
    protected GetWeatherDataDAO getWeatherDataDAO;

    @BeforeMethod
    public void setUp() {
        initializePages();
        openWeatherDAO.createWeatherTableIfNotExists();
    }

    private void initializePages() {
        openWeatherDAO = new OpenWeatherDAO();
        getWeatherDataDAO = new GetWeatherDataDAO();
    }
}
