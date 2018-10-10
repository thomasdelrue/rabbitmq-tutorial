import java.io.IOException;
import java.util.StringJoiner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
	
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String [] args) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		String message = getMessage(args);
		
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println(" [x] Sent '"+ message +"'");
		
		channel.close();
		connection.close();
		
	}
	
	private static String getMessage(String [] strings) {
		if (strings.length < 1)
			return "Hello World!";
		StringJoiner sj = new StringJoiner(" ");
		for (String s : strings)
			sj.add(s);
		return sj.toString();
	}	

}
