// c:/bsd/rigel/Unification/fol/ZTest0.java
// (C) OntoOO Inc / Dennis de Champeaux/ Sat Apr 17 11:04:50 2021
/*
Ako Robinson unification algorithm; likely faster than the one in the Atom class.
Still may need more work.
The control flow:
dunify -> unif
unif -> unif2 & unif3
unif2 -> unif4 -> unif2
unif3 -> unif5
 */

package fol;

import java.util.*;
import java.io.*;

public class ZTest0 {
     private static final boolean trace = true;  // reset as needed
     // private static final boolean trace = false;  // reset as needed
     public static int cnt = 0;
     public static void main (String [] arguments) {
	if (trace) System.out.println("------------------------------------------");
	// The unify function return null in case of non-unification

	// for (int i = 100; i <= 1000; i = i + 100) {
	// for (int i = 800; i <= 810; i++) {
	// for (int i = 1500; i <= 1510; i++) {
	// for (int i = 1500; i <= 1510; i++) {
	// for (int i = 10; i <= 20; i++) {
	for (int i = 1; i <= 1; i++) {
	// for (int i = 1; i <= 7; i++) {
	// for (int i = 1; i <= 3; i++) {
	// for (int i = 3; i <= 5; i++) {
	    // if (trace) System.out.println("size: " + i);
	    // String a = gen1arg1(i); // generator succeeds
	    // String b = gen1arg2(i); // generator
	    // String a = gen1arg1f(i); // generator fail
	    // String a = gen2arg1(i); // generator
	    // String b = gen2arg2(i); // generator
	    // String b = gen2arg2f(i); // generator
	    // String a = gen3arg1(i); // generator
	    // String b = gen3arg2(i); // generator
	    // String b = gen3arg2f(i); // generator ???????
	    // String a = gen4arg1(i); // generator
	    // String b = gen4arg2(i); // generator
	    // String b = gen4arg2f(i); // generator 
	    // System.out.println("a: " + a);
	    // System.out.println("b: " + b);
	    // ad hoc exmples if the generor is not used:
	    //     not unifiable
	    // String a = "P(a)";  String b = "P(f(a))"; // disjoint classes zz3 unif4
	    // String a = "P(a b)";  String b = "P(f(b) b)"; // incompatible arguments unif4
	    // String a = "P(f(a))";  String b = "P(g(a))";  // function match failure unif4
	    // String a = "P(a)";  String b = "P(b)";        // constant match failure unif4
	    // String a = "P(a)";  String b = "Q(a)";        // predicate match failure 
	    // String a = "P(?x)";  String b = "P(f(?x))"; // - unif2G
	    // String a = "P(?x ?y ?z)";  String b = "P(f(?y) ?z ?x)"; // - unif5 check3
	    // String a = "P(?x ?x)";  String b = "P(f(g(?z)) ?z))"; // - unif5 check3
	    // String a = "P(?x ?x)";  String b = "P(a f(b))"; // - unif4
	    // String a = "P(?x ?x)";  String b = "P(a b)"; // - unif4
	    // String a = "P(?x ?y ?x)";  String b = "P(a b ?y)"; // - unif4-2
	    // String a = "P(?x ?x)";  String b = "P(f(a) f(b))"; // -unif4 s1A t1A
	    // String a = "P(?x g(f(?x)))";  String b = "P(f(?y) ?y)"; // -unif5-check3
	    // String a = "P(?x ?y ?y)"; String b = "P(a ?x b)"; // - unif4 
	    // String a = "P(?x g(?x))"; String b = "P(f(?y) ?y)";  // -unif5-check3
	    // String a = "P(?x h(?z) f(?x))"; String b = "P(g(?y) ?y ?z)"; // -unif5-check3
	    // String b = "P(?x h(?z) f(?x))"; String a = "P(g(?y) ?y ?z)"; //  -unif5-check3
	    // String a = "P(?x ?y)";  String b = "P(f(?y) g(?x))"; // -unif5-check3
	    // String a = "P(?x ?y ?z)";  String b = "P(f(?y) g(?z) h(?x))"; // -unif5-check&3

	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y) ?z ?x)"; // - unif5-check3
	    // String a = "P(?z ?y ?x)"; String b = "P(?x ?z f(?y))"; // reverse arguments unif2E
	    // String a = "P(?y ?z ?x)"; String b = "P(?z ?x f(?y))"; // reverse arguments unif2E

