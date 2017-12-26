package net.udevs.hashqr;

import java.util.TreeMap;

public interface Genesis {
	
	/*	
	 *  Treemap is needed in order to keep metadata autosorted so that the hasher does
	 *	not produce different hashes when same metadata appear in different order.
	 *
	*/
	Seed createRandomSeed(TreeMap<String,String> metadata);
}
