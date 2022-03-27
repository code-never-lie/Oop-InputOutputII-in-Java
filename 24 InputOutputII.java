Data Input/Output Streams for Binary Data

-Data streams support binary I/O of primitive data type values 
  (boolean, char, byte, short, int, long, float, and double) as well as String values.

- All data streams implement either the DataInput or the DataOutput interface.
.................................................................................
Example : Write Binary Data

import java.io.*;
class Test {
  static final String dataFile = "invoicedata";

  static final double[] prices =
    { 19.99, 9.99, 15.99, 3.99, 4.99 };
  static final int[] units = { 12, 8, 13, 29, 50 };
  static final String[] descs = { "Java T-shirt",
                                  "Java Mug",
                                  "Duke Juggling Dolls",
                                  "Java Pin",
                                  "Java Key Chain" };
public static void main(String[] args) throws IOException {
  DataOutputStream out = null;

  try {
    out = new DataOutputStream(new
      BufferedOutputStream(new FileOutputStream(dataFile)));
for (int i = 0; i < prices.length; i ++) {
    out.writeDouble(prices[i]);
    out.writeInt(units[i]);
    out.writeUTF(descs[i]);
  }
} finally {
    out.close();
}
....................................................................
Example Read Binary Data

import java.io.*;
class Test {
  static final String dataFile = "invoicedata";

public static void main(String[] args) throws IOException {

DataInputStream in = null;
double total = 0.0;
try {
  in = new DataInputStream(new
    BufferedInputStream(new FileInputStream(dataFile)));

  double price;
  int unit;
  String desc;
try {
        while (true) {
          price = in.readDouble();
          unit = in.readInt();
          desc = in.readUTF();
          System.out.format("You ordered %d units of %s " +
                            "at $%.2f%n", unit, desc, price);
          total += unit * price;
        }
      } catch (EOFException e) { }
      System.out.format("For a TOTAL of: $%.2f%n", total);
    }
    finally {
      in.close();
    }
  }
}
........................................................................
UseFull Method
DataType		DataInputStream		DataOutputStream	
					
  byte			readByte		writeByte	
  short			readShort		writeShort	
  int			readInt			writeInt	
  long			readLong		writeLong	
  float			readFloat		writeFloat	
  double		readDouble		writeDouble	
  boolean		readBoolean		writeBoolean	
  char			readChar		writeChar	
  String		readUTF			writeUTF	
  byte[]		readFully			

- The readFully method blocks until all bytes are read or an EOF occurs
 Values are written in big-endian fashion regardless of computer platform

- UTF encoding represents a 2-byte Unicode 

- Use DataStreams for byte-based I/O

- Chain a FileOutputStream to a DataOutputStream for binary file output

- Chain a FileInputStream to a DataInputStream for binary file input
............................................................................ 
Random Access Files
- Random access files support nonsequential access to disk file data.
- Random access files permit nonsequential, or random, access to a file's contents
- The java.io.RandomAccessFile class implements both the DataInput and DataOutput interfaces 
- RandomAccessFile is similar to FileInputStream and FileOutputStream in that you specify a file 
  on the native file system to open when you create it.
- When you create a RandomAccessFile, you must indicate whether 
  you will be just reading the file or writing to it also.
- After the file has been opened, you can use the common read or write methods defined 
  in the DataInput and DataOutput interfaces to perform I/O on the file.
...........................................................................
Example Open File for read

new RandomAccessFile("xanadu.txt", "r"); 
...........................................................................
Example Open File for bot read and write

new RandomAccessFile("xanadu.txt", "rw");
...........................................................................

Example Write data into file

import java.io.*;
class Test {
public static void main(String[] args) throws IOException {
 try{
	RandomAccessFile rafile = new RandomAccessFile("c:\\java\\a.txt","rw");
        if (rafile==null){
            System.out.println("File with problem");    
            System.exit(0);
        }
	String line;
	line = "MY First Program of Random Files";
	line = line + "\n Very Easy to Learn";
	line = line + "\n WAD course gives me a chance to learn";
	rafile.writeBytes(line);
	rafile.writeByte('\n');
        rafile.close();
}

catch (IOException excep){
	System.out.println("An IO exception has occured.");}
}
}
..........................................................................
Access Modes


-"r"	 Open for reading only. Invoking any of the write methods of the resulting object 
	 will cause an IOException to be thrown.
-"rw"	 Open for reading and writing. If the file does not already exist then an attempt
         will be made to create it.
-"rws"	 Open for reading and writing, as with "rw", and also require that every update to
         the file's content or metadata be written synchronously to the underlying storage device.
-"rwd"   Open for reading and writing, as with "rw", and also require that every update to the 
         file's content be written synchronously to the underlying storage device.
..............................................................................................        
Example Append data to file(RandomAccessFile)

import java.io.*;
class Test {
public static void main(String[] args) throws IOException {
 try{
	RandomAccessFile rafile = new RandomAccessFile("c:\\java\\a.txt","rw");
        if (rafile==null){
            System.out.println("File with problem");    
            System.exit(0);
        }
	String line;
	line = "MY First Program of Random Files";
	line = line + "\n Very Easy to Learn";
	line = line + "\n WAD course gives me a chance to learn";
        rafile.seek(rafile.length());
	rafile.writeBytes(line);
	rafile.writeByte('\n');
        rafile.close();
}

catch (IOException excep){
	System.out.println("An IO exception has occured.");}
}
}
.................................................................................................
Example Read data from File (RandomAccessFile)

import java.io.*;
class Test {
public static void main(String[] args) throws IOException {
 try{
	RandomAccessFile rafile = new RandomAccessFile("c:\\java\\a.txt","rw");
        if (rafile==null){
            System.out.println("File with problem");    
            System.exit(0);
        }
        int ch=0;
        while((ch=rafile.read())!=-1)
              System.out.print((char)ch);
        rafile.close();
}

catch (IOException excep){
	System.out.println("An IO exception has occured.");}
}
}

..........................................................................................
Useful Methods

int skipBytes(int) Moves the file pointer forward the specified number of bytes.



void seek(long) Positions the file pointer just before the specified byte.



long getFilePointer() Returns the current byte location of the file pointer.

.........................................................................................
Moving Around a RandomAccessFile

- To read or write at a specific location in a RandomAccessFile you must first position 
  the file pointer at the location to read or write. 
- This is done using the seek() method. The current position of the file pointer can be 
  obtained by calling the getFilePointer() method.
.........................................................................................
Example

RandomAccessFile file = new RandomAccessFile("c:\\data\\file.txt", "rw");

file.seek(200);

long pointer = file.getFilePointer();

file.close();
......................................................................................

File Class

- File is a class that helps you write platform-independent code that examines and manipulates
  files and directories
................................................................................................

Example List File Properties

import java.io.*;
class Test {
public static void main(String[] args) {
	File filename = new File("Test.java");

	if (filename.exists())
	{
		System.out.println("File name: " + filename.getName());
		System.out.println("Path: " + filename.getAbsolutePath());
		System.out.println("File Length: " + filename.length());
		System.out.println("Last Modified: " + filename.lastModified());
		System.out.println("Is a file? " + filename.isFile());
		System.out.println("Is file hidden? " + filename.isHidden());
		System.out.println("Is a Directory? " + filename.isDirectory());
		System.out.println("Is file readable? " + filename.canRead());
		System.out.println("Is file Writable? " + filename.canWrite());
		System.out.println("Path separator: " + filename.pathSeparator);
		System.out.println("Name separator: " + filename.separator);
	}


}
}
............................................................................................
Example List Folder Contents

import java.io.*;
class Test {
public static void main(String[] args) {

	File dirname = new File("c:\\java");

	if (dirname.exists()&&dirname.isDirectory())
	{
		System.out.println("Path: " + dirname.getAbsolutePath() );

		String [] allfiles = dirname.list();

		for (int i=0; i< allfiles.length; i++){

			System.out.println(allfiles[i]);

		}
	}
}

}

....................................................................................
Working with Directories

- File has some useful methods for working with directories.
mkDir
- The mkdir method creates a directory.


list and listFiles

- The list and listFiles methods list the contents of a directory. 
- The list method returns an array of String file names, while listFiles returns an array of File objects.

..........................................................................................
Example Display all Drive Letter of your computer's hard disk

import java.io.*;
class Test {
public static void main(String[] args) {
File[] roots = File.listRoots();
for(int i=0;i<roots.length;i++)
    System.out.println("Root["+i+"]:" + roots[i]);
}
}

.............................................................................
Example Display Drive Letters with free, used and total space.

import java.io.File;
class Test{
public static void main(String[] args) {
    File[] roots = File.listRoots();
    for (int i = 0; i < roots.length; i++) {
      System.out.println(roots[i]);
      System.out.println("Free space = " + roots[i].getFreeSpace());
      System.out.println("Usable space = " + roots[i].getUsableSpace());
      System.out.println("Total space = " + roots[i].getTotalSpace());
      System.out.println();
    }
  }
}
...........................................................................
Example Display image icon of specified file

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test {

  public static void main(String[] args) throws Exception {
    File file = new File(args[0]);
    sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder(file);
    Icon icon = new ImageIcon(sf.getIcon(true));
    System.out.println("type = " + sf.getFolderType());
    JLabel label = new JLabel(icon);
    JFrame frame = new JFrame();
    frame.getContentPane().add(label);
    frame.pack();
    frame.setVisible(true);

  }

}
.......................................................................
Static Methods

File contains some useful static methods.

createTempFile

-The createTempFile method creates a new file with an unique name and returns a File object referring to it.

 listRoots

-The listRoots returns a list of file system root names. On Microsoft Windows, 
this will be the root directories of mounted drives, such as a:\ and c:\.

- On UNIX and Linux systems, this will be the root directory, /.
..............................................................................................
Formatting
- formats almost any number of values based on a format string, with many options for precise formatting

The format Method

- The format method formats multiple arguments based on a format string.
-  The format string consists of static text embedded with format specifiers.
-  except for the format specifiers, the format string is output unchanged
...............................................................................................
Example 
public class Test {
  public static void main(String[] args) {
    int i = 2;
    double r = Math.sqrt(i);

    System.out.format("The square root of %d is %f.%n", i, r);
  }
}



Here is the output:

The square root of 2 is 1.414214.
.......................................................................................

- all format specifiers begin with a % and end with a 1- or 2-character conversion that specifies
 the kind of formatted output being generated. 

The three conversions used in above example  are:

d formats an integer value as a decimal value.

f formats a floating point value as a decimal value.

n outputs a platform-specific line terminator.



-Here are some other conversions:

x formats an integer as a hexadecimal value.

s formats any value as a string.

tB formats an integer as a locale-specific month name.

There are many other conversions.
...........................................................................................
Example : Open File (JFrame,TextArea,menu, JFileChooser) 


import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
 
class FileChooserDemo extends JFrame{
  private TextArea display = new TextArea();
  private JMenuBar mb= new JMenuBar();
  private JMenu m=new JMenu("File");
  private JMenuItem open1=new JMenuItem("Open");
  public FileChooserDemo() {
    setJMenuBar(mb);
    mb.add(m);
    m.add(open1);
    open1.addActionListener(new OpenMenu());
    add(display);
    setSize(500,500);
    setVisible(true); 
  }
private class OpenMenu implements ActionListener{
    public void actionPerformed(ActionEvent e) { 
         String item=e.getActionCommand();
         if (item.equals("Open")){
               JFileChooser chooser = new JFileChooser();
               int option = chooser.showOpenDialog(FileChooserDemo.this);
               String path=chooser.getSelectedFile().getAbsolutePath();
               MyFile file=new MyFile(path);
               display.setText(file.readAll());
         }
  }
}
}
class MyFile {
 MyFile (String fname) {
   try {f= new File("fname"); 
        in= new FileInputStream(fname);
        System.out.print(fname +"  Exist ");
       } catch (Exception e ){
           System.out.print(fname +" File Does Not Exist ");
           System.exit(0);
     }
 }
 String readAll(){
     String st="";   
     int ch=0;
    try {while((ch=in.read())!=-1)
              st=st+(char)ch;  
        }catch (Exception e )  {}
    return st;
 }
 File f;
 FileInputStream in;
}

class Test{
   public static void main(String o[]){
     new  FileChooserDemo();
   }
}

.................................................................................................
Example : Open and Save  File (JFrame,JTextArea,menu, JFileChooser) 


import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
 
class FileChooserDemo extends JFrame{
  private JTextArea ta = new JTextArea();
  private JScrollPane sp = new JScrollPane(ta);
  private JMenuBar mb= new JMenuBar();
  private JMenu m=new JMenu("File");
  private JMenuItem open1=new JMenuItem("Open");
  private JMenuItem save1=new JMenuItem("Save");
  private JMenuItem exit1=new JMenuItem("Exit");
  public FileChooserDemo() {
    setJMenuBar(mb);
    mb.add(m);
    m.add(open1); m.add(save1); m.add(exit1);
    open1.addActionListener(new OpenMenu());
    save1.addActionListener(new OpenMenu());
    exit1.addActionListener(new OpenMenu());
    ta.setFont(new java.awt.Font("Arial", 1, 20));
    ta.setLineWrap(true);
    ta.setWrapStyleWord(true);
    ta.grabFocus();
    add(sp);
    setSize(500,500);
    setVisible(true); 
  }
private class OpenMenu implements ActionListener{
    public void actionPerformed(ActionEvent e) { 
         String item=e.getActionCommand();
         if (item.equals("Open")){
               JFileChooser chooser = new JFileChooser();
               int option = chooser.showOpenDialog(FileChooserDemo.this);
               String path=chooser.getSelectedFile().getAbsolutePath();
               MyFile file=new MyFile(path);
               ta.setText(file.readAll());
         }
         else if (item.equals("Save")){
              JFileChooser chooser = new JFileChooser();
               int option = chooser.showSaveDialog(FileChooserDemo.this);
               String path=chooser.getSelectedFile().getAbsolutePath();
               MyFileWrite file=new MyFileWrite(path);
               file.save(ta.getText());
         }

          
  }
}
}
class MyFile {
 MyFile (String fname) {
   try { 
        f= new File("fname"); 
        in= new FileInputStream(fname);
        System.out.print(fname +"  Exist ");
       } catch (Exception e ){
           System.out.print(fname +" File Does Not Exist ");
           System.exit(0);
     }
 }
String readAll(){
     String st="";   
     int ch=0;
    try {while((ch=in.read())!=-1)
              st=st+(char)ch;  
        }catch (Exception e )  {}
    return st;
 }
 File f;
 FileInputStream in;
}

class MyFileWrite {
 MyFileWrite (String fname) {
   try { 
        f= new File("fname"); 
        in= new FileOutputStream(fname);
        System.out.print(fname +"  Exist ");
       } catch (Exception e ){
           System.out.print(fname +" File Does Not Exist ");
           System.exit(0);
     }
 }
void  save(String p){
     
    try {
           in.write(p.getBytes());
        }catch (Exception e )  {}
    
 }
 File f;
 FileOutputStream in;
}



class Test{
   public static void main(String o[]){
     new  FileChooserDemo();
   }
}



