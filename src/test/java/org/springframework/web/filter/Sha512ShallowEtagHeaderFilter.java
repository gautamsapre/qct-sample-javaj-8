package org.springframework.web.filter;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Custom implementation of ShallowEtagHeaderFilter that uses SHA-512 for ETag generation.
 * This implementation works with Spring Framework 6.x (Spring Boot 3.x)
 */
public class Sha512ShallowEtagHeaderFilter extends ShallowEtagHeaderFilter {

    /**
     * Overrides the default ETag generation to use SHA-512 instead of MD5
     * The method name may vary between Spring versions, but the functionality remains the same.
     */
    protected String generateETagHeaderValue(byte[] body) {
        final HashCode hash = Hashing.sha512().hashBytes(body);
		return "\"" + hash + "\"";
	}
}
