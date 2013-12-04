
public class ReverseEncryption implements EncryptionStrategy {

	public String encrypt(String original){
		
		String reversed = new StringBuilder(original).reverse().toString();
		return reversed;
	}
	
	public String decrypt(String encrypted){
		return encrypt(encrypted);
	}
	
}
