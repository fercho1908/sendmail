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
public class Principal {

    public static void main(String[] args) {
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
            System.err.print(e.getMessage());
        }
    }
}
