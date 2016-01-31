public class Application {
        
        private static Application _instance;
        
        private IndexManager indexManager;
        
        private CoverCoeficientCalculator coeficientCalculator;
        
        private DocumentFactory documentFactory;
        
        private Application() {
                indexManager = new IndexManager();
                DocumentLevelIndexer documentLevelIndexer=new DocumentLevelIndexer();
                SentenceLevelIndexer sentenceLevelIndexer=new SentenceLevelIndexer();
                indexManager.setDocumentLevelIndexer(documentLevelIndexer);
                indexManager.setSentenceLevelIndexer(sentenceLevelIndexer);
                
                documentFactory = new DocumentFactory();
                documentFactory.registerSentenceListener(indexManager);
                documentFactory.registerWordListener(indexManager);
                
                coeficientCalculator = new CoverCoeficientCalculator();
                coeficientCalculator.setIndexManager(indexManager);
                UniformAlphaProbability alphaProbability = new UniformAlphaProbability();
                alphaProbability.setIndexManager(indexManager);
                coeficientCalculator.setSentOrDocProbability(alphaProbability);
                
                UniformBetaProbabilityProvider termProbabilityProvider =
                        new UniformBetaProbabilityProvider();
                termProbabilityProvider.setIndexManager(indexManager);
                coeficientCalculator.setTermProbabilityProvider(
                                termProbabilityProvider);
        }
        
        public static Application getInstance() {
                if(_instance == null) {
                        _instance = new Application();
                }
                return _instance;
        }

        public CoverCoeficientCalculator getCoeficientCalculator() {
                return coeficientCalculator;
        }

        public DocumentFactory getDocumentFactory() {
                return documentFactory;
        }

        public IndexManager getIndexManager() {
                return indexManager;
        }
        
        
        

}