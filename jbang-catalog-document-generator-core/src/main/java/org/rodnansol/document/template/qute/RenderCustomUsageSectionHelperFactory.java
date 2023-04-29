package org.rodnansol.document.template.qute;

import io.quarkus.qute.ImmutableList;
import io.quarkus.qute.Scope;
import io.quarkus.qute.SectionHelperFactory;
import jakarta.enterprise.context.ApplicationScoped;
import org.rodnansol.document.DocumentGenerationProperties;

import java.util.List;

@ApplicationScoped
public class RenderCustomUsageSectionHelperFactory implements SectionHelperFactory<RenderCustomUsageSectionHelper> {

    protected static final String KEY = "key";
    private final DocumentGenerationProperties documentGenerationProperties;

    public RenderCustomUsageSectionHelperFactory(DocumentGenerationProperties documentGenerationProperties) {
        this.documentGenerationProperties = documentGenerationProperties;
    }

    @Override
    public List<String> getDefaultAliases() {
        return ImmutableList.of("renderCustomUsage");
    }

    @Override
    public ParametersInfo getParameters() {
        return ParametersInfo.builder()
            .addParameter(KEY)
            .build();
    }

    @Override
    public RenderCustomUsageSectionHelper initialize(SectionInitContext context) {
        return new RenderCustomUsageSectionHelper(documentGenerationProperties, context.getExpression(KEY));
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
