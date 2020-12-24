package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {

	public Rot13Encoding() {
		// TODO Auto-generated constructor stub
	}

	
	
	char mapCharacter(char c) {
		
		if       (c >= 'a' && c <= 'm') c += 13;
        else if  (c >= 'A' && c <= 'M') c += 13;
        else if  (c >= 'n' && c <= 'z') c -= 13;
        else if  (c >= 'N' && c <= 'Z') c -= 13;
		
		return c;
	}
	
	

}
