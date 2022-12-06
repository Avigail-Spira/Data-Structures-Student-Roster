/* Author:   Avigail Spira
 * Project 1 StudentRecord Class
*/
import java.util.ArrayList;

public class StudentRecord extends Student{
	
	ArrayList<String> courses;
	
	public StudentRecord()
	{super();
	 this.courses = new ArrayList<String>();
	}
	
	public StudentRecord(Student s)	
	{ 
		super(s.first, s.last, s.IDNo);
		this.courses = new ArrayList<String>();
	}
		
	
	
	public StudentRecord(String first, String last, String IDNo)
	{ super(first, last, IDNo);
	  this.courses = new ArrayList<String>();}
	
	public void addCourse(String newCourse)
	{
		courses.add(newCourse);
	}
	
	public void removeCourse(String oldCourse)
	{
		courses.remove(oldCourse);
	}
	
	public String toString()
	{
		String s = "\n";
		for (String c:courses)
			s += c +"\n";
		return super.toString() + s;
		
	}
	
	
	

}