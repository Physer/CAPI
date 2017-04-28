package com.valtech.amsterdam.valtechapipoc.service.loader.implementation.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by jasper.van.zijp on 18-4-2017.
 */

public class BufferedStreamContentReader implements UrlContentReader {
    @Override
    public String readContent(HttpURLConnection urlConnection) throws IOException {
        BufferedReader bufferedReader = null;

        bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
}
