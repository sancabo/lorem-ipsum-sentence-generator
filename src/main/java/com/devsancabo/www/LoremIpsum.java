package com.devsancabo.www;

public final class LoremIpsum {
    private static final String PARAGRAPH = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, " +
            "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam " +
            "voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
            "no sea takimata sanctus est Lorem ipsum dolor sit amet.";
    private static final String[] LOREM_IPSUM_WORDS = PARAGRAPH.split("\\s");
    private static final int WORDS_ARRAY_LENGTH = 50;
    private static final PRNG RNG = new PRNG();

    private LoremIpsum(){}

    /**
     * Get a sentence of 20 words from the lorem ipsum text.
     * @return a String containing the sentence.
     */
    public static String getSentence() {return getSentence(20, 0);
    }

    /**
     * Get a sentence of an arbitrary amount of words from the lorem ipsum text.
     * The words will start to loop around after 50.
     * @param wordAmount the amount of words to generate.
     * @return a String containing the words, separated by space.
     */
    public static String getSentence(int wordAmount) {
        return fasterGetWords(wordAmount);
    }

    /**
     * Get a sentence of an arbitrary amount of words from the lorem ipsum text.
     * The words will start to loop around after 50.
     * The first word will be determined by an offset. This selector will also loop after 50.
     * @param wordAmount The amount of words to generate.
     * @param startOffset How many words to skip before selecting the first one.
     * @return a String containing the words separated by space.
     */
    public static String getSentence(int wordAmount, int startOffset) {
        if (wordAmount <= 0) return "";
        StringBuilder lorem = new StringBuilder();
        int wordPosition = startOffset % WORDS_ARRAY_LENGTH;

        String firstWord = LOREM_IPSUM_WORDS[wordPosition % WORDS_ARRAY_LENGTH];
        String casedFirstWord = firstWord.substring(0,1).toUpperCase() + firstWord.substring(1);
        lorem.append(casedFirstWord);
        lorem.append(' ');

        for(int i = wordPosition + 1; i < wordPosition + wordAmount; ++i) {
            lorem.append(LOREM_IPSUM_WORDS[i % WORDS_ARRAY_LENGTH]);
            lorem.append(' ');
        }
        return fixEnd(lorem.toString().stripTrailing());
    }

    /**
     * Get two paragraphs of lorem ipsum words.
     * Each paragraph's content is equivalent to LoremIpsum.getWords(50).
     * @return a String containing the paragraphs, separated by two newlines.
     */
    public static String getParagraphs() {
        return getParagraphs(2);
    }

    /**
     * Get an arbitrary amount of paragraphs of lorem ipsum words.
     * Each paragraph's content is equivalent to LoremIpsum.getWords(50).
     * @param amount The amount of paragraphs to generate.
     * @return a String containing the paragraphs separated by two newlines.
     */
    public static String getParagraphs(int amount) {
        StringBuilder lorem = new StringBuilder();

        for(int i = 0; i < amount; ++i) {
            lorem.append(PARAGRAPH);
            if (i < amount - 1) {
                lorem.append("\n\n");
            }
        }

        return lorem.toString();
    }

    /**
     * Get an arbitrary amount of paragraphs of lorem ipsum words.
     * Each paragraph's content is equivalent to LoremIpsum.getWords(paragraphLength).
     * @param amount The amount of paragraphs to generate.
     * @param paragraphLength The length of each generated paragraph.
     * @return a String containing the paragraphs separated by two newlines.
     */
    public static String getParagraphs(int amount, int paragraphLength) {
        StringBuilder lorem = new StringBuilder();

        for(int i = 0; i < amount; ++i) {
            lorem.append(getSentence(paragraphLength));
            if (i < amount - 1) {
                lorem.append("\n\n");
            }
        }

        return lorem.toString();
    }

    /**
     * Get a random sentence from the lorem ipsum text.
     * The maximum character count of the sentence is maxWords * 10
     * @param  maxWords The max amount of words the sentence can have.
     * @return a String containing the words, separated by space.
     */
    public static String getRandomSentence(int maxWords){
        int words = RNG.getValue(maxWords);
        return getSentence(words == 0 ? 1 : words, RNG.getValue(50));
    }


    private static String fasterGetWords(int amount) {
        if (amount <= 0) return "";
        //add chunks of 50 words instead of 1
        int loops = amount / WORDS_ARRAY_LENGTH;
        int endIndex = amount - (loops * WORDS_ARRAY_LENGTH);
        StringBuilder lorem = new StringBuilder();
        for(int i = 0; i < loops; ++i) {
            lorem.append(PARAGRAPH);
            lorem.append(' ');
        }
        for(int i = 0; i < endIndex; ++i) {
            lorem.append(LOREM_IPSUM_WORDS[i]);
            lorem.append(' ');
        }
        return fixEnd(lorem.toString().stripTrailing());
    }

    private static String fixEnd(String rawResult){
        if(rawResult.charAt(rawResult.length() - 1) == ',')
            return rawResult.substring(0,rawResult.lastIndexOf(',')) + ".";
        if(rawResult.charAt(rawResult.length() - 1) != '.')
            return rawResult + ".";
        return rawResult;
    }

}