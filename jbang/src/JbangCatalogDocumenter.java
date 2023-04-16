//usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.rodnansol:jbang-catalog-generator:999-SNAPSHOT
//JAVA 17

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.rodnansol.ConvertCommand;
import picocli.CommandLine;

@QuarkusMain
@CommandLine.Command(mixinStandardHelpOptions = true, subcommands = {ConvertCommand.class})
public class JbangCatalogDocumenter implements QuarkusApplication {
    @Inject
    CommandLine.IFactory factory;

    @Override
    public int run(String... args) throws Exception {
        return new CommandLine(this, factory).execute(args);
    }
}

