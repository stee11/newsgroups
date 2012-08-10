/**Steven Kolln
Processor*/
package edu.tcnj.kollns1;
import javafoundations.exceptions.*;
import javafoundations.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class Processor
{
	private LinkedSet<Message>[] sets;
	private PrintStream print;
	public Processor() throws FileNotFoundException
	{
		sets=(LinkedSet<Message>[])(new LinkedSet[20]);
		print=new PrintStream("p2Output.txt");
	}	
	public void start() throws FileNotFoundException, IOException
	{
		this.read();
		this.output1();
		this.output4();
		this.output2();
		this.output3();
		this.output5();
	}
	private void output1()
	{
		int directoryNum=0;
		print.println("Section 0: Newsgroups Counts");
		File dataDir = new File("newsgroups");
		String [] dirListing = dataDir.list();
		for (String name : dirListing)
		{
			print.println(name+"-"+sets[directoryNum].size()+" messages");
			directoryNum++;
		}
		print.println();
	}
	private void output2() throws IOException
	{
		int n=0;
		print.println("Section 2: Message Bodies Containing Capital:");
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				if (s.get(start).containsInBody("capital"))
				{
					n++;
				}
			}
		}
		print.println("Number of time capital was used: "+n+"\n");
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				if (s.get(start).containsInBody("capital"))
				{
					print.println("The word capital is found in here.");
					print.println(s.get(start));
				}
			}
		}
		print.println();
	}
	private void output3()
	{
		int n=0;
		print.println("Section 3: Message Bodies Containing Computer:");
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				if (s.get(start).containsInBody("computer"))
				{
					n++;
				}
			}
		}
		print.println("Number of time computer was used: "+n+"\n");
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				if (s.get(start).containsInBody("computer"))
				{
					print.println("The word computer is found in here.");
					print.println(s.get(start));
				}
			}
		}
	}
	private void output4()
	{
		int counter=0;
		print.println("Section 1: Cross-Posted Analysis");
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				if (s.get(start).isCrossPosted())
				{
					counter++;
				}
			}
		}
		print.println("The total number of cross posted messages is: "+counter+"\n");
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				if (s.get(start).isCrossPosted())
				{
					print.println("This message is cross posted\n"+s.get(start));
				}
			}
		}
		print.println();
	}
	private void output5()
	{
		int counter=0;
		print.println("Section 4: Emails List");
		LinkedSet<String> ls=new LinkedSet<String>();
		for (LinkedSet<Message> s: sets)
		{
			for (int start=1;start<s.size();start++)
			{
				counter++;
				int count=s.get(start).getEmail().length;
				String[] emails=s.get(start).getEmail();
				for (int i=0;i<emails.length;i++)
					ls.add(emails[i]);
			}
		}
		String[] finalEmail=new String[ls.size()];
		for (int i=0;i<finalEmail.length;i++)
			finalEmail[i]=ls.get(i+1);
		Arrays.sort(finalEmail);
		int total=finalEmail.length;
		print.println("Total emails found: "+total);
		print.println();
		for (String l: finalEmail)
			print.println(l);
		
	}
	/** This will read the files in each directories. 
	IMPORTANT: Remember to change the name of directory TWICE when changing newsgroups. Ex: go from newsgroups to newsgroups*/
	private void read() throws FileNotFoundException
	{
		/**Goes in the very head directory in home*/
		File dataDir = new File("newsgroups");
		if (dataDir.isDirectory())
		{
			/**Now you are in the first directory. The one which contains the newsgroups*/
			int count=0;
			boolean BOD=true, ref=false, all=false, from=true;
			String [] dirListing = dataDir.list();
			for (String name : dirListing)
			{
				LinkedSet LS=new LinkedSet<Message>();
				/**We are in the array of string of each name newgroup and we are itterating through*/
				File dirFile = new File("newsgroups" , name);
				if (dirFile.isDirectory());
				{
					/**Now we are in the specific directory with many file Message names*/
					//System.out.print("Section-"+count+": Directory: " + dirFile+"-");
					String [] newsgroup = dirFile.list();
					for (String fileName: newsgroup)
					{
						/**Iteration of each Message starts*/
						String Xref="";
						String Path="";
						String From="";
						String Newsgroups="";
						String Subject="";
						String Date="";
						String Organization="";
						String Lines="";
						String Distribution="";
						String MessageID="";
						String References="";
						String NNTPSoftware="";
						String NewsSoftware="";
						String XNewsreader="";
						String Summary="";
						String ReplyTo="";
						String Keywords="";
						String Approved="";  
						String Sender="";
						String Expires="";
						String Body="";
						String Host="";
						String Follow="";
						String ArticleID="";
						String Supersedes="";
						LinkedSet<String> linkedString=new LinkedSet<String>();
						File reader=new File(dirFile, fileName);
						//System.out.print(fileName);
						Scanner scan=new Scanner(reader);
						while (scan.hasNextLine())
						{
							String line=scan.nextLine();
							Scanner lineScan=new Scanner(line);
							if (all!=true)
							{
							if (lineScan.hasNext("Xref:"))
							{
								int num=0;
								num=lineScan.findInLine("Xref: ").length();
								BOD=false;
								Xref=line.substring(num, line.length());
								linkedString.add("Xref: "+Xref);
								//System.out.println(Xref);
							}
							if (lineScan.hasNext("Path:"))
							{
								int num=0;
								num=lineScan.findInLine("Path: ").length();
								BOD=false;
								Path=line.substring(num, line.length());
								linkedString.add("Path: "+Path);
								//System.out.println(Path);
							}
							if (lineScan.hasNext("From:")&&from)
							{
								int num=0;
								num=lineScan.findInLine("From: ").length();
								BOD=from=false;
								From=line.substring(num, line.length());
								//System.out.println(From);
							}
							if (lineScan.hasNext("Newsgroups:"))
							{
								int num=0;
								num=lineScan.findInLine("Newsgroups: ").length();
								BOD=false;
								Newsgroups=line.substring(num, line.length());
								//System.out.println(Newsgroups);
							}
							if (lineScan.hasNext("Subject:"))
							{
								int num=0;
								num=lineScan.findInLine("Subject: ").length();
								BOD=false;
								Subject=line.substring(num, line.length());
								//System.out.println(Subject);
							}
							if (lineScan.hasNext("Date:"))
							{
								int num=0;
								num=lineScan.findInLine("Date: ").length();
								BOD=false;
								Date=line.substring(num, line.length());
								//System.out.println(Date);
							}
							if (lineScan.hasNext("Organization:"))
							{
								int num=0;
								num=lineScan.findInLine("Organization: ").length();
								BOD=false;
								Organization=line.substring(num, line.length());
								linkedString.add("Organization: "+Organization);
								//System.out.println(Organization);
							}
							if (lineScan.hasNext("Lines:"))
							{
								int num=0;
								num=lineScan.findInLine("Lines: ").length();
								BOD=false;
								Lines=line.substring(num, line.length());
								linkedString.add("Lines: "+Lines);
								//System.out.println(Lines);
							}
							if (lineScan.hasNext("Distribution:"))
							{
								int num=0;
								num=lineScan.findInLine("Distribution: ").length();
								BOD=false;
								Distribution=line.substring(num, line.length());
								linkedString.add("Distribution: "+Distribution);
								//System.out.println(Distribution);
							}
							if (lineScan.hasNext("Message-ID:"))
							{
								int num=0;
								num=lineScan.findInLine("Message-ID: ").length();
								BOD=false;
								MessageID=line.substring(num, line.length());
								linkedString.add("MessageID: "+MessageID);
								//System.out.println(MessageID);
							}
							if (lineScan.hasNext("References:")&&ref)
							{
								int num=0;
								num=lineScan.findInLine("References:").length();
								BOD=ref=false;
								References=line.substring(num, line.length());
								linkedString.add("References: "+References);
								//System.out.println(References);
							}
							if (lineScan.hasNext("NNTP-Software:"))
							{
								int num=0;
								num=lineScan.findInLine("NNTP-Software: ").length();
								BOD=false;
								NNTPSoftware=line.substring(num, line.length());
								linkedString.add("NNTPSoftware: "+NNTPSoftware);
								//System.out.println(NNTPSoftware);
							}
							if (lineScan.hasNext("News-Software:"))
							{
								int num=0;
								num=lineScan.findInLine("News-Software: ").length();
								BOD=false;
								NewsSoftware=line.substring(num, line.length());
								linkedString.add("NewsSoftware: "+NewsSoftware);
								//System.out.println(NewsSoftware);
							}
							if (lineScan.hasNext("X-Newsreader:"))
							{
								int num=0;
								num=lineScan.findInLine("X-Newsreader: ").length();
								BOD=false;
								XNewsreader=line.substring(num, line.length());
								linkedString.add("Xnewsreader: "+XNewsreader);
								//System.out.println(XNewsreader);
							}
							if (lineScan.hasNext("Summary:"))
							{
								int num=0;
								num=lineScan.findInLine("Summary: ").length();
								BOD=false;
								Summary=line.substring(num, line.length());
								linkedString.add("Summary"+Summary);
								//System.out.println(Summary);
							}
							if (lineScan.hasNext("Reply-To:"))
							{
								int num=0;
								num=lineScan.findInLine("Reply-To: ").length();
								BOD=false;
								ReplyTo=line.substring(num, line.length());
								linkedString.add("ReplyTo: "+ReplyTo);
								//System.out.println(ReplyTo);
							}
							if (lineScan.hasNext("Keywords:"))
							{
								int num=0;
								num=lineScan.findInLine("Keywords: ").length();
								BOD=false;
								Keywords=line.substring(num, line.length());
								linkedString.add("Keywords: "+Keywords);
								//System.out.println(Keywords);
							}
							if (lineScan.hasNext("Approved:"))
							{
								int num=0;
								num=lineScan.findInLine("Approved: ").length();
								BOD=false;
								Approved=line.substring(num, line.length());
								linkedString.add("Approved: "+Approved);
								//System.out.println(Approved);
							}
							if (lineScan.hasNext("Sender:"))
							{
								int num=0;
								num=lineScan.findInLine("Sender: ").length();
								BOD=false;
								Sender=line.substring(num, line.length());
								linkedString.add("Sender"+Sender);
								//System.out.println(Sender);
							}
							if (lineScan.hasNext("Expires:"))
							{
								int num=0;
								num=lineScan.findInLine("Expires: ").length();
								BOD=false;
								Expires=line.substring(num, line.length());
								linkedString.add("Expires: "+Expires);
								//System.out.println(Expires);
							}
							if (lineScan.hasNext("NNTP-Posting-Host:"))
							{
								int num=0;
								num=lineScan.findInLine("NNTP-Posting-Host: ").length();
								BOD=false;
								Host=line.substring(num, line.length());
								linkedString.add("Host: "+Host);
								//System.out.println(Host);
							}
							if (lineScan.hasNext("Followup-To:"))
							{
								int num=0;
								num=lineScan.findInLine("Followup-To: ").length();
								BOD=false;
								Follow=line.substring(num, line.length());
								linkedString.add("Follow up: "+Follow);
								//System.out.println(Follow);
							}
							if (lineScan.hasNext("Article-I.D.:"))
							{
								int num=0;
								num=lineScan.findInLine("Article-I.D.: ").length();
								BOD=false;
								ArticleID=line.substring(num, line.length());
								linkedString.add("ArticleID: "+ArticleID);
								//System.out.println(ArticleID);
							}
							if (lineScan.hasNext("Supersedes:"))
							{
								int num=0;
								num=lineScan.findInLine("Supersedes: ").length();
								BOD=false;
								Supersedes=line.substring(num, line.length());
								linkedString.add("Supersedes: "+Supersedes);
								//System.out.println(Supersedes);
							}
							}
							if (BOD)
							{
								while (lineScan.hasNextLine())
								{
									all=true;
									Body+=lineScan.nextLine()+"\n";
									//System.out.println(Body);
								}
							}
							BOD=true;
						}
						/*linkedString.add(Xref);
						linkedString.add(Path);
						linkedString.add(Organization);
						linkedString.add(Lines);
						linkedString.add(Distribution);
						linkedString.add(MessageID);
						linkedString.add(References);
						linkedString.add(NNTPSoftware);
						linkedString.add(NewsSoftware);
						linkedString.add(XNewsreader);
						linkedString.add(Summary);
						linkedString.add(ReplyTo);
						linkedString.add(Keywords);
						linkedString.add(Approved);
						linkedString.add(Expires);
						linkedString.add(Sender);
						linkedString.add(Host);
						linkedString.add(Follow);
						linkedString.add(ArticleID);
						linkedString.add(Supersedes);*/
						Message mes=new Message(Date, From, Newsgroups, Subject, Body, linkedString);
						LS.add(mes);
						//System.out.println(count);
						//System.out.println(mes);
						ref=from=true;
						all=false;
						//System.out.println(linkedString);
					}
					//System.out.println(newsgroup.length+" messages");
					sets[count]=LS;
					//System.out.println("the size is "+LS.size());
					count++;
				}
			}
		}
	}
}