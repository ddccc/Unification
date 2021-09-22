// (C) OntoOO Inc / Dennis de Champeaux/ 2020 Sept
package fol;

import java.util.*;
import java.io.*;

public class ZTestRUnify {
    private static final boolean trace = true;  // reset as needed
    // private static final boolean trace = false;  // reset as needed
    public static void main (String [] arguments) {
	System.out.println("------------------------------------------");
	// The unify function return null in case of non-unification
	Parser parser = new Parser(false);
	for (int i = 1; i <= 1; i++) {	
	// for (int i = 1; i <= 2; i++) {
	// for (int i = 1; i <= 7; i++) {
	// for (int i = 1000; i <= 2000; i = i+100) {
	// for (int i = 10; i <= 18; i++) {
	    // for (int i = 100; i <= 300; i = i+10) {
	    // System.out.println("size: " + i);
	    // String a = gen1arg1(i);
	    // String b = gen1arg2(i);
	    // String a = gen1arg1f(i);
	    // String a = gen2arg1(i); // generator
	    // String b = gen2arg2(i); // generator
	    // String b = gen2arg2f(i); // generator
	    // String a = gen3arg1(i); // generator
	    // String b = gen3arg2(i); // generator
	    // String b = gen3arg2f(i); // generator
	    // String a = gen4arg1(i); // generator
	    // String b = gen4arg2(i); // generator
	    // String b = gen4arg2f(i); // generator 
	    // ad hoc exmples if the generor is not used:
	    //  not unifiable
	    // String a = "P(a)";  String b = "P(f(a))"; // disjoint classes zz3 
	    // String a = "P(a b)";  String b = "P(f(b) b)"; // incompatible arguments
	    // String a = "P(f(a))";  String b = "P(g(a))";  // function match failure
	    // String a = "P(a)";  String b = "P(b)";        // constant match failure
	    // String a = "P(a)";  String b = "Q(a)";        // predicate match failure 
	    // String a = "P(?x ?y)";  String b = "P(f(?y) ?x)";
	    // String a = "P(?x ?x)";  String b = "P(a f(b))";
	    // String a = "P(?x ?x)";  String b = "P(a b)";
	    // String a = "P(?x ?y ?x)";  String b = "P(a b ?y)";
	    // String a = "P(?x ?x)";  String b = "P(f(a) f(b))";
	    // String a = "P(?x g(f(?x)))";  String b = "P(f(?y) ?y)"; 
	    // String a = "P(?x ?y ?y)"; String b = "P(a ?x b)";
	    // String a = "P(?x g(?x))"; String b = "P(f(?y) ?y)"; 
	    // String a = "P(?x h(?z) f(?x))"; String b = "P(g(?y) ?y ?z)"; 
	    // String b = "P(?x h(?z) f(?x))"; String a = "P(g(?y) ?y ?z)"; // reverse
	    // String a = "P(?x ?y)";  String b = "P(f(?y) g(?x))";
	    // String a = "P(?x ?y ?z)";  String b = "P(f(?y) g(?z) h(?x))";
	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y) ?z ?x)"; 
	    // String a = "P(?z ?y ?x)"; String b = "P(?x ?z f(?y))"; // reverse arguments
	    // String a = "P(?y ?z ?x)"; String b = "P(?z ?x f(?y))";

	    // unifiable
	    // String a = "P(a)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(?y)"; 
	    // String a = "P(?x)";  String b = "P(?x)"; 
	    // String a = "P(?x)";  String b = "P(f(?y))"; 
	    // String a = "P(?x a)";  String b = "P(a ?x)";
 	    // String a = "P(f(?x))";  String b = "P(f(a))"; 

	    // String a = "P(?x f(?y))";  String b = "P(f(?y) f(f(?z)))"; // test post
	    // String a = "P(a ?x f(a ?x))"; String b = "P(?y g(?y) f(?z g(?z)))"; // text !!!

	    // String a = "P(?x ?x)"; String b = "P(f(b) f(a))";

	    // System.out.println("a: " + a); System.out.println("b: " + b);

	    /*
	    int reps = 30000;
	    if (trace) System.out.println("reps: " + reps);
	    runTests(parser, 2000); // warm up
	    for ( int k = 1; k <= 1; k+=100) {
		long t0 = System.currentTimeMillis();
		runTests(parser, reps);
		long delta = System.currentTimeMillis() - t0;
		System.out.println("Example i: " + i + " delta timing: " + delta);
	    }
	    // */

