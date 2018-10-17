package telran.post.configoration;

import java.util.Base64;

import org.hibernate.validator.constraints.SafeHtml.Attribute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
@Configuration
@ManagedResource
public class AccountConfigoration {
	@Value("${exp.value}")
	int expPeriod;
	
	
	@ManagedAttribute
	public int getExpPeriod() {
		return expPeriod;
	}


	@ManagedAttribute
	public void setExpPeriod(int expPeriod) {
		this.expPeriod = expPeriod;
	}



	public AccountUserCredential tokenDecode(String auth)
	{
		//FIXME
		int pos=auth.indexOf(" ");
		String token=auth.substring(pos+1);
	byte[] decodeByte=Base64.getDecoder().decode(token);
	String credential=new String(decodeByte);
	String [] credentials=credential.split(":");
	return new AccountUserCredential(credentials[0],credentials[1]);
	}
}
