import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
public class ServidorWebSocket extends WebSocketServer {
	/*Constructor del WebSocket*/
	public ServidorWebSocket(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}
	public ServidorWebSocket(InetSocketAddress address) {
		super(address);
	}
	public ServidorWebSocket(int port, Draft_6455 draft) {
		super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
	}
	/*cuando recibimos un nuevo cliente se lo hacemos saber a todos*/
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		broadcast("Nueva Conexion");
	}
	/*cuando se desconecta un cliente se lo hacemos saber a todos*/
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		broadcast("Un usuario se ha Desconectado.");
	}
	/*cuando recibimos un mensaje se lo hacemos saber a todos*/
	@Override
	public void onMessage(WebSocket conn, String message) {
		broadcast(message);
	}
	@Override
	public void onMessage(WebSocket conn, ByteBuffer message) {
		broadcast(message.array());
	}
	/*cuando hay un error lo mostramos en consola*/
	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
	}
	/*timer de inicio de conexion*/
	@Override
	public void onStart() {
		setConnectionLostTimeout(0);
		setConnectionLostTimeout(100);
	}


	/*si queremos montar un WebService independiente invocamos directamente el Main de la clase*/
	public static void main(String[] args) {
		int port = 8081; 
		try { 
			/*iniciamos el servidor*/
			ServidorWebSocket s = new ServidorWebSocket(port);
			s.start();
			/*si escribimos datos en consola se los enviaremos a los clientes*/
			BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				String in = sysin.readLine();
				s.broadcast(in);
				//si escribo la palabra exit se cerrara la aplicacion
				if (in.equals("exit")) {
					s.stop(1000);
					break;
				}
			}
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
	}


}
