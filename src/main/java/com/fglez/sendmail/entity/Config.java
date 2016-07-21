package com.fglez.sendmail.entity;

/**
 * Created by IntelliJ IDEA
 * User: Fernando Gonzalez<fgonzalez@syesoftware.com>
 * Date: 7/18/16.
 * Time: 4:30 PM.
 */
public class Config {

    private String user;
    private String password;
    private String host;
    private int port;
    private boolean ssl;
    private boolean auth;
    private String recive;
    private String subject;
    private String body;
    private String configFile;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getRecive() {

        return recive;
    }

    public void setRecive(String recive) {
        this.recive = recive;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    @Override
    public String toString() {
        return "Config{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", ssl=" + ssl +
                ", auth=" + auth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (port != config.port) return false;
        if (ssl != config.ssl) return false;
        if (auth != config.auth) return false;
        if (user != null ? !user.equals(config.user) : config.user != null) return false;
        if (password != null ? !password.equals(config.password) : config.password != null) return false;
        return !(host != null ? !host.equals(config.host) : config.host != null);

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (ssl ? 1 : 0);
        result = 31 * result + (auth ? 1 : 0);
        return result;
    }
}