	    // unifiable
	    // String a = "P(a)";  String b = "P(a)"; 
	    // String a = "P(f(a))";  String b = "P(f(a))"; 
	    // String a = "P(?x)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(?y)"; 
	    // String a = "P(?x)";  String b = "P(?x)"; 
	    // String a = "P(?x)";  String b = "P(f(?y))"; 
	    // String a = "P(?x a)";  String b = "P(a ?x)";
 	    // String a = "P(f(?x))";  String b = "P(f(a))"; 

	    // String a = "P(?x f(?y))";  String b = "P(f(?y) f(f(?z)))"; // + test post
	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y ?z) ?z ?y)";  // + test post
	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y ?z) a ?y)";  // + check 

	    // String a = "P(a ?x f(a ?x))"; String b = "P(?y g(?y) f(?z g(?z)))"; // +  text
	    // String a = "P(?x ?x)"; String b = "P(f(?y) f(b))"; // +

	    // String a = "P(?x)"; String b = "P(f(?x))"; // - unif3A 
	    // String a = "P(?x ?x)"; String b = "P(a b))"; // - unif4-2 fail first t
	    // String a = "P(?x b)"; String b = "P(a ?x))"; // - unif4-2 fail first s

	    // String a = "P(?x ?y)"; String b = "P(a b)"; // +
	    // String a = "P(?x ?x)"; String b = "P(a b)"; // - unif4  s first <-> t
	    // String a = "P(?x ?x)"; String b = "P(f(?y) ?y)"; // - unif5&3
	    // String a = "P(?x ?y)"; String b = "P(f(?y) ?x)"; // - unif2G
	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y) ?z ?x)"; // - unif5&3 

	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y ?z) f(?x ?z) f(?x ?y))"; //- unif5&3
	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y ?z) ?z ?y)";  // + check
	    // String a = "P(?x ?y ?z)"; String b = "P(f(?y ?z) a ?y)";  // + check 

	    // String a = "P(a ?x b)"; String b = "P(?x ?y ?y)"; // - unif4
	    // String a = "P(?x ?z ?y)"; String b = "P(f(?z)?y ?x)"; // - unif5&3

	    // String a = "P(?x ?y ?z)"; String b = "P(?y ?z ?v)"; // +
	    // String a = "P(?x ?y ?z)"; String b = "P(?y ?z a)"; // + ok
	    // String a = "P(?x ?y ?z)"; String b = "P(?y ?z f(x))"; // - unif2E

	    // String a = "P(?x ?x)"; String b = "P(?y f(?y))"; // optional unif2D
	    // String a = "P(?x ?y)"; String b = "P(f(?y) ?x)"; // optional unif2G
	    // String a = "P(f(?y) ?y)"; String b = "P(?x ?x)"; // optional unif2I
	    // String a = "P(?y ?x)"; String b = "P(?x f(?y))"; // optional unif2E

	    Ontooo ontooo = new Ontooo();

	    /*
	    Vector out = ontooo.unify(a, b);
	    System.out.println("--------------\nout: Unification " +
	    	       ( null == out ? "failed" : "ok" ) );
	    // */
	    /*
	    runTests(ontooo, 2000); // warm up
	    long t0 = System.currentTimeMillis();
	    for ( int k = 1; k <= 100; k++) {
		Vector out = null;
		ontooo = new Ontooo();
		out = ontooo.unify(a, b);
	    }
	    long delta = System.currentTimeMillis() - t0;
	    System.out.println("Example i: " + i + " delta timing: " + delta);
	    */

	    /*
	    // Tests with generators
	    int k, reps; long t0, delta;
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

	    t0 = System.currentTimeMillis(); k = 600; reps = 2200;
	    gen4(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen4  k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));
	    t0 = System.currentTimeMillis();
	    gen3f(ontooo, k, reps);
	    delta = System.currentTimeMillis() - t0;
	    System.out.println("gen4f k:  " + k + " delta timing: " +
			       String.format("%.2f", ((1.0/ reps) * delta)));

	    System.out.println();
	    // */

	    // /*
	    // size base tests
	    runTests(ontooo, 2000); // warm up
	    for ( int k = 100; k <= 1000; k+=100) { // gen1/f
	    // for ( int k = 200; k <= 200; k+=100) { // gen2/f
	    // for ( int k = 600; k <= 600; k+=100) { // gen3
		cnt = 0;
		long t0 = System.currentTimeMillis();
		int reps = 400;
		// gen1(ontooo, k, reps);
	        gen1f(ontooo, k, reps);
		// gen2(ontooo, k, reps);
	        // gen2f(ontooo, k, reps);
		// gen3(ontooo, k, reps);
		// gen3f(ontooo, k, reps);
		// add more here based on:
		long delta = System.currentTimeMillis() - t0;
		System.out.println("Example k: " + k + " delta timing: " + 
				   String.format("%.2f", ((1.0/ reps) * delta)) +
				   " cnt: " + (cnt/reps));
	    }
	    // */

	    /*
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

	    // showPairs();
	}
    } // end of main

     static private void showPairs() {
	 String a, b;
	 
	 for ( int i = 1; i < 3; i++ ) {
	     a = gen1arg1(i); b = gen1arg2(i);
	     System.out.println("a: " + a);
	     System.out.println("b: " + b);
	 }
	 System.out.println();
	 for ( int i = 1; i < 3; i++ ) {
	     a = gen1arg1f(i); b = gen1arg2(i);
	     System.out.println("a: " + a);
	     System.out.println("b: " + b);
	 }
	 System.out.println();
	 for ( int i = 3; i < 5; i++ ) {
	     a = gen2arg1(i); b = gen2arg2(i);
	     System.out.println("a: " + a);
	     System.out.println("b: " + b);
	 }
	 System.out.println();
	 for ( int i = 3; i < 5; i++ ) {
	     a = gen2arg1(i); b = gen2arg2f(i);
	     System.out.println("a: " + a);
	     System.out.println("b: " + b);
	 }
	 System.out.println();
	 for ( int i = 1; i < 3; i++ ) {
	     a = gen3arg1(i); b = gen3arg2(i);
	     System.out.println("a: " + a);
	     System.out.println("b: " + b);
	 }
     } // end showPairs

   static private void gen1(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1(size); b = gen1arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen1
   static private void gen1f(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen1arg1f(size); b = gen1arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen1f
   static private void gen2(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen2
   static private void gen2f(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen2arg1(size); b = gen2arg2f(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen2f
   static private void gen3(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen3
   static private void gen3f(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen3arg1(size); b = gen3arg2f(size); 
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   }
  static private void gen4(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen4
   static private void gen4f(Ontooo ontooo, int size, int reps) {
	 String a, b;
	 for ( int i = 1; i < reps; i++ ) {
	     a = gen4arg1(size); b = gen4arg2(size); 
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
   } // end gen4f


     /*
     static private void genA(Ontooo ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argument(i); b = gen2argument(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void genB(Ontooo ontooo, int rep) { 
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) {
	     a = gen1argumentf(i); b = gen2argument(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     static private void genC(Ontooo ontooo, int rep) {
	 String a, b;
	 for ( int i = 1; i < rep; i++ ) { 
	     a = gen2arg1(i); b = gen2arg2(i);
	     ontooo.clear();
	     Vector out = ontooo.unify(a, b);
	 }
     }
     */

     static private void runTests(Ontooo ontooo, int repeat) {
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
	    // 76 examples::
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
	    //////////////////////////////////////////////////////
	    ontooo.clear();
	    a = "P(a)";  b = "P(a)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(f(a))";  b = "P(f(a))"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)";  b = "P(a)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)";  b = "P(?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)";  b = "P(?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear(); 
	    a = "P(?x)";  b = "P(f(?y))";
	    out = ontooo.unify(a, b);
	    ontooo.clear(); 
	    a = "P(?x a)";  b = "P(a ?x)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
 	    a = "P(f(?x))";  b = "P(f(a))"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x f(?y))";  b = "P(f(?y) f(f(?z)))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) ?z ?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(f(?y ?z) a ?y)"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear(); 
	    a = "P(a ?x f(a ?x))"; b = "P(?y g(?y) f(?z g(?z)))";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(f(?y) f(b))"; 
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x)"; b = "P(f(?x))";
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
	    a = "P(?x ?x)"; b = "P(a b)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?x)"; b = "P(f(?y) ?y)";
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
	    a = "P(?x ?y ?z)"; b = "P(?y ?z ?v)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z a)";
	    out = ontooo.unify(a, b);
	    ontooo.clear();
	    a = "P(?x ?y ?z)"; b = "P(?y ?z f(x))";
	    out = ontooo.unify(a, b);
	}
    } //  runTests

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

 } // end  ZTest0

