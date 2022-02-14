# zer.mail :mailbox:
#### A small module for conveniently sending emails.

## Example of usage :bird:

Main.java
```java
import zer.mail.MailClient;

public class Main
{
  public static void main(String[] args)
  {
    new MailClient()
      .from("<sender_email>", "<sender_password>")
      .to("<receiver_email>")
      .message("Example subject", "<h1>Example content</h1>")
      .contentType("text/html")
      .send();
  }
}
```
