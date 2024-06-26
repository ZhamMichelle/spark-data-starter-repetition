package com.example.starter.unsafe;

import java.beans.Introspector;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhamilya on 4/14/24
 */
public class WordsMatcher {
    public static String findAndRemoveMatchingPiecesIfExists(Set<String> options, List<String> pieces) {
        StringBuilder match = new StringBuilder(pieces.remove(0));
        List<String> remainingOptions =
                options.stream()
                        .filter(option -> option.toLowerCase().startsWith(match.toString().toLowerCase()))
                        .toList();
        if(remainingOptions.isEmpty()){
            return "";
        }
        while(remainingOptions.size()>1){
            match.append(pieces.remove(0));
            remainingOptions.removeIf(option->!option.toLowerCase().startsWith(match.toString().toLowerCase()));
        }
        while (!remainingOptions.get(0).equalsIgnoreCase(match.toString())){
            match.append(pieces.remove(0));
        }
        return Introspector.decapitalize(match.toString());
    }
}
