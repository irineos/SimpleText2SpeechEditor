package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding {

	public AtBashEncoding() {
		// TODO Auto-generated constructor stub
	}

	
	
	char mapCharacter(char c) {
		if       (c >= 'a' && c <= 'z') c = (char) ('a' + ('z' - c));
        else if  (c >= 'A' && c <= 'Z') c = (char) ('A' + ('Z' - c));
        
		return c;
	}

}
