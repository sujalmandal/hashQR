package net.udevs.hashqr;

import java.util.TreeMap;

public interface Genesis {
	Seed createRandomSeed(TreeMap<String,String> metadata);
}
