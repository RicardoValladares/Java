import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.*;
public class ServidorWeb extends JFrame implements ActionListener{
	private JLabel label1, label2, label3;
	private JComboBox puertohttp, puertows;
	private JTextField ip;
	private JButton activador;
	private JScrollPane escroll;
	private JTextArea informacion;
	private Thread mutiplesconexiones;
	private ServerSocket serverSocket;
	private Socket socket;
	private ServidorWebSocket serverWS;
	/*interfaz grafica*/
	public ServidorWeb(){
		setLayout (null);
		setTitle("Servidor");
		try{
			label1 = new JLabel("puerto http:");
			puertohttp = new JComboBox(new String[]{"8080", "9090", "1234"});
			puertows = new JComboBox(new String[]{"8081", "9091", "1235"});
			label2 = new JLabel("IP:");
			ip = new JTextField(5);
			ip.setEditable(false);
			label3 = new JLabel("puerto ws:");
			activador = new JButton("Encender");
			informacion = new JTextArea(5, 5);
			escroll = new JScrollPane(informacion);
			activador.setToolTipText("Servidor");
			add(label1);
			add(label2);
			add(label3);
			add(puertohttp);
			add(puertows);
			add(ip);
			add(activador);
			add(escroll);
			label1.setBounds(10, 10, 135, 25);
			label2.setBounds(10, 70, 135, 25);
			label3.setBounds(10, 40, 135, 25);
			puertohttp.setBounds(145, 10, 165, 25);
			puertows.setBounds(145, 40, 165, 25);
			ip.setBounds(145, 70, 165, 25);
			activador.setBounds(10, 105, 300, 25);
			escroll.setBounds(10, 140, 300, 80);
			activador.addActionListener(this);
			determinarip();
		}catch(Exception e){System.exit(0);}
	}
	public void actionPerformed(ActionEvent ae){
		/*ON/OFF SERVER*/
		if(ae.getSource()==activador){
			int puertoweb = Integer.parseInt((String)puertohttp.getSelectedItem());
			int puertowebsocket = Integer.parseInt((String)puertows.getSelectedItem());
			/*Encender Servidor*/
			if(activador.getText().equals("Encender")){
				try{
					/*Conexion WS*/
					serverWS = new ServidorWebSocket(puertowebsocket);
					serverWS.start();
					/*Conexion HTTP*/
					serverSocket = new ServerSocket(puertoweb);
					informacion.setText("Servidor Encendido \n");
					mutiplesconexiones = new Thread("Hilos de procesos"){
					public void run(){
						do{
							activador.setText("Apagar");
							puertohttp.setEnabled(false);
							puertows.setEnabled(false);
							try{
								/*Recivimos conexion web*/
								socket=serverSocket.accept();
								BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
								DataOutputStream salida = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
								String peticion = entrada.readLine();
								informacion.append("\n"+peticion);
								/*Respondemos el html*/
								try{
										String html = "<html>\n\t<head>\n\t\t<meta charset='utf-8' />\n\t\t<style>\n\t\t\t*{ margin:0; padding:0; border:0; list-style:none; text-decoration:none; outline:none; font-weight:inherit; font-style:inherit; line-height:1; font-family:inherit; vertical-align:baseline; }\n\t\t\tbody{ background-color: #eaf0f7; }\n\t\t\ttextarea{ font-family: monospace; border: 1px solid #000000; outline:0; }\n\t\t\tinput[type=text]{ height: 20px; font-family: monospace; border: 1px solid #000000; outline:0; }\n\t\t\tinput[type=button]{ font-weight: bold; font-family: monospace; text-align: center; height: 25px; border: 1px solid #7a8a99;  outline:0; background: #e9f0f7; background: -moz-linear-gradient(top, #e9f0f7 0%, #fdfefe 37%, #bdd3e7 100%); background: -webkit-gradient(left top, left bottom, color-stop(0%, #e9f0f7), color-stop(37%, #fdfefe), color-stop(100%, #bdd3e7)); background: -webkit-linear-gradient(top, #e9f0f7 0%, #fdfefe 37%, #bdd3e7 100%); background: -o-linear-gradient(top, #e9f0f7 0%, #fdfefe 37%, #bdd3e7 100%); background: -ms-linear-gradient(top, #e9f0f7 0%, #fdfefe 37%, #bdd3e7 100%); background: linear-gradient(to bottom, #e9f0f7 0%, #fdfefe 37%, #bdd3e7 100%); filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e9f0f7', endColorstr='#bdd3e7', GradientType=0 ); }\n\t\t\tinput[type=button]:hover{ border: 2px solid #7a8a99; }\n\t\t\tinput[type=button]:active{ border: 2px solid #7a8a99; background: #b8cfe5; }\n\t\t\t#centrar{ background-color: #eeeeee; position: absolute; left: 50%; top: 50%; transform: translate(-50%, -50%); -webkit-transform: translate(-50%, -50%); border-style: solid; border-width: 5px 5px 5px 4px; -moz-border-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANwAAACFCAYAAAAw25a+AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAhRJREFUeJzt1yFOXFEAhtFL8wQ7YAesY1ZQg0IjuoMKyJiKClgBCX7UGDZQzRYQ+NGYcYMhZGhSV76XzJyj7s01v/mS906ub+93m+35AL7W2enzmMYY42G5mHkKHL6bu/fgnl5e594CR+Hb3APgmEz7l8f1aq4dcLC+X1x+nKe/H3///HGSroEDdn17v9u/+6SEkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgNO1fNtvzcfXrz26uMXBoNtvP90/BPSwX4RQ4Dk8vrx/n6V8PwP/nHw5C0xhjPK5Xc++Ao/AGrxYip2Y3mPoAAAAASUVORK5CYII=) 5 5 5 4 repeat; -webkit-border-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANwAAACFCAYAAAAw25a+AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAhRJREFUeJzt1yFOXFEAhtFL8wQ7YAesY1ZQg0IjuoMKyJiKClgBCX7UGDZQzRYQ+NGYcYMhZGhSV76XzJyj7s01v/mS906ub+93m+35AL7W2enzmMYY42G5mHkKHL6bu/fgnl5e594CR+Hb3APgmEz7l8f1aq4dcLC+X1x+nKe/H3///HGSroEDdn17v9u/+6SEkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgNO1fNtvzcfXrz26uMXBoNtvP90/BPSwX4RQ4Dk8vrx/n6V8PwP/nHw5C0xhjPK5Xc++Ao/AGrxYip2Y3mPoAAAAASUVORK5CYII=) 5 5 5 4 repeat; -o-border-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANwAAACFCAYAAAAw25a+AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAhRJREFUeJzt1yFOXFEAhtFL8wQ7YAesY1ZQg0IjuoMKyJiKClgBCX7UGDZQzRYQ+NGYcYMhZGhSV76XzJyj7s01v/mS906ub+93m+35AL7W2enzmMYY42G5mHkKHL6bu/fgnl5e594CR+Hb3APgmEz7l8f1aq4dcLC+X1x+nKe/H3///HGSroEDdn17v9u/+6SEkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgNO1fNtvzcfXrz26uMXBoNtvP90/BPSwX4RQ4Dk8vrx/n6V8PwP/nHw5C0xhjPK5Xc++Ao/AGrxYip2Y3mPoAAAAASUVORK5CYII=) 5 5 5 4 repeat; border-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAANwAAACFCAYAAAAw25a+AAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAOxAAADsQBlSsOGwAAAhRJREFUeJzt1yFOXFEAhtFL8wQ7YAesY1ZQg0IjuoMKyJiKClgBCX7UGDZQzRYQ+NGYcYMhZGhSV76XzJyj7s01v/mS906ub+93m+35AL7W2enzmMYY42G5mHkKHL6bu/fgnl5e594CR+Hb3APgmEz7l8f1aq4dcLC+X1x+nKe/H3///HGSroEDdn17v9u/+6SEkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgJDgICQ5CgoOQ4CAkOAgJDkKCg5DgICQ4CAkOQoKDkOAgNO1fNtvzcfXrz26uMXBoNtvP90/BPSwX4RQ4Dk8vrx/n6V8PwP/nHw5C0xhjPK5Xc++Ao/AGrxYip2Y3mPoAAAAASUVORK5CYII=) 5 5 5 4 fill repeat; }\n\t\t</style>\n\t\t<script type='text/javascript'>\n\t\t\t/*variables globales para la conexion de envio y recepcion*/\n\t\t\tvar ws;\n\t\t\t/*funcion para conectar y escuchar al WebSocket*/\n\t\t\tfunction conectar() {\n\t\t\t\tdocument.getElementById('chat').value = document.getElementById('chat').value + '\\n\\nConectando...';\n\t\t\t\tdocument.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight;\n\t\t\t\tws = new WebSocket('ws://"+ip.getText()+":"+puertowebsocket+"');\n\t\t\t\t/*cuando se apertura la conexion*/\n\t\t\t\tws.onopen = function() {\n\t\t\t\t\tdocument.getElementById('chat').value = document.getElementById('chat').value + '\\n\\nConnectado.';\n\t\t\t\t\tdocument.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight;\n\t\t\t\t};\n\t\t\t\t/*cuando el servidor envia datos*/\n\t\t\t\tws.onmessage = function (evento) { \n\t\t\t\t\tvar mensaje = evento.data;\n\t\t\t\t\tdocument.getElementById('chat').value = document.getElementById('chat').value + '\\n\\n' +mensaje;\n\t\t\t\t\tdocument.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight;\n\t\t\t\t};\n\t\t\t\t/*cuando se cierra la conexion*/\n\t\t\t\tws.onclose = function() { \n\t\t\t\t\tdocument.getElementById('chat').value = document.getElementById('chat').value + '\\n\\nDesconectado.';\n\t\t\t\t\tdocument.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight; \n\t\t\t\t\twindow.location.replace('http://"+ip.getText()+":"+puertoweb+"');\n\t\t\t\t};\n\t\t\t}\n\t\t\t/*funcion para enviar al WebSocket*/\n\t\t\tfunction enviar(){\n\t\t\t\tvar nombre=document.getElementById('nombre').value;\n\t\t\t\tvar mensaje=document.getElementById('mensaje').value;\n\t\t\t\tif(nombre!='' && mensaje!=''){ ws.send(nombre+': '+mensaje); }\n\t\t\t}\n\t\t</script>\n\t</head>\n\t<body onload='conectar()'>\n\t\t<div id='centrar'>\n\t\t\t<center><br><br>\n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;Usuario: <input type='text' id='nombre'>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>\n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;Mensaje: <input type='text' id='mensaje'>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>\n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' onclick='enviar()' value='enviar mensaje'>&nbsp;&nbsp;&nbsp;&nbsp;<br><br>\n\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;<textarea id='chat' rows='30' cols='50' readonly='readonly'></textarea>&nbsp;&nbsp;&nbsp;&nbsp;<br><br><br>\n\t\t\t</center>\n\t\t</div>\n\t</body>\n</html>";
        								DataInputStream transmision = new DataInputStream(new ByteArrayInputStream(html.getBytes()));
										int dimension = transmision.available();
										String mimeType="text/html";
										String header = "HTTP/1.0 200 OK\n"+"Allow: GET\n"+"MIME-Version: 1.0\n"+"Server : HMJ Basic HTTP Server\n"+"Content-Type: "+mimeType+"\n"+"Content-Length: "+dimension+"\n\n";
										salida.writeBytes(header);
										int i, bytes = 0;
										while((i=transmision.read()) != -1){
											salida.writeByte(i);
											bytes++;
										}
										salida.flush();
										salida.close();
										informacion.append("\ntext/html");
								}catch(Exception x){
									salida.writeBytes("HTTP/1.0 404 Not Found\r\n"+"Content-type: text/html\r\n\r\n"+"<HTML><HEAD><LINK REL='icon' TYPE='image/png' HREF='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABHNCSVQICAgIfAhkiAAAMhNpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDQuMS1jMDM0IDQ2LjI3Mjk3NiwgU2F0IEphbiAyNyAyMDA3IDIyOjExOjQxICAgICAgICAiPgogICA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogICAgICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIgogICAgICAgICAgICB4bWxuczp4YXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iPgogICAgICAgICA8eGFwOkNyZWF0b3JUb29sPkFkb2JlIEZpcmV3b3JrcyBDUzM8L3hhcDpDcmVhdG9yVG9vbD4KICAgICAgICAgPHhhcDpDcmVhdGVEYXRlPjIwMDgtMDYtMjRUMjE6NTA6MjNaPC94YXA6Q3JlYXRlRGF0ZT4KICAgICAgICAgPHhhcDpNb2RpZnlEYXRlPjIwMDktMDEtMDRUMTE6NDU6MDhaPC94YXA6TW9kaWZ5RGF0ZT4KICAgICAgPC9yZGY6RGVzY3JpcHRpb24+CiAgICAgIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiCiAgICAgICAgICAgIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyI+CiAgICAgICAgIDxkYzpmb3JtYXQ+aW1hZ2UvcG5nPC9kYzpmb3JtYXQ+CiAgICAgIDwvcmRmOkRlc2NyaXB0aW9uPgogICA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgIAo8P3hwYWNrZXQgZW5kPSJ3Ij8+FOs60wAAAAlwSFlzAAALEgAACxIB0t1+/AAAABx0RVh0U29mdHdhcmUAQWRvYmUgRmlyZXdvcmtzIENTM5jWRgMAAAAVdEVYdENyZWF0aW9uIFRpbWUANi8yNC8wOIHGW3UAAAQRdEVYdFhNTDpjb20uYWRvYmUueG1wADw/eHBhY2tldCBiZWdpbj0iICAgIiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDQuMS1jMDM0IDQ2LjI3Mjk3NiwgU2F0IEphbiAyNyAyMDA3IDIyOjExOjQxICAgICAgICAiPgogICA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogICAgICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIgogICAgICAgICAgICB4bWxuczp4YXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iPgogICAgICAgICA8eGFwOkNyZWF0b3JUb29sPkFkb2JlIEZpcmV3b3JrcyBDUzM8L3hhcDpDcmVhdG9yVG9vbD4KICAgICAgICAgPHhhcDpDcmVhdGVEYXRlPjIwMDgtMDYtMjRUMjE6NTA6MjNaPC94YXA6Q3JlYXRlRGF0ZT4KICAgICAgICAgPHhhcDpNb2RpZnlEYXRlPjIwMDgtMDYtMjRUMjE6NTI6NTRaPC94YXA6TW9kaWZ5RGF0ZT4KICAgICAgPC9yZGY6RGVzY3JpcHRpb24+CiAgICAgIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiCiAgICAgICAgICAgIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyI+CiAgICAgICAgIDxkYzpmb3JtYXQ+aW1hZ2UvcG5nPC9kYzpmb3JtYXQ+CiAgICAgIDwvcmRmOkRlc2NyaXB0aW9uPgogICA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIGPlT5MAAAHPSURBVDiNnY9vK0NhGMafT8An8AHkrS/htbzBhC1ZVlhJ1oQItcxmmLND2Go4bGZ2tsOYjcb8yfythSTeKkyRlMt5DtnWzhR33T099339rud6iPMCGFs9BxNKYEKm2Y0LjIbvMmbMd3sSTyDWlTP8t5iVExAzf/i7yteN9ymV7MrsPwIZ9OzlZD/CFjzri/ERtQLbTNaesqTfvSsLv/k7kdQW4HW8HC+mUlkDypJebkvW4L42Hy/Tajy2FOVMSFnS4diUXT62FSI524rL6rycBpQlOntQdvngaELS0SgleWarZTWUJVqWzxh2chvocobhOr7FtTCJm1E1Dtws5qNX0rzPlUpMWaIZWcow0DA8VEPLYrwQ9I4IOrgYehai8MbimOQPUKKzpbQiS+osrqxodZZlaKw8mm1+6Wy3r8EdjqNh2Ae7sJ+mc4EoDVz236aCqDQsQjsegHLIg/7ZCLyReJaOsqTKNPMzaLQJ0E+vgw3G0MQGpNfrR3gMe3Zweh6C1bsNezCVgLKkwuj8GShMPpQZ3FCIUdWsgIYxQTQIQDHgRaVxCTrRPL0oS5TGBel/fy3KUJbM7SZQM8iJr391jZGTFirzfEanaySdeKfsJ/qlaVs4p/5rAAAAAElFTkSuQmCC'><TITLE>Servidor JAVA</TITLE><META NAME='viewport' CONTENT='width=device-width, initial-scale=1'></HEAD><BODY BGCOLOR='#fff' TEXT='#6382bf'><H2>ERROR</H2></BODY></HTML>\n");
									salida.flush();
									salida.close();
								}
							}catch(Exception x){ informacion.append("\n"+x); }
						}while(true);
					}
				};
				mutiplesconexiones.start();
				}catch(Exception e2){
					activador.setText("Encender");
					puertohttp.setEnabled(true);
					puertows.setEnabled(true);
					activador.setSelected(false);
					JOptionPane.showMessageDialog(this,"Error en puerto:"+puertoweb+" o en puerto:"+puertowebsocket+" ocupado","ERROR",0);
				}
			}
			/*Apagar el Servidor*/
			else if(activador.getText().equals("Apagar")){
				try{
					activador.setText("Encender");
					puertohttp.setEnabled(true);
					puertows.setEnabled(true);
					serverWS.stop(1000);
					mutiplesconexiones.stop();
					serverSocket.close();
					socket.close();
					informacion.append("\n\nServidor Apagado");
				}catch(Exception e3){}
			}
		}
	}
	
	/*Determinar IP*/
	public void determinarip() throws Exception{
		String IP = null;
		Enumeration e = NetworkInterface.getNetworkInterfaces();
		while(e.hasMoreElements()){
			NetworkInterface n = (NetworkInterface) e.nextElement();
			Enumeration ee = n.getInetAddresses();
			while(ee.hasMoreElements()){
				InetAddress i = (InetAddress) ee.nextElement();
				String colecta = (String) i.getHostAddress();
				if(colecta.contains(".")){
					String[] parts = colecta.split(Pattern.quote("."));
					//validamos los rangos de ip de redes privadas:
					//		IP CLASE A				IP CLASE B				IP CLASE C
					if(parts[0].equals("10") || parts[0].equals("172") || parts[0].equals("192")){
						IP = colecta;
					}
				}
			}
		}
		if(IP == null){ IP = "127.0.0.1"; }
		ip.setText(String.valueOf(IP));
	}
	
	/*Metodo Principal*/
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		ServidorWeb interfaz = new ServidorWeb();
		interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaz.setSize(330, 270);
		interfaz.setResizable(false);
		interfaz.setLocationRelativeTo(null);
		interfaz.setVisible(true);
	}
}
