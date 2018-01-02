package com.bluespacetech.contact.fileupload.batch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class UploadContactStepExecutionListener
  implements StepExecutionListener
{
  public ExitStatus afterStep(StepExecution stepExecution)
  {
    return stepExecution.getExitStatus();
  }
  
  public void beforeStep(StepExecution arg0) {}
}
