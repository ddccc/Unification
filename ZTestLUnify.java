// (C) OntoOO Inc / Dennis de Champeaux/ 2020 Sept
package fol;

import java.util.*;
import java.io.*;

// import ZUgenerators.java;

public class ZTestLUnify {
    public static int cntf = 0; // finish invocation counter
    private static final Boolean trace = true;  // reset as needed
    // private static final Boolean trace = false;  // reset as needed
    public static void main (String [] arguments) {
	if (trace) System.out.println("------------------------------------------");
	// The unify function return null in case of non-unification

	// Parser parser = new Parser(false);
	// for (int i = 100; i <= 1000; i=i+100) {
	// for (int i = 100; i <= 110; i++) {
	// for (int i = 10; i <= 18; i++) {
	// for (int i = 800; i <= 810; i++) {
	// for (int i = 1500; i <= 1510; i++) {
	for (int i = 1; i <= 1; i++) {
	    // for (int i = 1; i <= 7; i++) {
	    // if (trace) System.out.println("size: " + i);
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
	    // System.out.println("a: " + a);
	    // System.out.println("b: " + b);
	    // ad hoc exmples if the generor is not used:
	    //  not unifiable
	    // String a = "P(a)";  String b = "P(f(a))"; // disjoint classes zz3 
	    // String a = "P(a b)";  String b = "P(f(b) b)"; // incompatible arguments
	    // String a = "P(f(a))";  String b = "P(g(a))";  // function match failure
	    // String a = "P(a)";  String b = "P(b)";        // constant match failure
	    // String a = "P(a)";  String b = "Q(a)";        // predicate match failure 
	    // String a = "P(?x)";  String b = "P(f(?x))"; //
	    // String a = "P(?x ?x)";  String b = "P(f(g(?z)) ?z))"; // 
	    // String a = "P(?x ?x)";  String b = "P(a f(b))"; // 
	    // String a = "P(?x ?x)";  String b = "P(a b)"; // 
	    // String a = "P(?x ?y ?x)";  String b = "P(a b ?y)"; // 
	    // String a = "P(?x ?x)";  String b = "P(f(a) f(b))"; // 
	    // String a = "P(?x g(f(?x)))";  String b = "P(f(?y) ?y)"; // 
	    // String a = "P(?x ?y ?y)"; String b = "P(a ?x b)"; // 
	    // String a = "P(?x g(?x))"; String b = "P(f(?y) ?y)";  //
	    // String a = "P(?x h(?z) f(?x))"; String b = "P(g(?y) ?y ?z)"; // 
	    // String b = "P(?x h(?z) f(?x))"; String a = "P(g(?y) ?y ?z)"; //  
	    // String a = "P(?x ?y)";  String b = "P(f(?y) g(?x))"; // 
	    // String a = "P(?x ?y ?z)";  String b = "P(f(?y) g(?z) h(?x))"; // 

	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y) ?z ?x)"; // 
	    // String a = "P(?z ?y ?x)"; String b = "P(?x ?z f(?y))"; // reverse arguments
	    // String a = "P(?y ?z ?x)"; String b = "P(?z ?x f(?y))"; // reverse arguments

	    // String a = "P(?x ?y)";  String b = "P(f(?y) ?x)";
	    // String a = "P(?x ?x)";  String b = "P(a f(b))";
	    // String a = "P(?x ?x)";  String b = "P(a b)";
	    // String a = "P(?x ?y ?x)";  String b = "P(a b ?y)";
	    // String a = "P(?x ?x)";  String b = "P(f(a) f(b))";
	    // String a = "P(?x g(f(?x)))";  String b = "P(f(?y) ?y)"; 
	    // String a = "P(?x ?y ?y)"; String b = "P(a ?x b)"; 
	    // String a = "P(?x h(?z) f(?x))"; String b = "P(g(?y) ?y ?z)"; 
	    // String b = "P(?x h(?z) f(?x))"; String a = "P(g(?y) ?y ?z)"; // reverse

	    // unifiable
	    // String a = "P(a)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(?y)"; 
	    // String a = "P(?x)";  String b = "P(?x)"; 
	    // String a = "P(?x)";  String b = "P(f(?y))"; 
	    // String a = "P(?x a)";  String b = "P(a ?x)";
 	    // String a = "P(f(?x))";  String b = "P(f(a))"; 

	    // String a = "P(?x f(?y))";  String b = "P(f(?y) f(f(?z)))"; // test post
	    // String a = "P(a ?x f(a ?x))"; String b = "P(?y g(?y) f(?z g(?z)))"; // text

	    String a = "P(?x ?y ?z)"; String b = "P(?y ?z ?v)"; // ok