class Vnode {
    private Variable v;
    public Vnode(Variable x) { v = x; }
    public Variable getVariable() { return v; }
    private Term first = null;
    public Term getFirst() { return first; }
    public void setFirst(Term t) { first = t; }
    public boolean isRoot() { return (null != first); }
    // if variables != null then first != null and contains a variable or more
    protected HashSet variables = null;

    private int size = 1;
    public int getSize() { return size; }
    public void setSize(int s) { size = s; }

    protected boolean isVroot = true; 
    protected Variable myVroot = null;

    protected boolean checking = false;
    protected boolean checked = false;

    private Term l2 = null; // for post processing
    public void setL2(Term lx) { l2 = lx; }
    public Term ready() { return l2; }

} // end Vnode

class TermsPair1 {
    protected Term s1;
    protected Term t1;
    public TermsPair1(Term s1, Term t1) {
	this.s1 = s1; this.t1 = t1;
    }
} // end TermsPair1

class Ontooo {
    public void clear() {
	// int cnt = 0;
	htv = new Hashtable<Variable, Vnode>();
	sigma = new Vector();
	stack = new Stack<TermsPair1>();
    }
    private static boolean trace = false;  // reset as needed
    // private static boolean trace = true; 
    // private int cnt = 0;
    Parser parser = new Parser(false);
    private String arg1;
    private String arg2;
    public Vector unify(String a1, String a2) {
	if (trace) System.out.println("==========================================");
	// cnt = 0;
	// Text to lisp-like transformation using parser in the fol package
	Atom aa1 = parseIt(parser, a1);
	Atom aa2 = parseIt(parser, a2);
	if (trace) System.out.println("----------- parsing OK.");
	Vector out = dunify(aa1, aa2); // null | substitution

	// show result:
	if (trace) System.out.println("--------------\nout: Unification " + 
				      ( null == out ? "failed" : "ok" ) );
	if (  null == out ) return out; // when failed
	    // post processing
	if (trace) System.out.println("----------- post processing");

	// if (trace) System.out.println("cnt: " + cnt + "    ");
	
	if ( trace ) {
	    int lng = out.size();
	    for (int i = 0; i < lng; i++ ) {
		SubsZ s = (SubsZ) sigma.elementAt(i);
		Variable v = s.getVariable();
		Term val = s.getVal();
		System.out.println("v: " + v.html() + " --> " + val.html());
	    }
	}

	Vector subsList = buildSigma(sigma);
	if (trace) { 
	    int lng = subsList.size(); // # variables
	    System.out.println("Ordered substitution lng " + lng);
	    for (int j = 0; j < lng; j++) {
		SubsZ s = (SubsZ) subsList.elementAt(j);
		Variable vx = s.getVariable();
		Term val = s.getVal();
		System.out.print("vx " + vx.html() + "  ");
		System.out.println("val " + val.html());
	    }
	}
	// subsList is the ordered substitution when unification ok
	return subsList;
	// return out; // for now
    } // end unify

