package be.kdg.schelderadarchain.generator.utility;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;
import be.kdg.schelderadarchain.generator.dom.Route;
import be.kdg.schelderadarchain.generator.generator.GeneratorException;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Cas on 4/11/2015.
 */
public class RouteFileReader {

    private static final int BEGIN_INDEX = 0;
    private static final String ROUTE_FILE_NAME_FORMATTER = "%s/%s";

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
                        throw new UtilityException("Error reading the csv file", e);
                    } finally {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e) {
                                throw new UtilityException("Error closing the buffered reader after reading the csv file", e);
                            }
                        }
                    }
                    routes.add(new Route(shipId, positionMessages));
                }
            }
        } catch (URISyntaxException e) {
            throw new GeneratorException("Unexpected error reading the route files", e);
        }
        return routes;
    }
}
