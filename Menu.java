/* Author:   Avigail Spira
 * Project 1 Menu Class
*/

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;

public class Menu {
   private LinkedStudentList rosterLinkedList = new LinkedStudentList();
   private Student[] rosterArray = new Student[10];
   private static int index = 0;
   
   public Menu() {
   }

   
   public static void main(String[] args) {
      Menu menu = new Menu();
      menu.displayMenu();
   }

   
   public void displayMenu() {
      String[] menuOptions = {
    		  "Load Roster",
    		  "Add Student",
    		  "Remove Student",
    		  "Search Student by Name",
    		  "Search Student by ID", 
    		  "Save",
    		  "Save Changes",
    		  };
      
      for (int option=0; option<menuOptions.length; option++) {
         option = JOptionPane.showOptionDialog(
        	null, 
            "Select a Menu Option", 
            "Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, 
            null,
            menuOptions,
            menuOptions[0]); 
         switch (option) {
            case 0:
               try {
            	  this.readFromFile();
               } 
               catch (Exception exception) {
            	   System.out.println("Error in reading from File: " + exception);
               }
               break;
            case 1:
            	this.addStudent();
            	break;
            case 2:
            	this.removeStudent();
            	break;
            case 3:
            	this.searchByName();
            	break;
            case 4:
            	this.searchByID();
            	break;
            case 5:
            	this.save();
            	break;
            case 6:
            	try {
            		this.saveChanges();
            	}
            	catch (Exception exception) {
            		System.out.println("Error in saving to File: " + exception);
            	}
            	break;
               
         }
      }
   }

   private void readFromFile() throws FileNotFoundException {
      JFileChooser chooser;
      chooser = new JFileChooser();
      int status = chooser.showOpenDialog(null);
      File file = chooser.getSelectedFile();
 
      Scanner input = new Scanner(file);
      String firstName = "";
      String lastName = "";
      String IDNo = "";
      String line = input.nextLine();
      while (input.hasNextLine()) {
         line = input.nextLine();
         Scanner lineInput = new Scanner(line).useDelimiter(" ");
         firstName = lineInput.next();
         lastName = lineInput.next();
         IDNo = lineInput.next();
         Student student = new Student(firstName, lastName, IDNo);
         rosterLinkedList.insertSorted(student);
         lineInput.close();
      }
      input.close(); 
   }

	private void addStudent() {
		String firstName = JOptionPane.showInputDialog("Enter Student First Name:");
		String lastName = JOptionPane.showInputDialog("Enter Student Last Name:");
		String IDNo = JOptionPane.showInputDialog("Enter Student ID Number:");
		if (! this.idCheck(IDNo)) {
			Student newStudent = new Student(firstName, lastName, IDNo);
			rosterLinkedList.insertSorted(newStudent);
		} else 
			JOptionPane.showMessageDialog(null, "That student is already in the roster.");
	      
	}
	
	private void removeStudent() {
		String id_number = JOptionPane.showInputDialog("Enter Student ID Number:");
		Student studentToDelete = new Student(null, null, id_number);
		rosterLinkedList.delete(studentToDelete);
	}

	public static int binarySearch( Student[] array, Student key )
	   {
	      int low = 0;
	      int high = index;

	      return binarySearch(array, key, low, high);
	   }

	   private static int binarySearch(Student[]array, Student key, int low, int high) {
		   if(low > high)       
			   return -low - 1;
		   int mid = (low + high) / 2;
		   if (array[mid].getLast() == key.getLast() && array[mid].getFirst() == key.getFirst())
			   return mid;
		   if(array[mid].compareTo(key) < 0) 
              return binarySearch(array, key, low, mid-1);
          else 
              return binarySearch(array, key, 0, array.length -1);
	   }
	   
	
	private void searchByName() {
		this.save(); //save the linked list into array
		String firstName = JOptionPane.showInputDialog("Search by Student First Name:");
		String lastName = JOptionPane.showInputDialog("Search by Student Last Name:");
		Student searchStudent = new Student(firstName, lastName, null);
		
		int  searched  = binarySearch(rosterArray, searchStudent);
		
		if (searched > 0)  
            System.out.println("Student is not found!");  
        else  
            System.out.println("Student is in the roster"); 
		
	}
	
	private boolean idCheck(String ID) {
		Student searchStudent = new Student(null, null, ID);
		StudentNode current = rosterLinkedList.first;
		while (current.data != null && ! current.data.equals(searchStudent)) {
			current = current.next;	
		}
		
		if (current.data == null) {
			return false;
		} else {
			return true;
		}
	}
	
	private void searchByID() {
		String id_number = JOptionPane.showInputDialog("Search by Student ID Number:");
		Student searchStudent = new Student(null, null, id_number);
		StudentNode current = rosterLinkedList.first;
		while (current.data != null && ! current.data.equals(searchStudent)) {
			current = current.next;	
		}
		
		if (current.data == null) {
			JOptionPane.showMessageDialog(null, "That ID is not found in the roster");
		} else {
			JOptionPane.showMessageDialog(null, "Student " + current.data.getFirst() + " " + current.data.getLast() + " is in the list");
		}
	}
	
	
	
	
	private void save() {
	    StudentNode current = rosterLinkedList.first;
	    while (index<10 && current.data != null) {
	    	if (! Arrays.asList(rosterArray).contains(current.data)) {
				rosterArray[index] = current.data;
				index++;
				current = current.next;
	    	}
	    }
	    if (index == 10)
	    	JOptionPane.showMessageDialog(null, "The max number of students are in your array");
	    
	}
	
	
	 private void saveChanges() throws FileNotFoundException{
		File file = new File("./SaveRoster.txt");
		PrintWriter fileWriter = new PrintWriter(file);
		fileWriter.println("FirstName, LastName, IDNo");
		
		  
		if (index != 0) {
			for (int i=0; i<index; i++)
				fileWriter.println(rosterArray[i]);
		} else if (! rosterLinkedList.isEmpty()) {
			fileWriter.print(rosterLinkedList.toString());
		} else
			JOptionPane.showMessageDialog(null, "There are no changes to save");
		
		fileWriter.close();
	}


}
