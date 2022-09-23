package io;

import util.interfaces.Serializer;

import java.io.*;

public class AppStateManager {
    private static String filePath;
    private static ObjectOutputStream outState;
    private static ObjectInputStream inState;



    private AppStateManager(){
    }

    public static void setPath(String path) {
        filePath = path;
    }

    //Serialize
    public static<T> void serialize(T obj){
        try{
//            File file = new File(filePath);
//            file.createNewFile();
            FileOutputStream stateFile = new FileOutputStream(filePath);
            outState = new ObjectOutputStream(stateFile);
            outState.writeObject(obj);
            outState.close();
            stateFile.close();
        } catch(IOException e){
            e.printStackTrace();
        } finally {

        }
    }

    //Deserialize
    public static<T> T deserialize(){
        try{
            FileInputStream stateFile = new FileInputStream(filePath);
            inState = new ObjectInputStream(stateFile);
            T obj = (T) inState.readObject();
            inState.close();
            stateFile.close();
            return obj;
        } catch(IOException e){
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            return null;
        }
    }
}
