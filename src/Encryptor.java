public class Encryptor {

	private EncryptionStrategy strategy;
	
	public Encryptor(){
		strategy = new CopyEncryption();
	}
	
	public String encrypt(String original){
		return strategy.encrypt(original);
	}
	
	public String decrypt( String encrypted){
		return strategy.decrypt(encrypted);
	}
	
	public void setStrategy(EncryptionStrategy strategy){
		this.strategy = strategy;
	}
	
}
