package org.rodnansol.document.customcontent;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.rodnansol.document.TemplateType;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

/**
 * CDI configuration class to set up any {@link CustomContentParser} related instances.
 *
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@ApplicationScoped
class CustomContentParserConfiguration {

    @Produces
    Map<CustomContentParserKey, CustomContentParser> parserMap() {
        return ofEntries(
            entry(new CustomContentParserKey(TemplateType.ADOC, false), new AsciiDocCustomContentParser())
        );
    }

}
