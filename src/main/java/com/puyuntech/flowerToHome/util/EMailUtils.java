package com.puyuntech.flowerToHome.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by 王凯斌 on 2015/10/7 0007.
 *
 * 邮件发送工具类
 *  使用 apache common email jar 发送邮件
 */
public class EMailUtils {

    /**
     * 邮件发送工具类
     * @param smtpHost
     *          SMIP服务器地址
     * @param smtpPort
     *          SMIP服务器端口
     * @param smtpUsername
     *          SMIP用户名
     * @param smtpPassword
     *          SMIP密码
     * @param smtpSSLEnabled
     *          SMIP是否启用SSL
     * @param smtpFromMail
     *          发件人邮箱 [可以和用户名一样，若和用户名不一样，SMIP服务地址必须一样]
     * @param toMails
     *          收件人邮箱
     * @param subject
     *          主题
     * @param content
     *          邮件内容
     * @return
     */
    public static void send(String smtpHost, int smtpPort, String smtpUsername, String smtpPassword, boolean smtpSSLEnabled, String smtpFromMail, String[] toMails, String subject, String content) throws Exception{
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(smtpHost);
            email.setSmtpPort(smtpPort);
            email.setAuthentication(smtpUsername, smtpPassword);
            email.setSSLOnConnect(smtpSSLEnabled);
            email.setFrom(smtpFromMail, smtpFromMail );
            email.addTo(toMails);
            email.setSubject(subject);
            email.setCharset("UTF-8");
            email.setHtmlMsg(content);
            email.send();
        } catch (EmailException e) {
            throw new Exception(e.getMessage(), e);
        }
    }


}