	    // /*
	    LunificationContext luc = new LunificationContext();
	    Vector out = luc.unify(a, b);
	    //  + " fcnt: " + luc.getFcnt());
	    System.out.println("--------------\nout: Unification " +
			       ( null == out ? "failed" : "ok" ) );
	    // */
	    /*
	    long t0 = System.currentTimeMillis();
	    for ( int j = 0; j < 20; j++) {
		LunificationContext luc = new LunificationContext();
		Vector out = luc.unify(a, b);
	    }
	    long delta = System.currentTimeMillis() - t0;
	    System.out.println("Example i: " + i + " delta timing: " + delta/(1.0*20));
	    //	    */

	    /*
	    LunificationContext luc = new LunificationContext();
	    runTests(luc, 2000); // warm up
	    long t0 = System.currentTimeMillis();
	    int reps = 100;
	    // for ( int k = 1; k <= 100; k++) {
	    for ( int k = 1; k <= reps; k++) {
		Vector out = null;
		luc = new LunificationContext();
		out = luc.unify(a, b);
	    }
	    long delta = System.currentTimeMillis() - t0;
	    System.out.println("Example i: " + i + " delta timing: " + delta/(1.0*reps));
	    // */

	    /*
	    // Tests with generators
	    int k, reps; long t0, delta;
	    LunificationContext ontooo = new LunificationContext();
	    runTests(ontooo, 2000); // warm up

	    t0 = System.currentTimeMillis(); k = 1000; reps = 400;
	    gen1(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen1  k: " + k + " delta timing: " + 
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    t0 = System.currentTimeMillis();
	    gen1f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen1f k: " + k + " delta timing: " + 
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    t0 = System.currentTimeMillis(); k = 200; reps = 200;
	    gen2(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen2  k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));
	    t0 = System.currentTimeMillis();
	    gen2f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen2f k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    t0 = System.currentTimeMillis(); k = 600; reps = 1000;
	    gen3(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen3  k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));
	    t0 = System.currentTimeMillis();
	    gen3f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen3f k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    t0 = System.currentTimeMillis(); k = 600; reps = 1000;
	    gen4(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen4  k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));
	    t0 = System.currentTimeMillis();
	    gen4f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen4f k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    System.out.println();

	    // */

	    // LunificationContext ontooo = new LunificationContext();

	    /*
	    // size base test
	    // LunificationContext ontooo = new LunificationContext();
	    runTests(ontooo, 2000); // warm up
	    for ( int k = 100; k <= 1000; k+=100) { // gen1/f
	    // for ( int k = 200; k <= 200; k+=100) { // gen2/f
	    // for ( int k = 600; k <= 600; k+=100) { // gen3
		long t0 = System.currentTimeMillis();
		cntf = 0;
		int reps = 400;
		// gen1(ontooo, k, reps);
		gen1f(ontooo, k, reps);
		// gen2(ontooo, k, reps);
		// gen2f(ontooo, k, reps);
		// gen3(ontooo, k, reps);
		// gen3f(ontooo, k, reps);
		// add more here based on:
		long delta = System.currentTimeMillis() - t0;
		System.out.println("k: " + k + " delta timing: " +
				   String.format("%.2f", ((1.0/ reps) * delta)) +
				   " cntf: " + (cntf/reps));
	    }
	    // */

