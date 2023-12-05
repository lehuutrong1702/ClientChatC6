package com.teamc6.chatsystem.model;
public class Connection {
    private String Ipv4;
    private int port;

    public Connection() {
    }

    public Connection(String ipv4, int port) {
        Ipv4 = ipv4;
        this.port = port;
    }

    public String getIpv4() {
        return Ipv4;
    }

    public void setIpv4(String ipv4) {
        Ipv4 = ipv4;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
