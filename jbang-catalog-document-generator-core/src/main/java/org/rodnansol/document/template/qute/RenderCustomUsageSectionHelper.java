package org.rodnansol.document.template.qute;

import io.quarkus.qute.Expression;
import io.quarkus.qute.ResultNode;
import io.quarkus.qute.SectionHelper;
import org.rodnansol.document.DocumentGenerationProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

public class RenderCustomUsageSectionHelper implements SectionHelper {

    private final DocumentGenerationProperties documentGenerationProperties;
    private final Expression aliasExpression;

    public RenderCustomUsageSectionHelper(DocumentGenerationProperties documentGenerationProperties, Expression aliasExpression) {
        this.documentGenerationProperties = documentGenerationProperties;
        this.aliasExpression = aliasExpression;
    }

    private String getPath(String context) {
        return documentGenerationProperties.currentWorkingDirectory() + "/" + context + CustomUsageConstants.EXTENSION;
    }

    @Override
    public CompletionStage<ResultNode> resolve(SectionResolutionContext context) {
        return context.resolutionContext()
            .evaluate(aliasExpression)
            .thenCompose(it -> {
                CompletableFuture<ResultNode> resultNodeCompletableFuture = new CompletableFuture<>();
                resultNodeCompletableFuture.complete(resultConsumer -> extracted((String) it, resultConsumer));
                return resultNodeCompletableFuture;
            });
    }

    private void extracted(String it, Consumer<String> resultConsumer) {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Path.of(getPath(it)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultConsumer.accept(new String(bytes, StandardCharsets.UTF_8));
    }
}
