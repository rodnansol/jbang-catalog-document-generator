package org.rodnansol.document.checksum;

import jakarta.enterprise.context.Dependent;
import org.rodnansol.document.DocumentGenerationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Service dealing with reading and saving checksum for inputs.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@Dependent
public class ChecksumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChecksumService.class);

    /**
     * Calculates the checksum for the incoming service and reads the stored checksum if exists.
     *
     * @param inputFile input file which checksum is being calculated.
     * @return object containing the stored and actual checksum of the file.
     */
    public ChecksumResult calculateChecksums(File inputFile) {
        Objects.requireNonNull(inputFile, "inputFile is NULL");
        String storedChecksum = readStoredChecksum(inputFile);
        FileInputStream inputStream = getFileInputStream(inputFile);
        String actualChecksum = calculateChecksum(inputStream);
        LOGGER.debug("Stored and actual checksum for file:[{}] - Stored: [{}] - Actual:[{}]", inputFile.getName(), storedChecksum, actualChecksum);
        return new ChecksumResult(storedChecksum, actualChecksum);
    }

    /**
     * Saves the checksum of the file into the .jbang/<b>file-name</b>.txt file.
     *
     * @param inputFile input file which checksum is saved to the mentioned file.
     */
    public void saveChecksumForInputFile(File inputFile) {
        Objects.requireNonNull(inputFile, "inputFile is NULL");
        FileInputStream inputStream = getFileInputStream(inputFile);
        String actualChecksum = calculateChecksum(inputStream);
        File checksumFile = getChecksumFile(inputFile);
        LOGGER.debug("Writing checksum:[{}] to file:[{}]", actualChecksum, checksumFile.getPath());
        try (FileWriter fileWriter = new FileWriter(checksumFile)) {
            fileWriter.write(actualChecksum);
        } catch (IOException e) {
            throw new ChecksumCalculationException("Unable to open or write to file " + checksumFile.getPath(), e);
        }
    }

    private String readStoredChecksum(File inputFile) {
        File checksumFile = getChecksumFile(inputFile);
        FileInputStream fileInputStream = getFileInputStream(checksumFile);
        String storedChecksum;
        try (Scanner scanner = new Scanner(fileInputStream)) {
            storedChecksum = scanner.hasNext() ? scanner.nextLine() : "";
        }
        return storedChecksum;
    }

    private File getChecksumFile(File inputFile) {
        File checksumFile = new File(getChecksumPath(getChecksumFileName(inputFile)));
        if (!checksumFile.exists()) {
            checksumFile.getParentFile().mkdirs();
            try {
                boolean newFile = checksumFile.createNewFile();
                if (!newFile) {
                    throw new ChecksumCalculationException("Unable to create " + checksumFile.getPath());
                }
            } catch (IOException e) {
                throw new ChecksumCalculationException("Unable to create " + checksumFile.getPath(), e);
            }
        }
        return checksumFile;
    }

    private String getChecksumPath(String checksumFileName) {
        return ".jbang/" + checksumFileName;
    }

    private FileInputStream getFileInputStream(File file) {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ChecksumCalculationException("Error during opening file:" + file.getPath(), e);
        }
        return inputStream;
    }

    private String calculateChecksum(InputStream inputStream) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dataBytes = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, bytesRead);
            }
            byte[] mdBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte mdByte : mdBytes) {
                sb.append(Integer.toString((mdByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new DocumentGenerationException("Error during calculating checksum of given input file", e);
        }
    }

    private String getChecksumFileName(File file) {
        return file.getName() + ".txt";
    }

}
