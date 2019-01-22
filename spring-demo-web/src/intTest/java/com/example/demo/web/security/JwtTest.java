package com.example.demo.web.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/** This test case is show how JWT work. */
public class JwtTest {
  private static Logger logger = LoggerFactory.getLogger(JwtTest.class);
  private RSAPrivateKey privateKey;
  private RSAPublicKey publicKey;

  @Before
  public void setUp() throws Exception {
    Resource keyStoreResource = new ClassPathResource(".keystore");
    KeyStore keyStore = KeyStore.getInstance(keyStoreResource.getFile(), "123456".toCharArray());
    privateKey = (RSAPrivateKey) keyStore.getKey("mykey", "123456".toCharArray());
    publicKey = (RSAPublicKey) keyStore.getCertificate("mykey").getPublicKey();
    logger.info(
        "load private key:\n{}\nbase64:\n{}",
        privateKey,
        Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    logger.info(
        "load public key:\n{}\nbase64:\n{}",
        publicKey,
        Base64.getEncoder().encodeToString(publicKey.getEncoded()));
  }

  @Test
  public void testEncodeJwtAndDecodeJwt() throws Exception {
    // encode jwt
    Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
    String token =
        JWT.create().withClaim("userId", 123).withClaim("authorities", "ROLE_USER").sign(algorithm);
    logger.info("token:{}", token);
    // decode jwt only with public key
    testDecodeJwtWithPublicKey(token, publicKey);
  }

  private void testDecodeJwtWithPublicKey(String token, RSAPublicKey publicKey) {
    Algorithm algorithm2 = Algorithm.RSA256(publicKey, null);
    JWTVerifier verifier = JWT.require(algorithm2).build();
    DecodedJWT jwt = verifier.verify(token);
    Assert.assertEquals(Long.valueOf(123), jwt.getClaim("userId").asLong());
    Assert.assertEquals("ROLE_USER", jwt.getClaim("authorities").asString());
  }

  @Test
  public void testDecodeJwtUsingBase64PublicKey()
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    // convert base64 to public key
    byte[] bytes =
        Base64.getDecoder()
            .decode(
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAklnEBGluPYjDMsPsw8GquGSpEgPoqG70ADdZMA6phSd2o7Mezt5LjiB0HcQ5pGWJkpy0NMjnH4W++ZnTCPdCm2AXWzFJLdzXUudtUFJNlu4+DC1BxgQsPmCt4f+5WnSx7BK8lwRvzAPTmbV9CA8sCcAIoU8MD9FTiKv6HQtBn8jveFg9g2QT9D2d1ssI84wg7wZravvICeKOT+v8QieU9giWa96mdj4PYpvL5UahKz6YdyO5HtaCpdEHVlLgFFmxHUlsqM25Iyt3j0yCULyNLNbtRHxIRHf0cqZThYTF5RBCBzOlFECFfdkpzra1ItXRzH0KspBqQDzm3I4sBscAmQIDAQAB");
    X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes, "RSA");
    RSAPublicKey key = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
    // decode jwt
    String token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VySWQiOjEyMywiYXV0aG9yaXRpZXMiOiJST0xFX1VTRVIifQ.kAX34omzHrsVhlYb_lnMopkfnHUfVT-UIKec3EX_YpObw3g6t_p-0fNhfrLYZCJreVl710NPAb2aFeNf1KQZBdBuVTz1_DycD048yqZ1ceZ5utjpPy1b7BPOYWs2F-w52ghoGX_qbMly0e6b4WU9GqrKxc7pw-kvpV6cIFh3vHA9WqjE2Fr2M56AcyAczbOFp_Hw5mr8pG8Na4ZfU5brUaSlE3Zge73WQ7-s6viIjxzkhv-IzxZfvGBC87t0TU2oA3mFG9Ma3FoH4UXuiKmVegMSMQzCfaKTFI2WHn61MJZfdJXRKE7wTS6t4Jl3p0aRCQnGJmtrpr7D1k7CLhFdiw";
    testDecodeJwtWithPublicKey(token, key);
  }
}
