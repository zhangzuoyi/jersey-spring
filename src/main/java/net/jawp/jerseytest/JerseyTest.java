package net.jawp.jerseytest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("jt")
public class JerseyTest {
	@Path("person")
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public Person getPerson() {
		Person p=new Person();
		p.setBirthday(new Date());
		p.setName("张三");
		p.setSex("M");
		p.setWeight(60);
		return p;
    }
	@Path("hello/{name}")
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
		return "Hello "+name;
    }
	@Path("upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String upload(@FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileInfo) {
		File f=new File("/home/zhangzuoyi/tmp/",fileInfo.getFileName());
		try {
			FileOutputStream out=new FileOutputStream(f);
			byte[] b=new byte[1024];
			int len=file.read(b);
			while(len!=-1){
				out.write(b, 0, len);
				len=file.read(b);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Success";
    }
}
