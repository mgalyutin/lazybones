package uk.co.cacoethes.lazybones.commands

import groovy.transform.CompileStatic
import groovy.util.logging.Log
import joptsimple.OptionException
import joptsimple.OptionParser
import joptsimple.OptionSet

/**
 *
 */
@CompileStatic
@Log
abstract class AbstractCommand implements Command {
    protected abstract String getUsage()

    protected OptionParser createParser() { return new OptionParser() }

    protected OptionSet parseArguments(List<String> args, IntRange validArgCount) {
        try {
            def options = createParser().parse(args as String[])

            if (!(options.nonOptionArguments().size() in validArgCount)) {
                log.severe getHelp("Incorrect number of arguments.")
                return null
            }

            return options
        }
        catch(OptionException ex) {
            log.severe getHelp(ex.message)
            return null
        }
    }

    String getHelp(String message) {
        def writer = new StringWriter()
        createParser().printHelpOn(writer)

        return """\
${message}

${usage}
${writer}"""
    }
}
