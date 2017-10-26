package gmail.alexdudarkov.task02.dao.util;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class Parser {

    private static final String FILE_NAME = "task02.xml";

    private InputStream getInputStream() {

        URL urlConfig = this.getClass().getClassLoader().getResource(FILE_NAME);
        if (urlConfig==null){
            throw  new NullPointerException();
        }
        URLConnection urlConnection;
        InputStream inputStream = null;

        try {
            urlConnection = urlConfig.openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public List<String> printFile() {
        InputStream inputStream = getInputStream();
        int line;
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            boolean tagStart = false;
            boolean valueStart = false;
            StringBuilder stringBuilder = new StringBuilder();
            char c;
            do {
                line = reader.read();
                c = (char) line;


                if (!tagStart) {
                    if (c != ' ') {
                        valueStart = true;
                    }
                }
                if (c == '<' || line == -1) {
                    if (!stringBuilder.toString().trim().isEmpty()) {
                        list.add(stringBuilder.toString().trim());
                    }
                    stringBuilder.setLength(0);
                    tagStart = true;
                }

                if (c == '>') {
                    stringBuilder.append(c);
                    list.add(stringBuilder.toString().trim());
                    stringBuilder.setLength(0);
                    tagStart = false;

                }

                if (tagStart) {
                    valueStart   = false;
                    stringBuilder.append(c);
                }

                if (valueStart) {
                    stringBuilder.append(c);
                }

            } while (line != -1);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}

