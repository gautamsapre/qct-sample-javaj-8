package org.springframework.web.filter;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Custom implementation of ShallowEtagHeaderFilter that uses SHA-512 for ETag generation
 * Adapted for Spring 6.x
 */
public class Sha512ShallowEtagHeaderFilter extends ShallowEtagHeaderFilter {

    /**
     * Custom implementation for generating ETag header values using SHA-512
     * Compatible with Spring 6.x
     */
    protected String calculateETagHeaderValue(byte[] body) {
        final HashCode hash = Hashing.sha512().hashBytes(body);
		return "\"" + hash + "\"";
	}
}
