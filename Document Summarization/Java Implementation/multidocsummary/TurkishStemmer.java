import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;


public class TurkishStemmer implements Stemmer {

        transient Zemberek zemberek = new Zemberek(new TurkiyeTurkcesi());

        /**
         * Iterated stemming of the given word.
         */
        public String stem(String str) {
                if (zemberek == null) {
                        zemberek = new Zemberek(new TurkiyeTurkcesi());
                }

                List ayrisimlar = zemberek.kelimeAyristir(str);
                Iterator it = ayrisimlar.iterator();
                int shortestLength = Integer.MAX_VALUE;
                String shortestStem = null;

                while (it.hasNext()) {
                        String[] strings = (String[]) it.next();
                        String stem = strings[0];

                        if (shortestLength > stem.length()) {
                                shortestLength = stem.length();
                                shortestStem = stem;
                        }
                }
                
                if (shortestStem == null) {
                        shortestStem = str;
                }
                
                return shortestStem;
        }
}

