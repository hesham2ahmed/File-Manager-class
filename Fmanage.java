    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airlineapplication;


import java.io.*;
import java.util.*;



/**
 *
 * @author hesham
 */
public class Fmanage implements Serializable {

 
    
 // to append username@password in text file \\correct;
     public boolean  WriteInTextFile(String E_mail,String password,String id, String FilePath) {
         String data = E_mail+"#"+password+"#"+id+"#"+"true";
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(new FileWriter(new File(FilePath),true));
            writter.println(data);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            writter.close();
        }
    }
     
     
     
     public boolean WriteInTextFileFlights(String from,String to,String Movetime,String btime,String distance,String price,String id,String path){
           String data = from+"#"+to+"#"+Movetime+"#"+btime+"#"+distance+"#"+price+"#"+id;
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(new FileWriter(new File(path),true));
            writter.println(data);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            writter.close();
        }
     }
     
     
     
// write in text file without append \\ correct;
      public  boolean  WriteInTxtFileWithoutAppend(String E_mail,String password,String id, String FilePath) {
         String data = E_mail+"#"+password+"#"+id+"true";
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(new FileWriter(new File(FilePath),false));
            writter.println(data);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            writter.close();
        }
    }
      
      
      
//read all line in text file and return it in ArraYList of String with #\\correct
      public  ArrayList<String> ReadAllLines(String path){
             ArrayList<String> Infile =new ArrayList<>();
             Scanner Reader = null;        
        try {         
            Reader = new Scanner(new File(path));}
  
        catch (FileNotFoundException e) {
        return null;}
         while (Reader.hasNext()) {
                String Line = Reader.nextLine();
                Infile.add(Line);
         }
         Reader.close();
         return Infile;
      }
      
   
      
//read one line from text file by id \\ correct;
      public  String DisplayLine(String id,String path){
           Scanner Reader = null;        
        try {         
            Reader = new Scanner(new File(path));
        }
  
        catch (FileNotFoundException e) {
        return null;}
        
         while (Reader.hasNext()) {
                String Line = Reader.nextLine();
                String[] seprated = Line.split("#");
                if(seprated[2].equals(id)){
                    Reader.close();
                    return seprated[0]+" "+seprated[1]+" "+seprated[2]+" "+seprated[3];}
         }
         return null;
      }
      
      
//Search for flight if flight is exist = true,else =;\\correct
        public  boolean SearchForFlight(String From,String To,String MoveTime,String BackTime,String Distance,String price,String path){
                Scanner Reader = null;        
        try {         
            Reader = new Scanner(new File(path));}
  
        catch (FileNotFoundException e) {
        return false;}
        
         while (Reader.hasNext()) {
                String Line = Reader.nextLine();
                String[] seprated = Line.split("#");
                if(seprated[0].equals(From)&&
                        seprated[1].equals(To)&&
                        seprated[2].equals(MoveTime)&&
                        seprated[3].equals(BackTime)&&
                        seprated[4].equals(Distance)&&
                        seprated[5].equals(price))
                {
                    
                    return true;
                }
         }
         
           return false;   
        }
     
         
        
     // update line by id in flie;
      public  boolean UpdateLineInTextFile(String email,String password,String id,String path){ 
        Scanner Reader = null;
        PrintWriter writter = null;
        String line;
        String fline;
        int size;
        int x=0;
         File file = new File(path);
        ArrayList<String> infile=new ArrayList<>();
        try {
             
            Reader = new Scanner(file);
            while((Reader.hasNext())){
                 line=Reader.next();
                 infile.add(line);
             }
             Reader.close();
             if(file.delete())
             {
                 size=infile.size();
                  for (int i=0;i<infile.size();i++)
                 {   
                      line=infile.get(i);              
                      String[] seprated = line.split("#");
                      if(seprated[2].equals(id)){
                          seprated[0]=email;
                          seprated[1]=password;
                          line  = seprated[0]+"#"+seprated[1]+"#"+seprated[2]+"#"+seprated[3];
                          infile.remove(i);
                          infile.add(i,line);
                 }
                     Fmanage.WriteInTextFileAfterRemove(infile.get(i),path);
                 }
                 return true;
             }
        }
         catch (IOException e) { return false;}
         return false;
    }   
        
        
        
