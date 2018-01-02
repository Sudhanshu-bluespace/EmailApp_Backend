package com.bluespacetech.notifications.email.batch;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class EmailContactGroupItemReader
  implements ItemReader<EmailContactGroupVO>
{
  private int contactCount;
  private List<EmailContactGroupVO> contactListVO;
  
  public EmailContactGroupItemReader(List<EmailContactGroupVO> contactListVO)
  {
    this.contactListVO = contactListVO;
    this.contactCount = 0;
  }
  
  public EmailContactGroupVO read()
    throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException
  {
    EmailContactGroupVO contact = null;
    if ((this.contactListVO != null) && (!this.contactListVO.isEmpty()) && (this.contactCount < this.contactListVO.size()))
    {
      contact = (EmailContactGroupVO)this.contactListVO.get(this.contactCount);
      this.contactCount += 1;
    }
    return contact;
  }
}
