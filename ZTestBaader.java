// c:/bsd/rigel/Unification/fol/ZTestBaader.java
// (C) OntoOO Inc / Dennis de Champeaux/ Sat Jan 30 14:50:14 2021

package fol;

import java.util.*;
import java.io.*;

public class ZTestBaader {
    public static int cntf = 0; 
    private static final Boolean trace = true;  // reset as needed
    // private static final Boolean trace = false;  // reset as needed
    public static void main (String [] arguments) {
	if (trace) System.out.println("------------------------------------------");
	// The unify function return null in case of non-unification

	// Parser parser = new Parser(false);
	for (int i = 1; i <= 1; i++) {
	// for (int i = 2; i <= 2; i++) {
	// for (int i = 1; i <= 7; i++) {
	// for (int i = 800; i <= 810; i++) {
	// for (int i = 100; i <= 1000; i = i + 100) {
	// for (int i = 1500; i <= 1510; i++) {
	    // if (trace) System.out.println("size: " + i);
	    // String a = gen1arg1(i); // generator
	    // String b = gen1arg2(i); // generator
	    // String a = gen1arg1f(i); // generator
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
	    // String a = "P(?x)";  String b = "P(f(?x))";
	    // String b = "P(?x)";  String a = "P(f(?x))";
	    // String a = "P(a)";  String b = "P(f(a))"; // disjoint classes zz3 
	    // String a = "P(a b)";  String b = "P(f(b) b)"; // incompatible arguments
	    // String a = "P(f(a))";  String b = "P(g(a))";  // function match failure
	    // String a = "P(a)";  String b = "P(b)";        // constant match failure
	    // String a = "P(a)";  String b = "Q(a)";        // predicate match failure 
	    // String a = "P(?x ?y)";  String b = "P(f(?y) ?x)"; // -
	    // String a = "P(?x ?x)";  String b = "P(a f(b))"; // -
	    // String a = "P(?x ?x)";  String b = "P(a b)"; // - 
	    // String a = "P(?x ?y ?x)";  String b = "P(a b ?y)"; // -
	    // String a = "P(?x ?x)";  String b = "P(f(a) f(b))"; // -
	    // String a = "P(?x g(f(?x)))";  String b = "P(f(?y) ?y)"; // find-solution
	    // String a = "P(?x ?y ?y)"; String b = "P(a ?x b)"; // unifClosure
	    // String a = "P(?x h(?z) f(?x))"; String b = "P(g(?y) ?y ?z)"; // find-solution
	    // String b = "P(?x h(?z) f(?x))"; String a = "P(g(?y) ?y ?z)"; // find-solution
	    // String a = "P(?x g(?x))"; String b = "P(f(?y) ?y)";  // find-solution

	    // unifiable
	    // String a = "P(a)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(?y)"; 
	    // String a = "P(?x)";  String b = "P(?x)"; 
	    // String a = "P(?x)";  String b = "P(f(?y))"; 
	    // String a = "P(?x a)";  String b = "P(a ?x)";
 	    // String a = "P(f(?x))";  String b = "P(f(a))"; 
	    // String a = "P(?x a)";  String b = "P(b ?y)";
	    // String a = "P(?x)";  String b = "P(?y)"; // duplicate
	    // String a = "P(?x ?y ?z)";  String b = "P(?y ?z a)";

	    // String a = "P(?x f(?y))";  String b = "P(f(?y) f(f(?z)))"; // test post ++++
	    String a = "P(a ?x f(a ?x))"; String b = "P(?y g(?y) f(?z g(?z)))"; // text !!!

	    // String a = "P(?x ?y a)"; String b = "P(?y ?x ?x)";
	    // String a = "P(f(?x) g(a))"; String b = "P(?y ?x)";

	    // String a = "P(f(g(h(i(?y)))))"; String b = "P(?x)"; // +
	    // String a = "P(?x ?y ?z ?v)";  String b = "P(a ?z ?v ?x)"; // +
	    // String a = "P(a b)"; String b = "P(?x ?x)"; // -
	    // String a = "P(a b ?x)"; String b = "P(?x ?y ?y)"; // -

	    // String a = "P(?x ?y ?z)"; String b = "P(?y ?z ?v)"; // +

	    /* The next example shows that the vars container should be a hashset
	       to avoid  a quadratic search suggested by concatenate the vars in union.
	       Or are duplicates ok because they will be skipped? YES - see the union code.

	     */
	    // String a = "P(?x ?x ?x ?z ?z ?z ?x)"; String b = "P(?y1 ?y2 ?y3 ?y1 ?y2 ?y3 ?z)";
	    // String b = "P(?x ?x ?x ?z ?z ?z ?x)"; String a = "P(?y1 ?y2 ?y3 ?y1 ?y2 ?y3 ?z)";

	    // Baader baader = new Baader();


	    Baader ontooo = new Baader();

	    // /*
	    Vector out = null;
	    out = ontooo.unify(a, b);
	    // System.out.println("--------------\nout: Unification " +
	    //  	       ( null == out ? "failed" : "ok" ) );
	    // */


	    /*
	    // Tests with generators
	    int k, reps; long t0, delta;
	    // Baader ontooo = new Baader();
	    runTests(ontooo, 2000); // warm up

	    t0 = System.currentTimeMillis(); k = 1000; reps = 1500;
	    gen1(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen1  k: " + k + " delta timing: " + 
			       String.format("%.2f", ((1.0/ reps) * delta)));
	    t0 = System.currentTimeMillis();
	    gen1f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen1f k: " + k + " delta timing: " + 
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    t0 = System.currentTimeMillis(); k = 200; reps = 800;
	    gen2(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen2  k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));
	    t0 = System.currentTimeMillis();
	    gen2f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0; 
	    System.out.println("gen2f k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    t0 = System.currentTimeMillis(); k = 600; reps = 2200;
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

	    /*
	    // Baader ontooo = new Baader();
	    runTests(ontooo, 2000); // warm up
	    long t0 = System.currentTimeMillis();
	    for ( int k = 1; k <= 100; k++) {
		Vector out = null;
		ontooo = new Baader();
		out = ontooo.unify(a, b);
	    }
	    long delta = System.currentTimeMillis() - t0;
	    System.out.println("Example i: " + i + " delta timing: " + delta);

	    // System.out.println("--------------\nout: Unification " +
	    //  	       ( null == out ? "failed" : "ok" ) );
	    // */

	    /*
	    // Baader ontooo = new Baader();
	    {
	    // SMALL size base tests OK
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
	    // */
	    /*
	    // Baader ontooo = new Baader();
	    // SMALL size base tests fail
	    {
	    runTests(ontooo, 2000); // warm up
	    int reps = 5000;
	    System.out.println("SMALL size base tests fail reps:" + reps);
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
		System.out.println("gen1-4 k: " + k + " delta timing: " + 
				   String.format("%.5f", ((1.0/ (4*reps2)) * best)));
	    }
	    }
	    // */
	    /*
	    {
	    // Large size base tests OK&fail
	    // Baader ontooo = new Baader();
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
	    Baader ontooo = new Baader();
	    int reps = 30000;
	    if (trace) System.out.println("reps: " + reps);
	    runTests(ontooo, 2000); // warm up
	    for ( int k = 1; k <= 1; k+=100) {
		long t0 = System.currentTimeMillis();
		runTests(ontooo, reps);
		long delta = System.currentTimeMillis() - t0;
		System.out.println("Example k: " + i + " delta timing: " + delta);
	    }
	    //  */
	    /*
	    Baader ontooo = new Baader();
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
    static private void gen1(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1(size); b = gen1arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
    } // end gen1
    static private void gen1f(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1f(size); b = gen1arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
    } // end gen1f
   static private void gen2(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen2
   static private void gen2f(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2f(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen2f
   static private void gen3(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen3
  static private void gen3f(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2f(size); //  undefined
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
  } // end gen3f
   static private void gen4(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen4
   static private void gen4f(Baader ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size); //  undefined
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen4f

    /*
     static private void genA(Baader ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argument(i); b = gen2argument(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void genB(Baader ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argumentf(i); b = gen2argument(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void genC(Baader ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) { 
	     a = gen2arg1(i); b = gen2arg2(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void runTest1(Baader ontooo, int repeat) {
	 // String a = gen1argument(4), b = gen2argument(4);
	 String a = gen1argumentf(4), b = gen2argument(4);
	 for ( int i = 0; i < repeat; i++ ) {
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void runTest2(Baader ontooo, int repeat) {
	 String a = gen2arg1(6); String b = gen2arg2(6);
	 // String a = gen2arg1(2); String b = gen2arg2f(2);
	 for ( int i = 0; i < repeat; i++ ) {
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }

     static private void runTest3(Baader ontooo, int repeat) {
	 String a = gen3arg1(2); String b = gen3arg2(2);
	 // String a = gen3arg1(2); String b = gen3arg2f(2);
	 for ( int i = 0; i < repeat; i++ ) {
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
    */
     static private void runTests(Baader ontooo, int repeat) {
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
    } //  runTests

    static private void runSmallFailTests(Baader ontooo, int repeat) {
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


     // Generators of arguments that causes normally exponential blow up 
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

} // end  ZTestBaader

class Baader {
    public void clear() {
	htc = new Hashtable();
	htv = new Hashtable();
	sigma = new Vector();
    }
    // private static boolean trace = false;  // reset as needed
    private static Boolean trace = true; 

    Parser parser = new Parser(false);
    private String arg1;
    private String arg2;
    public Vector unify(String ar1, String ar2) {
	arg1 = ar1; arg2 = ar2;
	// Text to lisp-like transformation using parser in the fol package
	parseArguments();
	if (trace) System.out.println("----------- Parsing OK.");
	createNodeLayers();
	if (trace) System.out.println("----------- Nodes done.");
	if (trace) System.out.println("la1: " + la1.html() + "la2: " + la2.html());
	Vector out = bunify(la1, la2); // null | substitution
	// show result:
	if (trace) System.out.println("--------------\nout: Unification " + 
				      ( null == out ? "failed" : "ok" ) );
	if (  null == out ) return out; // when failed
	    // post processing
	if (trace) {
	    System.out.println("----------- Post processing");
	    System.out.println("|out| " + out.size()); 
	    System.out.println("|sigma | " + sigma.size());
	} 
	// out == sigma

	Vector subsList = buildSigma(out);
	if (trace) { 
	    // if (false) { 
	    int lng = subsList.size(); // # variables
	    System.out.println("Substitution lng " + lng);
	    for (int j = 0; j < lng; j++) {
		Subsb s = (Subsb) subsList.elementAt(j);
		Node vx = s.getVariableNode();
		Node val = s.getVal();
		System.out.print("vx " + vx.string() + "  ");
		System.out.println("val " + val.string());
	    }
	}
	// subsList is the ordered substitution when unification ok
	return subsList;
	// return out; // for now
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
	}
	if ( trace) System.out.println("ParseIt out: " + out.html());
	return (Atom) out;
    } // end parseIt

    private AtomNode la1, la2;
    public void createNodeLayers() {
	la1 = new AtomNode(a1); // addFnode(la1);
	la1.createDownNodes(this);
	if ( trace ) System.out.println("AtomNode(a1): " + la1.html());

	la2 = new AtomNode(a2); // addFnode(la2);
	la2.createDownNodes(this);
	if ( trace )  System.out.println("AtomNode(a2): " + la2.html());
    } // end createNodeLayers

    private Hashtable htc = new Hashtable();
    public ConstantNode getConstantNode(Symbol s) {
	return (ConstantNode) htc.get(s); // returns null if not found
    } // end getConstantNode(
    public void addConstantNode(Symbol s, ConstantNode cl) {
	htc.put(s, cl);
    } // end addConstantNode

    private Hashtable htv = new Hashtable();
    public VariableNode getVariableNode(Variable v) {
	return (VariableNode) htv.get(v); // returns null if not found
    } // end getVariableNode
    public void addVariableNode(Variable v, VariableNode vl) {
	htv.put(v, vl);
    } // end addVariableNode

    private Vector sigma = new Vector(); // contains Subsb elements
    public Vector getSigma() { return sigma; }
    public void addSubs(Subsb s) { sigma.addElement(s); }

    private Vector bunify(AtomNode la1, AtomNode la2) {
	if ( !la1.getSymbol().getName().equals(la2.getSymbol().getName()) ) {
	    if ( trace ) System.out.println("Predicates differ");
	    return null; // because unequal predicates
	}
	if ( !unifClosure(la1, la2) ) return null; // failure
	if ( trace ) 
	    System.out.println("-------------- start findSolution -----------");
	if ( !findSolution(la1) ) return null; // failure
	//	out = findSolution(la1);
	// if ( !out ) return null;
	return sigma; // accumulated  substitution by findSolution
    } // end bunify

    private boolean unifClosure(Node s, Node t) {
	// ZTestBaader.cntf++; // complexity counter
	if ( trace ) 
	    System.out.println("       unifClosure s: " + s.html() + " t: " + t.html());
	// if ( (t instanceof VariableNode) && !(s instanceof VariableNode) )
	//     return unifClosure(t, s);
	s = s.find(); // get representative 
	t = t.find();
	if ( trace ) 
	    System.out.println("unifClosure2 s: " + s.html() + " t: " + t.html());
	if ( s.equals(t) ) return true;
	Node s1 = s.getSchema(), t1 = t.getSchema();
	if ( trace ) {
	    System.out.println("unifClosure3 s1: " + s1.html() + " t1: " + t1.html());
	    System.out.println("class s1: " + s1.getMyClass().html() + 
			       " t1: " + t1.getMyClass().html());
	    System.out.println("schema s1: " + s1.getSchema().html() + 
			       " t1: " + t1.getSchema().html());
	}
	if ( s1 instanceof ConstantNode && t1 instanceof ConstantNode ) {
	    if ( s1.equals(t1) ) return true; // constants are unique
	    if ( trace )
		System.out.println("unifClosure: constants failure ");
	    return false;
	}
	if ( ( s1 instanceof ConstantNode && t1 instanceof FTermNode ) || 
	     ( t1 instanceof ConstantNode && s1 instanceof FTermNode ) ) {
	    if ( trace ) {
		System.out.print("unifClosure failure between s " + s.html());
		System.out.println(" and t " + t.html());
		System.out.print("unifClosure failure between s1 " + s1.html());
		System.out.println(" and t1 " + t1.html());
	    }
	    return false;
	}
	if ((s1 instanceof AtomNode && t1 instanceof AtomNode) || 
	    (s1 instanceof FTermNode && t1 instanceof FTermNode) ) {
	    CompositeNode s2 = (CompositeNode) s1, t2 = (CompositeNode) t1;
	    if ( !s2.getSymbol().getName().equals(t2.getSymbol().getName()) ) {
		// incompatible function names
		if ( trace ) { 
		    System.out.print("unifClosure: functions failure ");
		    System.out.println("s1: " + s1.html() + " t1: " + t1.html());
		}
		return false; 
	    }

	    // System.out.println("unifClosureA s: " + s.html() + " t: " + t.html());
	    // System.out.println("unifClosureB s2: " + s2.html() + " t2: " + t2.html());
	    // System.out.println("unifClosureC s2f: " + s2.getSchema().html() + 
	    //	       " t2f: " + t2.getSchema().html());
	    union(s, t);
	    // System.out.println("unifClosure4 s: " + s.html() + " t: " + t.html());
	    // System.out.println("unifClosure5 s2: " + s2.html() + " t2: " + t2.html());
	    // System.out.println("unifClosure6 s2f: " + s2.getSchema().html() + 
	    //		       " t2f: " + t2.getSchema().html());
	    Vector argss1 = s2.getNodedArgs(); 
	    Vector argst1 = t2.getNodedArgs();
	    int lng = argss1.size();
	    // System.out.println("unifClosure: lng " + lng);
	    for (int i = 0; i < lng; i++ ) {
		Node l1 = (Node) argss1.elementAt(i);
		Node l2 = (Node) argst1.elementAt(i);
		// System.out.println("unifClosure: " + l1.html() + " " +  l2.html() );
		if ( l1.equals(l2) ) continue; // skip
		if ( !unifClosure(l1, l2) ) {
		    if ( trace ) {
			System.out.print("unifClosure failure between l1 " + l1.html());
			System.out.println(" and l2 " + l2.html());
		    }
		    return false;
		}
	    }
	    return true;
	} // end (Atom & Atom) || (FTerm & FTerm)
	union(s, t);
	return true;
    } // end unifClosure

    /* This version of union differs from the description in the 2001 publication.
       When only one of the arguments is a varible and it is in 2nd position 
       its attributes will be set as if it would be in the 1st position.
     */ 
    private void union(Node s, Node t) {
       	// if ( (t instanceof VariableNode) && !(s instanceof VariableNode) ) {
	//     Node z = s; s = t; t = z; // exchange them
	// }
	int sizeS = s.getSize(), sizeT = t.getSize();
	if ( trace ) {
	    System.out.println("    union s: " +
			       s.html() + " size: " + sizeS + " t: " +
			       t.html() + " size: " + sizeT); 
	    System.out.println("union: " +
			       "class s: " + s.getMyClass().html() + " " +
			       " t: " + t.getMyClass().html());
	    System.out.println("schema s: " + s.getSchema().html() + 
	   		       " t: " + t.getSchema().html());
	}

	if ( sizeS  >= sizeT ) {
	    s.setSize(sizeS + sizeT);
	    if ( (s instanceof VariableNode) && (t instanceof VariableNode) ) {
		HashSet hs = s.getVars();
		for (Iterator i = t.getVars().iterator(); i.hasNext();) {
		    hs.add(i.next()); // duplicates are ignored
		}
	    }
	    if ( (s instanceof VariableNode) ) {
		if ( (s.getSchema()) instanceof VariableNode ) { 
		    s.setSchema(t.getSchema()); t.setMyClass(s); 
		} 
	    } else  {
		if ( (t.getSchema()) instanceof VariableNode ) { 
		    t.setSchema(s.getSchema()); s.setMyClass(t);  
		} else 
		    t.setMyClass(s); 
	    }
	} else { 
	    t.setSize(sizeS + sizeT);
	    if ( (s instanceof VariableNode) && (t instanceof VariableNode) ) {
		HashSet ht = t.getVars();
		for (Iterator i = s.getVars().iterator(); i.hasNext();) {
		    ht.add(i.next()); // duplicates are ignored
		}
	    }
	    if ( (t instanceof VariableNode) ) {
		if ( (t.getSchema()) instanceof VariableNode ) {
		    t.setSchema(s.getSchema()); s.setMyClass(t); 
		} 
	    } else {
		if ( (s.getSchema()) instanceof VariableNode ) { 
		    s.setSchema(t.getSchema()); s.setMyClass(t); 
		} else 
		    s.setMyClass(t); 
	    }
	}
	   
	if ( trace ) { 
	    System.out.println("union exit s: " +
			       s.html() + " size: " + s.getSize() + " t: " +
			       t.html() + " size: " + t.getSize()); 
	    System.out.println("union exit2: " +
			       "class s: " + s.getMyClass().html() +
			       " t: " + t.getMyClass().html());
	    System.out.println("union exit3: " +
	    	       "schema s: " + s.getSchema().html() + " " +
	    	       " t: " + t.getSchema().html() );
	}
    } // end union
       

    private boolean findSolution(Node s1) {
	ZTestBaader.cntf++; // complexity counter
	if ( trace ) 
	    System.out.println("--------- findSolution s1: " + s1.html());
	Node s2 = s1.find();
	if ( trace ) 
	    System.out.println("findSolution2 find s2: " + s2.html());
	Node s = s2.getSchema();
	if ( trace ) {
	    System.out.println("findSolution3 s: " + s.html());
	    System.out.println("class s: " + s.getMyClass().html());
	    System.out.println("schema s: " + s.getSchema().html());
	}
	if ( s.getAcyclic() ) return true;
	if ( s.getVisited() ) {
	    if ( trace ) 
		System.out.println("findSolution cycle visited s: " + s.html());
	    return false; // cycle found
	}
	if ( s instanceof FTermNode || s instanceof AtomNode ) {
	    CompositeNode s3 = (CompositeNode) s;
	    s3.setVisited(true);
	    if ( trace ) 
		System.out.println("findSolution set visited s3: " + s3.html());
	    Vector args = s3.getNodedArgs();
	    int lng = args.size();
	    for (int i = 0; i < lng; i++) {
		Node ni = (Node) args.elementAt(i);
		if ( !findSolution(ni) ) {
		    if ( trace ) 
			System.out.println("findSolution cycle ni: " + ni.string());
		    return false;
		}
	    }
	    s3.setVisited(false);
	}
	s.setAcyclic(true);
	if ( trace ) { System.out.println("acyclic true: " + s.html()); };
	Node s4 = s.find();
	if ( trace ) { System.out.println("s4: " + s4.html()); }
	HashSet hs = s4.getVars();
	if ( trace ) {
	    System.out.println("findSolution s4 hs.size: " + hs.size());
	}
	for (Iterator i = hs.iterator(); i.hasNext();) {
	    VariableNode x = (VariableNode)i.next();
	    if ( x.equals(s) ) { 
		if ( trace ) System.out.println("Unassigned Subs x: " +
					x.html() + " ---> s: " + s.html());
		Subsb su = new Subsb(x, s);
		x.setSubs(su); // store in x and ...
		addSubs(su); // ... add to 'global' sigma 
		continue;
	    }
	    if ( null != x.getSubs() ) continue;
	    if ( trace ) System.out.println("findSolution Subs x: " +
					    x.html() + " ---> s: " + s.html());
	    Subsb su = new Subsb(x, s);
	    x.setSubs(su); // store in x and ...
	    addSubs(su); // ... add to 'global' sigma 
	}
	return true;
    } // end findSolution

    // Post processing here:::
    // change again to variable
    // variable must not contain its val
    public Vector buildSigma(Vector sigma) { // 
	int lng = sigma.size();
	if ( trace ) 
	    System.out.println("buildSigma lng: " + lng);
	Vector out = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Subsb subs = (Subsb) sigma.elementAt(i);
	    VariableNode vi = (VariableNode) subs.getVariableNode();
	    Node val  = subs.getVal();
	    // System.out.println("sigmaI vi: " + vi.html() + " val: " + val.html());
	    Subsb subs2 = new Subsb(vi, exploreVariable(vi, val));
	    VariableNode vi2 = (VariableNode) subs2.getVariableNode();
	    Node val2  = subs2.getVal();
	    // System.out.println("sigmaI vi2: " + vi2.html() + " val2: " + val2.html());
	    out.addElement(subs2);
	}
	return out; 
    } // end buildSigma
    private Node exploreVariable(VariableNode vi, Node val) {
	Node l2 = vi.ready();
	if ( null != l2 ) return l2;
	if ( vi.equals(val) ) {
	    vi.setL2(vi);
	    return vi;
	}
	l2 = descend(val);
	if ( null == l2 ) l2 = vi;
	vi.setL2(l2);
	return l2; 
    } // end exploreVariable
    public Node descend(Node val) {
	if ( null == val ) return null;
	// System.out.println("descend val " + val.html());
	if ( val instanceof VariableNode ) {
	    VariableNode v = (VariableNode) val;
	    Subsb s = v.getSubs();
	    if ( null == s ) return v;
	    // replace variable terminals in the substitution as need be
	     return exploreVariable(v, s.getVal());
	}
	if ( val instanceof ConstantNode ) return val;
	Node l2 = val.ready();
	if ( null != l2 ) return l2;
	// val is a CompositeLayer
	//FTermNode val2 = (FTermNode) val;
	CompositeNode val2 = (CompositeNode) val;
	Symbol head = val2.getSymbol();
	Vector args = val2.getNodedArgs();
	int lng = args.size();
	Vector argsOut = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Node li = descend((Node) args.elementAt(i));
	    argsOut.addElement(li);
	}
	CompositeNode cl = new CompositeNode(head, argsOut);
	val.setL2(cl);
	return cl; 
    } // end descend

} // end Baader