// update line by id in flights flie;
      public  boolean UpdateLineInTextFileFlight(String from,String to,String Movetime,String backTime,String distance,String price,String id,String path){ 
        Scanner Reader = null;
        PrintWriter writter = null;
        String line;
        String fline;
        int size;
        int x=0;
         File file = new File(path);
        ArrayList<String> infile=new ArrayList<>();
        try {
             
            Reader = new Scanner(file);
            while((Reader.hasNext())){
                 line=Reader.next();
                 infile.add(line);
             }
             Reader.close();
             if(file.delete())
             {
                 size=infile.size();
                  for (int i=0;i<infile.size();i++)
                 {   
                      line=infile.get(i);              
                      String[] seprated = line.split("#");
                      if(seprated[6].equals(id)){
                          seprated[0]=from;
                          seprated[1]=to;
                          seprated[2]=Movetime;
                          seprated[3]=backTime;
                          seprated[4]=distance;
                          seprated[5]=price;
                          line  = seprated[0]+"#"+seprated[1]+"#"+seprated[2]+"#"+seprated[3]+"#"+seprated[4]+"#"+seprated[5]+"#"+seprated[6];
                          infile.remove(i);
                          infile.add(i,line);
                 }
                     Fmanage.WriteInTextFileAfterRemove(infile.get(i),path);
                 }
                 return true;
             }
        }
         catch (IOException e) { return false;}
         return false;
    }
      
     
      
      
// function to read last line and get last id from it and return it in String
      public  String ReadLastId(String file) {
        ArrayList<String> infile = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
          
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
             
            while ((line = bufferedReader.readLine()) != null) {
                infile.add(line);
            }
        } catch (IOException ignored) {
        }
        if (bufferedReader != null) try {
            
            bufferedReader.close();
        } catch (IOException e) {

        }
        int size=infile.size();
        if(size==0)
        {return null;}
        else {
        size=infile.size();
        String LastLine;
        LastLine = infile.get(size-1);
        String[] seprated = LastLine.split("#");
        return seprated[2];}
    }
      
      
      
      
// function to read last id in flights file 
      public  String ReadLastIdInFlightsFile(String file) {
        ArrayList<String> infile = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
          
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
             
            while ((line = bufferedReader.readLine()) != null) {
                infile.add(line);
            }
        } catch (IOException ignored) {
        }
        if (bufferedReader != null) try {
            
            bufferedReader.close();
        } catch (IOException e) {

        }
        int size=infile.size();
        if(size==0)
        {return null;}
        else {
        size=infile.size();
        String LastLine;
        LastLine = infile.get(size-1);
        String[] seprated = LastLine.split("#");
        return seprated[6];}
    }
      
      
      
//function SearchSorLogIn to search of email and password ,using with login(data,FilePath)\\correct
       public  boolean SearchForLogin(String E_mail,String password,String Filepath) {
        Scanner Reader = null;
        try {         
            Reader = new Scanner(new File(Filepath));}
        catch (FileNotFoundException e) {
        return false;}
         while (Reader.hasNext()) {
                String Line = Reader.nextLine();
                String[] seprated = Line.split("#");
                //if it there he will return true else will return false
               if(E_mail.equals(seprated[0])&&password.equals(seprated[1])){
                    Reader.close();
                    return true;
                
            }
                }
          return false;
    }
       
       
       
//function SearchForSignUp to return true if email is not there or false if email is there <using with sighnup\\correct
       public  boolean SearchForEmail(String Email,String Filepath) {
        Scanner Reader = null;
        try {         
            Reader = new Scanner(new File(Filepath));}
        
        catch (FileNotFoundException e) {
        return false;}
         while (Reader.hasNext()) {
                String Line = Reader.nextLine();
                String[] seprated = Line.split("#");
                if(seprated[0].equals(Email)){
                 Reader.close();
                return false;}
               
                  }
          return true;
    }

       
       
