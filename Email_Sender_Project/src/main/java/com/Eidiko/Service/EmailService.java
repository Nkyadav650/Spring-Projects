package com.Eidiko.Service;

import com.Eidiko.Entity.EmailRequest;
import com.Eidiko.Entity.MailCredentials;

public interface EmailService {

	public void sendEmailWithAttachment(EmailRequest emailRequest);

	public MailCredentials createMailCredentails(MailCredentials credentials);

	public MailCredentials getMailCredentialsByEmail(String email);
}
