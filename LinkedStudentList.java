/* Author:   Avigail Spira
 * Project 1 LinkedStudentList Class
*/

	public class LinkedStudentList {
	protected StudentNode first;
	protected StudentNode last;
	protected int length;
	
	public LinkedStudentList() { //Constructor that instantiates a dummy node at the front of the linked list
		first = new StudentNode(null);
		last = first;
		length = 0;
	}
	
	
	//to append to the end of the linked list
	public void append (Student s) {
		StudentNode student = new StudentNode(s);
		last.next = student;
		last = student;
		length++;
	}
	
	
	public void insertSorted(Student s) {
	    StudentNode newStudent = new StudentNode(s);
	    StudentNode current = first;
	    StudentNode previous = null;
	    while (current.data != null && s.compareTo(current.data) > 0){
	      previous = current;
	      current = current.next;
	    }
	    if (previous == null){
	      first = newStudent;
	    }
	    else {
	      previous.next = newStudent;
	    }
	    newStudent.next = current;
	}
	
	
	public void delete(Student s) {
	    StudentNode current = first;
	    StudentNode previous = null;
	    while (current.data != null && ! s.equals(current.data)) {
	    	previous = current;
	    	current = current.next;
		}
		if (s.equals(current.data)) {
			previous.next = current.next;
		}
	}
	
	
	
	public boolean isEmpty() {
		return (first.data == null);
	}
	
	@Override
	public String toString(){
        String returnStr = "";

        StudentNode current = first;
        while(current.data != null){
            returnStr += current.data.toString();
            current = current.next;
        }

        return returnStr;
    }
	
}





