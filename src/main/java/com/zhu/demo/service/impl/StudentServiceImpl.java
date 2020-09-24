package com.zhu.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhu.demo.dao.StudentMapper;
import com.zhu.demo.entity.StudentEntity;
import com.zhu.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Author: zhutao
 * @Date: 2020/4/29 10:10
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
    @Override
    public List<StudentEntity> queryAllByPage(long current, long size) {
        Page<StudentEntity> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        List<StudentEntity> records = this.baseMapper.queryAll(page).getRecords();
        return records;
    }

    @Override
    public void sendMail() throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yiibai@gmail.com", "<your password>");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("yiibai@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("yiibai@gmail.com"));
        msg.setSubject("Spring Boot Yiibai email");
        msg.setContent("Spring Boot Yiibai email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Spring Boot Yiibai email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("/var/tmp/image19.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);

    }


}
