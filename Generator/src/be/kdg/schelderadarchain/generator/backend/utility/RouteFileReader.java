package be.kdg.schelderadarchain.generator.backend.utility;

import be.kdg.schelderadarchain.generator.backend.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.backend.dom.Route;
import be.kdg.schelderadarchain.generator.backend.generator.GeneratorException;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is a utility class that reads and returns all route files
 *
 * @author Cas Decelle
 */
public class RouteFileReader {

    private static final int BEGIN_INDEX = 0;
    private static final String ROUTE_FILE_NAME_FORMATTER = "%s/%s";
    private static final Logger logger = Logger.getLogger(RouteFileReader.class);

    public static List<Route> readRouteFilesFromCsv(String folderName, String separator) {
        BufferedReader bufferedReader = null;
        String line = "";
        List<Route> routes = new ArrayList<>();

        try {
            File folder = new File(Thread.currentThread().getContextClassLoader().getResource(folderName).toURI());
            for (File file : folder.listFiles()) {
                if (file.isFile()) {
                    Collection<PositionMessage> positionMessages = new ArrayList<>();
                    String fileName = file.getName();
                    int index = fileName.indexOf(".");
                    String shipId = fileName.substring(BEGIN_INDEX, index);

                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(String.format(ROUTE_FILE_NAME_FORMATTER, folderName, fileName))));
                        while ((line = bufferedReader.readLine()) != null) {
                            String[] csvLine = line.split(separator);
                            positionMessages.add(new PositionMessage(shipId, csvLine[0], csvLine[1], csvLine[2]));
                        }
                    } catch (IOException e) {
                        String message = "Error reading the csv file";
                        logger.error(message);
                        throw new ConversionException(message, e);
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                String message = "Error closing the buffered reader after reading the csv file";
                                logger.error(message);
                                throw new ConversionException(message, e);
                            }
                        }
                    }
                    routes.add(new Route(shipId, positionMessages));
                }
            }
        } catch (URISyntaxException e) {
            String message = "Unexpected error reading the route files";
            logger.error(message);
            throw new GeneratorException(message, e);
        }
        return routes;
    }
}
