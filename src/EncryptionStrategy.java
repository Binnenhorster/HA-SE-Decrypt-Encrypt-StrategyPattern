
public interface EncryptionStrategy {

	public String encrypt(String original);
	
	public String decrypt(String encrypted);
	
}
