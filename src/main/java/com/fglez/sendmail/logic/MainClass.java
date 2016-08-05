package com.fglez.sendmail.logic;

import com.fglez.sendmail.entity.Config;
import com.fglez.sendmail.exception.LogicException;
import com.fglez.sendmail.utils.CLIConfig;
import com.fglez.sendmail.utils.SendMailUtil;

/**
 * Created by IntelliJ IDEA
 * User: Fernando Gonzalez<fgonzalez@syesoftware.com>
 * Date: 7/18/16.
 * Time: 4:59 PM.
 */
public class MainClass {

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","/usr/java/jdk1.7.0_79/jre/lib/security/cacerts");
        CLIConfig cliConfig = new CLIConfig();
        try {
            Config config = cliConfig.getConfig(args);
            if (config == null) {
                return;
            }
            SendMailUtil sendMailUtil = new SendMailUtil();
            sendMailUtil.setConfig(config);

            sendMailUtil.sendMail();
        } catch (LogicException e) {
            e.printStackTrace();
            System.err.print(e.getMessage());
        }
    }
}
