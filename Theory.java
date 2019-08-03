// (C) OntoOO Inc 2005 Jan
package fol;

import java.util.*;
import java.io.Serializable;

public class Theory implements Serializable {
    // fixed constants
    // fixed predicates
    // mapping in a domain in which things can be calculated

    private Vector assertions = new Vector(); // atoms
    private Vector axioms = new Vector();
    private Hashtable definitions = new Hashtable(); // iff / <->
    private Vector theorems = new Vector();

    private boolean traceb = false;

    // constructors
    public Theory() { traceb = true; }
    public Theory(boolean bool) { traceb = bool; }

    public Vector getAssertions() { return assertions; }
    public void addAssertion(Atom atom) { 
	// ignore false statements as well as those that can be calculated to be true
	if ( atom.canEvaluate() ) return; 
	assertions.addElement(atom); 
    }
    public Vector getAxioms() { return axioms; }
    public void addAxiom(Formula formula) { 
	Axiom axiom = new Axiom(formula);
	axioms.addElement(axiom); 
    }
    public Equivalence getDefinition(Symbol predicate) { 
	return (Equivalence) definitions.get(predicate); 
    }
    public void addDefinition(Universal formula) { // Universal with inside an Equivalence
	Formula body = formula.getBody();
	if ( body instanceof Universal ) { 
	    addDefinition((Universal) body);
	    return;
	}
	if ( !(body instanceof Equivalence) ) return; // ignore
	Equivalence eq = (Equivalence) body;
	Formula left = eq.getLeft();
	if ( !(left instanceof Atom) ) return; // ignore
	Atom leftAtom = (Atom) left;
	Symbol predicate = leftAtom.getPredicate();
	definitions.put(predicate, eq);
    }

    public Vector getTheorems() { return theorems; }
    public void addTheorem(Formula formula, ProofStep ps) { 
	Theorem theorem = new Theorem(formula, ps);
	theorems.addElement(theorem); 
    }
  
