package com.sandman.download.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wangj on 2018/4/17.
 */
@Component
public class SftpParam {
    private static String USERNAME;
    private static String PASSWORD;
    private static String HOST;
    private static int SSH_PORT;

    @Value("${sftpServer.userName}")
    private void setUSERNAME(String USERNAME) {
        SftpParam.USERNAME = USERNAME;
    }

    @Value("${sftpServer.password}")
    private void setPASSWORD(String PASSWORD) {
        SftpParam.PASSWORD = PASSWORD;
    }

    @Value("${sftpServer.host}")
    private void setHOST(String HOST) {
        SftpParam.HOST = HOST;
    }

    @Value("${sftpServer.sshPort}")
    private void setSshPort(int sshPort) {
        SSH_PORT = sshPort;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getHOST() {
        return HOST;
    }

    public static int getSshPort() {
        return SSH_PORT;
    }
}