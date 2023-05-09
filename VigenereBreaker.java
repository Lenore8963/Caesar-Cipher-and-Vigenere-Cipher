import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    private String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i = i + totalSlices) {
            char c = message.charAt(i);
            slice.append(c);
        }
        return slice.toString();
    }

    private int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int i = 0; i < klength; i++) {
            String encryptedSlice = sliceString(encrypted, i, klength);
            CaesarCracker cracker = new CaesarCracker(mostCommon);
            key[i] = cracker.getKey(encryptedSlice);
        }
        return key;
    }
    
    private HashSet readDictionary (FileResource fr) {
        HashSet<String> set = new HashSet<String>();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            set.add(word);
        }
        return set;
    }
    
    private int countWords (String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int num = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();
            if (dictionary.contains(word)) {
                num++;
            }
        }
        return num;
    }
    
    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        char mostCom = mostCommonCharIn (dictionary);
        int num = 0;
        String result = null;
        int[] finalKey = null;
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(encrypted, i, mostCom);
            VigenereCipher vc = new VigenereCipher(key);
            String message = vc.decrypt(encrypted);
            int realWords = countWords (message, dictionary);
            if (realWords > num) {
                num = realWords;
                result = message;
                finalKey = key;
            }
        }
        return result;  
    }
    
    public char mostCommonCharIn (HashSet<String> dictionary) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (String s : dictionary) {
            StringBuilder sb = new StringBuilder(s);
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
        }
        int max = 0;
        Character maxC = null;
        for (char cc : map.keySet()) {
            if (map.get(cc) > max) {
                max = map.get(cc);
                maxC = cc;
            }
        }
        return maxC;
    }
    
    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        int max = 0;
        String result = null;
        String language = null;
        for (String name : languages.keySet()) {
            HashSet<String> dictionary = languages.get(name);
            String decrypted = breakForLanguage (encrypted, dictionary);
            int numOfWords = countWords (decrypted, dictionary);
            if (numOfWords > max) {
                max = numOfWords;
                result = decrypted;
                language = name;
            }
        }
        System.out.println(result);
        System.out.println(language);
    }
    
    public void breakVigenere () {
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        FileResource dicDanish = new FileResource("dictionaries/Danish");
        HashSet dictionaryDanish = readDictionary (dicDanish);
        FileResource dicDutch = new FileResource("dictionaries/Dutch");
        HashSet dictionaryDutch = readDictionary (dicDutch);
        FileResource dicEnglish = new FileResource("dictionaries/English");
        HashSet dictionaryEnglish = readDictionary (dicEnglish);
        FileResource dicFrench = new FileResource("dictionaries/French");
        HashSet dictionaryFrench = readDictionary (dicFrench);
        FileResource dicGerman = new FileResource("dictionaries/German");
        HashSet dictionaryGerman = readDictionary (dicGerman);
        FileResource dicItalian = new FileResource("dictionaries/Italian");
        HashSet dictionaryItalian = readDictionary (dicItalian);
        FileResource dicPortuguese = new FileResource("dictionaries/Portuguese");
        HashSet dictionaryPortuguese = readDictionary (dicPortuguese);
        FileResource dicSpanish = new FileResource("dictionaries/Spanish");
        HashSet dictionarySpanish = readDictionary (dicSpanish);
        languages.put("Danish", dictionaryDanish);
        languages.put("Dutch", dictionaryDutch);
        languages.put("English", dictionaryEnglish);
        languages.put("French", dictionaryFrench);
        languages.put("German", dictionaryGerman);
        languages.put("Italian", dictionaryItalian);
        languages.put("Portuguese", dictionaryPortuguese);
        languages.put("Spanish", dictionarySpanish);
       
        FileResource fr = new FileResource();
        String contents = fr.asString();
        breakForAllLangs (contents, languages);
    }
}

