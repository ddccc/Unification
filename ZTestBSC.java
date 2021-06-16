// c:/bsd/rigel/Unification/fol/ZTestBSC.java
// (C) OntoOO Inc / Dennis de Champeaux/ Tue Mar 02 15:38:10 2021

package fol;

import java.util.*;
import java.io.*;

public class ZTestBSC {
    private static final Boolean trace = true;  // reset as needed
    // private static final Boolean trace = false;  // reset as needed
    public static void main (String [] arguments) {
	if (trace) System.out.println("------------------------------------------");
	// The unify function return null in case of non-unification

	// Parser parser = new Parser(false);
	for (int i = 1; i <= 1; i++) {
	// for (int i = 100; i <= 1000; i=i+100) {
	    if (trace) System.out.println("size: " + i);
	    // String a = gen1argument(i); // generator
	    // String b = gen2argument(i); // generator
	    // String a = gen2arg1(i); // generator
	    // String b = gen2arg2(i); // generator
	    // System.out.println("a: " + a);
	    // System.out.println("b: " + b);
	    // ad hoc exmples if the generor is not used:
	    //  not unifiable
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
	    // String a = "P(?x g(?x))"; String b = "P(f(?y) ?y)";  // find-solution
	    // String b = "P(?x h(?z) f(?x))"; String a = "P(g(?y) ?y ?z)"; // find-solution

	    // unifiable
	    // String a = "P(a)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(a)"; 
	    // String a = "P(?x)";  String b = "P(?y)"; 
	    // String a = "P(?x)";  String b = "P(?x)"; 
	    // String a = "P(?x)";  String b = "P(f(?y))"; 
	    // String a = "P(?x a)";  String b = "P(a ?x)";
 	    // String a = "P(f(?x))";  String b = "P(f(a))"; 
	    // String a = "P(?x a)";  String b = "P(b ?y)";
	    // String a = "P(?x)";  String b = "P(?y)";
	    // String a = "P(?x ?y ?z)";  String b = "P(?y ?z a)";

	    // String a = "P(?x f(?y))";  String b = "P(f(?y) f(f(?z)))"; // test post
	    // String a = "P(a ?x f(a ?x))"; String b = "P(?y g(?y) f(?z g(?z)))"; // text

	    // String a = "P(?x ?y a)"; String b = "P(?y ?x ?x)";
	    // String a = "P(f(?x) g(a))"; String b = "P(?y ?x)";

	    // String a = "P(f(g(h(i(?y)))))"; String b = "P(?x)";
	    // String a = "P(?x ?y ?z ?v)";  String b = "P(a ?z ?v ?x)";
	    // String a = "P(g(a) g(b))"; String b = "P(?x ?x)";
	    String a = "P(a b ?x)"; String b = "P(?x ?y ?y)";

	    long t0 = System.currentTimeMillis();
	    BSC bsc = new BSC();
	    Vector out = bsc.unify(a, b);
	    long delta = System.currentTimeMillis() - t0;
	    System.out.println("Example i: " + i + " delta timing: " + delta);
	    System.out.println("--------------\nout: Unification " +
	    	       ( null == out ? "failed" : "ok" ) );
	}
    } // end of main

