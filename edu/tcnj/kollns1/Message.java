/**Steven Kolln
Message*/
package edu.tcnj.kollns1;
import javafoundations.*;
import java.util.Scanner;
public class Message implements Comparable <Message>
{
	private String Date, From, Newsgroups, Subject, Body;
	private LinkedSet<String> Header;
	public Message(String date, String from, String newsgroups, String subject, String body, LinkedSet<String> header)
	{
		Date=date;
		From=from;
		Newsgroups=newsgroups;
		Subject=subject;
		Body=body;
		Header=header;
	}
	public int compareTo(Message obj)
	{
		if (getBody().equals(obj.getBody()))
		{
			//System.out.println("trueinCompare");
			return 0;
		}
		else
		{
			//System.out.println("flaseinCompare");
			return 1;
		}
	}
		
	public String getDate()
	{
		return Date;
	}
	public String getFrom()
	{
		return From;
	}
	public String getNewsgroups()
	{
		return Newsgroups;
	}
	public String getSubject()
	{
		return Subject;
	}
	public String getBody()
	{
		return Body;
	}
	public LinkedSet getHeader()
	{
		return Header;
	}
	public String toString()
	{
		String words="The newsgroup is: "+getNewsgroups()+"\nThe message is from: "+getFrom()+"\nThe Date is: "+getDate()+"\nThe subject is: "+getSubject()+"\nThe rest of the info is:\n"+getHeader();
		return words;
	}
	public boolean isCrossPosted()
	{
		boolean cross=false;
		String newsGroups1=this.getNewsgroups();
		Scanner scan=new Scanner(newsGroups1);
		try
		{
		if (scan.findInLine(",")!=null)
			cross=true;
		}
		catch (NullPointerException e)
		{}
		return cross;
	}
	public boolean containsInBody(String str)
	{
		boolean result=false;
		String bod=this.getBody();
		Scanner s=new Scanner(bod);
		String pattern="[^A-Za-z]";
		while (s.hasNext())
		{
			String newString=s.next();
			String finalString=newString.replaceAll(pattern, "");
			if (finalString.equalsIgnoreCase(str))
			{
				result=true;
				break;
			}
		}
		return result;
	}
	public String[] getEmail()
	{
		LinkedSet<String> ls=new LinkedSet<String>();
		int counter=0;
		String bod=this.getBody();
		String from=this.getFrom();
		Scanner f=new Scanner(from);
		Scanner b=new Scanner(bod);
		String pattern="^[A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		while (b.hasNext())
		{
			String newString=b.next();
			String finalString=newString.replaceAll(pattern, "");
			if (newString.matches(pattern))
			{
				ls.add(newString);
				counter++;
			}
		}
		while (f.hasNext())
		{
			String newString=f.next();
			String finalString=newString.replaceAll(pattern, "");
			if (newString.matches(pattern))
			{
				ls.add(newString);
				counter++;
			}
		}
		String[] Lastemails=new String[ls.size()];
		if (ls.size()>0)
		{
			for (int i=0; i<ls.size();i++)
			{
				Lastemails[i]=ls.get(i+1);
			}
		}
		return Lastemails;
	}
}
