package org.rodnansol.document.customcontent;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FileContentBasedCustomContentParserTest {

    FileContentBasedCustomContentParser underTest = new FileContentBasedCustomContentParser();

    @TempDir
    Path tempDir;

    private static Stream<Arguments> customArgs() {
        return Stream.of(
            Arguments.of(new TestCase(null, null)),
            Arguments.of(new TestCase("Hello Custom Header", null)),
            Arguments.of(new TestCase("Hello Custom Header", "Hello Custom Footer")),
            Arguments.of(new TestCase(null, "Hello Custom Footer"))
        );
    }

    @ParameterizedTest
    @MethodSource("customArgs")
    void createCustomContent_shouldReturnCustomContentWithContentsOfTheGivenFile(TestCase testCase) throws IOException {
        // Given
        DocumentCustomization documentCustomization = new DocumentCustomization();
        if (testCase.headerContent != null) {
            Path resolve = tempDir.resolve("custom-header.txt");
            Files.writeString(resolve, testCase.headerContent);
            documentCustomization.setCustomHeaderFilePath(resolve.toAbsolutePath().toString());
        }
        if (testCase.footerContent != null) {
            Path resolve = tempDir.resolve("custom-footer.txt");
            Files.writeString(resolve, testCase.footerContent);
            documentCustomization.setCustomFooterFilePath(resolve.toAbsolutePath().toString());
        }

        // When
        CustomContent customContent = underTest.createCustomContent(documentCustomization);

        // Then
        CustomContent expectedCustomContent = new CustomContent(testCase.headerContent, testCase.footerContent);
        assertThat(customContent).isEqualTo(expectedCustomContent);

    }

    private record TestCase(
        String headerContent,
        String footerContent) {

    }
}