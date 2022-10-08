package interviews.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Solution1 {
    public static void main(String[] args) {
        System.out.println(generateSentence(
                "this is a sentence it is not a good one and it is also bad", 5));
    }

    public static String generateSentence(String sentence, int length) {
        String[] words = sentence.split(" ");
        if (words.length == 0) {
            return sentence;
        }

        List<String> newWords = new ArrayList<>();

        int randIdx = new Random().nextInt(words.length);
        String prev = words[randIdx];
        newWords.add(prev);

        for (int i = 1; i < length; i++) {
            String chosenWord = randomNextWord(words, prev);
            newWords.add(chosenWord);
            prev = chosenWord;
        }

        return String.join(" ", newWords);
    }

    private static String randomNextWord(String[] words, String prev) {
        int randomBeginIndex = new Random().nextInt(words.length);

        for (int _i = 0; _i < words.length; _i++) {
            int idx = (randomBeginIndex + _i) % words.length;
            if (words[idx].equals(prev)) {
                return words[nextPos(idx, words)];
            }
        }

        return null;
    }

    private static int nextPos(int pos, String[] words) {
        return (pos + 1) % words.length;
    }
}
