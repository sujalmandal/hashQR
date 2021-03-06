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

}