	    /*
	    // LunificationContext ontooo = new LunificationContext();
	    int reps = 30000;
	    if (trace) System.out.println("reps: " + reps);
	    runTests(ontooo, 2000); // warm up
	    for ( int k = 1; k <= 1; k+=100) {
		long t0 = System.currentTimeMillis();
		runTests(ontooo, reps);
		long delta = System.currentTimeMillis() - t0;
		System.out.println("delta timing: " + delta);
	    }
	    // */
	    /*
	    // SMALL size base tests OK
	    LunificationContext ontooo = new LunificationContext();
	    {
	    runTests(ontooo, 2000); // warm up
	    int reps = 5000;
	    System.out.println("SMALL size base tests OK/ reps:" + reps);
	    for ( int k = 1; k <= 6; k++) {
		int begin = 0;
		int reps2 = 500;
		float best = 100000;
		while ( begin < reps ) {
		    long t0 = System.currentTimeMillis();
		    gen1(ontooo, k, reps2);
		    gen2(ontooo, k, reps2);
		    gen3(ontooo, k, reps2);
		    gen4(ontooo, k, reps2);
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
	    //	*/
	    /*
	    {
	    // SMALL size base tests fail
	    // LunificationContext ontooo = new LunificationContext();
	    runTests(ontooo, 2000); // warm up
	    int reps = 5000;
	    System.out.println("SMALL size base tests fail/ reps:" + reps);
	    for ( int k = 1; k <= 6; k++) {
		int begin = 0;
		int reps2 = 500;
		float best = 100000;
		while ( begin < reps ) {
		    long t0 = System.currentTimeMillis();
		    gen1f(ontooo, k, reps2);
		    gen2f(ontooo, k, reps2);
		    gen3f(ontooo, k, reps2);
		    gen4f(ontooo, k, reps2);
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
	    {
	    // Large size base tests OK&fail
	    runTests(ontooo, 2000); // warm up
	    int reps = 5000;
	    System.out.println("Large size base tests OK&fail reps:" + reps);
	    for ( int k = 5; k <= 50; k = k*2) {
		// { int k = 40;
		int begin = 0;
		int reps2 = 500;
		float best = 100000;
		while ( begin < reps ) {
		    long t0 = System.currentTimeMillis();
		    gen1(ontooo, k, reps2);
		    gen1f(ontooo, k, reps2);
		    gen2(ontooo, k, reps2);
		    gen2f(ontooo, k, reps2);
		    gen3(ontooo, k, reps2);
		    gen3f(ontooo, k, reps2);
		    gen4(ontooo, k, reps2);
		    gen4f(ontooo, k, reps2);
		    long delta = System.currentTimeMillis() - t0;
		    if ( delta < best ) {
			// System.out.println("delta: " + delta + " best: " + best);
			best = delta;
		    }
		    begin = begin + reps2;
		}
		System.out.println("gen1-4 k: " + k + " delta timing: " + 
				   String.format("%.5f", ((1.0/ (8*reps2)) * best)));
		// reps = reps/2;
	    }
	    }

	    /*
	    LunificationContext ontooo = new LunificationContext();
	    int reps = 30000;
	    if (trace) System.out.println("reps: " + reps);
	    runTests(ontooo, 2000); // warm up
	    for ( int k = 1; k <= 1; k+=100) {
		long t0 = System.currentTimeMillis();
		runSmallFailTests(ontooo, reps);
		long delta = System.currentTimeMillis() - t0;
		System.out.println("delta timing: " + delta);
	    }
	    // */ 


	}
    } // end of main
    static private void gen1(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1(size); b = gen1arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
    static private void gen1f(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1f(size); b = gen1arg2(size); 
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
   static private void gen2(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   }
   static private void gen2f(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2f(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   }
   static private void gen3(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   }
   static private void gen3f(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2f(size); 
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   }
   static private void gen4(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen4
   static private void gen4f(LunificationContext ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size); 
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen4f


    /*
     static private void genA(LunificationContext ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argument(i); b = gen2argument(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void genB(LunificationContext ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argumentf(i); b = gen2argument(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void genC(LunificationContext ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) { 
	     a = gen2arg1(i); b = gen2arg2(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
    */
     static private void runTests(LunificationContext ontooo, int repeat) {
	String a, b;
	for ( int i = 0; i < repeat; i++ ) {

	    /*
	    // small set
	    for ( int j = 1; j <= 2; j++) gen1(ontooo, j, 3); 
	    for ( int j = 1; j <= 2; j++) gen1f(ontooo, j, 3); 
	    for ( int j = 3; j <= 5; j++) gen2(ontooo, j, 2); 
	    for ( int j = 3; j <= 5; j++) gen2f(ontooo, j, 2); 
	    for ( int j = 1; j <= 2; j++) gen3(ontooo, j, 3);
	    for ( int j = 1; j <= 2; j++) gen3f(ontooo, j, 3);
	    for ( int j = 1; j <= 2; j++) gen4(ontooo, j, 3);
	    for ( int j = 1; j <= 2; j++) gen4f(ontooo, j, 3);
	    // */
	    // /*
	    // larger set
	    for ( int j = 1; j <= 3; j++) gen1(ontooo, j, 3); 
	    for ( int j = 1; j <= 3; j++) gen1f(ontooo, j, 3); 
	    for ( int j = 3; j <= 5; j++) gen2(ontooo, j, 2); 
	    for ( int j = 3; j <= 5; j++) gen2f(ontooo, j, 2); 
	    for ( int j = 1; j <= 3; j++) gen3(ontooo, j, 3);
	    for ( int j = 1; j <= 3; j++) gen3f(ontooo, j, 3);
	    for ( int j = 1; j <= 3; j++) gen4(ontooo, j, 3);
	    for ( int j = 1; j <= 3; j++) gen4f(ontooo, j, 3);
	    // */

	    ontooo.clear();
	    a = "P(a)"; b = "P(f(a))";
	    Vector out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a b)"; b = "P(f(b) b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(a))";  b = "P(g(a))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a)"; b = "P(b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a)"; b = "Q(a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(f(?x))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(f(g(?z)) ?z))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(a f(b))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?x)"; b = "P(a b ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)";  b = "P(f(a) f(b))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x g(f(?x)))"; b = "P(f(?y) ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?y)"; b = "P(a ?x b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x g(?x))";  b = "P(f(?y) ?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x h(?z) f(?x))";  b = "P(g(?y) ?y ?z)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    b = "P(?x h(?z) f(?x))";  a = "P(g(?y) ?y ?z)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a)"; b = "P(a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(a))"; b = "P(f(a))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(a)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(f(?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x a)";  b = "P(a ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(?x))"; b = "P(f(a))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x f(?y))"; b = "P(f(?y) f(f(?z)))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)";  b = "P(f(?y ?z) a ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a ?x f(a ?x))"; b = "P(?y g(?y) f(?z g(?z)))"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)";  b = "P(f(?x))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(a b))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x b)"; b = "P(a ?x))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y)"; b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)";  b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)";  b = "P(f(?y) ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y)"; b = "P(f(?y) ?x)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a ?x b)"; b = "P(?x ?y ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?z ?y)"; b = "P(f(?z)?y ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)";  b = "P(?y ?z ?v)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z f(x))";
	    out = ontooo.unify(a, b);
	}
    } // end runTests

    static private void runSmallFailTests(LunificationContext ontooo, int repeat) {
	String a, b;
	for ( int i = 0; i < repeat; i++ ) {
	    // small set
	    // time ratio 8 -> 1.21 -> // 1.077
	    // 9 -> 1.072
	    for ( int j = 9; j <= 9; j++) gen1f(ontooo, j, 3); 
	    for ( int j = 9; j <= 9; j++) gen2f(ontooo, j, 2); 
	    for ( int j = 9; j <= 9; j++) gen3f(ontooo, j, 3);
	    for ( int j = 9; j <= 9; j++) gen4f(ontooo, j, 3);
	    /*
	    ontooo.clear();
	    a = "P(a)"; b = "P(f(a))";
	    Vector out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a b)"; b = "P(f(b) b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(a))";  b = "P(g(a))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a)"; b = "P(b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a)"; b = "Q(a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(f(?x))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(f(g(?z)) ?z))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(a f(b))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?x)"; b = "P(a b ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)";  b = "P(f(a) f(b))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x g(f(?x)))"; b = "P(f(?y) ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?y)"; b = "P(a ?x b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x g(?x))";  b = "P(f(?y) ?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x h(?z) f(?x))";  b = "P(g(?y) ?y ?z)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    b = "P(?x h(?z) f(?x))";  a = "P(g(?y) ?y ?z)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a)"; b = "P(a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(a))"; b = "P(f(a))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(a)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(f(?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x a)";  b = "P(a ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(?x))"; b = "P(f(a))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x f(?y))"; b = "P(f(?y) f(f(?z)))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)";  b = "P(f(?y ?z) a ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a ?x f(a ?x))"; b = "P(?y g(?y) f(?z g(?z)))"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)";  b = "P(f(?x))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(a b))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x b)"; b = "P(a ?x))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y)"; b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)";  b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)";  b = "P(f(?y) ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y)"; b = "P(f(?y) ?x)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y) ?z ?x)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) f(?x ?z) f(?x ?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(a ?x b)"; b = "P(?x ?y ?y)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?z ?y)"; b = "P(f(?z)?y ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)";  b = "P(?y ?z ?v)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z f(x))";
	    out = ontooo.unify(a, b);
	    */
	}
    } // end runSmallFailTests


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

} // end class ZTestLUnify

