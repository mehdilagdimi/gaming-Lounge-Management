package io;

import java.io.*;

public class AppStateManager<T> {
    private static String filePath;
    private static ObjectOutputStream outState;
    private static ObjectInputStream inState;

    public T obj = null;

    private AppStateManager(){
    }

    public static void setPath(String path) {
        filePath = path;
    }

    //Serialize
    public static void serialize(T obj){
        try{
            FileOutputStream stateFile = new FileOutputStream(filePath);
            outState = new ObjectOutputStream(stateFile);
            outState.writeObject(obj);
            outState.close();
            stateFile.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //Deserialize
    public static void deserialize(){
        try{
            FileInputStream stateFile = new FileInputStream(filePath);
            inState = new ObjectInputStream(stateFile);
            obj = (T) inState.readObject();
            inState.close();
            stateFile.close();
//            return obj;
        } catch(IOException e){
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
