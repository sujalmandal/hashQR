package net.udevs.hashqr;

import java.io.File;
import java.io.IOException;

import net.glxn.qrgen.core.image.ImageType;

public interface QRUtil {
	
	File generateQR(Seed seed,String fileName,ImageType format) throws IOException;

}