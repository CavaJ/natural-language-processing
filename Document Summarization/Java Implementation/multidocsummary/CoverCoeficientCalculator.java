import java.util.Collection;

public class CoverCoeficientCalculator {
        
        private IndexManager indexManager;
        
        private AlphaProbability alphaProbability;
        
        private BetaProbabilityProvider betaProbabilityProvider;
        
        public double computeCoeficient(Sentence sentenceStart,
                        Sentence sentenceEnd) {
                Collection<String> terms = indexManager.getSentenceTerms(sentenceStart);
                double coeficient = 0;
                for (String term : terms) {
                        double alphaProb = alphaProbability.getAlphaProbability(term, sentenceStart);
                        double betaProb = betaProbabilityProvider.getProbability(sentenceEnd, term);
                        coeficient += alphaProb * betaProb;
                }
                
                return coeficient;
        }

        public IndexManager getIndexManager() {
                return indexManager;
        }

        public void setIndexManager(IndexManager indexManager) {
                this.indexManager = indexManager;
        }

        public AlphaProbability getSentOrDocProbability() {
                return alphaProbability;
        }

        public void setSentOrDocProbability(AlphaProbability sentOrDocProbability) {
                this.alphaProbability = sentOrDocProbability;
        }

        public BetaProbabilityProvider getTermProbabilityProvider() {
                return betaProbabilityProvider;
        }

        public void setTermProbabilityProvider(
                        BetaProbabilityProvider termProbabilityProvider) {
                this.betaProbabilityProvider = termProbabilityProvider;
        }
        
        

}