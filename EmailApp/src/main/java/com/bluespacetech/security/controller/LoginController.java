package com.bluespacetech.security.controller;

import com.bluespacetech.core.controller.AbstractBaseController;
import java.io.PrintStream;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
  extends AbstractBaseController
{
  @RequestMapping(value={"/authenticate"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public void login(@RequestBody Credentials credentials)
  {
    System.out.println("Inside authenticate");
  }
}
