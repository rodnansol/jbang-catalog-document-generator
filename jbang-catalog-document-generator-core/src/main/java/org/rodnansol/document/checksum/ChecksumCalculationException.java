package org.rodnansol.document.checksum;

/**
 * Exception thrown when the checksum calculation process encounters an error.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public class ChecksumCalculationException extends RuntimeException {

    public ChecksumCalculationException(String message) {
        super(message);
    }

    public ChecksumCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
