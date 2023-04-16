package org.rodnansol.document.checksum;

/**
 * Record representing the result of the checksum calculation.
 *
 * @param storedChecksum     previously stored checksum.
 * @param calculatedChecksum currently calculated checksum.
 * @author nandorholozsnyak
 * @since 0.1.0
 */
public record ChecksumResult(
    String storedChecksum,
    String calculatedChecksum) {

    public boolean checksumEquals() {
        return storedChecksum.equals(calculatedChecksum);
    }

}
