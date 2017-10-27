package gmail.alexdudarkov.task02.dao.util;


import gmail.alexdudarkov.task02.dao.exception.DAOException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Parser {

    private static final String FILE_NAME = "task02.xml";


    public List<String> printFile() throws DAOException {
        InputStream inputStream = getInputStream();
        List<String> tags = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            StringBuilder stringBuilder = new StringBuilder();

            char c;
            int fileChar;

            do {
                fileChar = reader.read();
                c = (char) fileChar;

                if (c == '<' || fileChar == -1) {
                    String value = stringBuilder.toString().trim();

                    if (!value.isEmpty()) {
                        tags.add(value);
                    }

                    stringBuilder.setLength(0);

                }
                stringBuilder.append(c);

                if (c == '>') {

                    stringBuilder.append(c);
                    tags.add(stringBuilder.toString().trim());
                    stringBuilder.setLength(0);

                }

            } while (fileChar != -1);


        } catch (IOException e) {
            throw new DAOException(e);
        }
        return tags;
    }


    private InputStream getInputStream() throws DAOException {

        URL urlConfig = this.getClass().getClassLoader().getResource(FILE_NAME);

        if (urlConfig == null) {
            throw new DAOException("file not found");
        }

        InputStream inputStream;

        try {

            URLConnection urlConnection = urlConfig.openConnection();
            inputStream = urlConnection.getInputStream();

        } catch (IOException e) {
            throw new DAOException(e);
        }

        return inputStream;
    }


}