abstract class Layer {
    // protected static final Boolean trace = false;  // reset as needed
    protected static final Boolean trace = true;  // reset as needed
    // intrastructure for a layer
    private Vector parents = new Vector(); // for up refs
    public Vector getParents() { return parents; }
    protected void addParent(Layer layer) { 
	parents.addElement(layer);
    }	
    private Vector links = new Vector(); // for horizontal refs
    public void addLink(Layer l) { links.addElement(l); }
    public Vector getLinks() { return links; }
    private boolean complete = false; // to capture whether node can be further ignored
    public void setCompleteTrue() { complete = true; }
    public  boolean getComplete() { return complete; }
    private Layer pointer = null; // equivalence class
    public Layer getPointer() { return pointer; }
    public void setPointer(Layer l) { pointer = l; }
    abstract String html(); // for display
    abstract String string(); // for display
    private Layer l2 = null; // for post processing
    public void setL2(Layer lx) { l2 = lx; }
    public Layer ready() { return l2; }
} // end  Layer

class ConstantLayer extends Layer { // for constants
    protected Symbol symbol;
    ConstantLayer(Symbol s, Layer parent) {
	symbol = s;
	addParent(parent);
    }
    public Symbol getSymbol() { return symbol; }
    public String html() { return "ConstantLayer " + string(); }
    public String string() { return symbol.getName(); }
} // end ConstantLayer

