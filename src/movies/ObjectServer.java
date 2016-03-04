package movies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {
	ServerMode mode = ServerMode.SAVE;
	static FileOutputStream fos;
	static ObjectOutputStream oos;
	Socket socket;

	public void runServer() {
		System.out.printf("[SERVER] - Startig server.\n[SERVER] - Actual server mode: " + mode + "\n");
		try {
			ServerSocket serverSocket = new ServerSocket(811);
			socket = serverSocket.accept();
			System.out.println("[SERVER] - Client connected");

			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			while (true) {
					Command objectOis = (Command) ois.readObject();
					if (objectOis.equals(Command.PUT)) {
						mode = ServerMode.SAVE;
						getMode();
						System.out.println("[SERVER] - Actual server mode: " + mode);
					}
					else if (objectOis.equals(Command.GET)) {
						mode = ServerMode.LOAD;
						getMode();
						System.out.println("[SERVER] - Getting objects from file.");
						System.out.println("[SERVER] - Objects load to the client side.");
					}
					else if (objectOis.equals(Command.EXIT)) {
						System.out.println("[SERVER] - Clients & server shut down.");
						break;
					} 
				}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Product> deserialize() {
		List<Product> deserializedList = new ArrayList<>();	
		 try {
	         FileInputStream fis = new FileInputStream("c:\\savedFiles\\files.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         deserializedList = (List<Product>) ois.readObject();
	         ois.close();
	         fis.close();
	         System.out.println(deserializedList);
	     }
		 catch(IOException i) {
	         i.printStackTrace();
	     }
		 catch(ClassNotFoundException c) {
	         System.out.println("Person class not found.");
	         c.printStackTrace();
	     }
		 return deserializedList;
	}

	public void serialize(List<Product> productList){
		try {
			File file = new File("c:\\savedFiles\\files.ser");
			FileOutputStream fos = new  FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(productList);
			oos.close();
			fos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			@SuppressWarnings("unchecked")
			List<Product> productList = (List<Product>) ois.readObject();
			serialize(productList);
			ois.close();
			System.out.println("Serialized data is saved at C:\\savedfiles\\files.ser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getMode() {
		switch (mode) {
		case LOAD:
			load();
			break;
		case SAVE:
			save();
			break;
		default:
			break;
		}
	}
	
	public void load(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			List<Product> productList = deserialize();
			oos.writeObject(productList);
			oos.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ObjectServer server = new ObjectServer();
		server.runServer();
	}
}
