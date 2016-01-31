import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseIndexer<E> {
        
        private Map</*Sentence*/E, Map</*Word*/String, /*Frequency*/Integer>> index;
        private Map</*Word*/String, Map</**/E, Integer>> invertedIndex;
        
        public BaseIndexer() {
                reset();
        }

        public void reset() {
                index = new HashMap<E, Map<String, Integer>>();
                invertedIndex = new HashMap<String, Map<E, Integer>>();
        }
        
        public void addToIndex(E key, String word){
                addToIndexInternal(key, word);
                addToInvertedIndexInternal(key, word);          
        }

        private void addToInvertedIndexInternal(E key, String word) {
                Map<E, Integer> keysForTerm = this.invertedIndex.get(word);
                if(keysForTerm == null) {
                        keysForTerm = new HashMap<E, Integer>();
                        this.invertedIndex.put(word, keysForTerm);
                }
                int count = 0;
                if(keysForTerm.get(word) != null) {
                        count=keysForTerm.get(word);
                }
                count++;
                keysForTerm.put(key, count);
        }

        private void addToIndexInternal(E key, String word) {
                Map<String, Integer> inKeyTerms = this.index.get(key);
                if(inKeyTerms == null) {
                        inKeyTerms = new HashMap<String, Integer>();
                        this.index.put(key, inKeyTerms);
                }
                int count = 0;
                if(inKeyTerms.get(word) != null) {
                        count=inKeyTerms.get(word);
                }
                count++;
                inKeyTerms.put(word, count);
        }
        
        public int getCountKey(E key, String word) {
                Integer count = this.index.get(key).get(word);
                int retval=0;
                if(count!=null) retval=count;
                return retval;
        }
        
        public int getCount(String word, E key) {
                Integer count = this.invertedIndex.get(word).get(key);
                int retval=0;
                if(count!=null) retval=count;
                return retval;
        }
        
        public Collection<E> getKeysWithTerm(String word) {
                Collection<E> returnValue = new ArrayList<E>();
                Map<E, Integer> map=invertedIndex.get(word);
                if(map!=null) {
                        Set<E> entries=map.keySet();
                        for (E e : entries) {
                                returnValue.add(e);
                        }
                }
                
                return returnValue;
        }
        
        public Collection<String> getTermsInKey(E key) {
                Collection<String> returnValue = new ArrayList<String>();
                Map<String, Integer> map=index.get(key);
                if(map!=null) {
                        Set<String> entries=map.keySet();
                        for (String word : entries) {
                                returnValue.add(word);
                        }
                }
                
                return returnValue;
        }

        

}
