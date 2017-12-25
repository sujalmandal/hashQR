package net.udevs.hashqr;


public interface Hasher {
	
	String generateHash(Seed seed);
	
	boolean verifyHash(Seed seed,String hash);
	
}
