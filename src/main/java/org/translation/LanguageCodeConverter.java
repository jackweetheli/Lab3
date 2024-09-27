package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {
    private final List<String> languageName = new ArrayList<String>();
    private final List<String> languageCode = new ArrayList<>();
    // TODO Task: pick appropriate instance variables to store the data necessary for this class

    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));
            for (int i = 1; i < lines.size(); i++) {
                String[] temp = lines.get(i).split("\t");
                this.languageName.add(temp[0]);
                this.languageCode.add(temp[1]);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        // TODO Task: update this code to use your instance variable to return the correct value
        int i = 0;
        for (int index = 0; index < this.languageCode.size(); index++) {
            if (this.languageCode.get(index).equals(code)) {
                i = index;
                break;
            }
        }
        return this.languageName.get(i);
    }

    /**
     * Returns the code of the language for the given language name.
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        int i = 0;
        for (int index = 0; index < this.languageName.size(); index++) {
            if (this.languageName.get(index).equals(language)) {
                i = index;
                break;
            }
        }
        return this.languageCode.get(i);
    }

    /**
     * Returns how many languages are included in this code converter.
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        // TODO Task: update this code to use your instance variable to return the correct value
        return this.languageName.size();
    }
}
