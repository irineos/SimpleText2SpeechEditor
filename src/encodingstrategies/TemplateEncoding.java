package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {

	public TemplateEncoding() {
		// TODO Auto-generated constructor stub
	}
	
	public String encode(String s) {
		String encoded= "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			encoded+=mapCharacter(c);
		}
		
		return encoded;
	}
	
	abstract char mapCharacter(char c);
	
	

}
