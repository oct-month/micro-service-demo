package cn.sun.user.edge.thrift;

import cn.sun.message.api.MessageService;
import cn.sun.user.api.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider
{
	@Value("${thrift.user.ip}")
	private String userServerIp;
	@Value("${thrift.user.port}")
	private int userServerPort;

	@Value("${thrift.message.ip}")
	private String messageServerIp;
	@Value("${thrift.message.port}")
	private int messageServerPort;


	public UserService.Client getUserService()
	{
		return getService(userServerIp, userServerPort, UserService.Client.class);
	}

	public MessageService.Client getMessageService()
	{
		return getService(messageServerIp, messageServerPort, MessageService.Client.class);
	}

	private <T> T getService(String ip, int port, Class<T> service)
	{
		T client = null;
		TSocket socket = new TSocket(ip, port, 5000);
		TTransport transport = new TFramedTransport(socket);
		try {
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			client = service.getConstructor(TProtocol.class).newInstance(protocol);
		} catch (Exception e) {
			e.printStackTrace();
			client = null;
		}
		return client;
	}
}