    /* prove should return:
       false: cannot be proven
       undecided, due to resource constraint or the like
       true with a proof
    */
    public ProofStep prove(Formula conjecture, int maxSteps) {
	// System.out.println("prove: " + conjecture.html());
	if ( maxSteps < 0) 
	    return new ProofStep(conjecture, Symbol.UNKNOWN, "Reached Limit");
	if ( conjecture instanceof Atom ) {
	    Atom atomConjecture = (Atom) conjecture;
	    if ( Symbol.FALSE == atomConjecture || 
		 Symbol.TRUE == atomConjecture ||
		 Symbol.UNKNOWN == atomConjecture ) 
		return new ProofStepAtom(atomConjecture, 
					 atomConjecture,
					 "Input symbol");
	    Atom evalOut = atomConjecture.eval();
	    if ( Symbol.FALSE == evalOut || Symbol.TRUE == evalOut )
		return new ProofStepAtom(atomConjecture, 
					 evalOut,
					 "Output of evaluation");
	    if ( !assertions.isEmpty() ) {
		Atom assertion = tryAssertions(atomConjecture, assertions);
		if ( null != assertion ) {
		    return new ProofStepAtom(atomConjecture, 
					     Symbol.TRUE, 
					     "Member of Assertions");
		}
	    } 
	    // check whether we can expand a definition
	    Symbol predicate = atomConjecture.getPredicate();
	    Equivalence eq = getDefinition(predicate);
	    if ( null != eq ) { // will expand this 
		Atom atomDef = (Atom) eq.getLeft();
		Formula target = replaceAtom(
		     predicate, atomDef.getArgs(),
		     eq.getRight(), atomConjecture);
		ProofStep ps = prove(target, maxSteps - 1);
		return new ProofStepDefinition(atomConjecture,
					       ps.getResult(),
					       ps);
	    }
	    if ( traceb )
		System.out.println("/// Last resort: try resolution on: " +
				   atomConjecture.html());
	    ProofStep ps = resolve(new Negation(atomConjecture), maxSteps);
	    return new ProofStepResolve(atomConjecture, ps.getResult(), ps);
	}

	// conjecture is NOT an atom -- try splitting
	Formula conjecture2 = Formula.insurer(conjecture);
	if ( traceb )
	    System.out.println("conjecture2: " + conjecture2.html());
	if ( conjecture2 == Symbol.FALSE || conjecture2 == Symbol.TRUE )
	    return (new ProofStep(conjecture, (Atom) conjecture2, "insurer"));

	if ( conjecture2 instanceof Conjunction ) 
	    return proveConjunction((Conjunction)conjecture2, maxSteps);

	// conjecture2 is an instance of something?
	ProofStep ps = instanceOfAssertions(conjecture2);
	if ( ps.getResult().equals(Symbol.TRUE) ) return ps;
	ps = instanceOfAxioms(conjecture2);
	if ( ps.getResult().equals(Symbol.TRUE) ) return ps;
	ps = instanceOfTheorems(conjecture2);
	if ( ps.getResult().equals(Symbol.TRUE) ) return ps;

	// check whether there is a predicate inside conjecture2 for 
	// which a definition is available
	// if so replace all occurrences with the bodies

	Symbol predicate = containsDefinition(conjecture2);
	if ( null != predicate ) {
	    if ( traceb )
		System.out.println("predicate: " + predicate.html());
	    Equivalence eq = getDefinition(predicate);
	    Atom atomDef = (Atom) eq.getLeft();
	    Formula conjecture3 = replaceAtom(
		     predicate, atomDef.getArgs(),
		     eq.getRight(), conjecture2);
	    ps = prove(conjecture3, maxSteps - 1);
	    return (new ProofStepSubstitution(atomDef,
					    ps.getResult(),
					    ps));
	}
	System.out.println("last resort: try resolution");
	// System.exit(1);

	// last resort: try resolution
	Formula negConjecture = new Negation(conjecture2);

	ps = resolve(negConjecture, maxSteps);
	return new ProofStepResolve(conjecture, ps.getResult(), ps);

	/*
	System.out.println("Giving up prove: " + conjecture.html());

	return new ProofStep(conjecture, Symbol.UNKNOWN, "Default exit");
	*/
    } // end prove

    public Formula replaceAtom(Symbol def, // predicate to be replaced
			       Vector argsDef, // variables to match
			       Formula target, // body of the definition
			       Formula conjecture)  {
	// System.out.println("replace " + target.html() + " " + conjecture.html());
	if ( conjecture instanceof Atom ) {
	    Atom atomConjecture = (Atom) conjecture;
	    if ( def != atomConjecture.getPredicate() ) return conjecture;
	    Vector argsConjecture = atomConjecture.getArgs();
	    Vector substitutions = new Vector();
	    int lng = argsDef.size();
	    for ( int i = 0; i < lng; i++ ) {
		Variable var = (Variable) argsDef.elementAt(i);
		Term term = (Term) argsConjecture.elementAt(i);
		// System.out.println(i + " " + var.html() + " " + term.html());
		Substitution subs = new Substitution(var, term);
		substitutions.addElement(subs);
	    }
	    return target.sublis(substitutions);
	}
	if ( conjecture instanceof Negation ) {
	    Negation n = (Negation) conjecture;
	    return new Negation(replaceAtom(def, argsDef, target, 
					    n.getFormula()));
	}
	if ( conjecture instanceof Universal ) {
	    Universal u = (Universal) conjecture;
	    Variable v = u.getVariable();
	    Formula b2 = replaceAtom(def, argsDef, target, u.getBody());
	    return new Universal(v, b2);
	}
	if ( conjecture instanceof Existential ) {
	    Existential e = (Existential) conjecture;
	    Variable v = e.getVariable();
	    Formula b2 = replaceAtom(def, argsDef, target, e.getBody());
	    return new Existential(v, b2);
	}
	if ( conjecture instanceof Conjunction ) {
	    Conjunction c = (Conjunction) conjecture;
	    Vector v = c.getConjuncts();
	    int lng = v.size();
	    Vector v2 = new Vector();
	    for (int i = 0; i < lng; i++ ) 
		v2.addElement(replaceAtom(def, argsDef, target,
					  (Formula)v.elementAt(i)));
	    return new Conjunction(v2);
	}
	if ( conjecture instanceof Disjunction ) {
	    Disjunction c = (Disjunction) conjecture;
	    Vector v = c.getDisjuncts();
	    int lng = v.size();
	    Vector v2 = new Vector();
	    for (int i = 0; i < lng; i++ ) 
		v2.addElement(replaceAtom(def, argsDef, target,
					  (Formula)v.elementAt(i)));
	    return new Disjunction(v2);
	}
	return Symbol.UNKNOWN; // should not happen 
    } // end replaceATOM