abstract class Node { 
    protected static final Boolean trace = false;  // reset as needed
    // protected static final Boolean trace = true;  // reset as needed
    /*
    Node() {
	myClass = this;
	schema = this;
    }
    */
    private int size = 1;
    public int getSize() { return size; }
    public void setSize(int i) { size = i; }
    protected Node myClass = null; // representative if myClass = this
    public Node getMyClass() { return myClass; }
    public void setMyClass(Node n) { myClass = n; }
    protected Node schema = null; 
    public Node getSchema() { return schema; } // schema function
    public void setSchema(Node n) { schema = n; }
    private boolean visited = false;
    public boolean getVisited() { return visited; }
    public void setVisited(boolean b) { visited = b; }

    private boolean acyclic = false;
    public boolean getAcyclic() { return acyclic; }
    public void setAcyclic(boolean b) { acyclic = b; }

    protected HashSet vars = new HashSet();
    public HashSet getVars() { return vars; }
    public void setVars(HashSet h) { vars = h; }
    public Node find() {
	if ( this != myClass ) {
	    // System.out.println("find() this: " + html() + " myClass: " +  myClass.html());
	    myClass = myClass.find();
	}
	return myClass;
    } // end find
    abstract String string();
    abstract String html(); 

    private Node l2 = null; // for post processing
    public void setL2(Node lx) { l2 = lx; }
    public Node ready() { return l2; }
} // end Node

