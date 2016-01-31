import java.util.ArrayList;

/**
 *  @author gonenc
 *  
 *  
 *
 */

public abstract class RegExpTokenizer {
        
        protected String _originalText;
        
        protected int _index=0;
        
        protected ArrayList<String> tokens = new ArrayList<String>();
        
        protected String _regExp = "(\\[a-zA-z])+"; 
                //"[a-zA-z0-9]*";
        
        public void setRegExp(String regExp) {
                this._regExp = regExp;
        }

        public String getNextToken() {
                String retval=null;
                if (hasNext())
                {
                        retval = tokens.get(_index);
                        _index++;
                }       
                return retval;
        }

        
        public void setText(String string) {
                this._originalText = string; 
        }
        
        public boolean hasNext() {
                return (tokens.size() > _index);
        }

        
        

}