    public Symbol containsDefinition(Formula conjecture) { 
	if ( conjecture instanceof Atom ) {
	    Atom atomConjecture = (Atom) conjecture;
	    Symbol predicate = atomConjecture.getPredicate();
	    Equivalence eq = getDefinition(predicate);
	    return ( null == eq ? null : predicate );
	}
	if ( conjecture instanceof Negation ) {
	    Negation f = (Negation) conjecture;
	    return containsDefinition(f.getFormula());
	}
	if ( conjecture instanceof Conjunction ) { 
	    Conjunction c = (Conjunction) conjecture;
	    Vector conjuncts = c.getConjuncts();
	    int lng = conjuncts.size();
	    for (int i = 0; i < lng; i++) {
		Formula ci = (Formula) conjuncts.elementAt(i);
		Symbol cia =  containsDefinition(ci);
		if ( null != cia ) return cia;
	    }
	    return null;
	}
	if ( conjecture instanceof Disjunction ) {
	    Disjunction c = (Disjunction) conjecture;
	    Vector disjuncts = c.getDisjuncts();
	    int lng = disjuncts.size();
	    for (int i = 0; i < lng; i++) {
		Formula di = (Formula) disjuncts.elementAt(i);
	        Symbol dia =  containsDefinition(di);
		if ( null != dia ) return dia;
	    }
	    return null;
	}
	if ( conjecture instanceof Universal ) {
	    Universal u = (Universal) conjecture;
	    return containsDefinition(u.getBody());
	}
	if ( conjecture instanceof Existential ) {
	    Existential e = (Existential) conjecture;
	    return containsDefinition(e.getBody());
	}
	return null;  // should not happen
    } // end containsDefinition

