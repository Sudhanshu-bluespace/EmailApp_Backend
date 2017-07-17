package com.bluespacetech.common.util;

import java.nio.file.Path;

public class Base64ToImageDecodeHelper
{

    private String imageIdentifierKey;
    private Path classpathToImage;
    private String replacedText;
    
    public String getReplacedText()
    {
        return replacedText;
    }
    public void setReplacedText(String replacedText)
    {
        this.replacedText = replacedText;
    }
    @Override
    public String toString()
    {
        return "Base64ToImageDecodeHelper [imageIdentifierKey=" + imageIdentifierKey + ", classpathToImage="
                + classpathToImage + ", replacedText=" + replacedText + "]";
    }
    public String getImageIdentifierKey()
    {
        return imageIdentifierKey;
    }
    public void setImageIdentifierKey(String imageIdentifierKey)
    {
        this.imageIdentifierKey = imageIdentifierKey;
    }
    public Path getClasspathToImage()
    {
        return classpathToImage;
    }
    public void setClasspathToImage(Path classpathToImage)
    {
        this.classpathToImage = classpathToImage;
    }
    
}
