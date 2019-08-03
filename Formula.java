// (C) OntoOO Inc 2004 Dec
package fol;

import java.util.*;
import java.io.Serializable;

public abstract class Formula implements Serializable {
    abstract public String html();
    protected HashSet variables = null;
    public int getNumberOfVariables() { return variables.size(); }
    public HashSet getVariables() { return variables; }
    abstract public Formula subst(Variable var, Term term); 
    public Formula sublis(Vector substitutions) { 
	int lng = substitutions.size();
	Formula out = this;
	for ( int i = 0; i < lng; i++ ) {
	    Substitution sub = (Substitution) substitutions.elementAt(i);
	    Variable var = sub.getVariable();
	    Term term = sub.getTerm();
	    out = out.subst(var, term);
	}
	return out;
    }

    static final private boolean trace = false;
    static int cnt = 0;
    static public Formula insurer(Formula in) { 
	cnt = 0;
	Formula out1 = insurer1(in);
	// System.out.println("insurer1 out1: " + out1.html());
	cnt = 0;
	return insurer3(out1);
    }
    static public Formula insurer1(Formula in) { 
	if (trace) {
	    cnt++;
	    System.out.println("cnt: " + cnt + " " + in.html());
	}
	Formula out, out2, in1, in2;
	Vector vec = new Vector();
	if ( in instanceof Implication ) {
	    Implication imp = (Implication) in;
	    vec.addElement(new Negation(imp.getCondition()));
	    vec.addElement(imp.getConclusion());
	    return insurer1(new Disjunction(vec));
	}
	if ( in instanceof Equivalence ) {
	    Equivalence equiv = (Equivalence) in;
	    in1 = equiv.getLeft();
	    in2 = equiv.getRight();
	    vec.addElement(new Negation(in1));
	    vec.addElement(in2);
	    out = new Disjunction(vec);
	    vec = new Vector();
	    vec.addElement(in1);
	    vec.addElement(new Negation(in2));
	    out2 = new Disjunction(vec);
	    vec = new Vector();
	    vec.addElement(out);
	    vec.addElement(out2);
	    return insurer1(new Conjunction(vec));
	}
	if ( in instanceof Negation ) {
	    Negation neg = (Negation) in;
	    out = insurer1(neg.getFormula());
	    if ( out == Symbol.TRUE ) return Symbol.FALSE;
	    if ( out == Symbol.FALSE ) return Symbol.TRUE;
	    if ( out instanceof Negation ) {
		neg = (Negation) out;
		return neg.getFormula();
	    }
	    if ( out instanceof Conjunction ) {	 
		Conjunction con = (Conjunction) out;
		Vector vecIn = con.getConjuncts();
		int lng = vecIn.size();
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    vec.addElement(new Negation(in1));
		}
		return insurer1(new Disjunction(vec));
	    }  
	    if ( out instanceof Disjunction ) {	  
		Disjunction dis = (Disjunction) out;
		Vector vecIn = dis.getDisjuncts();
		int lng = vecIn.size();
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    vec.addElement(new Negation(in1));
		}
		return insurer1(new Conjunction(vec));
	    }  
	    if ( out instanceof Universal ) {	  
		Universal uni = (Universal) out;
		Variable var = uni.getVariable();
		in1 = uni.getBody();
		out2 = new Negation(in1);
		return insurer1(new Existential(var, out2));
	    }
	    if ( out instanceof Existential ) {	  
		Existential uni = (Existential) out;
		Variable var = uni.getVariable();
		in1 = uni.getBody();
		out2 = new Negation(in1);
		return insurer1(new Universal(var, out2));
	    }
	    if ( out instanceof Unequal ) {	  
		Unequal uni = (Unequal) out;
		vec.addElement(uni.leftTerm());
		vec.addElement(uni.rightTerm());
		return new Equal(vec);
	    }
	    if ( out instanceof LessOrEqual ) {	  
		LessOrEqual uni = (LessOrEqual) out;
		vec.addElement(uni.leftTerm());
		vec.addElement(uni.rightTerm());
		return new Greater(vec);
	    }
	    if ( out instanceof GreaterOrEqual ) {	  
		GreaterOrEqual uni = (GreaterOrEqual) out;
		vec.addElement(uni.leftTerm());
		vec.addElement(uni.rightTerm());
		return new Less(vec);
	    }
	    return in;
	}
	if ( in instanceof Universal ) {
	    Universal uni = (Universal) in;
	    Variable var = uni.getVariable();
	    in1 = uni.getBody();
	    out2 = insurer1(in1);
	    return new Universal(var, out2);
	}
	if ( in instanceof Existential ) {
	    Existential uni = (Existential) in;
	    Variable var = uni.getVariable();
	    in1 = uni.getBody();
	    out2 = insurer1(in1);
	    return new Existential(var, out2);
	}
	if ( in instanceof Conjunction ) {	 
	    Conjunction con = (Conjunction) in;
	    Vector vecIn = con.getConjuncts();
	    int lng = vecIn.size();
	    for ( int i = 0; i < lng; i++ ) {
		in1 = (Formula) vecIn.elementAt(i);
		vec.addElement(insurer1(in1));
		}
	    return new Conjunction(vec);
	}  
	if ( in instanceof Disjunction ) {	 
	    Disjunction con = (Disjunction) in;
	    Vector vecIn = con.getDisjuncts();
	    int lng = vecIn.size();
	    for ( int i = 0; i < lng; i++ ) {
		in1 = (Formula) vecIn.elementAt(i);
		vec.addElement(insurer1(in1));
		}
	    return new Disjunction(vec);
	}  
	if ( in instanceof Unequal ) {	  
	    Unequal uni = (Unequal) in;
	    vec.addElement(uni.leftTerm());
	    vec.addElement(uni.rightTerm());
	    out = new Equal(vec);
	    return new Negation(out);
	}
	if ( in instanceof LessOrEqual ) {	  
	    LessOrEqual uni = (LessOrEqual) in;
	    vec.addElement(uni.leftTerm());
	    vec.addElement(uni.rightTerm());
	    out = new Greater(vec);
	    return new Negation(out);
	}
	if ( in instanceof GreaterOrEqual ) {	  
	    GreaterOrEqual uni = (GreaterOrEqual) in;
	    vec.addElement(uni.leftTerm());
	    vec.addElement(uni.rightTerm());
	    out = new Less(vec);
	    return new Negation(out);
	}
	return in;
    }
    // -------------------------------------------------------------
    static private Formula insurer3(Formula in) { 
	if (trace) {
	    cnt++;
	    System.out.println("cnt: " + cnt + " " + in.html());
	}
	Formula out, out2, in1, in2;
	Vector vec = new Vector();
	if ( in instanceof Conjunction ) {
	    Conjunction con = (Conjunction) in;
	    Vector vecIn = con.getConjuncts();
	    int lng = vecIn.size();
	    for ( int i = 0; i < lng; i++ ) {
		in1 = (Formula) vecIn.elementAt(i);
		vec.addElement(insurer3(in1));
	    }
	    return t3And(vec);
	}
	if ( in instanceof Disjunction ) {
	    Disjunction dis = (Disjunction) in;
	    Vector vecIn = dis.getDisjuncts();
	    int lng = vecIn.size();
	    for ( int i = 0; i < lng; i++ ) {
		in1 = (Formula) vecIn.elementAt(i);
		vec.addElement(insurer3(in1));
	    }
	    return t3Or(vec);
	}
	if ( in instanceof Universal ) {
	    Universal uni = (Universal) in;
	    Variable var = uni.getVariable();
	    in1 = uni.getBody();
	    in2 = insurer3(in1); 
	    HashSet vars = in2.getVariables();
	    if ( !vars.contains(var) ) return in2;
	    if ( in2 instanceof Conjunction ) {
		Conjunction con = (Conjunction) in2;
		Vector vecIn = con.getConjuncts(); 
		int lng = vecIn.size();
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    HashSet variables = in1.getVariables();
		    if ( variables.contains(var) ) {
			Variable var2 = Variable.gensym(var);
			in1 = in1.subst(var, var2);
			vec.addElement(new Universal(var2, in1));
		    } else
			vec.addElement(in1);
		}
		return insurer3(new Conjunction(vec));
	    }
	    if ( in2 instanceof Disjunction ) {
		Disjunction dis = (Disjunction) in2;
		Vector vecIn = dis.getDisjuncts(); 
		Vector noVar = new Vector();
		int lng = vecIn.size();
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    HashSet variables = in1.getVariables();
		    if ( variables.contains(var) ) 
			vec.addElement(new Universal(var, in1));
		    else
			noVar.addElement(in1);
		}
		if ( 0 < noVar.size() ) {
		    if ( 0 == vec.size() )
			return insurer3(new Disjunction(noVar));
		    if ( 1 == vec.size() ) { 
			in1 = (Formula) vec.elementAt(0);
			noVar.addElement(in1);
			return insurer3(new Disjunction(noVar));
		    }
		    in1 = new Disjunction(vec);
		    in1 = new Universal(var, in1);
		    noVar.addElement(in1);
		    return insurer3(new Disjunction(noVar));
		}
		int index = -1;
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    if ( in1 instanceof Conjunction ) {
			index = i;
			break;
		    }
		}
		if ( 0 <= index ) {
		    // distribute ....
		    vec.clear();
		    Conjunction b = (Conjunction) vecIn.elementAt(index);
		    Vector bConjuncts = b.getConjuncts();
		    int bSize = bConjuncts.size();
		    for ( int j = 0; j < bSize; j++ ) {
			Vector disjuncts = new Vector();
			in2 = (Formula) bConjuncts.elementAt(j);
			disjuncts.addElement(in2);
			for ( int i = 0; i < lng; i++ ) {
			    if ( index == i ) continue;
			    in1 = (Formula) vecIn.elementAt(i);
			    disjuncts.addElement(in1);
			}
			in2 = new Disjunction(disjuncts);
			in2 = new Universal(var, in2);
			vec.addElement(in2);
		    }
		    return insurer3(new Conjunction(vec));
		}
		return new Universal(var, in2);
	    } 
	    if ( in2 instanceof Universal ) {
		Universal uni2 = (Universal) in2;
		Variable var2 = uni2.getVariable();
		in1 = new Universal(var, uni2.getBody());
		in1 = insurer3(in1);
		return new Universal(var2, in1);
	    }
	    return new Universal(var, in2);
	}
	if ( in instanceof Existential ) {
	    Existential uni = (Existential) in;
	    Variable var = uni.getVariable();
	    in1 = uni.getBody();
	    in2 = insurer3(in1);
	    HashSet vars = in2.getVariables();
	    if ( !vars.contains(var) ) return in2;
	    if ( in2 instanceof Disjunction ) {
		Disjunction con = (Disjunction) in2;
		Vector vecIn = con.getDisjuncts(); 
		int lng = vecIn.size();
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    HashSet variables = in1.getVariables();
		    if ( variables.contains(var) ) {
			Variable var2 = Variable.gensym(var);
			in1 = in1.subst(var, var2);
			vec.addElement(new Existential(var2, in1));
		    } else
			vec.addElement(in1);
		}
		return insurer3(new Disjunction(vec));
	    }
	    if ( in2 instanceof Conjunction ) {
		Conjunction dis = (Conjunction) in2;
		Vector vecIn = dis.getConjuncts(); 
		Vector noVar = new Vector();
		int lng = vecIn.size();
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    HashSet variables = in1.getVariables();
		    if ( variables.contains(var) ) 
			vec.addElement(new Existential(var, in1));
		    else
			noVar.addElement(in1);
		}
		if ( 0 < noVar.size() ) {
		    if ( 0 == vec.size() )
			return insurer3(new Conjunction(noVar));
		    if ( 1 == vec.size() ) { 
			in1 = (Formula) vec.elementAt(0);
			noVar.addElement(in1);
			return insurer3(new Conjunction(noVar));
		    }
		    in1 = new Conjunction(vec);
		    in1 = new Existential(var, in1);
		    noVar.addElement(in1);
		    return insurer3(new Conjunction(noVar));
		}
		int index = -1;
		for ( int i = 0; i < lng; i++ ) {
		    in1 = (Formula) vecIn.elementAt(i);
		    if ( in1 instanceof Disjunction ) {
			index = i;
			break;
		    }
		}
		if ( 0 <= index ) {
		    // distribute ....
		    vec.clear();
		    Disjunction b = (Disjunction) vecIn.elementAt(index);
		    Vector bConjuncts = b.getDisjuncts();
		    int bSize = bConjuncts.size();
		    for ( int j = 0; j < bSize; j++ ) {
			Vector disjuncts = new Vector();
			in2 = (Formula) bConjuncts.elementAt(j);
			disjuncts.addElement(in2);
			for ( int i = 0; i < lng; i++ ) {
			    if ( index == i ) continue;
			    in1 = (Formula) vecIn.elementAt(i);
			    disjuncts.addElement(in1);
			}
			in2 = new Conjunction(disjuncts);
			in2 = new Existential(var, in2);
			vec.addElement(in2);
		    }
		    return insurer3(new Disjunction(vec));
		}
		return new Existential(var, in2);
	    } 
	    if ( in2 instanceof Existential ) {
		Existential uni2 = (Existential) in2;
		Variable var2 = uni2.getVariable();
		in1 = new Existential(var, uni2.getBody());
		in1 = insurer3(in1);
		return new Existential(var2, in1);
	    }
	    return new Existential(var, in2);
	}
	if ( in instanceof Negation ) { 
	    Negation neg = (Negation) in;
	    Formula f = insurer3(neg.getFormula());
	    if ( f == Symbol.TRUE ) return Symbol.FALSE;
	    if ( f == Symbol.FALSE ) return Symbol.TRUE;
	    return in;
	}
	/* I learned that when 'in' is in a subclass of Atom
           and when that subclass has a def of eval then that
	   eval function will be executed; which is the case
	   for Less, Greater, Equal, etc.
	 */
	if ( in instanceof Atom ) { 
	    Atom b = (Atom) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	/*
	if ( in instanceof BoolInt2 ) { 
	    BoolInt2 b = (BoolInt2) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	if ( in instanceof Less ) { 
	    Less b = (Less) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	if ( in instanceof LessOrEqual ) { 
	    LessOrEqual b = (LessOrEqual) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	if ( in instanceof Greater ) { 
	    Greater b = (Greater) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	if ( in instanceof GreaterOrEqual ) { 
	    GreaterOrEqual b = (GreaterOrEqual) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	if ( in instanceof Equal ) { 
	    Equal b = (Equal) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	if ( in instanceof Unequal ) { 
	    Unequal b = (Unequal) in;
	    Atom atom = b.eval();
	    return ( atom == Symbol.UNKNOWN ? in : atom );
	}
	*/
	return in;
    }
    static private Formula t3And(Vector vec) {
	int lng = vec.size();
	// <<<
	int locCnt = cnt;
	if (trace) {
	    System.out.println("t3And IN: " + locCnt);
	    for ( int i = 0; i < lng; i++ ) {
		Formula f = (Formula) vec.elementAt(i);
		System.out.println("f i: " + i + " " + f.html());
	    }
	    // >>>
	}
	Vector vec2 = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Formula f = (Formula) vec.elementAt(i);
	    if ( f == Symbol.TRUE ) continue;
	    if ( f == Symbol.FALSE ) return Symbol.FALSE;
	    if ( ! (f instanceof Conjunction) ) {
		vec2.addElement(f);
		continue;
	    }
	    Conjunction con = (Conjunction) f;
	    Vector conjuncts = con.getConjuncts();
	    int lng2 = conjuncts.size();
	    for ( int j = 0; j < lng2; j++ ) {
		vec2.addElement(conjuncts.elementAt(j));
	    }
	}
	lng = vec2.size();
	// vec2 can be smaller than vec
	if ( 0 == lng ) return Symbol.TRUE;
	if ( 1 == lng ) return (Formula) vec2.elementAt(0);

	Vector vec3 = new Vector(); // rejects
	for ( int i = 0; i < lng; i++ ) {
	    Formula fi = (Formula) vec2.elementAt(i);
	    boolean reject = false;
	    for ( int j = 0; j < lng; j++ ) { 
		if ( i == j ) continue;
		Formula fj = (Formula) vec2.elementAt(j);
		if ( vec3.contains(fj) ) continue; // ignore
		if ( instance(fi, fj) ) {
		    reject = true;
		    break;
		}
	    }
	    if ( reject ) vec3.addElement(fi);
	}
	lng = vec3.size();
	for ( int i = 0; i < lng; i++ ) {
	    Formula fi = (Formula) vec3.elementAt(i);
	    vec2.remove(fi);
	}
	lng = vec2.size();
	if ( 1 == lng ) return (Formula) vec2.elementAt(0);
	for ( int i = 0; i < lng; i++ ) {
	    Formula fi = (Formula) vec2.elementAt(i);
	    Formula fiN = insurer1(new Negation(fi));
	    for ( int j = 0; j < lng; j++ ) { 
		if ( i == j ) continue;
		if ( instance(fiN, (Formula) vec2.elementAt(j)) ) 
		    return Symbol.FALSE;
	    }
	}
	Formula out = new Conjunction(vec2);
	// <<<
	if (trace) {
	    System.out.println("t3And OUT: " + locCnt);
	    System.out.println("out: " + out.html());
	}
	// >>>
	return out;
    } // end t3And

    static private Formula t3Or(Vector vec) {
	int lng = vec.size();
	// <<<
	int locCnt = cnt;
	if (trace) {
	    System.out.println("t3Or IN: " + locCnt);
	    for ( int i = 0; i < lng; i++ ) {
		Formula f = (Formula) vec.elementAt(i);
		System.out.println("f i: " + i + " " + f.html());
	    }
	}
	// >>>
	Vector vec2 = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Formula f = (Formula) vec.elementAt(i);
	    if ( f == Symbol.FALSE ) continue;
	    if ( f == Symbol.TRUE ) return Symbol.TRUE;
	    if ( ! (f instanceof Disjunction) ) {
		vec2.addElement(f);
		continue;
	    }
	    Disjunction dis = (Disjunction) f;
	    Vector disjuncts = dis.getDisjuncts();
	    int lng2 = disjuncts.size();
	    for ( int j = 0; j < lng2; j++ ) {
		vec2.addElement(disjuncts.elementAt(j));
	    }
	}
	lng = vec2.size();
	// vec2 can be smaller than vec
	if ( 0 == lng ) return Symbol.FALSE;
	if ( 1 == lng ) return (Formula) vec2.elementAt(0);

	Vector vec3 = new Vector(); // rejects
	for ( int i = 0; i < lng; i++ ) {
	    Formula fi = (Formula) vec2.elementAt(i);
	    boolean reject = false;
	    for ( int j = 0; j < lng; j++ ) { 
		if ( i == j ) continue;
		Formula fj = (Formula) vec2.elementAt(j);
		if ( vec3.contains(fj) ) continue; // ignore
		if ( instance(fj, fi) ) {
		    reject = true;
		    break;
		}
	    }
	    if ( reject ) vec3.addElement(fi);
	}
	lng = vec3.size();
	for ( int i = 0; i < lng; i++ ) {
	    Formula fi = (Formula) vec3.elementAt(i);
	    vec2.remove(fi);
	}
	lng = vec2.size();
	if ( 1 == lng ) return (Formula) vec2.elementAt(0);
	for ( int i = 0; i < lng; i++ ) {
	    Formula fi = (Formula) vec2.elementAt(i);
	    Formula fiN = insurer1(new Negation(fi));
	    for ( int j = 0; j < lng; j++ ) { 
		if ( i == j ) continue;
		if ( instance(fiN, (Formula) vec2.elementAt(j)) ) 
		    return Symbol.TRUE;
	    }
	}
	Formula out = new Disjunction(vec2);
	// <<<
	if (trace) {
	    System.out.println("t3Or OUT: " + locCnt);
	    System.out.println("out: " + out.html());
	}
	// >>>
	return out;
    } // end t3Or

    static public boolean instance(Formula in1, Formula in2) {
	if (trace) {
	    System.out.println("instance input: ");
	    System.out.println("in1: " + in1.html());
	    System.out.println("in2: " + in2.html());
	}
	Vector inVec = new Vector();
	Vector out = ins2(askolemize(in1), skolemize(in2), inVec);
	if (trace) System.out.println("instance output: null == out?: " + (null == out));
	if ( null != out ) {
	    if (trace) { 
		System.out.println("instance succeedes for: ");
		System.out.println("in1: " + in1.html());
		System.out.println("in2: " + in2.html());
		System.out.println("out: " + out.toString());
	    }
	    int lng = out.size();
	    for ( int i = 0; i < lng; i++ ) {
		Vector subsi = (Vector) out.elementAt(i);
		if (trace) System.out.println("instance subI: " + Substitution.html(subsi));
	    }
	}
	return ( null != out );
    } // end instance

    static private Vector ins2(Formula st, Formula sk, Vector vars) {
	if (trace) {
	    System.out.println("ins2 input: ");
	    System.out.println("st: " + st.html());
	    System.out.println("sk: " + sk.html());
	}
	if ( sk instanceof Universal ) { 
	    Universal uni = (Universal) sk;
	    Variable var = uni.getVariable();
	    vars.addElement(var);
	    return ins2(st, uni.getBody(), vars);
	}
	if ( st instanceof Existential ) { 
	    Existential uni = (Existential) st;
	    Variable var = uni.getVariable();
	    vars.addElement(var);
	    return ins2(uni.getBody(), sk, vars);
	}
	if ( sk instanceof Disjunction ) { 
	    Disjunction dis = (Disjunction) sk;
	    Vector disjuncts = dis.getDisjuncts();
	    Vector subs = new Vector();
	    subs.addElement(new Vector()); // empty substitution
	    Vector out = insork(subs, st, disjuncts, vars);
	    return out;
	}
	if ( st instanceof Conjunction ) { 
	    Conjunction con = (Conjunction) st;
	    Vector conjuncts = con.getConjuncts();
	    Vector subs = new Vector();
	    subs.addElement(new Vector()); // empty substitution
	    Vector out = insandt(subs, conjuncts, sk, vars);
	    return out;
	}
	if ( sk instanceof Conjunction ) { 
	    Conjunction con = (Conjunction) sk;
	    Vector conjuncts = con.getConjuncts();
	    Vector out = new Vector();
	    int lng = conjuncts.size();
	    for ( int i = 0; i < lng; i++ ) {
		Formula coni = (Formula) conjuncts.elementAt(i);
		Vector outi = ins2(st, coni, (Vector)vars.clone());
		if ( null == outi ) continue;
		out.addAll(outi);
	    }
	    return ( 0 == out.size() ? null : out );
	}
	if ( st instanceof Disjunction ) { 
	    Disjunction con = (Disjunction) st;
	    Vector disjuncts = con.getDisjuncts();
	    Vector out = new Vector();
	    int lng = disjuncts.size();
	    for ( int i = 0; i < lng; i++ ) {
		Formula coni = (Formula) disjuncts.elementAt(i);
		Vector outi = ins2(coni, sk, (Vector)vars.clone());
		if ( null == outi ) continue;
		out.addAll(outi);
	    }
	    return ( 0 == out.size() ? null : out );
	}
	if ( st instanceof Negation ) {
	    if ( ! (sk instanceof Negation) ) return null;
	    Negation neg = (Negation) st;
	    st = neg.getFormula();
	    neg = (Negation) sk;
	    sk = neg.getFormula();
	} else 
	    if ( sk instanceof Negation ) return null;


	// st and sk are atom
	if ( !(st instanceof Atom) || !(sk instanceof Atom) ) return null; // cant happen
	Atom sta = (Atom) st; Atom ska = (Atom) sk; 
	Vector subs = sta.unify(ska);
	if ( null == subs ) return null;
	int lng = subs.size();
	for ( int i = 0; i < lng; i++ ) {
	    Substitution subi = (Substitution) subs.elementAt(i);
	    Variable var = subi.getVariable();
	    if ( !vars.contains(var) ) return null;
	}
	Vector out = new Vector();
	out.addElement(subs);
	return out;
    }

    static private Vector insork(Vector vecSubs, Formula stst, Vector disjuncts, Vector vars) {
	int lng = disjuncts.size();
	// <<<
	int locCnt = cnt;
	if (trace) {
	    System.out.println("insork IN: " + locCnt);
	    System.out.println("stst: " + stst.html());
	    for ( int i = 0; i < lng; i++ ) {
		Formula f = (Formula) disjuncts.elementAt(i);
		System.out.println("f i: " + i + " " + f.html());
	    }
	    System.out.println("vars: " + vars.toString());
	}
	// >>>

	if ( 0 == lng ) return vecSubs;
	Formula ki = (Formula) disjuncts.elementAt(0);
	disjuncts.remove(0);
	Vector subs = ins2(stst, ki, vars);
	if ( null == subs ) return null; 
	lng--;
	int lng2 = subs.size();
	Vector vecSubsOut = new Vector(); // sum of recursive calls
	int lngVecSubs = vecSubs.size();
	for ( int i = 0; i < lng2; i++ ) {
	    Vector subsi = (Vector) subs.elementAt(i);
	    Vector vecSubs2 = new Vector();
	    for ( int j = 0; j < lngVecSubs; j++ ) {
		Vector vecSubsJ = (Vector) vecSubs.elementAt(j);
		Vector vecSubsJExtend = new Vector();
		int lngVecSubsJ = vecSubsJ.size();
		for (int k = 0; k < lngVecSubsJ; k++) {
		    Substitution sK = (Substitution) vecSubsJ.elementAt(k);
		    Substitution sK2 = 
			new Substitution(sK.getVariable(),
					 (Term) sK.getTerm().sublis(subsi));
		    vecSubsJExtend.addElement(sK2);
		}
		vecSubsJExtend.addAll(subsi);
		vecSubs2.addElement(vecSubsJExtend);
	    }
	    
	    Formula stst2 = stst.sublis(subsi);
	    Vector disjuncts2 = new Vector();
	    for ( int j = 0; j < lng; j++ ) {
		Formula disjunctJ = (Formula) disjuncts.elementAt(j);
		Formula disjunctJ2 = disjunctJ.sublis(subsi);
		disjuncts2.addElement(disjunctJ2);
	    }
	    Vector out = insork(vecSubs2, stst2, disjuncts2, vars);
	    if ( null != out ) vecSubsOut.addAll(out);
	}
	/* create empty vecSubs2
	   for each subi: 
	        - apply subi to each element in vecSubs and add 
		- apply subi to stst
		- apply subi to disjuncts
		- call insork
		ignore null results
 		add substitution to vecSubsOut
	*/
	if (trace) System.out.println("insork OUT vecSubsOut: " + vecSubsOut.toString());
	return ( 0 == vecSubsOut.size() ? null : vecSubsOut );
    }

    static private Vector insandt(Vector vecSubs, Vector conjuncts, Formula sksk, Vector vars) {
	int lng = conjuncts.size();
	// <<<
	int locCnt = cnt;
	if (trace) {
	    System.out.println("inandt IN: " + locCnt);
	    for ( int i = 0; i < lng; i++ ) {
		Formula f = (Formula) conjuncts.elementAt(i);
		System.out.println("f i: " + i + " " + f.html());
	    }
	    System.out.println("sksk: " + sksk.html());
	    System.out.println("vars: " + vars.toString());
	}
	// >>>
	if ( 0 == lng ) return vecSubs;
	Formula ti = (Formula) conjuncts.elementAt(0);
	conjuncts.remove(0);
	Vector subs = ins2(ti, sksk, vars);
	if ( null == subs ) return null; 
	lng--;
	int lng2 = subs.size();
	Vector vecSubsOut = new Vector(); // sum of recursive calls
	int lngVecSubs = vecSubs.size();
	for ( int i = 0; i < lng2; i++ ) {
	    Vector subsi = (Vector) subs.elementAt(i);
	    Vector vecSubs2 = new Vector();
	    for ( int j = 0; j < lngVecSubs; j++ ) {
		Vector vecSubsJ = (Vector) vecSubs.elementAt(j);
		Vector vecSubsJExtend = new Vector();
		int lngVecSubsJ = vecSubsJ.size();
		for (int k = 0; k < lngVecSubsJ; k++) {
		    Substitution sK = (Substitution) vecSubsJ.elementAt(k);
		    Substitution sK2 = 
			new Substitution(sK.getVariable(),
					 (Term) sK.getTerm().sublis(subsi));
		    vecSubsJExtend.addElement(sK2);
		}
		vecSubsJExtend.addAll(subsi);
		vecSubs2.addElement(vecSubsJExtend);
	    }
	    
	    Formula sksk2 = sksk.sublis(subsi);
	    Vector conjuncts2 = new Vector();
	    for ( int j = 0; j < lng; j++ ) {
		Formula conjunctJ = (Formula) conjuncts.elementAt(j);
		Formula conjunctJ2 = conjunctJ.sublis(subsi);
		conjuncts2.addElement(conjunctJ2);
	    }
	    Vector out = insandt(vecSubs2, conjuncts2, sksk2, vars);
	    if ( null != out ) vecSubsOut.addAll(out);
	}
	/* create empty vecSubs2
	   for each subi: 
	        - apply subi to each element in vecSubs and add 
		- apply subi to sksk
		- apply subi to conjuncts
		- call insandt
		ignore null results
		add substitution to vecSubsOut
	*/
	return ( 0 == vecSubsOut.size() ? null : vecSubsOut );
    }

    static public Formula skolemize(Formula f) { return skolemize(f, new Vector()); }
    static private Formula skolemize(Formula f, Vector vars) { 
	if ( f instanceof Conjunction ) {
	    Conjunction con = (Conjunction) f;
	    Vector conjuncts = con.getConjuncts();
	    int lng = conjuncts.size();
	    Vector vec = new Vector();
	    for ( int i = 0; i < lng; i++ ) {
		Formula fi = (Formula) conjuncts.elementAt(i);
		vec.addElement(skolemize(fi, vars));
	    }
	    return new Conjunction(vec);
	}
	if ( f instanceof Disjunction ) {
	    Disjunction dis = (Disjunction) f;
	    Vector disjuncts = dis.getDisjuncts();
	    int lng = disjuncts.size();
	    Vector vec = new Vector();
	    for ( int i = 0; i < lng; i++ ) {
		Formula fi = (Formula) disjuncts.elementAt(i);
		vec.addElement(skolemize(fi, vars));
	    }
	    return new Disjunction(vec);
	}
	if ( f instanceof Universal ) {
	    Universal u = (Universal) f;
	    Variable var = u.getVariable();
	    vars.addElement(var);
	    return new Universal(var, skolemize(u.getBody(), vars));
	}
	if ( f instanceof Existential ) {
	    Existential u = (Existential) f;
	    Variable var = u.getVariable();
	    Formula body = u.getBody();
	    Term skolem = FTerm.getSkolem(vars);
	    body = body.subst(var, skolem);
	    return skolemize(body, vars);
	}
	return f;
    }

   static public Formula askolemize(Formula f) { return askolemize(f, new Vector()); }
    static private Formula askolemize(Formula f, Vector vars) { 
	// System.out.println("askolemize in: " + f.html());
	// System.out.println("vars: " + vars.toString());
	if ( f instanceof Conjunction ) {
	    Conjunction con = (Conjunction) f;
	    Vector conjuncts = con.getConjuncts();
	    int lng = conjuncts.size();
	    Vector vec = new Vector();
	    for ( int i = 0; i < lng; i++ ) {
		Formula fi = (Formula) conjuncts.elementAt(i);
		vec.addElement(askolemize(fi, vars));
	    }
	    return new Conjunction(vec);
	}
	if ( f instanceof Disjunction ) {
	    Disjunction dis = (Disjunction) f;
	    Vector disjuncts = dis.getDisjuncts();
	    int lng = disjuncts.size();
	    Vector vec = new Vector();
	    for ( int i = 0; i < lng; i++ ) {
		Formula fi = (Formula) disjuncts.elementAt(i);
		vec.addElement(askolemize(fi, vars));
	    }
	    return new Disjunction(vec);
	}
	if ( f instanceof Existential ) {
	    Existential u = (Existential) f;
	    Variable var = u.getVariable();
	    vars.addElement(var);
	    return new Existential(var, askolemize(u.getBody(), vars));
	}
	if ( f instanceof Universal ) {
	    Universal u = (Universal) f;
	    Variable var = u.getVariable();
	    Formula body = u.getBody();
	    Term skolem = FTerm.getSkolem(vars);
	    body = body.subst(var, skolem);
	    return askolemize(body, vars);
	}
	return f;
    }
}