class VariableLayer extends Layer { // for variables
    protected Variable variable;
    VariableLayer(Variable v, Layer parent) {
	variable = v;
	addParent(parent);
    }
    public Variable getVariable() { return variable; }
    public String html() { return "VariableLayer " + variable.html(); }
    public String string() { return variable.html(); }
    private Subsl subs = null; // the substitution of the variable
    public void setSubs(Subsl s) { subs = s; }
    public Subsl getSubs() { return subs; }
} // end VariableLayer

class CompositeLayer extends Layer {
    protected Symbol symbol; // name of predicate or function
    protected Vector args;
    CompositeLayer(Formula formula, Layer parent) {
	if ( formula instanceof Atom ) {
	    Atom ax = (Atom) formula; // predicate
	    symbol = ax.getPredicate();
	    args = ax.getArgs();
	    // there is no parent, thus |parents| = 0
	} else { 
	    FTerm ft = (FTerm) formula; // function
	    symbol = ft.getFunction();
	    args = ft.getArgs();
	    addParent(parent);
	}
    }
    CompositeLayer(Symbol head, Vector argsx) {
	// This constructor is used by the post-processor
	symbol = head; layeredArgs = argsx;
	int lng = argsx.size();
	args = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Layer li = (Layer) argsx.elementAt(i);
	    if ( trace ) 
		System.out.println("li : " + li.html());
	    if ( li instanceof ConstantLayer) {
		ConstantLayer lic = (ConstantLayer) li;
		args.addElement(lic.getSymbol());
		continue;
	    }
	    if ( li instanceof VariableLayer) {
		VariableLayer liv = (VariableLayer) li;
		args.addElement(liv.getVariable());
		continue;
	    }
	    CompositeLayer lix = (CompositeLayer) li;
	    Symbol symbolx = lix.getSymbol();
	    Vector argsy = lix.getArgs();
	    FTerm ft = new FTerm(symbolx, argsy);
	    args.addElement(ft);
	}
    } // end CompositeLayer
    public Symbol getSymbol() { return symbol; }
    public Vector getArgs() { return args; }
    protected Vector layeredArgs = new Vector();
    protected void addlayeredArg(Layer in) { layeredArgs.addElement(in); }
    protected Vector getLayeredArgs() { return layeredArgs; }

    // Add the layers for up-references
    public void createDownLayers(LunificationContext luc) {
	int lng = args.size();
	if ( trace )
	    System.out.println("CompositeLayer-createDownLayers lng: " + lng);
	for (int i = 0; i < lng; i++) { 
	    Term term = (Term)  args.elementAt(i);
	    if ( trace )
		System.out.println("term: " + i + " " + term.html());
	    if ( term instanceof Symbol ) {
		Symbol s = (Symbol) term;
		if ( !(s instanceof Variable ) ) { // constant
		    ConstantLayer cl = luc.getConstantlayer(s);
		    if ( null != cl ) {
			cl.addParent(this);
			addlayeredArg(cl);
			continue;
		    }		
		    cl = new ConstantLayer(s, this);
		    luc.addConstantlayer(s, cl);
		    luc.addFnode(cl);
		    addlayeredArg(cl);
		    continue;
		}
		// term is variable/ check first whether encounterd already
		Variable v = (Variable) term;
		VariableLayer vl = luc.getVariablelayer(v);
		if ( null != vl ) {
		    vl.addParent(this);
		    addlayeredArg(vl);
		    continue;
		}		
		// create variable-layer
		vl = new VariableLayer(v, this);
		luc.addVariablelayer(v, vl);
		luc.addVnode(vl);
		addlayeredArg(vl);
		continue;
	    }
	    // term is FTerm thus create a FTerm-layer
	    FTerm fterm = (FTerm) term;
	    FTermLayer ftl = new FTermLayer(fterm, this);
	    luc.addFnode(ftl);
	    addlayeredArg(ftl);
	    // recurse further down
	    if ( trace ) 
		System.out.println("CompositeLayer-createDownLayers/ recurse down fterm ");
	    ftl.createDownLayers(luc);
	    if ( trace ) luc.showNodes("CL exit of fterm " + fterm.html() + " ");
	}
    } // end createDownLayers
    public String html() { return  "CompositeLayer " + string(); }
    public String string() { 
	FTerm ft = new FTerm(symbol, args);
	return ft.html();
    }
} // end class CompositeLayer

class AtomLayer extends CompositeLayer {
    AtomLayer(Atom ax, Layer parent) { super(ax, parent); }
}
class  FTermLayer extends CompositeLayer { 
    FTermLayer(Term tm, Layer parent) { super(tm, parent); }
}

class Subsl {
    private VariableLayer vl;
    private Layer val;
    Subsl(VariableLayer vx, Layer v) {
	vl = vx; val = v;
    }
    public VariableLayer getVariableLayer() { return vl; }
    public Layer getVal() { return val; }
}

