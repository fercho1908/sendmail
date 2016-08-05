package com.fglez.sendmail.utils;

import com.fglez.sendmail.entity.Config;
import com.fglez.sendmail.exception.LogicException;
import org.apache.commons.lang.StringUtils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA
 * User: Fernando Gonzalez<fgonzalez@syesoftware.com>
 * Date: 7/21/16.
 * Time: 12:35 PM.
 */
public class SendMailUtil {

    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    private Properties readProperty() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = null;
        Reader reader = null;
        try {
            inputStream = new FileInputStream(config.getConfigFile());
            if (inputStream == null) {
                throw new IOException("file " + config.getConfigFile() + " not found");
            }
            reader = new InputStreamReader(inputStream, "UTF-8");
            prop.load(reader);
            return prop;
        } catch (IOException e) {
            throw new IOException("cannot read property file " + config.getConfigFile(), e);
        } finally {
            inputStream.close();
            reader.close();
        }
    }

    private Session createSession() throws IOException {
        Session session = null;
        Properties connectionProp = null;
        final String user;
        final String password;
        if (StringUtils.isEmpty(config.getConfigFile())) {
            user = config.getUser();
            password = config.getPassword();
            connectionProp = new Properties();
            connectionProp.setProperty("mail.smtp.host", config.getHost());
            connectionProp.setProperty("mail.smtp.port", String.valueOf(config.getPort()));
            connectionProp.setProperty("mail.smtp.starttls.enable", String.valueOf(config.isSsl()));
            connectionProp.setProperty("mail.smtp.auth", String.valueOf(config.isAuth()));
            return Connect(user, password, connectionProp);
        }
        connectionProp = this.readProperty();
        user = connectionProp.getProperty("user");
        password = connectionProp.getProperty("password");
        config.setUser(user);
        return Connect(user, password, connectionProp);
    }

    private Session Connect(final String user, final String password, Properties connectionProp) {
        Session session = Session.getInstance(connectionProp, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        session.setDebug(true);
        return session;
    }

    public void sendMail() throws LogicException {
        try {
            if (!DataValidations.emailValidation(config.getRecive())) {
                throw new LogicException("incorrect email format");
            }
            Session session = createSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.getUser()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(config.getRecive()));
            message.setSubject(config.getSubject());
            message.setText(config.getBody());
            Transport.send(message);
        } catch (MessagingException e) {
            throw new LogicException("cannot send email", e);
        } catch (IOException e) {
            throw new LogicException("cannot send email", e);
        }
    }
}
