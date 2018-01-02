package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ContactGroupEmailRowMapper
  implements RowMapper<EmailContactGroupVO>
{
  private String message;
  private String subject;
  private Long emailId;
  
  public EmailContactGroupVO mapRow(ResultSet rs, int rowNum)
    throws SQLException
  {
    EmailContactGroupVO emailContactGroupVO = new EmailContactGroupVO();
    emailContactGroupVO.setContactFirstName(rs.getString("FIRST_NAME"));
    emailContactGroupVO.setContactLastName(rs.getString("LAST_NAME"));
    emailContactGroupVO.setContactEmail(rs.getString("EMAIL"));
    emailContactGroupVO.setMessage(getMessage());
    emailContactGroupVO.setSubject(getSubject());
    emailContactGroupVO.setEmailId(getEmailId());
    emailContactGroupVO.setGroupId(Long.valueOf(rs.getLong("GROUP_ID")));
    emailContactGroupVO.setContactId(Long.valueOf(rs.getLong("CONTACT_ID")));
    return emailContactGroupVO;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public void setMessage(String message)
  {
    this.message = message;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public Long getEmailId()
  {
    return this.emailId;
  }
  
  public void setEmailId(Long emailId)
  {
    this.emailId = emailId;
  }
}