class LunificationContext {
   public void clear() {
       fnodes = new Vector();
       vnodes = new Vector();
       htc = new Hashtable();
       htv = new Hashtable();
       sigma = new Vector();

    }
    // private int fcnt = 0; // for tracing
    // public int getFcnt() { return fcnt; }
    // private static Boolean trace = false;  // reset as needed
    private static Boolean trace = true; 
    Parser parser = new Parser(false);
    private String arg1;
    private String arg2;
    LunificationContext() {}

    public Vector unify(String a1, String a2) {
	arg1 = a1; arg2 = a2;
	// Text to lisp-like tree transformation using parser in the fol package
	parseArguments();
	if (trace) System.out.println("----------- parsing OK.");
	createAtomLayers();
	if (trace) System.out.println("----------- layers done.");
	/*
	if (trace) { // to show the parents of nodes
	    int lg = fnodes.size();
	    for (int i = 0; i < lg; i++) {
		Layer cl = (Layer) fnodes.elementAt(i);
		Vector parents = cl.getParents();
		int lg2 = parents.size();
		System.out.println("cl: " + cl.html());
		for (int z = 0; z < lg2; z++)
		    System.out.println("   cli: " + ((Layer)parents.elementAt(z)).html()); 
	    }
	    lg = vnodes.size();
	    for (int i = 0; i < lg; i++) {
		Layer cl = (Layer) fnodes.elementAt(i);
		Vector parents = cl.getParents();
		int lg2 = parents.size();
		System.out.println("cl: " + cl.html());
		for (int z = 0; z < lg2; z++)
		    System.out.println("   cli: " + ((Layer)parents.elementAt(z)).html()); 
	    }
	}
	*/
	Vector out = lunify(); // returns null or substitution
	// show result:
	if (trace) System.out.println("--------------\nout: Unification " + 
				      ( null == out ? "failed" : "ok" ) );
	if (  null == out ) return out; // when failed
	    // post processing
	if (trace) System.out.println("----------- post processing");
	Vector subsList = buildSigma(getVnodes());
	if (trace) { 
	    int lng = subsList.size(); // # variables
	    System.out.println("Ordered substitution lng " + lng);
	    for (int j = 0; j < lng; j++) {
		Subsl s = (Subsl) subsList.elementAt(j);
		VariableLayer vx = s.getVariableLayer();
		Layer val = s.getVal();
		String valstr = val.string();
		System.out.print("vx " + vx.html() + "  ");
		System.out.println("val " + valstr);
	    }
	}
	// subsList is the ordered substitution when unification ok
	return subsList;
    } // end unify

    private Atom a1, a2;
    public void parseArguments() {
	a1 = parseIt(parser, arg1);
	a2 = parseIt(parser, arg2);
    } // end parseArguments
    private Atom parseIt(Parser parser, String arg) {
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
	if ( trace) System.out.println("ParseIt out: " + out.html());
	return (Atom) out;
    } // end  parseIt()
    private Vector fnodes = new Vector();
    private Vector vnodes = new Vector();
    public Vector getVnodes() { return vnodes; }
    public void addFnode(Layer layer) { fnodes.addElement(layer); }
    public void addVnode(Layer layer) { vnodes.addElement(layer); }
    public int numFnodes() { return fnodes.size(); }
    public int numVnodes() { return vnodes.size(); }

    public void showNodes(String loc) {
	System.out.println(loc + " numFnodes(): " + numFnodes() + 
			   " numVnodes(): " + numVnodes());
    } // end showNodes

    private AtomLayer la1, la2;
    public void createAtomLayers() {
	la1 = new AtomLayer(a1, null); addFnode(la1);
	la1.createDownLayers(this);
	if ( trace ) showNodes("AtomLayer(a1) exit");
	la2 = new AtomLayer(a2, null); addFnode(la2);
	la2.createDownLayers(this);
	if ( trace ) showNodes("AtomLayer(a2) exit");
    } // end createAtomLayers

    // for guaranteeting no duplicates of constants
    private Hashtable htc = new Hashtable();
    public ConstantLayer getConstantlayer(Symbol s) {
	return (ConstantLayer) htc.get(s); // returns null if not found
    }
    public void addConstantlayer(Symbol s, ConstantLayer cl) {
	htc.put(s, cl);
    }
    // for guaranteeting no duplicates of varaiables
    private Hashtable htv = new Hashtable();
    public VariableLayer getVariablelayer(Variable v) {
	return (VariableLayer) htv.get(v); // returns null if not found
    }
    public void addVariablelayer(Variable v, VariableLayer vl) {
	htv.put(v, vl);
    }
    // for sustitutions
    private Vector sigma = new Vector(); // contains Subsl elements
    public Vector getSigma() { return sigma; }
    public void addSubs(Subsl s) { sigma.addElement(s); }

