package be.kdg.schelderadarchain.generator.utility;

import be.kdg.schelderadarchain.generator.dom.PositionMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cas on 4/11/2015.
 */
public class RouteFileReader {



    public static List<PositionMessage> readPositionMessagesFromCsv(String shipId, String separator){
        BufferedReader bufferedReader = null;
        String line = "";
        List<PositionMessage> positionMessages = new ArrayList<>();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("routes/1234567.csv")));
            while ((line = bufferedReader.readLine()) != null){
                String[] csvLine = line.split(separator);
                PositionMessage positionMessage = new PositionMessage(shipId, csvLine[0], csvLine[1], csvLine[2]);
                positionMessages.add(positionMessage);
            }
        } catch (IOException e) {
            throw new UtilityException("Error reading the csv file", e);
        } finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new UtilityException("Error closing the buffered reader after reading the csv file", e);
                }
            }
        }
        return positionMessages;
    }
}
