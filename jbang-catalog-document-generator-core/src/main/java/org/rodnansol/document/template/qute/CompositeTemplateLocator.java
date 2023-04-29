package org.rodnansol.document.template.qute;

import io.quarkus.qute.TemplateLocator;
import io.quarkus.qute.Variant;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@ApplicationScoped
public class CompositeTemplateLocator implements TemplateLocator {

    @Override
    public Optional<TemplateLocation> locate(String path) {
        Path p = Paths.get(path);
        if (p.isAbsolute() || Files.isReadable(p)) {
            return Optional.of(new FileTemplateLocation(p));
        } else {
            URL resource = locatePath(path);
            if (resource != null) {
                return Optional.of(new ResourceTemplateLocation(resource));
            }
        }
        return Optional.empty();
    }

    private URL locatePath(String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = Thread.currentThread().getContextClassLoader();
        }
        return cl.getResource(path);
    }

    private static class FileTemplateLocation implements TemplateLocator.TemplateLocation {

        private final Path file;
        private Optional<Variant> variant;

        public FileTemplateLocation(Path file) {
            this.file = file;
            this.variant = Optional.empty();
        }

        @Override
        public Reader read() {
            try {
                return Files.newBufferedReader(file);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public Optional<Variant> getVariant() {
            return variant;
        }

    }

    private static class ResourceTemplateLocation implements TemplateLocator.TemplateLocation {

        private final URL resource;
        private Optional<Variant> variant;

        public ResourceTemplateLocation(URL resource) {
            this.resource = resource;
            this.variant = Optional.empty();
        }

        @Override
        public Reader read() {
            try {
                return new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public Optional<Variant> getVariant() {
            return variant;
        }

    }

}
