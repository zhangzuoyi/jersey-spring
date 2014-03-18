package net.jawp.jerseytest.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.jawp.jerseytest.Person;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.StreamDataBodyPart;
import org.junit.Test;

public class ClientTest {
	@Test
	public void upload() throws FileNotFoundException{
		InputStream stream = new FileInputStream("/home/zhangzuoyi/protobuf.buf");

	    ClientConfig clientConfig = new ClientConfig();
	    clientConfig.register(MultiPartFeature.class);
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://localhost:8080/jerseytest/jt/upload");
//		FileDataBodyPart bodyPart=new FileDataBodyPart("file",new File("/home/zhangzuoyi/protobuf.buf"));
		StreamDataBodyPart bodyPart=new StreamDataBodyPart("file",stream,"protobuf.buf");
		         
		FormDataMultiPart part=new FormDataMultiPart();
		part.bodyPart(bodyPart);
		String result=webTarget.request(MediaType.TEXT_PLAIN).post(Entity.entity(part, part.getMediaType()),String.class);
	    System.out.println(result);
	}
	@Test
	public void hello(){
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://localhost:8080/jerseytest/jt/person");

		Builder request = webTarget.request(MediaType.APPLICATION_XML);

		Response response = request.get();

		Person p = response.readEntity(Person.class);
		System.out.println(p.getName());
		System.out.println(p.getSex());
		System.out.println(p.getWeight());
		System.out.println(p.getBirthday());
	}
}
