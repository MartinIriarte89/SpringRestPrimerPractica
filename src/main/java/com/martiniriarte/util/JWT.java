package com.martiniriarte.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm; 

@Component
public class JWT {

	@Value("${security.jwt.secret}")
	private String key;
	
	@Value("${security.jwt.issuer}")
	private String issuer;
	
	@Value("${security.jwt.ttlMillis}")
	private Long ttlMillis;
	
	//private final Logger log = LoggerFactory.getLogger(JWT.class);
	
	/**
	 * Crear un nuevo token
	 * 
	 *  @param id
	 *  @param subject
	 *  @return
	 */
	 public String create(String id, String subject) {

	        // El algoritmo de firma JWT utilizado para firmar el token
	       
		 	SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
				 
	        long nowMillis = System.currentTimeMillis();
	        Date now = new Date(nowMillis);

	        //  Firma JWT con nuestra ApiKey secreta 
	        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
	        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

	        // establecer las reclamaciones JWT
	        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
	                .signWith(signatureAlgorithm, signingKey);

	        if (ttlMillis >= 0) {
	            long expMillis = nowMillis + ttlMillis;
	            Date exp = new Date(expMillis);
	            builder.setExpiration(exp);
	        }

	        // Crea el JWT y lo serializa en una cadena compacta segura para URL
	        return builder.compact();
	    }

	    /**
	     * Método para validar y leer el JWT
	     *
	     * @param jwt
	     * @return
	     */
	    public String getValue(String jwt) {
	        // Esta línea arrojará una excepción si no es un JWS firmado (como se esperaba)
	        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
	                .parseClaimsJws(jwt).getBody();

	        return claims.getSubject();
	    }

	    /**
	     * Método para validar y leer el JWT
	     *
	     * @param jwt
	     * @return
	     */
	    public String getKey(String jwt) {
	    //Esta línea arrojará una excepción si no es un JWS firmado (como se esperaba)
	        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
	                .parseClaimsJws(jwt).getBody();

	        return claims.getId();
	    }
}