    public Vector buildSigma(Vector sigma) { 
	int lng = sigma.size();
	if ( trace ) 
	    System.out.println("buildSigma lng: " + lng);
	Vector out = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    SubsZ subs = (SubsZ) sigma.elementAt(i);
	    Variable vi = (Variable) subs.getVariable();
	    Term val  = subs.getVal(); // first
	    // System.out.println("sigmaI vi: " + vi.html() + " val: " + 
	    //                    val.html());
	    SubsZ subs2 = new SubsZ(vi, exploreVariable(vi, val));
	    Variable vi2 = (Variable) subs2.getVariable();
	    Term val2  = subs2.getVal();
	    // System.out.println("sigmaI vi2: " + vi2.html() + " val2: " + 
	    //                    val2.html());
	    out.addElement(subs2);
	}
	return out; 
    } // end buildSigma

    private Term exploreVariable(Variable vi, Term val) {
	// System.out.println("exploreVariable vi: " + vi.html() + " val: " + 
	//                    val.html());
	Vnode vnode = htv.get(vi);
	Term l2 = vnode.ready();
	if ( null != l2 ) return l2;
	l2 = descend(vi, val);
	if ( null == l2 ) l2 = vi;
	vnode.setL2(l2);
	// System.out.println("exploreVariable l2: " + l2.html());
	return l2; 
    } // end exploreVariable

    private Term descend(Variable vi, Term val) {
	// System.out.println("descend vi: " + vi.html() + " val: " + val.html());
	if ( null == val ) return null;
	if ( val instanceof Variable ) {
	    Variable v = (Variable) val;
	    Vnode vnode = htv.get(v);
	    Term first = vnode.getFirst();
	    if ( null != first ) 
		return exploreVariable(v, first);
	    if ( !vnode.isVroot ) 
		return descend(v, vnode.myVroot);
	    return v;
	}
	if ( val instanceof Symbol ) return val;
	Vnode vnodei = htv.get(vi);
	Term l2 = vnodei.ready();
	if ( null != l2 ) return l2;
	FTerm val2 = (FTerm) val;
	Symbol head = val2.getFunction();
	Vector args = val2.getArgs();
	int lng = args.size();
	Vector argsOut = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Term li = descend(vi, (Term) args.elementAt(i));
	    argsOut.addElement(li);
	}
	FTerm cl = new FTerm(head, argsOut);
	vnodei.setL2(cl);
	return cl; 
    } // end descend


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
	findVariables(out);
	return (Atom) out;
    } // end parseIt

    // private Hashtable htc = new Hashtable(); // not used
    private Hashtable<Variable, Vnode> htv = new Hashtable<Variable, Vnode>();

    private void findVariables(Formula in) {
	if ( in instanceof Variable ) {
	    Variable v = (Variable) in;
	    if ( !htv.containsKey(in) ) htv.put(v, new Vnode(v));
	}
	if ( in instanceof Atom ) {
	    Atom a = (Atom) in;
	    Vector args = a.getArgs();
	    int lng = args.size();
	    for ( int i = 0; i < lng; i++) 
		findVariables((Formula) args.elementAt(i));
	}
	if ( in instanceof FTerm ) {
	    FTerm f = (FTerm) in;
	    Vector args = f.getArgs();
	    int lng = args.size();
	    for ( int i = 0; i < lng; i++) 
		findVariables((Formula) args.elementAt(i));
	}
	// ignore contant = symbol	
    } // end findVariables

    private Vector sigma = new Vector(); // contains Subs elements
    public Vector getSigma() { return sigma; }
    public void addSubs(SubsZ s) { sigma.addElement(s); }

    private Vector dunify(Atom a1, Atom a2) {
	if ( !a1.getPredicate().getName().equals(a2.getPredicate().getName()) ) {
	    if ( trace ) System.out.println("Predicates differ");
	    return null; // because unequal predicates
	}
	boolean out = unif(a1, a2);
	if ( !out ) return null;
	if ( trace ) System.out.println("----- start findSolution ----");
	Enumeration enumx = htv.keys();
	while ( enumx.hasMoreElements() ) { // show what is in htv
	    Variable v = (Variable)enumx.nextElement();
	    Vnode vnodev = htv.get(v);
	    Variable v2 = v;
	    if ( !vnodev.isVroot ) {
		v2 = findVroot(vnodev);
		vnodev = htv.get(v2);
	    }
	    Term first =  vnodev.getFirst();
	    if ( null == first ) {
		if ( trace ) System.out.println("v: " + v.html() + " null"); 
		if ( vnodev.isVroot ) {
		    SubsZ sub = new SubsZ(v, v2); 
		    addSubs(sub);
		    continue;
		}
		Variable vx = vnodev.myVroot;
		SubsZ sub = new SubsZ(v, vx);
		addSubs(sub);
	    }
	    else {
		SubsZ sub = new SubsZ(v, first);
		addSubs(sub);
		HashSet vars = first.getVariables();
		if ( trace ) {
		    System.out.println("v: " + v.html() + " first: " + first.html() +
				       " |vars| = " + ( null == vars ? 0 :  vars.size() ) );
		}
	    }
	}

	// System.out.println("sigma size: " + sigma.size());
	return sigma;
    } // end dunify

    private Stack<TermsPair1> stack = new Stack<TermsPair1>();

    private boolean unif(Atom s, Atom t) {
	if ( trace ) 
	    System.out.println("unif s: " + s.html() + " t: " + t.html());
	Vector argS = s.getArgs(), argT = t.getArgs();
	int lng = argS.size();
	for ( int i = 0; i < lng; i++ ) {
	    Term s1 = (Term) argS.elementAt(i), t1 = (Term) argT.elementAt(i);
	    if ( trace ) 
		System.out.println("unif s1: " + s1.html() + " t1: " + t1.html());
	    stack.push(new TermsPair1(s1, t1)); //replacing:
	    // if ( !unif2(s1, t1) ) return message("unif s1 t1", s1, t1);
	}
	while ( !stack.empty() ) {
	    TermsPair1 tp = stack.pop();
	    Term s1 = tp.s1, t1 =  tp.t1;
	    if ( !unif2(s1, t1) ) return message("unif s1 t1", s1, t1);
	}
	if ( !unif3() ) {
	    if ( trace ) 
		System.out.println("unif fail s: " + s.html() + 
				   " t: " + t.html()); 
	    return false;
	}
	return true;
    } // end of unif 

    private boolean message(String name, Term sx, Term tx) {
	if ( trace ) 
	    System.out.println(name + " failure sx: " + sx.html() + 
			       " tx: " + tx.html());
	return false;
    } // end message

    private boolean unif2(Term s, Term t) {
	if ( trace )
	    System.out.println("unif2 variable s: " + s.html() + " t: " + t.html());
	// ZTest0.cnt++;
	if ( s.equals(t) ) return true;  // identical constants or variables
	if ( (s instanceof Variable) ) {
	    Variable sv = (Variable) s;
	    Vnode snode = htv.get(sv);
	    if ( null == snode ) { 
		snode = new Vnode(sv);
		htv.put(sv, snode);
	    } else 
	    if ( !snode.isVroot ) {
		sv = findVroot(snode);
		snode = htv.get(sv);
	    }
	    // sv is root or sv is Vroot
	    if ( (t instanceof Variable) ) {
		Variable tv = (Variable) t;
		Vnode tnode  = htv.get(tv);
		if ( null == tnode ) {
		    tnode = new Vnode(tv);
		    htv.put(tv, tnode);
		} else
		if ( !tnode.isVroot ) {
		    tv = findVroot(tnode);
		    tnode = htv.get(tv);
		}
		// sv is root or sv is Vroot and tv is root or tv is Vroot 
		if ( sv.equals(tv) ) return true;
		int sizes = snode.getSize(), sizet = tnode.getSize();
		if ( sizet <= sizes ) 
		    snode.setSize(sizes + sizet); 
		else
		    tnode.setSize(sizes + sizet);
		if ( snode.isRoot() ) {
		    Term sterm = snode.getFirst();
		    if ( tnode.isRoot() ) {
			if ( sizet <= sizes ) {
			    tnode.isVroot = false;
			    tnode.myVroot = sv;
			} else {
			    snode.isVroot = false;
			    snode.myVroot = tv;
			}
			Term tterm = tnode.getFirst();
			// optional
			if ( snode.variables != null && snode.variables.contains(tv) )
			    return message(" unif2A sterm contains tv", sterm, tv);
			if ( tnode.variables != null && tnode.variables.contains(sv) )
			    return message("unif2B tterm contains sv", tterm, sv);
			// */
			if ( !unif2(sterm, tterm) )
			    return message("unif2C sterm tterm", sterm, tterm);
			return true;
		    }
		    // snode is root and  tnode is not root, tnode is Vroot
		    ///* // optional
		    if ( snode.variables != null && snode.variables.contains(tv) ) 
		        return message("unif2D sterm contains tv", sterm, tv);
		    // */
		    tnode.isVroot = false;
		    tnode.myVroot = sv;
		    return true;
		}
		// snode is not root, is Vroot
		if ( tnode.isRoot() ) {
		    Term tterm = tnode.getFirst();
		    // /* // optional
		    if ( tnode.variables != null && tnode.variables.contains(sv) )
		        return message("unif2E tterm contains sv", tterm, sv);
		    // */
		    snode.isVroot = false;
		    snode.myVroot = tv;
		    return true;
		}
		// snode is not root, is Vroot & tnode is not root, is Vroot
		if ( sizet <= sizes ) {
		    tnode.isVroot = false;
		    tnode.myVroot = sv;
		} else {
		    snode.isVroot = false;
		    snode.myVroot = tv;
		}
		return true;
	    }
	    // s variable &  t not a variable
	    if ( snode.isRoot() ) {
		Term sterm = snode.getFirst();
		if ( !unif2(sterm, t) ) return message("unif2F sterm t", sterm, t);
		return true;
	    }
	    //  s variable & s is Vroot  t not a variable
	    snode.variables = t.getVariables();
	    if ( snode.variables != null && snode.variables.contains(sv) )
		return message("unif2G t contains sv", t, sv);
	    snode.setFirst(t);
	    return true;
	}
	// s not a variable
	if ( (t instanceof Variable) ) {
	    Variable tv = (Variable) t;
	    Vnode tnode  = htv.get(tv);
	    if ( null == tnode ) {
		tnode = new Vnode(tv);
		htv.put(tv, tnode);
	    } else
	    if ( !tnode.isVroot ) {
		tv = findVroot(tnode);
		tnode  = (Vnode) htv.get(tv);
	    }
	    if ( tnode.isRoot() ) { 
		Term tterm = tnode.getFirst();
		if ( !unif2(tterm, s) ) return message("unif2H tterm s", tterm, s);
		return true;
	    }
	    //  s not a variable & tnode is not root isVroot 
	    tnode.variables = s.getVariables();
	    if ( tnode.variables != null && tnode.variables.contains(tv) )
	       return message("unif2I s contains tv", s, tv);
	    tnode.setFirst(s);
	    return true;
	}
	// s not a variable & t not a variable 
	return unif4(s, t); // both s and t are not variables
    } // end unif2

    private Variable findVroot(Vnode vn) {
	if ( vn.isVroot ) return vn.getVariable();
	Variable vx = vn.myVroot;
	Vnode vxnode = htv.get(vx);
	Variable y = findVroot(vxnode);
	vn.myVroot = y;
	return y;
    } // end findVroot

    private boolean unif3() {
	if ( trace ) { // show variables details
	    System.out.println("  unif3 htv.size: " + htv.size());
	    Enumeration enumx = htv.keys();
	    while ( enumx.hasMoreElements() ) { // show what is in htv
		Variable v = (Variable)enumx.nextElement();
		System.out.println("v: " + v.html());
		Vnode vn = htv.get(v);
		Term first = vn.getFirst();
		if ( null == first ) System.out.println("no first"); else
		    System.out.println("first: " + first.html());
		System.out.println("isRoot: " + vn.isRoot());
		System.out.println("isVroot: " + vn.isVroot);
		Variable myVroot = vn.myVroot;
		System.out.println("myVroot: " + 
			  ( null == myVroot ? "no myVroot" : myVroot.html()));		
	    }
	}
	return unif5();
    } // end unif3

    private boolean unif4(Term s, Term t) { // s and t are both not variables
	if ( trace ) 
	    System.out.println("unif4 enter s: " + s.html() + " t: " + t.html());
	if ( s instanceof FTerm ) {
	    FTerm s1 = (FTerm) s;
	    if ( t instanceof FTerm ) {
		FTerm t1 = (FTerm) t;
		if ( !s1.getFunction().equals(t1.getFunction()) ) return false;
		Vector s1Args = s1.getArgs(), t1Args = t1.getArgs();
		int lng = s1Args.size();
		for ( int i = 0; i < lng; i++ ) {
		    Term s1A = (Term)s1Args.elementAt(i), 
			 t1A = (Term)t1Args.elementAt(i);
		    // to avoid stack overflow
		    stack.push(new TermsPair1(s1A, t1A));
		}
		return true;
	    } // t not FTerm
	    return false;
	} // s not FTerm
	if ( t instanceof FTerm ) return false;
	// s & t are symbols
	return s.equals(t);
    } // end unif4
    
    private boolean unif5() { // occur checks
	if ( trace ) System.out.println("unif5 enter");
	Enumeration enumx = htv.keys(); // get the variables
	while ( enumx.hasMoreElements() ) { 
	    Variable v = (Variable)enumx.nextElement();
	    if ( trace ) System.out.println("unif5 check v: " + v.html());
	    if ( !check(v) ) return message("unif5 ", v, v);
	}
	if ( trace ) System.out.println("unif5 exit");
	return true;
    }
    private boolean check(Variable v) {
	ZTest0.cnt++;
	// if ( trace ) System.out.println("check v: " + v.html());
	Vnode vn = htv.get(v);
	if ( vn.checked ) {
	    // if ( trace ) System.out.println("checked! v: " + v.html());
	    return true;
	}
	if ( vn.checking ) return message("check checking cycle", v, v);
	if ( !vn.isRoot() ) { // not matched against a term
	    if ( vn.isVroot ) { // not matched against a variable
		vn.checked = true;
		return true;
	    }
	    vn.checking = true;
	    Variable vnVroot = vn.myVroot;
	    if ( !check(vnVroot) ) return message("unif5 ", v, vnVroot);
	    vn.checking = false;
	    vn.checked = true;
	    return true;
	} // vn is root
	vn.checking = true;
	// Term first = vn.getFirst();
	// if ( trace ) System.out.println("check first: " + first.html());
	// HashSet variables = first.getVariables();
	HashSet variables = vn.variables;
	if ( null == variables ) {
	    vn.checking = false;
	    vn.checked = true;
	    return true;
	}
	for( Iterator i = variables.iterator(); i.hasNext(); ) {
	    Variable w = (Variable)i.next();
	    if ( !check(w) ) return message("check3 v w ", v, w);
	}
	vn.checking = false;
	vn.checked = true;
	return true;
    } // end check
    
} // end Ontooo

class SubsZ {
    private Variable vl;
    private Term val;
    SubsZ(Variable vx, Term v) {
	vl = vx; val = v;
    }
    public Variable getVariable() { return vl; }
    public Term getVal() { return val; }
} // end SubsZ

