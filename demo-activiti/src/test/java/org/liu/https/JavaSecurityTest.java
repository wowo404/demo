package org.liu.https;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class JavaSecurityTest {
	
	@Test
	public void readP12()throws Exception {
		String p12FileName = "d:/certA.p12";

		String pfxPassword = "openssl";

		InputStream fis = new FileInputStream(p12FileName);

		// Create a keystore object

		KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");

		// Load the file into the keystore

		keyStore.load(fis, pfxPassword.toCharArray());

		String aliaesName = "abcd";

		PrivateKey priKey = (PrivateKey) (keyStore.getKey(aliaesName, null));

		System.out.println("private key:/n" + priKey);
	}

	@Test
	public void readCerByFile() throws Exception {
		String cerFileName = "d:/certA.cer";

		InputStream is = new FileInputStream(cerFileName);

		CertificateFactory cf = CertificateFactory.getInstance("x509");

		Certificate cerCert = cf.generateCertificate(is);

		System.out.println("public key:/n" + cerCert);
	}
	
	@Test
	public void readCerByClassPath() throws Exception {
		ClassPathResource resource = new ClassPathResource("cers/baidu.cer");
		
		InputStream is = resource.getInputStream();
		
		CertificateFactory cf = CertificateFactory.getInstance("x509");

		Certificate cerCert = cf.generateCertificate(is);

		System.out.println("public key:/n" + cerCert);
	}

}
