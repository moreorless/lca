package com.cnooc.lca;

import java.math.BigDecimal;
import java.math.MathContext;
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
		
		
		double num = 0.00000012123434353231;
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.###"); 
		
		String fixNum = String.format("%.5g%n", num);
		System.out.println(Double.valueOf(fixNum));
		
		System.out.println(num + " ---> " + df.format(num));
		
		BigDecimal bd = new BigDecimal(num);
		int decimalPlace = 2;
	    bd = bd.setScale(decimalPlace,BigDecimal.ROUND_HALF_UP);
	    System.out.println(bd.doubleValue());
	    
		double a = 0.00001254;
		BigDecimal b = new BigDecimal(String.valueOf(a));
		BigDecimal divisor = BigDecimal.ONE;
		MathContext mc = new MathContext(2);
		System.out.println(b.divide(divisor, mc));
	}
	
	public void printList(List<String> list){
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next() + ", ");
		}
		System.out.print("\n");
 	}
}
