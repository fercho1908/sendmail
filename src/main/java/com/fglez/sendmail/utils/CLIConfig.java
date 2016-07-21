package com.fglez.sendmail.utils;

import com.fglez.sendmail.entity.Config;
import com.fglez.sendmail.exception.LogicException;
import org.apache.commons.cli.*;

/**
 * Created by IntelliJ IDEA
 * User: Fernando Gonzalez<fgonzalez@syesoftware.com>
 * Date: 7/18/16.
 * Time: 4:36 PM.
 */
public class CLIConfig {
    Options options;

    public CLIConfig() {
        init();
    }

    private void init() {
        options = new Options();
        options.addOption("f", "from", true, "Cuenta de correo de envió");
        options.addOption("i", "ip", true, "ip Servidor de envio de correo");
        options.addOption("pw", "pwd", true, "password de la cuenta");
        options.addOption("p", "port", true, "puerto de salida de correo");
        options.addOption("ss", "ssl", false, "indica que tiene autenticacion ssl");
        options.addOption("a", "auth", false, "indica que el servidor solicita autenticacion");
        options.addOption("t", "to", true, "cuenta de correo que recibira el mensaje");
        options.addOption("s", "subject", true, "asunto del correo");
        options.addOption("b", "body", true, "cuerpo del correo");
        options.addOption("h", "help", false, "Muestra ayuda");
        options.addOption("c", "config", true, "archivo de configuración");
    }

    public Config getConfig(String[] args) throws LogicException {
        Config config = new Config();
        try {
            GnuParser gnuParser = new GnuParser();
            CommandLine commandLine = gnuParser.parse(this.options, args);
            if (commandLine.hasOption("h")) {
                this.help();
                return null;
            }
            config.setRecive(commandLine.getOptionValue("t"));
            config.setSubject(commandLine.getOptionValue("s"));
            config.setBody(commandLine.getOptionValue("b"));

            if (commandLine.hasOption("c")) {
                config.setConfigFile(commandLine.getOptionValue("c"));
                return config;
            }

            config.setUser(commandLine.getOptionValue("f"));
            config.setHost(commandLine.getOptionValue("i"));
            config.setPort(Integer.parseInt(commandLine.getOptionValue("p")));
            config.setPassword(commandLine.getOptionValue("pw"));
            if (commandLine.hasOption("ss")) {
                config.setSsl(true);
            }
            if (commandLine.hasOption("a")) {
                config.setAuth(true);
            }
            return config;
        } catch (MissingArgumentException argumentException) {
            this.help();
            throw new LogicException(argumentException.getMessage(), argumentException);
        } catch (MissingOptionException missingOptionException) {
            this.help();
            throw new LogicException(missingOptionException.getMessage(), missingOptionException);
        } catch (UnrecognizedOptionException unrecognizedOptionException) {
            this.help();
            throw new LogicException(unrecognizedOptionException.getMessage(), unrecognizedOptionException);
        } catch (ParseException e) {
            this.help();
            throw new LogicException(e.getMessage(), e);
        } catch (NumberFormatException e) {
            this.help();
            throw new LogicException("incorrect format port", e);
        }
    }

    private void help() throws LogicException{
        try {
            new HelpFormatter().printHelp(CLIConfig.class.getCanonicalName(), this.options);
        } catch (Exception e) {
            throw new LogicException("cannot print help information", e);
        }
    }
}

