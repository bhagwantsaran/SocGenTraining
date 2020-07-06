import java.util.*;
class CustomSortDemoByComparator{
	public static void main(String[] args) {
		
		java.util.ArrayList <Student>list = new java.util.ArrayList<>();
		Scanner sc =new Scanner(System.in);
		char c;
		System.out.println("Do you want to add more (y/n)");
		c=sc.next().charAt(0);
		while(c=='y')
		{	String name,city;
			int age;
			System.out.println("Name: ");
			name=sc.next();
			name+=" "+sc.next();

			System.out.println("Age: ");
			age=sc.nextInt();

			System.out.println("City: ");
			city=sc.next();

			list.add(new Student(name,age,city));	
			
			System.out.println("Do you want to add more (y/n)");
			c=sc.next().charAt(0);			
		}

		for(Student Student : list){
			System.out.println(Student);
		}
		System.out.println();

		java.util.Collections.sort(list, new SortByFirstName());

		System.out.println("After Sorting list according to FirstName: ");

		for(Student Student : list){
			System.out.println(Student);
		}
		System.out.println();

		java.util.Collections.sort(list, new SortByLastName());

		System.out.println("After Sorting list according to LastName: ");

		for(Student Student : list){
			System.out.println(Student);
		}
		System.out.println();
		 java.util.Collections.sort(list, new SortByAge());

		System.out.println("After Sorting list according to Age: ");

		for(Student Student : list){
			System.out.println(Student);
		}
		System.out.println();
		 java.util.Collections.sort(list, new SortByCity());

		System.out.println("After Sorting list according to City ");

		for(Student Student : list){
			System.out.println(Student);
		}

	}
}



class Student{
	String name, city;
	int age;

	Student(String name,  int age , String city){
		this.name = name;
		this.age = age;
		this.city = city;
	}

	String getName(){
		return this.name;
	}

	Integer getAge(){
		return this.age;
	}

	String getCity(){
		return this.city;
	}
	String getLastName()
	{
		String s="";
		int f=0;
		for(int i=0;i<this.name.length();i++)
		{
		 if(f==1)
			s=s+this.name.charAt(i);
		if(this.name.charAt(i)==' ')
			f=1;
		}
		return s;
		
	}


	@Override
	public String toString(){
		return "student : " + this.getName() + ", " + this.getAge().toString() + ", " + this.getCity();
	}
}
class SortByFirstName implements java.util.Comparator<Student>{
	@Override
	public int compare(Student first, Student second){
		return first.getName().compareTo(second.getName());
	}
}
class SortByLastName implements java.util.Comparator<Student>{
	@Override
	public int compare(Student first, Student second){
		return first.getLastName().compareTo(second.getLastName());
	}
}
class SortByAge implements java.util.Comparator<Student>{
	@Override
	public int compare(Student first, Student second){
		return first.getAge().compareTo(second.getAge());
	}
}

class SortByCity implements java.util.Comparator<Student>{
	@Override
	public int compare(Student first, Student second){
		return first.getCity().compareTo(second.getCity());
	}
}