	    /*
	    // SMALL size base tests OK
	    {
	    runTests(parser, 2000); // warm up
	    int reps = 5000;
	    System.out.println("SMALL size base tests OK reps:" + reps);
	    for ( int k = 1; k <= 6; k++) {
		// if ( 6 == k ) continue;
		int begin = 0;
		int reps2 = 500;
		float best = 100000;
		while ( begin < reps ) {
		    long t0 = System.currentTimeMillis();
		    gen1(parser, k, reps2);
		    gen2(parser, k, reps2);
		    gen3(parser, k, reps2);
		    gen4(parser, k, reps2);
		    long delta = System.currentTimeMillis() - t0;
		    if ( delta < best ) {
			// System.out.println("delta: " + delta + " best: " + best);
			best = delta;
		    }
		    begin = begin + reps2;
		}

		System.out.println("gen1-4 k: " + k + " timing: " + 
				   String.format("%.5f", ((1.0/ (4*reps2)) * best)));
	    }
	    }
	    // */
	    // /*
	    // SMALL size base tests fail
	    {
	    runTests(parser, 2000); // warm up
	    int reps = 5000;
	    System.out.println("SMALL size base tests fail reps:" + reps);
	    for ( int k = 1; k <= 6; k++) {
		int begin = 0;
		int reps2 = 500;
		float best = 100000;
		while ( begin < reps ) {
		    long t0 = System.currentTimeMillis();
		    gen1f(parser, k, reps2);
		    gen2f(parser, k, reps2);
		    gen3f(parser, k, reps2);
		    gen4f(parser, k, reps2);
		    long delta = System.currentTimeMillis() - t0;
		    if ( delta < best ) {
			// System.out.println("delta: " + delta + " best: " + best);
			best = delta;
		    }
		    begin = begin + reps2;
		}
		System.out.println("gen1-4 k: " + k + " timing: " + 
				   String.format("%.5f", ((1.0/ (4*reps2)) * best)));
	    }
	    }
	    // */

