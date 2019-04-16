package edu.umich.icpsr.c2metadata.camel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import edu.umich.icpsr.c2metadata.model.C2MFile;
import edu.umich.icpsr.c2metadata.service.C2MetadataService;
import edu.umich.icpsr.commons.dao.BusinessObjectDAO;
import edu.umich.icpsr.commons.model.sync.FedoraSyncHandler;

@Component
public class C2MetadataListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(C2MetadataListener.class);

	@Autowired
	@Qualifier("c2metadataService")
	private C2MetadataService c2metadataService;
	@Autowired
	@Qualifier("archonnexDAO")
	private BusinessObjectDAO archonnexDAO;

	public void onMessage(Message message) {
		LOG.info("Executing onMessage.");
		try {
			ActiveMQMessage msg = (ActiveMQMessage) message;
			String codePath = msg.getProperty("CODE_PATH").toString();
			System.err.println(msg.getProperty("DDI_PATH"));
			// msg.getProperty("DDI_PATH")==null?"":msg.getProperty("DDI_PATH").toString();
			String email = msg.getProperty("EMAIL").toString();
			String mode = msg.getProperty("MODE").toString();
			String type = msg.getProperty("TYPE").toString();
			String fileDetails =msg.getProperty("FILE_LIST").toString();
			c2metadataService.endToEnd(codePath,"",email,mode,type,getFileDetails(fileDetails));
		} catch (Exception e) {
			LOG.error("", e);
		} finally {
			FedoraSyncHandler.getCurrent().commit();
		}
	}
private List<C2MFile> getFileDetails(String ids){
	List<C2MFile> fileList = new ArrayList<>();
	StringTokenizer tokenizer= new StringTokenizer(ids, "-");
	
	while(tokenizer.hasMoreTokens()){
		Integer id = Integer.valueOf(tokenizer.nextToken());
		C2MFile file=archonnexDAO.findById(C2MFile.class, id);
		fileList.add(file);
	}
	return fileList;
}

}