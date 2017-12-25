package net.udevs.hashqr.impl;

import java.nio.charset.StandardCharsets;
import com.google.common.hash.Hashing;

import net.udevs.hashqr.Hasher;
import net.udevs.hashqr.Seed;

public class HasherImpl implements Hasher {
	
	public String generateHash(Seed seed) {
		return Hashing.sha256().hashString(seed.flatten(), StandardCharsets.UTF_8).toString();
	}

	public boolean verifyHash(Seed seed,String hash) {
		return (generateHash(seed).equals(hash));
	}

}