    // Generator of 1st argument that causes normally exponential blow up 
    static public String gen1argument(int n) { 
	if ( n <= 0 ) {
	    System.out.println("n <= 0");
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
    } // end  gen1argument
    // Generator of 2nd argument that causes normally exponential blow up
    static public String gen2argument(int n) { 
	if ( n <= 0 ) {
	    System.out.println("n <= 0");
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
    } // end gen2argument
    static public String gen2arg1(int n) { 
	if ( n <= 0 ) {
	    System.out.println("n <= 0");
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
    } // end gen1arg2
    static public String gen2arg2(int n) { 
	if ( n <= 0 ) {
	    System.out.println("n <= 0");
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

} // end  ZTestBSC

class BSC {
    // private static boolean trace = false;  // reset as needed
    private static Boolean trace = true; 

    Parser parser = new Parser(false);
    public Vector unify(String a, String b) {
	// Text to lisp-like transformation using parser in the fol package
	parseArguments(a, b);
	if (trace) System.out.println("----------- parsing OK.");
	createNodeBLayers(s, t);
	if (trace) System.out.println("----------- nodes done.");
	if (true) System.out.println("fs1: " + fs1.html() + " ft1: " + ft1.html());
	Vector out = bunify(fs1, ft1); // null | substitution
	// show result:
	if (trace) System.out.println("--------------\nout: Unification " + 
				      ( null == out ? "failed" : "ok" ) );
	if (  null == out ) return out; // when failed
	    // post processing
	if (trace) System.out.println("----------- post processing");

	// System.out.println("|out| " + out.size()); 
	// System.out.println("|sigma | " + sigma.size()); 
	// out == sigma

	Vector subsList = buildSigma(out);
	// if (trace) { 
	if (true) { 
	    int lng = subsList.size(); // # variables
	    System.out.println("Ordered substitution lng " + lng);
	    for (int j = 0; j < lng; j++) {
		Subsbb s = (Subsbb) subsList.elementAt(j);
		NodeB vx = s.getVariableNodeB();
		NodeB val = s.getVal();
		System.out.print("vx " + vx.string() + "  ");
		System.out.println("val " + val.string());
	    }
	}
	// subsList is the ordered substitution when unification ok
	return subsList;
	// return out; // for now
    } // end unify

    private FTerm s, t;
    public void parseArguments(String a, String b) {
	Atom za = parseIt(parser, a);
	Atom zb = parseIt(parser, b);
	Symbol zas = za.getPredicate(), zbs = zb.getPredicate();
	if ( !zas.getName().equals(zbs.getName()) ) {
	    // trouble thus make fake FTerm
	    s = new FTerm(zas, za.getArgs());
	    t = new FTerm(zbs, zb.getArgs());
	    return;
	}
	Symbol sy = new Symbol("foo");
	s = new FTerm(sy, za.getArgs());
	t = new FTerm(sy, zb.getArgs());
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
    } // end parseIt

    private FTermNodeB fs1, ft1;
    public void createNodeBLayers(FTerm s, FTerm t) {
	fs1 = new FTermNodeB(s); // 
	fs1.createDownNodeB(this);
	if ( trace ) System.out.println("createDownNodeB fs1: " + fs1.html());

	ft1 = new FTermNodeB(t); // 
	ft1.createDownNodeB(this);
	if ( trace ) System.out.println("createDownNodeB ft1: " + ft1.html());

    } // end createNodeBLayers

    private Hashtable htc = new Hashtable();
    public ConstantNodeB getConstantNodeB(Symbol s) {
	return (ConstantNodeB) htc.get(s); // returns null if not found
    } // end getConstantNodeB(
    public void addConstantNodeB(Symbol s, ConstantNodeB cl) {
	htc.put(s, cl);
    } // end addConstantNodeB

    private Hashtable htv = new Hashtable();
    public VariableNodeB getVariableNodeB(Variable v) {
	return (VariableNodeB) htv.get(v); // returns null if not found
    } // end getVariableNodeB
    public void addVariableNodeB(Variable v, VariableNodeB vl) {
	htv.put(v, vl);
    } // end addVariableNodeB

    private Vector sigma = new Vector(); // contains Subsbb elements
    public Vector getSigma() { return sigma; }
    public void addSubs(Subsbb s) { sigma.addElement(s); }

    private Vector bunify(FTermNodeB la1, FTermNodeB la2) {
	if ( !la1.getSymbol().getName().equals(la2.getSymbol().getName()) ) {
	    if ( trace ) System.out.println("'predicates' differ");
	    return null; // because unequal predicates
	}
	boolean out = unifClosure(la1, la2);
	if ( !out ) return null;
	if ( trace ) 
	    System.out.println("-------------- start findSolution -----------");
	out = findSolution(la1);
	if ( !out ) return null;
	return sigma; 
    } // end bunify

    private boolean unifClosure(NodeB s, NodeB t) {
	if ( trace ) 
	    System.out.println("       unifClosure s: " + s.html() + " t: " + t.html());
	s = s.find(); // get representative 
	t = t.find(); 
	if ( trace ) 
	    System.out.println("unifClosure2 s: " + s.html() + " t: " + t.html());
	if ( s.equals(t) ) return true;
	NodeB s1 = s.getSchema(), t1 = t.getSchema(); 
	if ( trace ) {
	    System.out.println("unifClosure3 s1: " + s1.html() + " t1: " + t1.html());
	    System.out.println("class s1: " + s1.getMyClass().html() + 
			       " t1: " + t1.getMyClass().html());
	    System.out.println("schema s1: " + s1.getSchema().html() + 
			       " t1: " + t1.getSchema().html());
	}
	if ( s1 instanceof ConstantNodeB && t1 instanceof ConstantNodeB ) {
	    if ( s1.equals(t1) ) return true; // constants are unique
	    if ( trace )
		System.out.println("unifClosure: constants failure ");
	    return false;
	}
	if (
	    (s1 instanceof FTermNodeB && t1 instanceof FTermNodeB) ) {
	    // Needs compilation
	    FTermNodeB s2 = (FTermNodeB) s1, t2 = (FTermNodeB) t1;
	    s2.createDownNodeB(this); t2.createDownNodeB(this); // one layer at the time!!
	    if ( !s2.getSymbol().getName().equals(t2.getSymbol().getName()) ) {
		// incompatible function names
		if ( trace ) { 
		    System.out.print("unifClosure: functions failure ");
		    System.out.println("s1: " + s1.html() + " t1: " + t1.html());
		}
		return false; 
	    }

	    System.out.println("unifClosureA s: " + s.html() + " t: " + t.html());
	    System.out.println("unifClosureB s2: " + s2.html() + " t2: " + t2.html());
	    System.out.println("unifClosureC s2f: " + s2.getSchema().html() + 
	    	       " t2f: " + t2.getSchema().html());
	    union(s, t); 
	    // System.out.println("unifClosure4 s: " + s.html() + " t: " + t.html());
	    // System.out.println("unifClosure5 s2: " + s2.html() + " t2: " + t2.html());
	    // System.out.println("unifClosure6 s2f: " + s2.getSchema().html() + 
	    //		       " t2f: " + t2.getSchema().html());
	    Vector argss1 = s2.getNodeBdArgs(); 
	    Vector argst1 = t2.getNodeBdArgs();
	    int lng = argss1.size();
	    // System.out.println("unifClosure: lng " + lng);
	    for (int i = 0; i < lng; i++ ) {
		NodeB l1 = (NodeB) argss1.elementAt(i);
		NodeB l2 = (NodeB) argst1.elementAt(i);
		// System.out.println("unifClosure: " + l1.html() + " " +  l2.html() );
		if ( l1.equals(l2) ) continue; // skip
		boolean out = unifClosure(l1, l2);
		if ( !out ) {
		    if ( trace ) {
			System.out.print("unifClosure failure between l1 " + l1.html());
			System.out.println(" and l2 " + l2.html());
		    }
		    return false;
		}
	    }
	    return true;
	}
	if ( ( s1 instanceof ConstantNodeB && t1 instanceof FTermNodeB ) || 
	     ( t1 instanceof ConstantNodeB && s1 instanceof FTermNodeB ) ) {
	    if ( trace ) {
		System.out.print("unifClosure failure between s " + s.html());
		System.out.println(" and t " + t.html());
		System.out.print("unifClosure failure between s1 " + s1.html());
		System.out.println(" and t1 " + t1.html());
	    }
	    return false;
	}
	union(s, t);
	return true;
    } // end unifClosure

    private void union(NodeB s, NodeB t) {
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
	    HashSet hs = s.getVars();
	    for (Iterator i = t.getVars().iterator(); i.hasNext();) {
		NodeB x = (NodeB)i.next();
		if ( !hs.contains(x) ) hs.add(x);
	    }
	    if ( s instanceof VariableNodeB ) s.setSchema(t.getSchema());
	    t.setMyClass(s); 
	} else { 
	    t.setSize(sizeS + sizeT);
	    HashSet ht = t.getVars();
	    for (Iterator i = s.getVars().iterator(); i.hasNext();) {
		NodeB x = (NodeB)i.next();
		if ( !ht.contains(x) ) ht.add(x);
	    }
	    if (t instanceof VariableNodeB  ) t.setSchema(s.getSchema());
	    s.setMyClass(t); 
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

    private boolean findSolution(NodeB s1) {
	if ( trace ) 
	    System.out.println("--------- findSolution s1: " + s1.html());
	NodeB s2 = s1.find();
	if ( trace ) 
	    System.out.println("findSolution2 find s2: " + s2.html());
	NodeB s = s2.getSchema();
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
	if ( s instanceof FTermNodeB ) {
	    FTermNodeB s3 = (FTermNodeB) s;
	    s3.setVisited(true);
	    if ( trace ) 
		System.out.println("findSolution set visited s3: " + s3.html());
	    Vector args = s3.getNodeBdArgs();
	    int lng = args.size();
	    for (int i = 0; i < lng; i++) {
		NodeB ni = (NodeB) args.elementAt(i);
		boolean out = findSolution(ni);
		if ( !out ) {
		    if ( trace ) 
			System.out.println("findSolution cycle ni: " + ni.string());
		    return false;
		}
	    }
	    s.setVisited(false);
	}
	// s is contant or variable
	s.setAcyclic(true);
	HashSet hs = s2.getVars();
	if ( trace ) {
	    System.out.println("s2: " + s2.html());
	    System.out.println("findSolution s2 hs.size: " + hs.size());
	}
	for (Iterator i = hs.iterator(); i.hasNext();) {
	    VariableNodeB x = (VariableNodeB)i.next();
	    // System.out.println("findSolution x: " + x.html());
	    if ( null != x.getSubs() ) continue;
	    if ( !x.equals(s) ) { 
		if ( trace ) System.out.println("findSolution Subs x: " +
				 x.html() + " ---> s: " + s.html());
		Subsbb su = new Subsbb(x, s);
		x.setSubs(su); // store in x and ...
		addSubs(su); // ... add to 'global' sigma 
	    }
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
	    Subsbb subs = (Subsbb) sigma.elementAt(i);
	    VariableNodeB vi = (VariableNodeB) subs.getVariableNodeB();
	    NodeB val  = subs.getVal();
	    // System.out.println("sigmaI vi: " + vi.html() + " val: " + val.html());
	    Subsbb subs2 = new Subsbb(vi, exploreVariable(vi, val));
	    VariableNodeB vi2 = (VariableNodeB) subs2.getVariableNodeB();
	    NodeB val2  = subs2.getVal();
	    // System.out.println("sigmaI vi2: " + vi2.html() + " val2: " + val2.html());
	    out.addElement(subs2);
	}
	return out; 
    } // end buildSigma
    private NodeB exploreVariable(VariableNodeB vi, NodeB val) {
	NodeB l2 = vi.ready();
	if ( null != l2 ) return l2;
	l2 = descend(val);
	if ( null == l2 ) l2 = vi;
	vi.setL2(l2);
	return l2; 
    } // end exploreVariable
    public NodeB descend(NodeB val) {
	if ( null == val ) return null;
	// System.out.println("descend val " + val.html());
	if ( val instanceof VariableNodeB ) {
	    VariableNodeB v = (VariableNodeB) val;
	    Subsbb s = v.getSubs();
	    if ( null == s ) return v;
	    // replace variable terminals in the substitution as need be
	     return exploreVariable(v, s.getVal());
	}
	if ( val instanceof ConstantNodeB ) return val;
	NodeB l2 = val.ready();
	if ( null != l2 ) return l2;
	// val is a FTermLayer
	FTermNodeB val2 = (FTermNodeB) val;
	Symbol head = val2.getSymbol();
	Vector args = val2.getNodeBdArgs();
	int lng = args.size();
	Vector argsOut = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    NodeB li = descend((NodeB) args.elementAt(i));
	    argsOut.addElement(li);
	}
	FTermNodeB cl = new FTermNodeB(head, argsOut);
	val.setL2(cl);
	return cl; 
    } // end descend

} // end Baader

abstract class NodeB { 
    protected static final Boolean trace = false;  // reset as needed
    /*
    NodeB() {
	myClass = this;
	schema = this;
    }
    */
    private int size = 1;
    public int getSize() { return size; }
    public void setSize(int i) { size = i; }
    protected NodeB myClass = null; // representative if myClass = this
    public NodeB getMyClass() { return myClass; }
    public void setMyClass(NodeB n) { myClass = n; }
    protected NodeB schema = null; 
    public NodeB getSchema() { return schema; } // schema function
    public void setSchema(NodeB n) { schema = n; }
    private boolean visited = false;
    public boolean getVisited() { return visited; }
    public void setVisited(boolean b) { visited = b; }

    private boolean acyclic = false;
    public boolean getAcyclic() { return acyclic; }
    public void setAcyclic(boolean b) { acyclic = b; }

    private HashSet vars = new HashSet();
    public HashSet getVars() { return vars; }
    public void setVars(HashSet h) { vars = h; }
    public NodeB find() {
	if ( this != myClass ) {
	    // System.out.println("find() this: " + html() + " myClass: " +  myClass.html());
	    myClass = myClass.find();
	}
	return myClass;
    }
    abstract String string();
    abstract String html();

    private NodeB l2 = null; // for post processing
    public void setL2(NodeB lx) { l2 = lx; }
    public NodeB ready() { return l2; }
} // end NodeB

class ConstantNodeB extends NodeB { // for constants
    protected Symbol symbol;
    ConstantNodeB(Symbol s) {
	// super();
	symbol = s;
	myClass = this;
	schema = this;
    }
    public Symbol getSymbol() { return symbol; }
    public String html() { return "ConstantNodeB " + string(); }
    public String string() { return symbol.getName(); }
} // end ConstantNodeB

class VariableNodeB extends NodeB { // for variables
    protected Variable variable;
    VariableNodeB(Variable v) {
	// super();
	variable = v;
	myClass = this;
	schema = this;
	getVars().add(this); 
    }
    public Variable getVariable() { return variable; }
    public String html() { return "VariableNodeB " + string(); }
    public String string() { return variable.html(); }
    private Subsbb subs = null; // the substitution of the variable
    public void setSubs(Subsbb s) { subs = s; }
    public Subsbb getSubs() { return subs; }
} // end VariableNodeB

class FTermNodeB extends NodeB {
    protected Symbol symbol; // name of function
    protected Vector args;
    FTermNodeB(Formula formula) {
	myClass = this;
	schema = this;
	FTerm ft = (FTerm) formula; // function
	symbol = ft.getFunction();
	args = ft.getArgs();
    } // end constructor

    public Symbol getSymbol() { return symbol; }
    public Vector getArgs() { return args; }
    protected Vector nodedArgs = new Vector();
    protected void addNodeBdArg(NodeB in) { nodedArgs.addElement(in); }
    protected Vector getNodeBdArgs() { return nodedArgs; }
    // create lower nodes ..
    public void createDownNodeB(BSC bsc) {
	int lng = args.size();
	if ( trace )
	    System.out.println("NodeB-createDownNodeBs lng: " + lng);
	for (int i = 0; i < lng; i++) { 
	    Term term = (Term) args.elementAt(i);
	    if ( trace )
		System.out.println("term: " + i + " " + term.html());
	    if ( term instanceof Symbol ) {
		Symbol s = (Symbol) term;
		if ( !(s instanceof Variable ) ) { // constant
		    ConstantNodeB cl = (ConstantNodeB)bsc.getConstantNodeB(s);
		    if ( null != cl ) {
			addNodeBdArg(cl);
			continue;
		    }		
		    cl = new ConstantNodeB(s);
		    bsc.addConstantNodeB(s, cl);
		    addNodeBdArg(cl);
		    continue;
		}
		// term is variable/ check first whether encounterd already
		Variable v = (Variable) term;
		VariableNodeB vl = (VariableNodeB)bsc.getVariableNodeB(v);
		if ( null != vl ) {
		    addNodeBdArg(vl);
		    continue;
		}		
		// create variable-layer
		vl = new VariableNodeB(v);
		bsc.addVariableNodeB(v, vl);
		addNodeBdArg(vl);
		continue;
	    }
	    // term is FTerm thus create a FTermNodeB
	    FTerm fterm = (FTerm) term;
	    FTermNodeB ftl = new FTermNodeB(fterm);
	    addNodeBdArg(ftl);
	    /*
	    // recurse further down
	    if ( trace ) 
		System.out.println("NodeB-createDownLayers/ recurse down fterm ");
	    ftl.createDownNodeB(bsc);
	    if ( trace ) System.out.println("cDN exit of fterm " + fterm.html() + " ");
	    */
	}
    } // end createDownNodeBs

    // This constructor is used by the post-processor
    FTermNodeB(Symbol head, Vector argsx) {
	symbol = head; nodedArgs = argsx;
	int lng = argsx.size();
	args = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    NodeB li = (NodeB) argsx.elementAt(i);
	    if ( trace ) 
		System.out.println("li : " + li.html());
	    if ( li instanceof ConstantNodeB) {
		ConstantNodeB lic = (ConstantNodeB) li;
		args.addElement(lic.getSymbol());
		continue;
	    }
	    if ( li instanceof VariableNodeB) {
		VariableNodeB liv = (VariableNodeB) li;
		args.addElement(liv.getVariable());
		continue;
	    }
	    FTermNodeB lix = (FTermNodeB) li;
	    Symbol symbolx = lix.getSymbol();
	    Vector argsy = lix.getArgs();
	    FTerm ft = new FTerm(symbolx, argsy);
	    args.addElement(ft);
	}
    } // FTermNodeB

    String string() { // for display
	FTerm ft = new FTerm(symbol, args);
	return ft.html();
    } 
    String html() { // for display
	return "FTermNodeB " + string();
    }
} // end FTermNodeB

class Subsbb {
    private NodeB vl;
    private NodeB val;
    Subsbb(NodeB vx, NodeB v) {
	vl = vx; val = v;
    }
    public NodeB getVariableNodeB() { return vl; }
    public NodeB getVal() { return val; }
}

/*
Unify( s : node; t : node )
begin
	UnifClosure(s, t);
	FindSolution(s);
end;

UnifClosure( s : node; t : node )
begin
	s := Find(s); f Find representatives g
	t := Find(t);
	if s and t are the same node then
	{ Do nothing }
	else 
          begin
               if &([s]) = f(s1, , , sn) and 
	          &([t]) = g(t1, , ,  tm) for n,m  >= 0 
	       then 
	       begin
	          if f = g then 
	          begin
	              Union(s, t);
	              for i := 1 to n do UnifClosure( si, ti );
	          end
	          else Exit with failure { Symbol clash }
   	       end
	       else Union(s, t);
          end; 
end;

Union( s : node; t : node ) { s and t are representatives }
begin
     if size(s) >= size(t) then 
     begin
        size(s) := size(s) + size(t);
        vars(s) := concatenation of lists vars(s) and vars(t);
        if &([s]) is a variable then
              &([s]) := &([t]);
        class(t) := s;
     end
     else 
     begin
        size(t) := size(t) + size(s);
	vars(t) := concatenation of lists vars(t) and vars(s);
	if &([t]) is a variable then
	      &([t]) := &([s]);
	class(s) := t;
     end;
end;

Find( s : node ) { Returns representative for [s] and compresses paths }
     t : node;
     begin
        if class(s) = s f s is a representative g 
	then
	    Return s;
	else begin
               t := Find(class(s));
               class(s) := t;
               return t;
             end;
      end;

FindSolution(s : node); { Fails if exists a cycle below s }
begin;
    s := &(Find(s));
    if acyclic(s) 
    then
       Return; { s is not part of a cycle }
    if visited(s) 
    then
       Fail; { Exists a cycle }
    if s = f(s1; : : : ; sn) for some n > 0 then 
    begin
        visited(s) := true;
        for i := 1 to n do
	    FindSolution(si);
        visited(s) := false;
    end;
    acyclic(s) := true;
    foreach x in vars(Find(s)) do
       if x != s then
       Add [x -> s] to front of sigma;
end;
*/
