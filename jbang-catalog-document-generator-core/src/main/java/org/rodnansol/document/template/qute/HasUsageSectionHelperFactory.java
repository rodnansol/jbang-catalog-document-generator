package org.rodnansol.document.template.qute;

import io.quarkus.qute.ImmutableList;
import io.quarkus.qute.Scope;
import io.quarkus.qute.SectionHelperFactory;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.DocumentGenerationProperties;

import java.util.List;

@ApplicationScoped
public class HasUsageSectionHelperFactory implements SectionHelperFactory<HasUsageSectionHelper> {

    protected static final String KEY = "key";
    private final DocumentGenerationProperties documentGenerationProperties;

    public HasUsageSectionHelperFactory(DocumentGenerationProperties documentGenerationProperties) {
        this.documentGenerationProperties = documentGenerationProperties;
    }

    @Override
    public List<String> getDefaultAliases() {
        return ImmutableList.of("hasUsage");
    }

    @Override
    public ParametersInfo getParameters() {
        return ParametersInfo.builder()
            .addParameter(KEY)
            .build();
    }

    @Override
    public HasUsageSectionHelper initialize(SectionInitContext context) {
        return new HasUsageSectionHelper(documentGenerationProperties, context.getExpression(KEY), context.getBlocks().get(0));
    }

    @Override
    public Scope initializeBlock(Scope previousScope, BlockInfo block) {
        if (block.getLabel().equals(MAIN_BLOCK_NAME)) {
            String iterable = block.getParameters().get(KEY);
            if (iterable == null) {
                iterable = "this";
            }
            block.addExpression(KEY, iterable);

            return new Scope(previousScope);
        } else {
            return previousScope;
        }
    }

}
