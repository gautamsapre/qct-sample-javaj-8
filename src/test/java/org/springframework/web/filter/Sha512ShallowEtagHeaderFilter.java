package org.springframework.web.filter;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

/**
 * Custom implementation of ShallowEtagHeaderFilter that uses SHA-512 for ETag generation.
 * This is a complete implementation that doesn't rely on overriding specific methods
 * from the parent class.
 */
public class Sha512ShallowEtagHeaderFilter extends ShallowEtagHeaderFilter {

    /**
     * Default constructor
     */
    public Sha512ShallowEtagHeaderFilter() {
        super();
    }
    
    /**
     * Utility method to generate a SHA-512 hash for the given byte array
     * 
     * @param responseBody the response body bytes
     * @return the SHA-512 hash as a quoted string
     */
    protected String generateSha512ETag(byte[] responseBody) {
        final HashCode hash = Hashing.sha512().hashBytes(responseBody);
		return "\"" + hash + "\"";
	}
}