class ConstantNode extends Node { // for constants
    protected Symbol symbol;
    ConstantNode(Symbol s) {
	// super();
	symbol = s;
	myClass = this;
	schema = this;
    }
    public Symbol getSymbol() { return symbol; }
    public String html() { return "ConstantNode " + string(); }
    public String string() { return symbol.getName(); }
} // end ConstantNode

class VariableNode extends Node { // for variables
    protected Variable variable;
    VariableNode(Variable v) {
	// super();
	variable = v;
	myClass = this;
	schema = this;
	getVars().add(this); 
    }
    public Variable getVariable() { return variable; }
    public String html() { return "VariableNode " + string(); }
    public String string() { return variable.html(); }
    private Subsb subs = null; // the substitution of the variable
    public void setSubs(Subsb s) { subs = s; }
    public Subsb getSubs() { return subs; }
} // end VariableNode

class CompositeNode extends Node {
    protected Symbol symbol; // name of predicate or function
    protected Vector args;
    CompositeNode(Formula formula) {
	myClass = this;
	schema = this;
	if ( formula instanceof Atom ) {
	    Atom ax = (Atom) formula; // predicate
	    symbol = ax.getPredicate();
	    args = ax.getArgs();
	} else { 
	    FTerm ft = (FTerm) formula; // function
	    symbol = ft.getFunction();
	    args = ft.getArgs();
	}
    } // end constructor

