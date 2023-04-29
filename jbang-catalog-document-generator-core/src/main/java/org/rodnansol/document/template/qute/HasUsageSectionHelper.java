package org.rodnansol.document.template.qute;

import io.quarkus.qute.Expression;
import io.quarkus.qute.ResultNode;
import io.quarkus.qute.SectionBlock;
import io.quarkus.qute.SectionHelper;
import org.rodnansol.document.DocumentGenerationProperties;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletionStage;

public class HasUsageSectionHelper implements SectionHelper {

    private final DocumentGenerationProperties documentGenerationProperties;
    private final Expression aliasExpression;
    private final SectionBlock block;

    public HasUsageSectionHelper(DocumentGenerationProperties documentGenerationProperties, Expression aliasExpression, SectionBlock block) {
        this.documentGenerationProperties = documentGenerationProperties;
        this.aliasExpression = aliasExpression;
        this.block = block;
    }

    private String getPath(String context) {
        return documentGenerationProperties.currentWorkingDirectory() + "/" + context + CustomUsageConstants.EXTENSION;
    }

    @Override
    public CompletionStage<ResultNode> resolve(SectionResolutionContext context) {
        return context.resolutionContext().evaluate(aliasExpression).thenCompose(it -> {
            if (!Files.exists(Path.of(getPath((String) it)))) {
                return ResultNode.NOOP;
            } else {
                return context.execute(block, context.resolutionContext());
            }
        });
    }
}
