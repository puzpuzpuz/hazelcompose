package com.hazelcast.hazelcompose;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.tools.keytool.CertAndKeyGen;
import sun.security.x509.X500Name;

public class CertificateGenerator {
    private static final int keysize = 1024;
    private static final String commonName = "localhost";
    private static final String organizationalUnit = "IT";
    private static final String organization = "Hazelcast";
    private static final String city = "Istanbul";
    private static final String state = "";
    private static final String country = "TR";
    private static final long validity = 1096; // 3 years
    private static final String alias = "mc";
    private static final char[] keyPass = "changeit".toCharArray();


    void generateKeystore()
            throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, NoSuchProviderException,
            InvalidKeyException, SignatureException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        CertAndKeyGen keypair = new CertAndKeyGen("RSA", "SHA1WithRSA", null);

        X500Name x500Name = new X500Name(commonName, organizationalUnit, organization, city, state, country);

        keypair.generate(keysize);
        PrivateKey privKey = keypair.getPrivateKey();

        X509Certificate[] chain = new X509Certificate[1];

        chain[0] = keypair.getSelfCertificate(x500Name, new Date(), validity * 24 * 60 * 60);

        keyStore.setKeyEntry(alias, privKey, keyPass, chain);

        keyStore.store(new FileOutputStream("mc.keystore"), keyPass);
    }
}