//function search for id to get id by using email and password
       public String SearchForId(String email,String password,String Filepath){
           Scanner Reader = null;
           String id=null;
        try {         
            Reader = new Scanner(new File(Filepath));}
  
        catch (FileNotFoundException e) {
        return null;}      
         while (Reader.hasNext()) {
                String Line = Reader.nextLine();
                String[] seprated = Line.split("#");
                //if he sent username to get password and id
                if(seprated[0].equals(email)&&seprated[1].equals(password)){
                    id= seprated[2];
                    Reader.close();
                    return id;
                }
    }
         return null;
 }
       
        
// remove line in text file with id\\ correct
       public boolean RemoveLineFromTextFile(String id,String Filepath) {
        Scanner Reader = null;
        PrintWriter writter = null;
        String line;
         File file = new File(Filepath);
        ArrayList<String> infile=new ArrayList<>();
        try {
            
            Reader = new Scanner(file);
            while((Reader.hasNext())){
                 line=Reader.next();
                 String[] seprated = line.split("#");
                 if(seprated[2].equals(id)){
                     continue;
                 }
               infile.add(line);
             }
             Reader.close();
             if(file.delete())
             {
                 for (int i=0;i<infile.size();i++) {
                 Fmanage.WriteInTextFileAfterRemove(infile.get(i),Filepath);
                 }
                 return true;
             }
        }
         catch (IOException e) { return false;}
         return false;   
           
           
}private  void WriteInTextFileAfterRemove(String data,String path){ 
         PrintWriter writter = null;
        try {
            writter = new PrintWriter(new FileWriter(new File(path),true));
            writter.println(data);
        } catch (IOException e) {
        } finally {
            writter.close();
        }
    }


// remove lin in text file with id for flights file
 public  boolean RemoveLineFromTextFileInFlightsFile(String id,String Filepath) {
        Scanner Reader = null;
        PrintWriter writter = null;
        String line;
         File file = new File(Filepath);
        ArrayList<String> infile=new ArrayList<>();
        try {
            
            Reader = new Scanner(file);
            while((Reader.hasNext())){
                 line=Reader.next();
                 String[] seprated = line.split("#");
                 if(seprated[6].equals(id)){
                     continue;
                 }
               infile.add(line);
             }
             Reader.close();
             if(file.delete())
             {
                 for (int i=0;i<infile.size();i++) {
                 Fmanage.WriteInTextFileAfterRemove(infile.get(i),Filepath);
                 }
                 return true;
             }
        }
         catch (IOException e) { return false;}
         return false;      
}



// remove line in text file using email 
     public  boolean RemoveLineFromTextByEmail(String email,String Filepath){
        Scanner Reader = null;
        PrintWriter writter = null;
        String line;
         File file = new File(Filepath);
        ArrayList<String> infile=new ArrayList<>();
        try {
            
            Reader = new Scanner(file);
            while((Reader.hasNext())){
                 line=Reader.next();
                 String[] seprated = line.split("#");
                 if(seprated[0].equals(email)){
                     continue;
                 }
               infile.add(line);
             }
             Reader.close();
             if(file.delete())
             {
                 for (int i=0;i<infile.size();i++) {
                 Fmanage.WriteInTextFileAfterRemove(infile.get(i),Filepath);
                 System.out.println(infile.get(i));
                 }
                 return true;
             }
        }
         catch (IOException e) { return false;}
         return false;      
     }
       
     
       
//to remove object \\correct 
       public  boolean RemoveObject(String path){
           File file=new File(path);
           if(file.delete()) 
             return true;
         
           else  
               return false;  
    }
       
       
       
//to remove file \\correct
       public  boolean RemoveFile(String path){
           File file=new File(path);
            return file.delete();
       }      
    
    
       
// to add new object in file working with regester function without append\\ correc;
    public  boolean writeobject(Object data,String path){
            ObjectOutputStream writer = null;
        try{
        writer =new ObjectOutputStream(new FileOutputStream(new File(path),false));
         
        writer.writeObject(data);
        writer.close();
        return true;}
        catch(IOException e){
            return false;}
       }
    
    

// to read object from binary file and return it and you must cast it in main function
     public  Object ReadObject(String path){
        Object infile = null;
        try{
            ObjectInputStream reader =new ObjectInputStream(new FileInputStream(path));
            infile=reader.readObject();
            reader.close();
            return infile;
        }
      catch (IOException | ClassNotFoundException e) {
          return null;
        }
    }
     
     
}
