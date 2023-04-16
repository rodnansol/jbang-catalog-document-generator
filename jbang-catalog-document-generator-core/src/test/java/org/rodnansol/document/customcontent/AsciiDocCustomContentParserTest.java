package org.rodnansol.document.customcontent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.rodnansol.document.CustomContent;
import org.rodnansol.document.DocumentCustomization;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AsciiDocCustomContentParserTest {


    @InjectMocks
    AsciiDocCustomContentParser underTest = new AsciiDocCustomContentParser();

    private static Stream<Arguments> customArgs() {
        return Stream.of(
            Arguments.of(new TestCase(null, null, null, null)),
            Arguments.of(new TestCase("header.adoc", null, "include::header.adoc[]", null)),
            Arguments.of(new TestCase("header.adoc", "footer.adoc", "include::header.adoc[]", "include::footer.adoc[]")),
            Arguments.of(new TestCase(null, "footer.adoc", null, "include::footer.adoc[]"))
        );
    }

    @ParameterizedTest
    @MethodSource("customArgs")
    void createCustomContent_shouldUseAsciiDocIncludeDirective_whenCustomHeaderOrFooterIsAdded(TestCase testCase) {
        // Given
        DocumentCustomization documentCustomization = new DocumentCustomization();
        documentCustomization.setCustomHeaderFilePath(testCase.header);
        documentCustomization.setCustomFooterFilePath(testCase.footer);

        // When
        CustomContent customContent = underTest.createCustomContent(documentCustomization);

        // Then
        CustomContent expectedCustomContent = new CustomContent(testCase.expectedHeader, testCase.expectedFooter);
        assertThat(customContent).isEqualTo(expectedCustomContent);
    }

    private record TestCase(
        String header,
        String footer,
        String expectedHeader,
        String expectedFooter) {

    }
}