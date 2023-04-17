package org.rodnansol.document.customcontent;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.rodnansol.document.TemplateType;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@ApplicationScoped
public class CustomContentParserConfiguration {

    @Produces
    public Map<TemplateType, CustomContentParser> parserMap() {
        return ofEntries(
            entry(TemplateType.ADOC, new AsciiDocCustomContentParser()),
            entry(TemplateType.MD, new MarkdownCustomContentParser())
        );
    }

}
