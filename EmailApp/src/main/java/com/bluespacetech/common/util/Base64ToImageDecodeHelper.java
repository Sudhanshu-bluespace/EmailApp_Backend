package com.bluespacetech.common.util;

import java.nio.file.Path;

public class Base64ToImageDecodeHelper
{
  private String imageIdentifierKey;
  private Path classpathToImage;
  private String replacedText;
  
  public String getReplacedText()
  {
    return this.replacedText;
  }
  
  public void setReplacedText(String replacedText)
  {
    this.replacedText = replacedText;
  }
  
  public String toString()
  {
    return "Base64ToImageDecodeHelper [imageIdentifierKey=" + this.imageIdentifierKey + ", classpathToImage=" + this.classpathToImage + ", replacedText=" + this.replacedText + "]";
  }
  
  public String getImageIdentifierKey()
  {
    return this.imageIdentifierKey;
  }
  
  public void setImageIdentifierKey(String imageIdentifierKey)
  {
    this.imageIdentifierKey = imageIdentifierKey;
  }
  
  public Path getClasspathToImage()
  {
    return this.classpathToImage;
  }
  
  public void setClasspathToImage(Path classpathToImage)
  {
    this.classpathToImage = classpathToImage;
  }
}