    public Symbol getSymbol() { return symbol; }
    public Vector getArgs() { return args; }
    protected Vector nodedArgs = new Vector();
    protected void addNodedArg(Node in) { nodedArgs.addElement(in); }
    protected Vector getNodedArgs() { return nodedArgs; }
    // create lower nodes ..
    public void createDownNodes(Baader baader) {
	int lng = args.size();
	if ( trace )
	    System.out.println("Node-createDownNodes lng: " + lng);
	for (int i = 0; i < lng; i++) { 
	    Term term = (Term) args.elementAt(i);
	    if ( trace )
		System.out.println("term: " + i + " " + term.html());
	    if ( term instanceof Symbol ) {
		Symbol s = (Symbol) term;
		if ( !(s instanceof Variable ) ) { // constant
		    ConstantNode cl = (ConstantNode)baader.getConstantNode(s);
		    if ( null != cl ) {
			addNodedArg(cl);
			continue;
		    }		
		    // create constant-layer
		    cl = new ConstantNode(s);
		    baader.addConstantNode(s, cl);
		    addNodedArg(cl);
		    continue;
		}
		// term is variable/ check first whether encounterd already
		Variable v = (Variable) term;
		VariableNode vl = (VariableNode)baader.getVariableNode(v);
		if ( null != vl ) {
		    addNodedArg(vl);
		    continue;
		}		
		// create variable-layer
		vl = new VariableNode(v);
		baader.addVariableNode(v, vl);
		addNodedArg(vl);
		continue;
	    }
	    // term is FTerm thus create a FTermNode
	    FTerm fterm = (FTerm) term;
	    if ( trace ) 
		System.out.println("Node-createDownLayers/ recurse down fterm " +
				   fterm.html());
	    FTermNode ftl = new FTermNode(fterm);
	    addNodedArg(ftl);
	    // recurse further down
	    ftl.createDownNodes(baader);
	    if ( trace ) 
		System.out.println("cDN exit of fterm " + fterm.html() + " ");
	}
    } // end createDownNodes

