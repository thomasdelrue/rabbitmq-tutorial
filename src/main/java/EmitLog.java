import java.io.IOException;

import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {
	
	private static final String EXCHANGE_NAME = "logs";
	
	public static void main(String [] args) throws IOException {
		
		ConnectionFactory factory = new ConnectionFactory();
	}

}
