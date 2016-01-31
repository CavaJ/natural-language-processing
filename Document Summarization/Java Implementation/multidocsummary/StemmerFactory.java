public class StemmerFactory {
        //private final static String TURKISH_STEMMER_ID = "ts";
        private final static String ENGLISH_STEMMER_ID = "es";
                
        public static Stemmer getStemmer(String stemmerId) {
                //if (stemmerId.equals(TURKISH_STEMMER_ID)) {
                        //return new TurkishStemmer();
                //}
                /*else*/ if (stemmerId.equals(ENGLISH_STEMMER_ID)) {
                        return new EnglishStemmer();
                }
                else {
                        return new DummyStemmer();
                }
        }
}

