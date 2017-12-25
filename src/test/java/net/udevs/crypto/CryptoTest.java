package net.udevs.crypto;

import java.util.TreeMap;

import com.google.common.annotations.VisibleForTesting;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.udevs.hashqr.Genesis;
import net.udevs.hashqr.Hasher;
import net.udevs.hashqr.Seed;
import net.udevs.hashqr.impl.GenesisImpl;
import net.udevs.hashqr.impl.HasherImpl;


public class CryptoTest extends TestCase
{
	Hasher hasher;
	Genesis genesis;
	Seed seed;
	String hash;
	TreeMap<String, String> metadata;
	
	@Override
	protected void setUp() throws Exception {
		hasher=new HasherImpl();
		genesis=new GenesisImpl();
		metadata=new TreeMap<>();
		metadata.put("version", "1.0.0");
		metadata.put("appName","SafeCam");
	}

    public CryptoTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(CryptoTest.class);
    }
    
    @VisibleForTesting
    public void testFlattenSeed() {
    	System.out.println("testFlattenSeed()");
    	
    	seed=genesis.createRandomSeed(metadata);
    	System.out.println("Created flattened seed : "+seed.flatten());
    }
    
    @VisibleForTesting
    public void testReadFlattenedSeedPositive() throws CloneNotSupportedException {
    	System.out.println("testReadFlattenedSeedPositive()");
    	seed=genesis.createRandomSeed(metadata);
    	Seed tempSeed=(Seed) seed.clone();
    	String flattenedSeed=seed.flatten();
    	seed=new Seed();
    	seed.read(flattenedSeed);
    	assertTrue(tempSeed.equals(seed));
    }
    
    @VisibleForTesting
    public void testReadFlattenedSeedNegative() throws CloneNotSupportedException {
    	System.out.println("testReadFlattenedSeedNegative()");
    	seed=genesis.createRandomSeed(metadata);
    	Seed tempSeed=(Seed) seed.clone();
    	tempSeed.getMetadata().put("NewMetaData", "NewMetaDataValue");
    	String flattenedSeed=seed.flatten();
    	seed=new Seed();
    	seed.read(flattenedSeed);
    	assertFalse(tempSeed.equals(seed));
    }

    @VisibleForTesting
    public void testGenerateRandomSeed() {
    	System.out.println("testGenerateRandomSeed()");
    	seed=genesis.createRandomSeed(metadata);
    	System.out.println("Creating random seed with metadata : "+seed);
    	assertNotNull(seed);
    }
    
    @VisibleForTesting
    public void testGenerateHash() {
    	System.out.println("testGenerateHash()");
    	seed=genesis.createRandomSeed(metadata);
    	hash=hasher.generateHash(seed);
    	System.out.println("Creating hash from the seed : "+hash);
    	assertNotNull(hash);
    }
    
    @VisibleForTesting
    public void testVerifyHash() throws CloneNotSupportedException {
    	System.out.println("testVerifyHash()");
    	seed=genesis.createRandomSeed(metadata);
    	hash=hasher.generateHash(seed);
        Seed tempSeed=(Seed) seed.clone();
        String secondHash=hasher.generateHash(tempSeed);
        System.out.println(seed+"->"+hasher.generateHash(seed));
        System.out.println(tempSeed+"->"+hasher.generateHash(tempSeed));
        assertEquals(hash, secondHash);
        assertTrue(hasher.verifyHash(seed,hash));
    }
}
