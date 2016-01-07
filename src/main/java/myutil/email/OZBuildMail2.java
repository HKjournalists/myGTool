package myutil.email;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 用来发送邮件的工具</br>
 * 使用方法：</br>
 * 调用sendExaMail()来发送邮件
 * @author apple
 *
 */
public class OZBuildMail2 {

	
	/** 邮件发送协议 */
	private final static String PROTOCOL = "smtp";
	// SMTP邮件服务器
//	 private final static String HOST = "smtp.sina.com";
	private final static String HOST = "mail.analysys.com.cn";
//	private final static String HOST = "smtp.qq.com";
	// SMTP邮件服务默认端口
	private final static String PORT = "25";
	// 是否要求身份认证
	private final static String IS_AUTH = "true";
	// 是否调用调试模式
	private final static String IS_ENABLED_DEBUG_MOD = "true";
	// 发件人
//	private static String from = "enfodesk_isu@sina.cn";
//	private static String from = "no-reply@analysys.com.cn";//此处必填，必须是完整的邮箱名。
	private static String from = "ouzhou@analysys.com.cn";//此处必填，必须是完整的邮箱名。
	// 收件人
	private static String to = "ouzhou@analysys.com.cn";
	// 初始化邮件服务的初始化信息
//	private static Properties pros = System.getProperties();
	private static Properties pros = null;

	//设置Properties
	static {
		pros = new Properties();
		pros.setProperty("mail.transport.protocol", PROTOCOL);
		pros.setProperty("mail.smtp.host", HOST);
		pros.setProperty("mail.smtp.prot", PORT);
		pros.setProperty("mail.smtp.auth", IS_AUTH);
		pros.setProperty("smal.debug", IS_ENABLED_DEBUG_MOD);
	}

	
	/**发送带附件的邮件
	 * @throws Exception */
	public void sendExaMail(String extra, String receiver, String cc,List<String[]> list) throws Exception {
		
		Session session = Session.getDefaultInstance(pros);
		
		//-------------设置邮件基本信息，收发件人等-----start------------------
		MimeMessage message = new MimeMessage(session);//建立一封邮件
		message.setFrom(new InternetAddress(from));//发件人，InternetAddress只有在有网络的情况下才会使用mail
		message.setSubject("用户安装卸载占回传总量的百分比");//主题
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("开始发邮件，时间：" + date);
		System.out.println("收件人：" + receiver);
		String[] emails = receiver.split(",");
		int emailNum = emails.length;
		Address[] adrs = new Address[emailNum];
		for(int i=0; i < emailNum; i++) {
			adrs[i] = new InternetAddress(emails[i]);
		}
	    message.setRecipients(RecipientType.TO, adrs);

	   //抄送
	   if(cc != null && !cc.equals("")){
			System.out.println("开始抄送");
			String[] ccSz = cc.split(",");
			
			String csStr = getMailList(ccSz);
			InternetAddress[] iaToListcs = new InternetAddress().parse(csStr);
			message.setRecipients(Message.RecipientType.CC, iaToListcs);//抄送
			System.out.println("设置抄送完成");
//			message.setRecipients(Message.RecipientType.CC,new InternetAddress[] { new InternetAddress("ouzhou@analysys.com.cn"), new InternetAddress("duyuanhai@analysys.com.cn")});//抄送
			
		}
	    
		message.setSentDate(new Date());//时间
		//-------------设置邮件基本信息，收发件人等-----end------------------
		
		//---------------添加附件，正文内容-------start-------------------------
		MimeMultipart allPart = new MimeMultipart("mixed");
		if(extra != null && !extra.equals("")){
			String[] extraSz = extra.split(",");
			/**发送多个附件*/
			addTach(extraSz, allPart);
//			MimeBodyPart attachment01 = createAttachment(extra);//发送单个附件
//			allPart.addBodyPart(attachment01);
		}
//		MimeBodyPart attachment02 = createAttachment("D:\\javamail\\进度.txt");
		
		String str = "";
		for(int i = 0; i<7; i++){
			str += list.get(i)[0]+" --- "+list.get(i)[1]+"<br/>";
		}
		
		MimeBodyPart content = createContent("<h4>"+"用户安装卸载占回传总量的百分比 : <br />  "
				+ "<br/>"+str+"</h4>");
//		MimeBodyPart content = createContent("<h4>"+"尊敬的用户：<br />  您好！<br />192.168.0.119服务器的distinguish访问异常！"
//				+ "<br/>请尝试访问：192.168.0.119:8080/distinguish/updata"+"</h4>");
		//---------------添加附件，正文内容-------end-------------------------
		
		//-------------把附件和正文保存到邮件中--------start------------
		
//		allPart.addBodyPart(attachment02);
		allPart.addBodyPart(content);//给正文部分添加内容
		message.setContent(allPart);//给邮件添加附件
		message.saveChanges();
		//-------------把附件和正文保存到邮件中--------end------------
		//----------发送邮件-------start-------------
		Transport transport = session.getTransport();
//		transport.connect("no-reply", "reply)#)(");//用户名，密码
		transport.connect("ouzhou", "ozstc!@#$%^");//用户名，密码
		try {
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("邮件发送成功\r");
			success = 0;
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("邮件发送失败");
			success = 1;
		}
		transport.close();
		//----------发送邮件-------end-------------
	}
	
	
	
	
	/**
	 * 根据传入的文件路径创建附件并返回
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public MimeBodyPart createAttachment(String fileName) throws MessagingException, UnsupportedEncodingException{
		MimeBodyPart attachmentPart = new MimeBodyPart();
		
		FileDataSource fds = new FileDataSource(fileName);
		
		System.out.println(fds.getName());
		
		attachmentPart.setDataHandler(new DataHandler(fds));
//		attachmentPart.setFileName(fds.getName());
		/**解决附件名字乱码*/
		attachmentPart.setFileName(MimeUtility.encodeText(fds.getName()));
		
		return attachmentPart;
	}
	
	
	
	 /**  
     * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分  
     */ 
    public MimeBodyPart createContent(String body)  
            throws Exception {  
        // 用于保存最终正文部分  
        MimeBodyPart contentBody = new MimeBodyPart();  
        // 用于组合文本和图片，"related"型的MimeMultipart对象  
        MimeMultipart contentMulti = new MimeMultipart("related");  
 
        // 正文的文本部分  
        MimeBodyPart textBody = new MimeBodyPart();  
        textBody.setContent(body, "text/html;charset=UTF-8");  
        contentMulti.addBodyPart(textBody);  
 
        
 
        // 将上面"related"型的 MimeMultipart 对象作为邮件的正文  
        contentBody.setContent(contentMulti);  
        return contentBody;  
    }
	
	
	/**
	 * 0表示发送成功，1表示发送失败
	 */
	int success ;

	//测试用main
	public static void main(String[] args) throws Exception {
		OZBuildMail2 m = new OZBuildMail2();
//		m.sendExaMail("", "ouzhou@analysys.com.cn", "");
//		m.sendSimpleMail();
//		m.sendExaMail();
		
	}
	
	//---------------方法区--------------------
	//---------------vvvvvv-------------------
	/**
	 *  添加多个附件
	 * @param fileList  附件路径
	 * @param multipart 
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void addTach(String fileList[], Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileList[index]);
			mailArchieve.setDataHandler(new DataHandler(fds));
			mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(),
					"GBK", "B"));
			multipart.addBodyPart(mailArchieve);
		}
	}
	
	/**
	 * 添加多个邮箱地址
	 * @param mailArray
	 * @return
	 */
	private String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}

}