    // This constructor is used by the post-processor
    CompositeNode(Symbol head, Vector argsx) {
	symbol = head; nodedArgs = argsx;
	int lng = argsx.size();
	args = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Node li = (Node) argsx.elementAt(i);
	    if ( trace ) 
		System.out.println("li : " + li.html());
	    if ( li instanceof ConstantNode) {
		ConstantNode lic = (ConstantNode) li;
		args.addElement(lic.getSymbol());
		continue;
	    }
	    if ( li instanceof VariableNode) {
		VariableNode liv = (VariableNode) li;
		args.addElement(liv.getVariable());
		continue;
	    }
	    CompositeNode lix = (CompositeNode) li;
	    Symbol symbolx = lix.getSymbol();
	    Vector argsy = lix.getArgs();
	    FTerm ft = new FTerm(symbolx, argsy);
	    args.addElement(ft);
	} // end CompositeNode 2nd constructor
    } // CompositeNode

    String string() { // for display
	FTerm ft = new FTerm(symbol, args);
	return ft.html();
    } 
    String html() { // for display
	return "CompositeNode " + string();
    }
} // end CompositeNode

class AtomNode extends CompositeNode {
    AtomNode(Atom ax) { super(ax); }
} // end  AtomNode
class FTermNode extends CompositeNode { 
    FTermNode(Term tm) { super(tm); }
} // end FTermNode

class Subsb {
    private Node vl;
    private Node val;
    Subsb(Node vx, Node v) {
	vl = vx; val = v;
    }
    public Node getVariableNode() { return vl; }
    public Node getVal() { return val; }
} // end Subsb 

