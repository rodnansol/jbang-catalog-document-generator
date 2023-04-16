package org.rodnansol;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

/**
 * @author nandorholozsnyak
 * @since 0.1.0
 */
@TopCommand
@CommandLine.Command(mixinStandardHelpOptions = true, subcommands = {ConvertCommand.class})
public class EntryCommand {
}

