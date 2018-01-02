package com.bluespacetech.notifications.email.util;

public enum EmailServerPropertyValueTypeConstant
{
  BOOLEAN("boolean"),  STRING("string"),  NUMBER("number");
  
  private String label;
  
  private EmailServerPropertyValueTypeConstant(String label)
  {
    this.label = label;
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public void setLabel(String label)
  {
    this.label = label;
  }
}
