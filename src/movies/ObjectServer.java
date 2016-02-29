package movies;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ObjectServer {
	final static String filePathName = "demo.ser";
	ServerMode mode = ServerMode.LOAD;
	static FileOutputStream fos;
	static ObjectOutputStream oos;

	public void runServer() {
		System.out.printf("[SERVER][INFO] - Startig server.\n[SERVER][INFO] - Actual server mode: " + mode + "\n");
		try {
			ServerSocket serverSocket = new ServerSocket(81);
			Socket socket = serverSocket.accept();
			System.out.println("[SERVER][INFO] - Client connected");

			ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
			
			while (true) {
				if (fromClient.read() > -1) {
					Object objectFromClient = fromClient.readObject();
					if (objectFromClient instanceof Command && ((Command) objectFromClient) == Command.EXIT) {
						System.out.println("[SERVER][CMD] - Clients & server shut down.");
						break;
					} else if (objectFromClient instanceof Command && ((Command) objectFromClient) == Command.GET) {
						mode = ServerMode.LOAD;
						System.out.println("[SERVER][CMD] - Getting objects from file.");
						//deserialize();
						System.out.println("[SERVER][INFO] - Objects load to the client side.");
						//load();
					} else if (objectFromClient instanceof Command && ((Command) objectFromClient) == Command.PUT) {
						mode = ServerMode.SAVE;
						System.out.println("[SERVER][CMD] - Actual server mode: " + mode);
					} else if (mode == ServerMode.SAVE) {
						save(objectFromClient);
						System.out.println("[SERVER][INFO] - Objects saved.");
					}
				}
			}
			serverSocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void deserialize() {
		Person deserializedPerson = null;
		try {
			FileInputStream file = new FileInputStream(filePathName);
			ObjectInputStream buffer = new ObjectInputStream(file);
			deserializedPerson = (Person) buffer.readObject();
			buffer.close();
			file.close();
			System.out.println(deserializedPerson);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public static void save(Object o) {
		try {
			fos = new FileOutputStream(filePathName, true);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ServerMode getMode() {
		return mode;
	}

	public void setMode(ServerMode mode) {
		this.mode = mode;
	}
	
	public static List<Object> load(){
		List<Object> objectList = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(filePathName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object oneObject = ois.readObject();
			while(ois != null){
				try {
					objectList.add(oneObject);
					System.out.println(oneObject);
					continue;
					} 
				catch (Exception e) {
					e.printStackTrace();
					}
				ois.close();
				fis.close();
				System.out.println(objectList);	
			}
		}
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }
		System.out.println(objectList);
		return objectList;
	}

	public static void main(String[] args) {
		ObjectServer server = new ObjectServer();
		server.runServer();
	}

	@Override
	public String toString() {
		return "";
	}
}