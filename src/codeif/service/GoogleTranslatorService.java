package codeif.service;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleTranslatorService {


    public static String translate(String word) {
        try {
            String url = "https://translate.googleapis.com/translate_a/single?" +
                    "client=gtx&" +
                    "sl=zh-CN" +
                    "&tl=en" +
                    "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return parseResult(response.toString());
        } catch (Exception e) {
            return word;
        }
    }

    private static String parseResult(String inputJson) {
        JsonArray array = new JsonParser().parse(inputJson).getAsJsonArray().get(0).getAsJsonArray();
        StringBuilder result = new StringBuilder();
        for (JsonElement o : array) {
            result.append(o.getAsJsonArray().get(0).getAsString());
        }
        return result.toString();
    }
}