    private Atom tryAssertions(Atom atomConjecture, Vector assertions) {
	Atom out = null;
	int lng = assertions.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom atomI = (Atom) assertions.elementAt(i);
	    if ( atomI.equals(atomConjecture) ) {
		out = atomI;
		break;
	    }
	}
	return out;
    }

    private ProofStep resolve(Formula negatedConjecture, int maxSteps) {
	// use here subsumption algorithm ??
	ClausedFormula negatedInput = new ClausedFormula(negatedConjecture);
	// CG cg = new CG(true);
	CG cg = new CG(traceb);
	cg.addClausedFormula(negatedInput);
	// add axioms
	int lng = axioms.size();
	for ( int i = 0; i < lng; i++ ) {
	    Axiom aI = (Axiom) axioms.elementAt(i);
	    cg.addClausedFormula(aI);
	}
	// add assertions
	lng = assertions.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom aI = (Atom) assertions.elementAt(i);
	    Vector vec = new Vector();
	    vec.addElement(aI);
	    PositiveClause pc = new PositiveClause(vec);
	    cg.addPosClause(pc, 0);
	}
	// add theorems
	// ++++

	// show the cg:
	if ( traceb )
	    System.out.println("cg: " + cg.html());

	// invoke tryProve:
	int resolveOut = cg.tryProve(maxSteps);
	/* -1 = cgLinks was empty
	   0 = empty clause found
	   1 = resource exhausted
	*/
	if ( traceb )
	    System.out.println("tryProve: " + resolveOut);
	// show the cg:
	if ( traceb )
	    System.out.println("cg: " + cg.html());

	if  ( 0 == resolveOut )
	    return new ProofStep(negatedConjecture, Symbol.TRUE, "Empty clause found");
	if  ( 1 == resolveOut )
	    return new ProofStep(negatedConjecture, Symbol.UNKNOWN, "Resource exhausted");
	// if  ( -1 == resolveOut )
	    return new ProofStep(negatedConjecture, Symbol.FALSE, "Links empty");

    } // end resolve(...)

    public ProofStep proveConjunction(Conjunction conjunction, int maxSteps) {
	Vector conjuncts = conjunction.getConjuncts();
	int lng = conjuncts.size();
	Vector proofSteps = new Vector();
	for ( int i = 0; i < lng; i++ ) {
	    Formula conI = (Formula) conjuncts.elementAt(i);
	    ProofStep psI = prove(conI, maxSteps);
	    Atom resultI = psI.getResult();
	    if ( !resultI.equals(Symbol.TRUE) ) return psI;
	    proofSteps.addElement(psI);
	}
	return new ProofStepAnd(conjunction, Symbol.TRUE, proofSteps);
    }

    public ProofStep instanceOfAssertions(Formula conjecture2) {
	int lng = assertions.size();
	boolean b;
	for ( int i = 0; i < lng; i++ ) {
	    Atom aI = (Atom) assertions.elementAt(i);
	    b = Formula.instance(conjecture2, aI);
	    if (b) return new ProofStep(conjecture2, Symbol.TRUE, "Instance of Assertion");
	}
	return new ProofStep(conjecture2, Symbol.UNKNOWN, "Undecided");
    }

    public ProofStep instanceOfAxioms(Formula conjecture2) {
	int lng = axioms.size();
	boolean b;
	for ( int i = 0; i < lng; i++ ) {
	    Axiom aI = (Axiom) axioms.elementAt(i);
	    Formula fI = aI.getFormula();
	    b = Formula.instance(conjecture2, fI);
	    if (b) return new ProofStep(conjecture2, Symbol.TRUE, "Instance of Axiom");
	}
	return new ProofStep(conjecture2, Symbol.UNKNOWN, "Undecided");
    }

    public ProofStep instanceOfTheorems(Formula conjecture2) {
	int lng = theorems.size();
	boolean b;
	for ( int i = 0; i < lng; i++ ) {
	    Theorem tI = (Theorem) axioms.elementAt(i);
	    Formula fI = tI.getFormula();
	    b = Formula.instance(conjecture2, fI);
	    if (b) return new ProofStep(conjecture2, Symbol.TRUE, "Instance of Theorem");
	}
	return new ProofStep(conjecture2, Symbol.UNKNOWN, "Undecided");
    }


    public CG setUpCGforConjecture(Formula conjecture) { // conjecture is in miniscope
	Formula negatedConjecture = new Negation(conjecture);
	ClausedFormula negatedInput = new ClausedFormula(negatedConjecture);
	// CG cgx = new CG(true);
	CG cgx = new CG(traceb);
	cgx.addClausedFormula(negatedInput);
	// add axioms
	int lng = axioms.size();
	for ( int i = 0; i < lng; i++ ) {
	    Axiom aI = (Axiom) axioms.elementAt(i);
	    cgx.addClausedFormula(aI);
	}
	// add assertions
	lng = assertions.size();
	for ( int i = 0; i < lng; i++ ) {
	    Atom aI = (Atom) assertions.elementAt(i);
	    Vector vec = new Vector();
	    vec.addElement(aI);
	    PositiveClause pc = new PositiveClause(vec);
	    cgx.addPosClause(pc, 0);
	}
	// add theorems
	// ++++

	return cgx;
    } // end of setUpCGforConjecture(Formula conjecture)

} // end Theory
