import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailTest {

    public static void main(String[] args) throws MessagingException {
        // ���÷����ʼ��Ļ�������
        final Properties props = new Properties() ;
        /*
         * ���õ����ԣ� mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // ��ʾSMTP�����ʼ�����Ҫ���������֤
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        // �����˵��˺�
        props.put("mail.user", "support@ydcfo.com");
        // ����SMTP����ʱ��Ҫ�ṩ������
        props.put("mail.password", "Support1234");

        // ������Ȩ��Ϣ�����ڽ���SMTP���������֤
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // �û���������
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
        Session mailSession = Session.getInstance(props, authenticator);
        // �����ʼ���Ϣ
        MimeMessage message = new MimeMessage(mailSession);
        // ���÷�����
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // �����ռ���
        InternetAddress to = new InternetAddress("582001651@qq.com");
        message.setRecipient(RecipientType.TO, to);

       /* // ���ó���
        InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
        message.setRecipient(RecipientType.CC, cc);

        // �������ͣ��������ռ��˲��ܿ������͵��ʼ���ַ
        InternetAddress bcc = new InternetAddress("aaaaa@163.com");
        message.setRecipient(RecipientType.CC, bcc);*/

        // �����ʼ�����
        message.setSubject("�����ʼ�");

        // �����ʼ���������
        message.setContent("��ã�����������п�ô��", "text/html;charset=UTF-8");

        // �����ʼ�
        Transport.send(message);
    }
}
