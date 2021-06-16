// File: c:/bsd/rigel/Unification/fol/ZGen.java
// Date: Mon May 17 12:22:23 2021
// (C) OntoOO Inc / Dennis de Champeaux/

package fol;

import java.util.*;
import java.io.*;


public class ZGen {
    // Generator of 1st argument that causes normally exponential blow up 
    static public String gen1arg1(int n) { 
	if ( n <= 0 ) {
	    System.out.println("gen1argument n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 1; i <= n; i++) {
	    String x = "?x" + i;
	    sb.append("h(" + x + " " + x + ") ");
	}
	for (int i = 2; i <= n+1; i++) {
	    String y = "?y" + i + " ";
	    sb.append(y);
	}
	sb.append("?x" + (n+1) + ")");
	return sb.toString();	    
    } // end  gen1arg1
    // Generator of 2nd argument that causes normally exponential blow up
   static public String gen1arg1f(int n) { 
	if ( n <= 0 ) {
	    System.out.println("gen1argumentf n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 1; i <= n; i++) {
	    String x = "?x" + i;
	    sb.append("h(" + x + " " + x + ") ");
	}
	for (int i = 2; i <= n+1; i++) {
	    String y = "?y" + i + " ";
	    sb.append(y);
	}
	// sb.append("?x" + (n) + ")");
	sb.append("aa)");
	return sb.toString();	    
    } // end  gen1arg1f
    static public String gen1arg2(int n) { 
	if ( n <= 0 ) {
	    System.out.println("gen2argument n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 2; i <= n+1; i++) {
	    String x = "?x" + i + " ";
	    sb.append(x);
	}
	for (int i = 1; i <= n; i++) {
	    String y = "?y" + i;
	    sb.append("h(" + y + " " + y + ") ");
	}
	sb.append("?y" + (n+1) + ")");
	return sb.toString(); 
    } // end gen1arg2
    static public String gen2arg1(int n) { 
	if ( n <= 0 ) {
	    System.out.println("gen2arg1 n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 1; i <= n; i++) {
	    String argi = "?x" + i;
	    for (int j = 1; j < i; j++) sb.append("f(");
	    sb.append(argi);
	    for (int j = 1; j < i; j++) sb.append(")");
	    if ( i < n ) sb.append(" ");
	} 
	sb.append(")");
	return sb.toString(); 
    } // end gen1arg1
    static public String gen2arg2(int n) { 
	if ( n <= 0 ) {
	    System.out.println("gen2arg2( n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 2; i <= n; i++) {
	    String argi = "?x" + i;
	    for (int j = 1; j < i; j++) sb.append("f(");
	    sb.append(argi);
	    for (int j = 1; j < i; j++) sb.append(")");
	    sb.append(" ");
	}
	for (int j = 1; j <= n; j++) sb.append("f(");
	sb.append("?y");
	for (int j = 1; j <= n; j++) sb.append(")"); 
	sb.append(")");
	return sb.toString(); 
    } // end gen2arg2
    static public String gen2arg2f(int n) { 
	if ( n <= 0 ) {
	    System.out.println("gen2arg2f n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 2; i <= n; i++) {
	    String argi = "?x" + i;
	    for (int j = 1; j < i; j++) sb.append("f(");
	    sb.append(argi);
	    for (int j = 1; j < i; j++) sb.append(")");
	    sb.append(" ");
	}
	for (int j = 1; j <= n; j++) sb.append("f(");
	sb.append("?x1");
	for (int j = 1; j <= n; j++) sb.append(")"); 
	sb.append(")");
	return sb.toString(); 
    } // end gen2arg2f
     static public String gen3arg1(int n) {
	if ( n <= 0 ) {
	    System.out.println("gen3arg1 n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 0; i <= n; i++) {
	    String x = "?x" + (i+1);
	    sb.append("?x" + i + " f(" + x + " " + x + ") ");
	}
	sb.append(")");
	return sb.toString(); 
     } // end gen3arg1
     static public String gen3arg2(int n) {
	if ( n <= 0 ) {
	    System.out.println("gen3arg2 n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 0; i <= n-1; i++) {
	    String y = "?y" + i;
	    sb.append("f(" + y + " " + y + ") " + y + " ");
	}
	String y = "?y" + n;
	sb.append("f(" + y + " " + y + ") " + "?y" + (n+1) + ")");
	return sb.toString();
     } // end gen3arg2
     static public String gen3arg2f(int n) {
	if ( n <= 0 ) {
	    System.out.println("gen3arg2f n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 0; i <= n-1; i++) {
	    String y = "?y" + i;
	    sb.append("f(" + y + " " + y + ") " + y + " ");
	}
	// String y = "?y" + n;
	String y = "?x" + 0;
	sb.append("f(" + y + " " + y + ") " + "?y" + (n+1) + ")");
	return sb.toString();
     } // end gen3arg2f
     static public String gen4arg1(int n) {
	if ( n <= 0 ) {
	    System.out.println("gen4arg1 n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 1; i <= n; i++) {
	    String x = "?x" + (i);
	    String y = "?y" + (i);
	    sb.append(x + " " + y + " ");
	}
	sb.append(")");
	return sb.toString(); 
     } // end gen4arg1
     static public String gen4arg2(int n) {
	if ( n <= 0 ) {
	    System.out.println("gen4arg2 n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 1; i <= n; i++) {
	    String y = "?y" + i;
	    String x = "?x" + (i+1);
	    sb.append("g(" + y + " " + y + ") " + "f(" + x + ") ");
	}
	sb.append(")");
	return sb.toString();
     } // end gen4arg2
     static public String gen4arg2f(int n) {
	if ( n <= 0 ) {
	    System.out.println("gen4arg2f n <= 0");
	    System.exit(1);
	}
	StringBuffer sb = new StringBuffer("P(");
	for (int i = 1; i <= n; i++) {
	    String y = "?y" + i;
	    String x = "?x" + (i+1);
	    // sb.append("g(" + y + " " + y + ") " + "f(" + x + ") ");
	    sb.append("g(" + y + " " + y + ") " );
	    if ( i < n ) sb.append("f(" + x + ") ");
	    else sb.append("?x1");
	}
	sb.append(")");
	return sb.toString();
     } // end gen4arg2f

} // end ZGen