    // launch the unifier 
    public Vector lunify() {
	// pre-check
	if ( !la1.getSymbol().getName().equals(la2.getSymbol().getName()) ) {
	    if ( trace ) System.out.println("Predicates differ");
	    return null; // because unequal predicates
	}
	if ( trace ) System.out.println("Add 1st link " + la1.html() + " " + la2.html());
	la1.addLink(la2);
	la2.addLink(la1);
	int lng = fnodes.size();
	// ---------------------------------------------------
	// First argument of finish() is used for indentation 
	// in a trace to show recursion depth
	for ( int i = 0; i < lng; i++ ) {
	    boolean out = finish(0, (Layer)fnodes.elementAt(i));
	    if ( !out ) return null;
	}
	if (trace) { 
	    System.out.println(" ================== fnodes done =============");
	}
	lng = vnodes.size();
	for ( int i = 0; i < lng; i++ ) {
	    boolean out = finish(0, (Layer)vnodes.elementAt(i));
	    if ( !out ) return null;
	}
	// Unification succeeded
	// trace = true; // to show substitution
	if (trace) { 
	    lng = sigma.size(); 
	    System.out.println("Show # substitutions: " + lng);
	    for (int j = 0; j < lng; j++) {
		Subsl s = (Subsl) sigma.elementAt(j);
		VariableLayer vx = s.getVariableLayer();
		Layer val = s.getVal();
		String valstr = val.string();
		System.out.print("vx " + vx.html() + "  ");
		System.out.println("val " + valstr);
	    }
	}
	return sigma; 
    } // end lunify

    // the core algorithm
    public boolean finish(int cnt, Layer r) { 
	ZTestLUnify.cntf++;
	// fcnt++;
	boolean trace = true;  // reset as needed
	// boolean trace = false;  // reset as needed
	if ( trace ) {
	    indent(cnt); System.out.println("Finish: r: " + cnt + " " + r.html()); }
	if ( r.getComplete() ) { // completed already
	    if ( trace ) 
		System.out.println("Completed already: " + r.html());
	    return true;
	}	    
	if ( null != r.getPointer() ) { // cycle found
	    if ( trace ) 
		System.out.println("Cycle found with: " + r.html());
	    return false;
	}
	if ( trace )
	    System.out.println("r.setPointer(r): " +  r.html());
	r.setPointer(r); // r is new equivalence class
	// stack for items to be investigated that have a horizontal link with r
	Stack stack = new Stack(); 
	stack.push(r); 
	while ( !stack.empty() ) {
	    Layer s = (Layer) stack.pop();
	    if ( trace )
		System.out.println("Stack element s: " + s.html());
	    // The next three checks are originally described concisely as:
	    //   if r and s have different function symbols
	    boolean rISs = r.equals(s); // to avoid checks
	    if ( !rISs ) {
		if ( ( s instanceof FTermLayer && r instanceof ConstantLayer ) ||
		     ( r instanceof FTermLayer && s instanceof ConstantLayer ) ) {
		    // r & s are incompatible
		    if ( trace ) { 
			System.out.println("Incompatible arguments");
			System.out.println("s: " + s.html() + " r: " + r.html());
		    }
		    return false; 
		}
		if ( s instanceof FTermLayer && r instanceof FTermLayer ) { 
		    // compare function names of r & s
		    FTermLayer s1 = (FTermLayer) s; FTermLayer r1 = (FTermLayer) r; 
		    if ( !s1.getSymbol().getName().equals(r1.getSymbol().getName()) ) {
			// incompatible function names
			if ( trace ) { 
			    System.out.println("Functions match failure");
			    System.out.println("s: " + s.html() + " r: " + r.html());
			}
			return false; 
		    }
		}
		if ( r instanceof ConstantLayer && s instanceof ConstantLayer ) { 
		    // constants are unique no need to compare the constant names of r & s
		    // ConstantLayer s1 = (ConstantLayer) s;
		    // ConstantLayer r1 = (ConstantLayer) r; 
		    // if ( !s1.getSymbol().getName().equals(r1.getSymbol().getName()) ) { // ???
		    if ( trace ) { 
			System.out.println("Constants match failure");
			System.out.println("s: " + s.html() + " r: " + r.html());
		    }
		    return false;
		}
	    }
	    Vector parents = s.getParents();
	    int lng = parents.size();
	    if ( trace ) System.out.println("Parents.size: " + lng);
	    // check items higher up/ the reverse occur check
	    for ( int i = 0; i < lng; i++ ) {
		Layer parent = (Layer) parents.elementAt(i);
		if ( trace ) System.out.println("Parent: " + parent.html());
		if ( !finish(cnt+1, parent) ) { 
		    if ( trace ) 
			System.out.println("Recursive failure with s: " +
					   s.html());
		    return false;
		}
	    }
	    // follow up on the horizontal items 
 	    Vector links = s.getLinks();
	    lng = links.size(); 
	    if ( trace) System.out.println("# links: " + lng);
	    for ( int i = 0; i < lng; i++ ) {
		Layer t = (Layer) links.elementAt(i);
		if ( trace) 
		    System.out.println("Link from: " + s.html() + " to: " + t.html());
		if ( t.getComplete() || r.equals(t) ) continue;
		if ( null == t.getPointer() ) { // eligible for further check
		    if ( trace )
			System.out.println("Make t " + t.html() + 
					   " a proto member of r " + r.html() +
					   "\n    and put on stack");
		    t.setPointer(r);
		    stack.push(t);
		    continue;
		} 
		if ( !r.equals(t.getPointer()) ) { // incompatible
		    // System.out.println("finish: disjoint classes" );
		    return false;
		} 
	    } // end of for links loop

	    if ( rISs ) { // skip when r equals s
		if ( trace )
		    System.out.println("r.equals(s) thus continue");
		continue;
	    }
	    // s != r
	    if ( s instanceof VariableLayer ) { // make substitution
		if ( trace ) 
		    System.out.println(s.html() + " --> " + r.string());
		VariableLayer sv = (VariableLayer) s;
		Subsl subs = new Subsl(sv, r);
		sv.setSubs(subs);
		addSubs(subs);
		s.setCompleteTrue();
		continue;
	    }
	    if ( s instanceof CompositeLayer && r instanceof CompositeLayer ) { 
		// this code is used for FTermLayer and also the AtomLayer arguments
		if ( trace )
		    System.out.println("Make links between args of " + s.html() +
				       "and " + r.html());
		CompositeLayer s1 = (CompositeLayer) s; 
		CompositeLayer r1 = (CompositeLayer) r; 
		Vector argss1 = s1.getLayeredArgs(); 
		Vector argsr1 = r1.getLayeredArgs();
		lng = argss1.size();
		for (int i = 0; i < lng; i++ ) {
		    Layer l1 = (Layer) argss1.elementAt(i);
		    Layer l2 = (Layer) argsr1.elementAt(i);
		    if ( l1.equals(l2) ) continue; // skip
		    if ( trace ) {
			System.out.print("Add two-way link between l1 " + l1.html());
			System.out.println(" and l2 " + l2.html());
		    }
		    l1.addLink(l2); l2.addLink(l1);
		}
		s.setCompleteTrue();
		continue;
	    }
	    // s and r both being constant and have been checked already
	    s.setCompleteTrue();
	    if ( trace )
		System.out.println("Complete s: " + s.html());
	} // end of while loop
	// all checked
	r.setCompleteTrue();
	if ( trace )
	    System.out.println("Complete r: " + r.html());
	return true;
    } // end Finish

