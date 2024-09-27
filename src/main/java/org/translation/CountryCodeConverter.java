package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides the service of converting country codes to their names.
 */
public class CountryCodeConverter {

    private final List<String> countries = new ArrayList<>();
    private final List<String> a2 = new ArrayList<>();
    private final List<String> a3 = new ArrayList<>();
    private final List<String> numeric = new ArrayList<>();

    public CountryCodeConverter() {
        this("country-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the country code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */

    public CountryCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));

            for (int i = 1; i < lines.size(); i++) {
                String[] temp = lines.get(i).split("\t");
                countries.add(temp[0]);
                a2.add(temp[1]);
                a3.add(temp[2]);
                numeric.add(temp[3]);
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Add.
     * @param args asd
     */
    public static void main(String[] args) {
        CountryCodeConverter ctr = new CountryCodeConverter();
        System.out.println(ctr.fromCountryCode("ASM"));
        System.out.println(ctr.fromCountry("Barbados"));
        System.out.println(ctr.getNumCountries());
    }

    /**
     * Returns the name of the country for the given country code.
     * @param code the 3-letter code of the country
     * @return the name of the country corresponding to the code
     */
    public String fromCountryCode(String code) {
        if (code.isEmpty()) {
            return "Country not found";
        }
        int index = 0;
        for (int i = 0; i < a3.size(); i++) {
            if (a3.get(i).equalsIgnoreCase(code)) {
                index = i;
                break;
            }
        }
        return countries.get(index);
    }

    /**
     * Returns the code of the country for the given country name.
     * @param country the name of the country
     * @return the 3-letter code of the country
     */
    public String fromCountry(String country) {
        if (country.isEmpty()) {
            return "Country not found";
        }
        int index = 0;
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).equals(country)) {
                index = i;
                break;
            }
        }
        return a3.get(index);
    }

    /**
     * Returns how many countries are included in this code converter.
     * @return how many countries are included in this code converter.
     */
    public int getNumCountries() {
        return countries.size();
    }
}
