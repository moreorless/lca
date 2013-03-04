package com.cnooc.lca;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {

	
	public static void main(String args[]){
		
		Test test = new Test();
		test.test();
	}
	public void test(){
		List<String> list = new LinkedList<>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		
		printList(list);
		
		
		list.set(1, "d");
		printList(list);
	}
	
	public void printList(List<String> list){
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next() + ", ");
		}
		System.out.print("\n");
 	}
}
