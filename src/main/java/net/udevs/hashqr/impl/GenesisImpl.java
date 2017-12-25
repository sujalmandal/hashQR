package net.udevs.hashqr.impl;

import java.util.TreeMap;
import java.util.UUID;

import net.udevs.hashqr.Genesis;
import net.udevs.hashqr.Seed;

public class GenesisImpl implements Genesis{

	@Override
	public Seed createRandomSeed(TreeMap<String, String> metadata) {
		String code=System.nanoTime()+"-"+UUID.randomUUID();
		Seed seed=new Seed();
		metadata.put(Seed.CODE, code);
		seed.setMetadata(metadata);
		return seed;
	}

}
