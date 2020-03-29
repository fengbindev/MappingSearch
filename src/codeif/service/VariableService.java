package codeif.service;

import java.util.List;


public class VariableService {

    public static List<String> getValues(String selectedText) {
        String word = GoogleTranslatorService.translate(selectedText);
        List<String> values = CodelfService.search(word);
        return values;
    }
}