	    /*
	    int reps = 30000;
	    if (trace) System.out.println("reps: " + reps);
	    runTests(parser, 2000); // warm up
	    for ( int k = 1; k <= 1; k+=100) {
		long t0 = System.currentTimeMillis();
		runSmallFailTests(parser, reps);
		long delta = System.currentTimeMillis() - t0;
		System.out.println("delta timing: " + delta);
	    }
	    */
	    /*
	    // alternative way to run a-b pairs
	    System.out.println("======================================");
	    Atom a1 = parseIt(parser, a);
	    // System.out.println(a1.html());
	    Atom a2 = parseIt(parser, b);
	    // System.out.println(a2.html());

	    long t0 = System.currentTimeMillis();
	    Vector out = a1.unify(a2);
	    long delta = System.currentTimeMillis() - t0;
	    // System.out.println("Substitutions:");
	    // System.out.println(Substitution.html(vec));
	    System.out.println("Example i: " + i + " delta timing: " + delta);
	    System.out.println("--------------\nout: Unification " +
	           ( null == out ? "failed" : "ok" ) );
	    // */
	    /*
	    // to print substitutions
	    if ( null != out ) {
		int lng = out.size();
		// StringBuffer sb = new StringBuffer("Substitutions: ");
		for ( int j = 0; j < lng; j++ ) {
		    Substitution sub = (Substitution) out.elementAt(j);
		    // sb.append(sub.html());
		    System.out.println(sub.html());
		}
		// System.out.println(sb.toString());
	    System.out.println();
	    }
	    // */
	}
} // end of main

   static private void clear() {} // nothing to be cleared

   static private void gen1(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1(size); b = gen1arg2(size);
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen1
   static private void gen1f(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1f(size); b = gen1arg2(size);
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen1f
   static private void gen2(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2(size);
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen2
   static private void gen2f(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2f(size);
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen2f
   static private void gen3(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2(size);
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen3
  static private void gen3f(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2f(size); 
	     clear();
	     unifyR(parser, a, b);
	 }
  } //  end gen3f
   static private void gen4(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size);
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen4
  static private void gen4f(Parser parser, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2f(size); 
	     clear();
	     unifyR(parser, a, b);
	 }
   } // end gen4f


    /*
    static private void genA(Parser parser, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argument(i); b = gen2argument(i);
	     unifyR(parser, a, b);
	 }
     }
     static private void genB(Parser parser, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argumentf(i); b = gen2argument(i);
	     unifyR(parser, a, b);
	 }
     }
     static private void genC(Parser parser, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) { 
	     a = gen2arg1(i); b = gen2arg2(i);
	     unifyR(parser, a, b);
	 }
     }
    static private void runTest1(Parser parser, int repeat) {
	// String a = gen1argument(4), b = gen2argument(4);
	String a = gen1argumentf(4), b = gen2argument(4);
	for ( int i = 0; i < repeat; i++ ) {
	     clear();
	     unifyR(parser, a, b);
	 }
     }
     static private void runTest2(Parser parser, int repeat) {
	 String a = gen2arg1(4); String b = gen2arg2(4);
	 // String a = gen2arg1(6); String b = gen2arg2f(6);
	 for ( int i = 0; i < repeat; i++ ) {
	     clear();
	     unifyR(parser, a, b);
	 }
     }

    static private void runTest3(Parser parser, int repeat) {
	String a = gen3arg1(2); String b = gen3arg2(2);
	 for ( int i = 0; i < repeat; i++ ) {
	     clear();
	     unifyR(parser, a, b);
	 }
     }
    */
     static private void runTests(Parser parser, int repeat) {
	String a, b;
	for ( int i = 0; i < repeat; i++ ) {

	    /*
	    // small set
	    for ( int j = 1; j <= 2; j++) gen1(parser, j, 3); 
	    for ( int j = 1; j <= 2; j++) gen1f(parser, j, 3); 
	    for ( int j = 3; j <= 5; j++) gen2(parser, j, 2); 
	    for ( int j = 3; j <= 5; j++) gen2f(parser, j, 2); 
	    for ( int j = 1; j <= 2; j++) gen3(parser, j, 3);
	    for ( int j = 1; j <= 2; j++) gen3f(parser, j, 3);
	    for ( int j = 1; j <= 2; j++) gen4(parser, j, 3);
	    for ( int j = 1; j <= 2; j++) gen4f(parser, j, 3);
	    */ 
	    // /*
	    // larger set
	    for ( int j = 1; j <= 3; j++) gen1(parser, j, 3); 
	    for ( int j = 1; j <= 3; j++) gen1f(parser, j, 3); 
	    for ( int j = 3; j <= 5; j++) gen2(parser, j, 2); 
	    for ( int j = 3; j <= 5; j++) gen2f(parser, j, 2); 
	    for ( int j = 1; j <= 3; j++) gen3(parser, j, 3);
	    for ( int j = 1; j <= 3; j++) gen3f(parser, j, 3);
	    for ( int j = 1; j <= 3; j++) gen4(parser, j, 3);
	    for ( int j = 1; j <= 3; j++) gen4f(parser, j, 3);
	    // */
	    clear();
	    a = "P(a)"; b = "P(f(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a b)"; b = "P(f(b) b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(a))";  b = "P(g(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a)"; b = "P(b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a)"; b = "Q(a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(f(?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(f(g(?z)) ?z))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a f(b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?x)"; b = "P(a b ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)";  b = "P(f(a) f(b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x g(f(?x)))"; b = "P(f(?y) ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?y)"; b = "P(a ?x b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x g(?x))";  b = "P(f(?y) ?y)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x h(?z) f(?x))";  b = "P(g(?y) ?y ?z)"; 
	    unifyR(parser, a, b);
	    clear();
	    b = "P(?x h(?z) f(?x))";  a = "P(g(?y) ?y ?z)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a)"; b = "P(a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(a))"; b = "P(f(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(a)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(f(?y))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x a)";  b = "P(a ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(?x))"; b = "P(f(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x f(?y))"; b = "P(f(?y) f(f(?z)))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)";  b = "P(f(?y ?z) a ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a ?x f(a ?x))"; b = "P(?y g(?y) f(?z g(?z)))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)";  b = "P(f(?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x b)"; b = "P(a ?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y)"; b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)";  b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)";  b = "P(f(?y) ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y)"; b = "P(f(?y) ?x)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a ?x b)"; b = "P(?x ?y ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?z ?y)"; b = "P(f(?z)?y ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)";  b = "P(?y ?z ?v)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z f(x))";
	    unifyR(parser, a, b);
	    //////////////////////////////////////
	    clear();
	    a = "P(a)";  b = "P(a)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(a))";  b = "P(f(a))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)";  b = "P(a)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)";  b = "P(?y)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)";  b = "P(?x)";
	    unifyR(parser, a, b);
	    clear(); 
	    a = "P(?x)";  b = "P(f(?y))";
	    unifyR(parser, a, b);
	    clear(); 
	    a = "P(?x a)";  b = "P(a ?x)";
	    unifyR(parser, a, b);
	    clear();
 	    a = "P(f(?x))";  b = "P(f(a))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x f(?y))";  b = "P(f(?y) f(f(?z)))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)"; 
	    unifyR(parser, a, b);
	    clear(); 
	    a = "P(a ?x f(a ?x))"; b = "P(?y g(?y) f(?z g(?z)))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(f(?y) f(b))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(f(?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x b)"; b = "P(a ?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y)"; b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(f(?y) ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y)"; b = "P(f(?y) ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a ?x b)"; b = "P(?x ?y ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?z ?y)"; b = "P(f(?z)?y ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z ?v)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z f(x))";
	    unifyR(parser, a, b);
	    clear();
	}
    } //  runTests

     static private void runSmallFailTests(Parser parser, int repeat) {
	String a, b;
	for ( int i = 0; i < repeat; i++ ) {

	    // small set
	    for ( int j = 2; j <= 2; j++) gen1(parser, j, 3); 
	    for ( int j = 2; j <= 2; j++) gen2(parser, j, 2); 
	    for ( int j = 2; j <= 2; j++) gen3(parser, j, 3);
	    for ( int j = 2; j <= 2; j++) gen4(parser, j, 3);

	    /*
	    // small set
	    for ( int j = 2; j <= 2; j++) gen1f(parser, j, 3); 
	    for ( int j = 2; j <= 2; j++) gen2f(parser, j, 2); 
	    for ( int j = 2; j <= 2; j++) gen3f(parser, j, 3);
	    for ( int j = 2; j <= 2; j++) gen4f(parser, j, 3);

	    clear();
	    a = "P(a)"; b = "P(f(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a b)"; b = "P(f(b) b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(a))";  b = "P(g(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a)"; b = "P(b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a)"; b = "Q(a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(f(?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(f(g(?z)) ?z))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a f(b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?x)"; b = "P(a b ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)";  b = "P(f(a) f(b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x g(f(?x)))"; b = "P(f(?y) ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?y)"; b = "P(a ?x b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x g(?x))";  b = "P(f(?y) ?y)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x h(?z) f(?x))";  b = "P(g(?y) ?y ?z)"; 
	    unifyR(parser, a, b);
	    clear();
	    b = "P(?x h(?z) f(?x))";  a = "P(g(?y) ?y ?z)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a)"; b = "P(a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(a))"; b = "P(f(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(a)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)"; b = "P(f(?y))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x a)";  b = "P(a ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(f(?x))"; b = "P(f(a))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x f(?y))"; b = "P(f(?y) f(f(?z)))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)";  b = "P(f(?y ?z) a ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a ?x f(a ?x))"; b = "P(?y g(?y) f(?z g(?z)))"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x)";  b = "P(f(?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)"; b = "P(a b))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x b)"; b = "P(a ?x))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y)"; b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)";  b = "P(a b)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?x)";  b = "P(f(?y) ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y)"; b = "P(f(?y) ?x)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)"; 
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(a ?x b)"; b = "P(?x ?y ?y)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?z ?y)"; b = "P(f(?z)?y ?x)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)";  b = "P(?y ?z ?v)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z a)";
	    unifyR(parser, a, b);
	    clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z f(x))";
	    unifyR(parser, a, b);
	    */
	}
     } // end runSmallFailTests


    static public void unifyR(Parser parser, String a, String b) {
	Atom a1 = parseIt(parser, a); Atom b1 = parseIt(parser, b); 
	clear();
	Vector out = a1.unify(b1);
    } // end unifyR

     // Generator of 1st argument that causes normally exponential blow up 
     static public String gen1arg1(int n) { return ZGen.gen1arg1(n); }
     static public String gen1arg1f(int n) { return ZGen.gen1arg1f(n); }
     static public String gen1arg2(int n) { return ZGen.gen1arg2(n); }
     static public String gen2arg1(int n) { return ZGen.gen2arg1(n); }
     static public String gen2arg2(int n) { return ZGen.gen2arg2(n); }
     static public String gen2arg2f(int n) { return ZGen.gen2arg2f(n); }
     static public String gen3arg1(int n) { return ZGen.gen3arg1(n); }
     static public String gen3arg2(int n) { return ZGen.gen3arg2(n); }
     static public String gen3arg2f(int n) { return ZGen.gen3arg2f(n); }
     static public String gen4arg1(int n) { return ZGen.gen4arg1(n); }
     static public String gen4arg2(int n) { return ZGen.gen4arg2(n); }
     static public String gen4arg2f(int n) { return ZGen.gen4arg2f(n); }

    static public Atom parseIt(Parser parser, String arg) {
	Formula out = null;
	try { out = parser.parse(arg); }
	catch (Exception ex) {
	    System.out.println("Top: " + ex.getMessage());
	    ex.printStackTrace();
	}
	if ( null == out ) {
	    System.out.println("out == null");
	    System.exit(1);
	}
	// System.out.println("out: " + out.html());
	return (Atom) out;
    }


} // end class ZTestRUnify


