/* Author:   Avigail Spira
 * Project 1 StudentNode Class
*/

public class StudentNode {
	protected Student data;
	protected StudentNode next;
	
	public StudentNode (Student s) { //constructor
		this.data = s;
		next = null;
	}
}