    public void indent(int cnt) { // for indentation of display text
	for ( int i = cnt; 0 < i; i-- ) System.out.print("       ");
    }

    // Post processing here for producing a non-ordered substitiution
    // The variable must not be contained in its val - obviously
    public Vector buildSigma(Vector vnodes) { // 
	Vector out = new Vector();
	int lng = vnodes.size();
	for ( int i = 0; i < lng; i++ ) {
	    VariableLayer vi = (VariableLayer) vnodes.elementAt(i);
	    Subsl subs2 = new Subsl(vi, exploreVariable(vi));
	    out.addElement(subs2);
	}
	return out; 
    } // end buildSigma

    public Layer exploreVariable(VariableLayer vi) {
	Layer l2 = vi.ready();
	if ( null != l2 ) return l2;
	Subsl subs = vi.getSubs();
	if ( null == subs ) l2 = vi; else
	    l2 = descend(subs.getVal());
	if ( null == l2 ) l2 = vi;
	vi.setL2(l2);
	return l2; 
    } // end exploreVariable

    public Layer descend(Layer val) {
	if ( null == val ) return null;
	if ( val instanceof VariableLayer )
	    // replace variable terminals in the substitution as need be
	    return exploreVariable((VariableLayer) val);
	if ( val instanceof ConstantLayer ) return val;
	Layer l2 = val.ready();
	if ( null != l2 ) return l2;
	// val is a CompositeLayer
	FTermLayer val2 = (FTermLayer) val;
	Symbol head = val2.getSymbol();
	Vector args = val2.getLayeredArgs();
	int lng = args.size();
	Vector argsOut = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Layer li = descend((Layer) args.elementAt(i));
	    argsOut.addElement(li);
	}
	CompositeLayer cl = new CompositeLayer(head, argsOut);
	val.setL2(cl);
	return cl; 
    } // end descend

} // end LunificationContext
