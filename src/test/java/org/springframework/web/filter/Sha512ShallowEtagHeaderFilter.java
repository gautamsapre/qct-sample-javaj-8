package org.springframework.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.util.DigestUtils;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

/**
 * Custom implementation of ShallowEtagHeaderFilter that uses SHA-512 for ETag generation
 * Compatible with Spring Framework 6.x
 */
public class Sha512ShallowEtagHeaderFilter extends OncePerRequestFilter {

    private static final String HEADER_ETAG = "ETag";
    private static final String HEADER_IF_NONE_MATCH = "If-None-Match";

    /**
     * Generate an ETag value from the given response body byte array.
     * <p>The default implementation generates an MD5 hash.
     * @param responseBody the response body as a byte array
     * @return the ETag value
     * @see org.springframework.util.DigestUtils
     */
    protected String generateETagHeaderValue(byte[] responseBody) {
        try {
            // Use SHA-512 algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest(responseBody);
            
            return "\"" + Base64.getEncoder().encodeToString(digest) + "\"";
        } catch (NoSuchAlgorithmException e) {
            // Fallback to the default implementation using MD5
            return "\"" + DigestUtils.md5DigestAsHex(responseBody) + "\"";
        }
    }

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        
        filterChain.doFilter(request, responseWrapper);
        
        byte[] responseBody = responseWrapper.getContentAsByteArray();
        if (responseBody.length > 0) {
            String eTag = generateETagHeaderValue(responseBody);
            response.setHeader(HEADER_ETAG, eTag);
            
            String ifNoneMatch = request.getHeader(HEADER_IF_NONE_MATCH);
            if (ifNoneMatch != null && ifNoneMatch.equals(eTag)) {
                response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                // No need to send the body
                return;
            }
        }
        
        // Copy the content to the original response
        responseWrapper.copyBodyToResponse();
	}
}
