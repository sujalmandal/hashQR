package net.udevs.hashqr.impl;

import java.io.File;
import java.io.IOException;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import net.udevs.hashqr.QRUtil;

public class QRUtilImpl implements QRUtil{

	@Override
	public File generateQR(String hash,String fileName,ImageType format) throws IOException {
		return QRCode.from(hash).to(format).file();
	}

	@Override
	public File generateQR(String hash, String fileName, int formatValue) throws IOException {
		ImageType format=null;
		if(formatValue==1) format=ImageType.JPG;
		if(formatValue==2) format=ImageType.GIF;
		if(formatValue==3) format=ImageType.PNG;
		if(formatValue==4) format=ImageType.BMP;
		return QRCode.from(hash).to(format).file();
	}

}
