package codeif.service;


import codeif.util.RequestHelper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mapping.spring.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CodelfService {

    private static final String CODELF_API = "https://searchcode.com/api/codesearch_I/?callback=searchcodeRequestVariableCallback&p=0&per_page=22&q=";
    private static final String[] FILTER_WORDS = new String[]{"at", "are", "am", "the", "of", "at", "a", "an", "is", "not", "no", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static List<String> search(String word) {
        if (StringUtils.isEmpty(word)) {
            return null;
        }
        List<String> list = addVariable(word);
        String content = null;
        try {
            content = RequestHelper.get(CODELF_API + URLEncoder.encode(word, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JsonArray results = new JsonParser().parse(content).getAsJsonObject().getAsJsonArray("results");
        for (JsonElement element : results) {
            JsonObject lines = element.getAsJsonObject().getAsJsonObject("lines");
            Set<Map.Entry<String, JsonElement>> entrySet = lines.entrySet();
            for (Map.Entry<String, JsonElement> line : entrySet) {
                String linesStr = line.getValue().getAsString();
                linesStr = linesStr.replaceAll("[^a-zA-Z_\\-]", " ");
                for (String target : word.split(" ")) {
                    for (String w : linesStr.split("\\s+")) {
                        if (w.toLowerCase().contains(target.toLowerCase()) && !list.contains(w)) {
                            list.add(w);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static List<String> addVariable(String word) {
        String[] words = word.split(" ");
        List<String> filterList = Arrays.asList(FILTER_WORDS);
        List<String> list = Arrays.stream(words).map(String::toLowerCase).filter(s -> !filterList.contains(s.toLowerCase())).collect(Collectors.toList());
        list.add(StringUtils.translatorToCamel(word));
        return list;
    }
}
