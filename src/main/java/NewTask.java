import java.io.IOException;
import java.util.StringJoiner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {
	private static final String QUEUE_NAME = "task_queue";
	
	public static void main(String [] argv) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
		String message = getMessage(argv);
		
		channel.basicPublish("", QUEUE_NAME, 
				MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
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
