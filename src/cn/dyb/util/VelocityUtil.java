package cn.dyb.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;  
import org.apache.velocity.Template;  
import org.apache.velocity.app.Velocity;  
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;  
import org.apache.velocity.exception.ParseErrorException;  
import org.apache.velocity.exception.MethodInvocationException;  

/**
 * @author dyb
 */
public class VelocityUtil {


	/**
	 * @param vmconfig
	 *            模板名字
	 * @param templateName
	 *            模板名字
	 * @param root
	 *            模板根 用于在模板内输出结果集
	 * @param out
	 *            输出对象 具体输出到哪里
	 */
	public static void processTemplate(String templateDirname, String templateName, Map<?, ?> root,
			Writer out) {
		
		Template template = null;  
		VelocityEngine velocityEngine = new VelocityEngine(); 
		Properties p = new Properties();         
        p.setProperty(Velocity.INPUT_ENCODING, "utf-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
		velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "file");  
		velocityEngine.setProperty("file.resource.loader.path", templateDirname);
		//选择要用到的模板  
		try {  
			velocityEngine.init(p);
			template = velocityEngine.getTemplate(templateName, "utf-8");
			VelocityContext context = new VelocityContext(root); 
			velocityEngine.mergeTemplate(templateName, "utf-8",context, out);
		}  
		catch( ResourceNotFoundException rnfe ) {  
		   // couldn't find the template  
		}  
		catch( ParseErrorException pee ) {  
		// syntax error : problem parsing the template  
		}  
		catch( MethodInvocationException mie ) {  
		// something invoked in the template  
		// threw an exception  
		}  
		catch( Exception e ){}  
		
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
