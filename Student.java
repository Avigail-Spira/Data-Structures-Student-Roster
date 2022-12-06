/* Author:   Avigail Spira
 * Project 1 Student Class
*/

public class Student
	{
		protected String first;
		protected String last;
		protected String IDNo;
		
		// constructors
		public Student()
		{ first = ""; last = ""; IDNo = "";}
		
		public Student(String first, String last, String IDNo)
		{
			this.first = first;
			this.last = last;
			this.IDNo = IDNo;
		}
		
		//sets
		public void setFirst(String first)
		{this.first = first;}
		
		public void setLast(String last)
		{ this.last = last;}
		
		public void setIDNo(String IDNo)
		{this.IDNo = IDNo;}
		
		//gets
		public String getFirst()
		{return this.first;}
		
		public String getLast()
		{return this.last;}
		
		public String getIDNo()
		{return this.IDNo;}
		
		//tests equality by ID
		public boolean equals(Student other)
		{return this.IDNo.equals(other.IDNo);}
		
		//compares students alphabetically
		public int compareTo(Student other)
		{
			int y = this.last.compareTo(other.last);
			if (y != 0) return y;
			y = this.first.compareTo(other.first);
			if (y != 0) return y;
			return this.IDNo.compareTo(other.IDNo);
			
		}

		//creates formatted string for student info 
		public String toString()
		{ return this.last + " "+ this.first + " " + this.IDNo + "\n";}
		
		
		

}

	