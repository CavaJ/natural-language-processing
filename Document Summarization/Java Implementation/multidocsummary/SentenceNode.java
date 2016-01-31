public class SentenceNode implements Comparable<SentenceNode>{

        private double coeficient;
        private Sentence sentence; 
        
        public SentenceNode(Sentence s, double c) {
                sentence=s;
                coeficient=c;
        }
        
        
        public int compareTo(SentenceNode o) {
                double result = this.getCoeficient()-o.coeficient;
                int retval = 0;
                if(result == 0) retval = 0;
                else if(result>0) retval = 1;
                else if(result<0) retval = -1;
                return retval;
        }


        public double getCoeficient() {
                return coeficient;
        }


        public void setCoeficient(double coeficient) {
                this.coeficient = coeficient;
        }


        public Sentence getSentence() {
                return sentence;
        }


        public void setSentence(Sentence sentence) {
                this.sentence = sentence;
        }
                

}