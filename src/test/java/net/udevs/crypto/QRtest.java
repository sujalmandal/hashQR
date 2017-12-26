package net.udevs.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;

import com.google.common.annotations.VisibleForTesting;

import junit.framework.TestCase;
import net.glxn.qrgen.core.image.ImageType;
import net.udevs.hashqr.Genesis;
import net.udevs.hashqr.Hasher;
import net.udevs.hashqr.QRUtil;
import net.udevs.hashqr.Seed;
import net.udevs.hashqr.impl.GenesisImpl;
import net.udevs.hashqr.impl.HasherImpl;
import net.udevs.hashqr.impl.QRUtilImpl;

public class QRtest extends TestCase {
	
	Hasher hasher;
	Genesis genesis;
	Seed seed;
	String hash;
	TreeMap<String, String> metadata;
	QRUtil qrUtil;
	String fileName;
	
	@Override
	protected void setUp() throws Exception {
		hasher=new HasherImpl();
		genesis=new GenesisImpl();
		metadata=new TreeMap<>();
		metadata.put("version", "1.0.0");
		metadata.put("appName","SafeCam");
		qrUtil=new QRUtilImpl();
		fileName="QR_CODE.jpg";
	}
	
	@VisibleForTesting
	public void testGenerateQR() throws IOException {
		System.out.println("testGenerateQR()");
		Seed seed=genesis.createRandomSeed(metadata);
		File f=qrUtil.generateQR(hasher.generateHash(seed),fileName,ImageType.JPG);
		assertNotNull(f);
		InputStream initialStream = new FileInputStream(f);
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);	 
		File targetFile = new File("./"+fileName);
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer);
		outStream.flush();
	    outStream.close();
		initialStream.close();
		System.out.println("QRCode saved : "+targetFile.getAbsolutePath());
	}
	
	
}
