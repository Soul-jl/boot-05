package com.jy.common;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtil {

    private static String MAIL_HOST = "smtp.qq.com";
    private static String PROTOCOL = "smtp";
    private static String SMTP_AUTH = "true";
    private static String SSL_ENABLE = "true";
    private static String USER_NAME = "1131578282@qq.com";
    private static String PASSWORD = "vvsowizlfkolhdjf";
    private static boolean DEBUG = true;

    public static void sendMail(String recipientAddress, String subject, String content) {
        Properties prop = new Properties();
        //        //设置QQ邮件服务器
        prop.setProperty("mail.host", MAIL_HOST);
        //邮件发送协议
        prop.setProperty("mail.transport.protocol", PROTOCOL);
        //需要验证用户名密码
        prop.setProperty("mail.smtp.auth", SMTP_AUTH);

        try {
            //关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", SSL_ENABLE);
            prop.put("mail.smtp.ssl.socketFactory", sf);
            prop.put("mail.smtp.socketFactory.port", 587);


            //使用JavaMail发送邮件的5个步骤
            //1.txt、创建定义整个应用程序所需的环境信息的Session对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication(USER_NAME,
                            PASSWORD);
                }
            });

            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(DEBUG);

            //2、通过session得到transport对象
            Transport ts = session.getTransport();

            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(MAIL_HOST, USER_NAME, PASSWORD);

            //4，创建邮件
            //4-1.txt，创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //4-2，指明邮件的发件人
            message.setFrom(new InternetAddress(USER_NAME));

            //4-3，指明邮件的收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientAddress));

            //4-4，邮件标题
            message.setSubject(subject);

            //4-5，邮件文本内容
            message.setText(content);

            //4-6，发送邮件
            ts.sendMessage(message, message.getAllRecipients());

            //5，关闭连接
            ts.close();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
