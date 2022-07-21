package com.hearthstone.cards.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

// 集合并 a | b
// 集合交 a & b
// 集合减 a - b
// 括号优先级最高， 其他优先级相同，从左向右计算

public class SetExpressionParser {
	
	public static <T> SortedSet<T> parse(String expr, Map<String, Set<T>> setMap) {
		SortedSet<T> left;
		SortedSet<T> right;
		SortedSet<T> result;
		
		// 处理括号
		if(expr.startsWith("(")) {
			int bracketIndex = findBracket(expr);
			System.out.println(expr);
			System.out.println(bracketIndex);
			left = parse(expr.substring(1, bracketIndex), setMap);
			
			if(bracketIndex<expr.length()-1) {
				char op = expr.charAt(bracketIndex+1);
				right = parse(expr.substring(bracketIndex+2), setMap);
				return parseOp(op, left, right);
			}
			else 
				return left;
		}
		
		int opIndex = findLastOp(expr);
				
		// 单个元素
		if(opIndex<0) {
			System.out.println(expr);
			return new TreeSet<T>(setMap.get(expr));
		}
		
		// 多个元素
		char op = expr.charAt(opIndex);
				
		left = 	parse(expr.substring(0, opIndex), setMap);
		right = parse(expr.substring(opIndex+1), setMap);
		
		return parseOp(op, left, right);
	}
	
	private static <T> SortedSet<T> parseOp(char op, SortedSet<T> left, SortedSet<T> right) {
		
		SortedSet<T> result ;
		
		switch(op) {
		case '|':
			
			
			left.addAll(right);
			return left;
			
		case '&':
						
			result = new TreeSet<>();
			for(T l: left) {
				if(right.contains(l))
					result.add(l);
			}
			
			return result;
			
		case '-':
				
			result = new TreeSet<>(left);
			for(T l: left) {
				if(right.contains(l))
					result.remove(l);
			}
			
			return result;
		}
		
		return null;
	}
	
	private static int findBracket(String expr) {
		
		int i = 1;
		int bCount = 1;
		
		while(i<expr.length()) {
			
			if(expr.charAt(i)=='(')
				bCount++;
			else if(expr.charAt(i)==')') {
				bCount--;
				if(bCount==0) {
					return i;
				}
			}
						
			i++;
		}
		
		return i;
	}
	
	private static int findLastOp(String expr) {
		
		int i=expr.length()-1;
		int bCount = 0;
		while(i>=0) {
			
			if( (expr.charAt(i)=='|' || expr.charAt(i)=='&' || expr.charAt(i)=='-')  ) {
				if(bCount==0)
					return i;
			}
			else if(expr.charAt(i)==')')
				bCount++;
			else if(expr.charAt(i)=='(')
				bCount--;
			
			i--;
		}
		
		return i;
		
	}
	
	
	public static void main(String args[]) {
		
		Set<Object> a = new HashSet<>();
		
		a.add("b");
		a.add("c");
		a.add("a");
		
		Set<Object> b = new HashSet<>();
		b.add("a");
		b.add("b");
		b.add("d");
		
		Map<String, Set<Object>> setMap = new HashMap<>();
		setMap.put("a", a);
		setMap.put("b", b);
		
		SortedSet<Object> result = parse("a|b", setMap);
		System.out.println(result);
		
		result = parse("a-b", setMap);
		System.out.println(result);
		
		result = parse("(a&b)|b", setMap);
		System.out.println(result);
		
		result = parse("((a|b)&(b|b))", setMap);
		System.out.println(result);
		
		
	}
	
}
