import java.util.ArrayList;
import java.util.List;


public class Fibonacci {
	
	public static void main(String[] args){
		
		System.out.println(getNthFibonacci(1));
		System.out.println(getNthFibonacci(2));
		System.out.println(getNthFibonacci(3));
		System.out.println(getNthFibonacci(4));
		System.out.println(getNthFibonacci(5));
		System.out.println(getNthFibonacci(6));
		System.out.println(getNthFibonacci(7));
		System.out.println(getNthFibonacci(8));
		System.out.println(getNthFibonacci(9));
		System.out.println(getNthFibonacci(10));
		
	}
	
	public static long getNthFibonacci(long n){
		double termA = Math.pow((1+Math.sqrt(5))/2, n);
		double termB = Math.pow((1-Math.sqrt(5))/2, n);
		double factor = 1 / Math.sqrt(5);
		
		return Math.round(factor * (termA - termB));
	}
	
	
	
	private static Member member = new Member(); 
	
	public void printfriendsByLevel(Member member){
		
		List<Member> actualLevelFriends = member.friends;
		List<Member> nextLevelFriends = new ArrayList<Member>();
		
		int levelCounter = 1;
		while(actualLevelFriends != null && actualLevelFriends.size()>0){
			System.out.println("Level " + levelCounter);
			for(Member friend : actualLevelFriends){
				System.out.println("Friend Name - " + friend.name);
				
				if(friend.friends != null && friend.friends.size()>0)
					nextLevelFriends.addAll(friend.friends);
			}
			
			actualLevelFriends.clear();
			actualLevelFriends.addAll(nextLevelFriends);
			nextLevelFriends.clear();
			levelCounter++;
		}
	}

}
