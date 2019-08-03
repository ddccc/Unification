// (C) OntoOO Inc 2005 Mar
package fol;

import java.util.*;

import java.io.*;

public class CG implements Serializable {
    static public boolean traceb = false;

    private HashSet cgNegNodes = new HashSet();
    private HashSet cgPosNodes = new HashSet();
    private HashSet cgMixNodes = new HashSet();
    private TreeSet cgLinks = new TreeSet();


    // constructors here ...
    public CG() { traceb = true; }
    public CG(boolean bool) { traceb = bool; }

    public void addClausedFormula(ClausedFormula cf) {
	Vector negClauses = (Vector) cf.getNegativeClauses();
	Vector posClauses = (Vector) cf.getPositiveClauses();
	Vector mixClauses = (Vector) cf.getMixedClauses();

	int lng = negClauses.size();
	for ( int i = 0; i < lng; i++ ) 
	    this.addNegClause( (NegativeClause) negClauses.elementAt(i), 0);
	lng = posClauses.size();
	for ( int i = 0; i < lng; i++ ) 
	    this.addPosClause( (PositiveClause) posClauses.elementAt(i), 0);
	lng = mixClauses.size();
	for ( int i = 0; i < lng; i++ ) 
	    this.addMixClause( (MixedClause) mixClauses.elementAt(i), 0);
    }

    public CGNegNode addNegClause(NegativeClause nc, int level) { 
	CGNegNode cnn = new CGNegNode(nc, level);
	cnn.integrate(cgPosNodes, cgMixNodes, cgLinks);
	cgNegNodes.add(cnn);
	Vector factors = nc.getFactors();
	int lng = factors.size();
	if ( traceb && 0 < lng )
	    System.out.println("# Factors: " + lng);
	for ( int i = 0; i < lng; i++ ) {
	    NegativeClause factorI = (NegativeClause) factors.elementAt(i);
	    CGNegNode fI = new CGNegNode(factorI, level);
	    fI.integrate(cgPosNodes, cgMixNodes, cgLinks);
	    cgNegNodes.add(fI);
	}
	return cnn;
    }
    public CGPosNode addPosClause(PositiveClause pc, int level) { 
	CGPosNode cpn = new CGPosNode(pc, level);
	cpn.integrate(cgNegNodes, cgMixNodes, cgLinks);
	cgPosNodes.add(cpn);
	return cpn;
    }
    public CGMixNode addMixClause(MixedClause mc, int level) { 
	CGMixNode cmn = new CGMixNode(mc, level);
	cmn.integrate(cgNegNodes, cgPosNodes, cgMixNodes, cgLinks);
	cgMixNodes.add(cmn);
	return cmn;
    }

    /* -1 = cgLinks was empty
       0 = empty clause found
       1 = resource exhausted
    */
    public int tryProve(int resolves) {
	while ( 0 < resolves ) {
	    resolves--;
	    int out = resolve();
	    if ( traceb )
		System.out.println("resolve() return value: " + out);
	    if ( out < 1 ) return out;
	}
	return 1;
    }


    /*
      We resolve only when:
      -- one of the clauses is pure negative
      -- the literal of that clause is the first one
      Return values:
      -1 = cgLinks was empty
       0 = empty clause found
       1 = link resolved, cg updated, no contradiction found 
       2 = true found, hence no clause added to the CG 
       3 = negative input clause was already deleted
       4 = other input clause was already deleted
       5 = new clause has literal without links 
       6 = new neg clause is subsumed 
       7 = new pos clause is subsumed 
       8 = new mix clause is subsumed 
    */
    private int resolveCnt = 0;
    public int resolve() {
	resolveCnt++;
	if ( traceb ) {
	    System.out.println("========= resolveCnt: " + resolveCnt);
	    System.out.println("|cgLinks|= " + cgLinks.size());
	}
	if ( cgLinks.isEmpty() ) return -1;
	CGLink cgLink = (CGLink) cgLinks.first();
	if ( traceb )
	    System.out.println("Chosen cgLink:  " + cgLink.html());
	boolean deleteb = cgLinks.remove(cgLink); 

	CGNegNode nodeN = (CGNegNode) cgLink.getCgNodeN();
	if ( traceb )
	    System.out.println("Negative clause nodeN: " + nodeN.html());
	CGNode node = (CGNode) cgLink.getCgNodeP();
	if ( traceb )
	    System.out.println("Other clause node: " + node.html());

	if ( nodeN.deleted() ) { 
	    return 3;
	}
	if ( node.deleted() ) { 
	    return 4;
	}
	int levelN = nodeN.getLevel();
	int level = node.getLevel();
	int nextLevel = 1 + ( levelN < level ? level : levelN );

	// collect in newNegLiterals and newPosLiterals the literals 
	// for the new clause with the old links
	Vector substitutions = cgLink.getSubstitutions();
	Vector nodeNegLiterals = nodeN.getCgNegLiterals();
	CGLiteral omitLiteral = (CGLiteral) nodeNegLiterals.elementAt(0);
	Atom omitAtom = omitLiteral.getAtom();
	omitAtom = (Atom) omitAtom.sublis(substitutions);
	Vector newNegLiterals = new Vector();
	int lng = nodeNegLiterals.size();
	for ( int i = 1; i < lng; i++ ) {
	    CGLiteral litI = (CGLiteral) nodeNegLiterals.elementAt(i);
	    Atom atI = litI.getAtom();
	    atI = (Atom) atI.sublis(substitutions);
	    if ( omitAtom.equals(atI) ) continue; 
	    if ( occurs(atI, newNegLiterals) ) continue;
	    Atom evalOut = atI.eval();
	    if ( Symbol.TRUE == evalOut ) continue; else // literal is false
	    if ( Symbol.FALSE == evalOut ) { // ignore true clause
		node.removeP(cgLink);
		nodeN.removeN(cgLink);
		return 2; 
	    }
	    /*
	    boolean canEval = atI.canEvaluate();
	    boolean boolinst = ( atI instanceof BoolInt2 );
	    System.out.println("Eval: " + atI.html() + 
			       " canEval: " + canEval +
			       " boolinst: " + boolinst +
			       " evalOut: " + evalOut.html());
	    */
	    Vector oldCgLinks = litI.getCgLinks(); 
	    // links will be replaced/updated later
	    newNegLiterals.addElement(new CGLiteral(atI, oldCgLinks));  
	}
	nodeNegLiterals = node.getCgNegLiterals();
	lng = nodeNegLiterals.size();
	for ( int i = 0; i < lng; i++ ) {
	    CGLiteral litI = (CGLiteral) nodeNegLiterals.elementAt(i);
	    Atom atI = litI.getAtom();
	    atI = (Atom) atI.sublis(substitutions);
	    if ( omitAtom.equals(atI) ) continue; 
	    if ( occurs(atI, newNegLiterals) ) continue;
	    Atom evalOut = atI.eval();
	    if ( Symbol.TRUE == evalOut ) continue; else // literal is false
	    if ( Symbol.FALSE == evalOut ) { // ignore true clause
		node.removeP(cgLink);
		nodeN.removeN(cgLink);
		return 2; 
	    }
	    Vector oldCgLinks = litI.getCgLinks(); 
	    // links will be replaced/updated later
	    newNegLiterals.addElement(new CGLiteral(atI, oldCgLinks));
	}
	int lngNegs = newNegLiterals.size();

	// now the positive literals:
	int indexJ = cgLink.getJ();
	Vector nodePosLiterals = node.getCgPosLiterals();
	Vector newPosLiterals = new Vector();
	lng = nodePosLiterals.size();
	for ( int i = 0; i < lng; i++ ) {
	    if ( indexJ == i ) continue;
	    CGLiteral litI = (CGLiteral) nodePosLiterals.elementAt(i);
	    Atom atI = litI.getAtom();
	    atI = (Atom) atI.sublis(substitutions);
	    if ( omitAtom.equals(atI) ) continue;
	    if ( occurs(atI, newPosLiterals) ) continue; // merge duplicate
	    if ( occurs(atI, newNegLiterals) ) {  // check for tautology
		node.removeP(cgLink);
		nodeN.removeN(cgLink);
		return 2; 
	    }
	    Atom evalOut = atI.eval();
	    if ( Symbol.FALSE == evalOut ) continue; else
	    if ( Symbol.TRUE == evalOut ) { // ignore true clause
		node.removeP(cgLink);
		nodeN.removeN(cgLink);
		return 2; 
	    }
	    Vector oldCgLinks = litI.getCgLinks(); 
	    // links will be replaced/updated later
	    newPosLiterals.addElement(new CGLiteral(atI, oldCgLinks));
	}
	int lngPoss = newPosLiterals.size();

	if ( 0 == lngNegs && 0 == lngPoss ) { 
	    node.removeP(cgLink);
	    nodeN.removeN(cgLink);
	    return 0;
	}

	Vector newNegAtoms = null;
	Vector newPosAtoms = null;
	if ( 0 < lngNegs ) {
	    newNegAtoms = new Vector();  // pre substitution atoms
	    for ( int i = 0; i < lngNegs; i++ ) {
		CGLiteral litI = (CGLiteral) newNegLiterals.elementAt(i);
		Atom atI = litI.getAtom();	    
		newNegAtoms.addElement(atI);
	    }
	}
	if ( 0 < lngPoss ) {
	    newPosAtoms = new Vector();  // pre substitution atoms
	    for ( int i = 0; i < lngPoss; i++ ) {
		CGLiteral litI = (CGLiteral) newPosLiterals.elementAt(i);
		Atom atI = litI.getAtom();	    
		newPosAtoms.addElement(atI);
	    }
	}

	CGNode newNode = null;
	if ( 0 == lngPoss ) { 
	    NegativeClause nc = new NegativeClause(newNegAtoms);
	    newNode = new CGNegNode(nc, nextLevel);
	    if ( subsumedNeg((CGNegNode) newNode) ) return 6;
	}
	else if ( 0 == lngNegs ) {
	    PositiveClause pc = new PositiveClause(newPosAtoms);
	    newNode = new CGPosNode(pc, nextLevel);
	    if ( subsumedPos((CGPosNode) newNode) ) return 7;
	}
	else {
	    MixedClause mc = new MixedClause(newNegAtoms, newPosAtoms);
	    newNode = new CGMixNode(mc, nextLevel);
	    if ( subsumedMix((CGMixNode) newNode) ) return 8;
	}

	if ( 0 < lngNegs ) { 
	    Vector newerNegLiterals = newNode.getCgNegLiterals();
	    // create new links using the info from the old links;  
	    for ( int i = 0; i < lngNegs; i++ ) {
		CGLiteral litI = (CGLiteral) newNegLiterals.elementAt(i);
		Vector oldLinks = litI.getCgLinks();
		int lngoldLinks = oldLinks.size();
		CGLiteral newerLitI = (CGLiteral) newerNegLiterals.elementAt(i);
		Atom atomI = newerLitI.getAtom();
		Vector newerLinks = newerLitI.getCgLinks();
		for ( int k = 0; k < lngoldLinks; k++ ) {
		    CGLink oldLink = (CGLink) oldLinks.elementAt(k);
		    int indexJ2 = oldLink.getJ();
		    CGNode nodeK = oldLink.getCgNodeP();
		    Vector nodeKposLiterals = nodeK.getCgPosLiterals();
		    CGLiteral nodeKJliteral = 
			(CGLiteral) nodeKposLiterals.elementAt(indexJ2);
		    Atom atKJ = nodeKJliteral.getAtom();
		    Vector subs = atomI.unify(atKJ);
		    if ( null != subs ) {
			CGLink cgLink2 = 
			    new CGLink(newNode, i, nodeK, indexJ2, subs);
			newerLinks.addElement(cgLink2); // = newerLitI.addLink(cgLink2);
			nodeKJliteral.addLink(cgLink2);
		    }
		}
		if ( 0 == newerLinks.size() ) {
		    node.removeP(cgLink);
		    nodeN.removeN(cgLink);
		    if ( traceb )
			System.out.println(
			    "New atom without links: " + atomI.html());
		    return 5;
		}
	    }
	}
	if ( 0 < lngPoss ) { 
	    Vector newerPosLiterals = newNode.getCgPosLiterals();
	    // create new links using the info from the old links;  
	    for ( int i = 0; i < lngPoss; i++ ) {
		CGLiteral litI = (CGLiteral) newPosLiterals.elementAt(i);
		Vector oldLinks = litI.getCgLinks();
		int lngoldLinks = oldLinks.size();
		CGLiteral newerLitI = (CGLiteral) newerPosLiterals.elementAt(i);
		Atom atomI = newerLitI.getAtom();
		Vector newerLinks = newerLitI.getCgLinks();
		for ( int k = 0; k < lngoldLinks; k++ ) {
		    CGLink oldLink = (CGLink) oldLinks.elementAt(k);
		    int indexI2 = oldLink.getI();
		    CGNode nodeK = oldLink.getCgNodeN();
		    Vector nodeKnegLiterals = nodeK.getCgNegLiterals();
		    CGLiteral nodeKJliteral = 
			(CGLiteral) nodeKnegLiterals.elementAt(indexI2);
		    Atom atKJ = nodeKJliteral.getAtom();
		    Vector subs = atomI.unify(atKJ);
		    if ( null != subs ) {
			CGLink cgLink2 = 
			    new CGLink(nodeK, indexI2, newNode, i, subs);
			newerLinks.addElement(cgLink2); // = newerLitI.addLink(cgLink2);
			nodeKJliteral.addLink(cgLink2);
		    }
		}
		if ( 0 == newerLinks.size() ) {
		    node.removeP(cgLink);
		    nodeN.removeN(cgLink);
		    if ( traceb )
			System.out.println(
			    "New atom without links: " + atomI.html());
		    return 5;
		}
	    }
	}

	// insert in CG and cgLinks
	if ( 0 == lngPoss ) {
	    Vector newerNegLiterals = newNode.getCgNegLiterals();
	    CGLiteral lit0 = (CGLiteral) newerNegLiterals.elementAt(0);
	    Vector newerLinks = lit0.getCgLinks();
	    cgLinks.addAll(newerLinks);
	    cgNegNodes.add(newNode);
	} else if ( 0 == lngNegs )
	    cgPosNodes.add(newNode);
	else
	    cgMixNodes.add(newNode);

	if ( traceb )
	    System.out.println("newNode: " + newNode.html());

	node.removeP(cgLink);
	nodeN.removeN(cgLink);
	return 1;
    }

    // occurs prevents duplicates/ merges literals
    private boolean occurs(Atom atI, Vector newLiterals) {
	int lng = newLiterals.size();
	for ( int i = 0; i < lng; i++ ) {
	    CGLiteral c = (CGLiteral) newLiterals.elementAt(i);
	    Atom ca = c.getAtom();
	    if ( ca.equals(atI) ) return true;
	}
	return false;
    }
    private boolean subsumedNeg(CGNegNode newNode) {
	NegativeClause nc = newNode.getNegativeClause();
	HashSet variables = nc.getVariables();
	Vector negativeAtoms = nc.getNegativeAtoms();
	// replace variables with fresh constants
	for ( Iterator e = variables.iterator(); e.hasNext(); ) {
	    Variable var = (Variable) e.next();
	    Symbol sym = FTerm.getSkolem();
	    negativeAtoms = Clause.subst(negativeAtoms, var, sym);
	}
	// subsume/resolve negativeAtoms against the nodes in cgNegNodes
	for ( Iterator e = cgNegNodes.iterator(); e.hasNext(); ) {
	    CGNegNode negNode = (CGNegNode) e.next();
	    // subsume/ resolve
	    boolean subsumed = negNode.subsume(negativeAtoms);
	    if ( subsumed ) return true;
	}
	return false;
    }
    private boolean subsumedPos(CGPosNode newNode) {
	PositiveClause pc = newNode.getPositiveClause();
	HashSet variables = pc.getVariables();
	Vector positiveAtoms = pc.getPositiveAtoms();
	// replace variables with fresh constants
	for ( Iterator e = variables.iterator(); e.hasNext(); ) {
	    Variable var = (Variable) e.next();
	    Symbol sym = FTerm.getSkolem();
	    positiveAtoms = Clause.subst(positiveAtoms, var, sym);
	}
	// subsume/resolve positiveAtoms against the nodes in cgPosNodes
	for ( Iterator e = cgPosNodes.iterator(); e.hasNext(); ) {
	    CGPosNode posNode = (CGPosNode) e.next();
	    // subsume/ resolve
	    boolean subsumed = posNode.subsume(positiveAtoms);
	    if ( subsumed ) return true;
	}
	return false;
    }
    private boolean subsumedMix(CGMixNode newNode) {
	MixedClause mc = newNode.getMixedClause();
	HashSet variables = mc.getVariables();
	Vector negativeAtoms = mc.getNegativeAtoms();
	Vector positiveAtoms = mc.getPositiveAtoms();
	// replace variables with fresh constants
	for ( Iterator e = variables.iterator(); e.hasNext(); ) {
	    Variable var = (Variable) e.next();
	    Symbol sym = FTerm.getSkolem();
	    negativeAtoms = Clause.subst(negativeAtoms, var, sym);
	    positiveAtoms = Clause.subst(positiveAtoms, var, sym);
	}
	// subsume/resolve positiveAtoms against the nodes in cgPosNodes
	for ( Iterator e = cgPosNodes.iterator(); e.hasNext(); ) {
	    CGPosNode posNode = (CGPosNode) e.next();
	    // subsume/ resolve
	    boolean subsumed = posNode.subsume(positiveAtoms);
	    if ( subsumed ) return true;
	}
	// subsume/resolve negativeAtoms against the nodes in cgNegNodes
	for ( Iterator e = cgNegNodes.iterator(); e.hasNext(); ) {
	    CGNegNode negNode = (CGNegNode) e.next();
	    // subsume/ resolve
	    boolean subsumed = negNode.subsume(negativeAtoms);
	    if ( subsumed ) return true;
	}
	// last chance:
	// subsume/resolve against the nodes in cgMixNodes
	for ( Iterator e = cgMixNodes.iterator(); e.hasNext(); ) {
	    CGMixNode mixNode = (CGMixNode) e.next();
	    // subsume/ resolve
	    boolean subsumed = mixNode.subsume(negativeAtoms, positiveAtoms);
	    if ( subsumed ) return true;
	}
	return false;
    }

    public String html() {
	StringBuffer sb = new StringBuffer("CG:::: \n");
	for ( Iterator e = cgLinks.iterator(); e.hasNext(); ) 
	    sb.append( ((CGLink)e.next()).html() + "\n");
	return sb.toString();
    }

    static public void integrate(CGNegNode cgNnode, int i, HashSet cgPosNodes, 
				 HashSet cgMixNodes, TreeSet cgLinks) {
	for ( Iterator e = cgPosNodes.iterator(); e.hasNext(); ) 
	    integrate(cgNnode, i, (CGPosNode)e.next(), cgLinks);
	for ( Iterator e = cgMixNodes.iterator(); e.hasNext(); ) 
	    integrate(cgNnode, i, (CGMixNode)e.next(), cgLinks);
    }
    static public void integrate(CGNegNode cgNnode, int i, 
				 CGNode cgNode, TreeSet cgLinks) {
	Vector cgPosLiterals = cgNode.getCgPosLiterals();
	int lng = cgPosLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgNnode, i, cgNode, j, cgLinks);
	}
    }
    static public void integrate(CGNegNode cgNnode, int i, 
				 CGNode cgNode, int j,
				 TreeSet cgLinks) {
	Vector cgNegLiterals = cgNnode.getCgNegLiterals();
	CGLiteral nLiteral = (CGLiteral) cgNegLiterals.elementAt(i);
	Atom nAtom = nLiteral.getAtom();
	Vector cgPosLiterals = cgNode.getCgPosLiterals();
	CGLiteral pLiteral = (CGLiteral) cgPosLiterals.elementAt(j);
	Atom pAtom = pLiteral.getAtom();
	Vector substitutions = nAtom.unify(pAtom);
	if ( null == substitutions ) return;
	CGLink cgLink = new CGLink(cgNnode, i, 
				   cgNode, j, 
				   substitutions);
	nLiteral.addLink(cgLink);
	pLiteral.addLink(cgLink);
	// System.out.println("trace: i= " + i + " " + cgLink.html());
	if ( 0 == i ) cgLinks.add(cgLink);
    }
    static public void integrate(CGPosNode cgPnode, int i, HashSet cgNegNodes, 
				 HashSet cgMixNodes, TreeSet cgLinks) {
	for ( Iterator e = cgNegNodes.iterator(); e.hasNext(); ) 
	    integrate(cgPnode, i, (CGNegNode)e.next(), cgLinks);
	for ( Iterator e = cgMixNodes.iterator(); e.hasNext(); ) 
	    integrate(cgPnode, i, (CGMixNode)e.next() );
    }
    static public void integrate(CGPosNode cgPnode, int i,
				 CGNegNode cgNnode, TreeSet cgLinks) {
	Vector cgNegLiterals = cgNnode.getCgNegLiterals();
	int lng = cgNegLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgNnode, j, cgPnode, i, cgLinks);
	}
    }
    static public void integrate(CGPosNode cgPnode, int i,
				 CGMixNode cgMnode ) { 
	Vector cgNegLiterals = cgMnode.getCgNegLiterals();
	int lng = cgNegLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgMnode, j, cgPnode, i);
	}
    }
    static public void integrate(CGMixNode cgMnode, int j, 
				 CGPosNode cgPNode, int i) {
	Vector cgNegLiterals = cgMnode.getCgNegLiterals();
	CGLiteral nLiteral = (CGLiteral) cgNegLiterals.elementAt(j);
	Atom nAtom = nLiteral.getAtom();
	Vector cgPosLiterals = cgPNode.getCgPosLiterals();
	CGLiteral pLiteral = (CGLiteral) cgPosLiterals.elementAt(i);
	Atom pAtom = pLiteral.getAtom();
	Vector substitutions = nAtom.unify(pAtom);
	if ( null == substitutions ) return;
	CGLink cgLink = new CGLink(cgMnode, j, 
				   cgPNode, i, 
				   substitutions);
	nLiteral.addLink(cgLink);
	pLiteral.addLink(cgLink);
    }
    static public void integrate(CGMixNode cgMnode, int i, 
				 HashSet cgPosNodes, HashSet cgMixNodes) { 
	for ( Iterator e = cgPosNodes.iterator(); e.hasNext(); ) 
	    integrate(cgMnode, i, (CGPosNode)e.next());
	for ( Iterator e = cgMixNodes.iterator(); e.hasNext(); ) 
	    integrate(cgMnode, i, (CGMixNode)e.next() ); // 1st arg is negative
    }
    static public void integrate(CGMixNode cgMnode, int i, 
				 CGPosNode cgPnode) {
	Vector cgPosLiterals = cgPnode.getCgPosLiterals();
	int lng = cgPosLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgMnode, i, cgPnode, j);
	}
    }
    static public void integrate(CGMixNode cgMnode, int i, // 1st arg is negative
				 CGMixNode cgMnode2) { 
	Vector cgPosLiterals = cgMnode2.getCgPosLiterals();
	int lng = cgPosLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgMnode, i, cgMnode2, j);
	}
    }
    static public void integrate2(CGMixNode cgMnode, int i, // 1st arg is positive
				 CGMixNode cgMnode2) { 
	Vector cgNegLiterals = cgMnode2.getCgNegLiterals();
	int lng = cgNegLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgMnode2, j, cgMnode, i);
	}
    }
    static public void integrate(CGMixNode cgMnode, int i, 
				 CGMixNode cgMnode2, int j) {
	Vector cgNegLiterals = cgMnode.getCgNegLiterals();
	CGLiteral nLiteral = (CGLiteral) cgNegLiterals.elementAt(i);
	Atom nAtom = nLiteral.getAtom();
	Vector cgPosLiterals = cgMnode2.getCgPosLiterals();
	CGLiteral pLiteral = (CGLiteral) cgPosLiterals.elementAt(j);
	Atom pAtom = pLiteral.getAtom();
	Vector substitutions = nAtom.unify(pAtom);
	if ( null == substitutions ) return;
	CGLink cgLink = new CGLink(cgMnode, i, 
				   cgMnode2, j, 
				   substitutions);
	nLiteral.addLink(cgLink);
	pLiteral.addLink(cgLink);
    }
    static public void integrate(CGMixNode cgMnode, int i, // 1st arg is positive
				 HashSet cgNegNodes, HashSet cgMixNodes,
				 TreeSet cgLinks) {
	for ( Iterator e = cgNegNodes.iterator(); e.hasNext(); ) 
	    integrate(cgMnode, i, (CGNegNode)e.next(), cgLinks);
	for ( Iterator e = cgMixNodes.iterator(); e.hasNext(); ) 
	    integrate2(cgMnode, i, (CGMixNode)e.next() );
    }    
    static public void integrate(CGMixNode cgMnode, int i, 
				 CGNegNode cgNnode, 
				 TreeSet cgLinks) {
	Vector cgNegLiterals = cgNnode.getCgNegLiterals();
	int lng = cgNegLiterals.size();
	for ( int j = 0; j < lng; j++ ) {
	    integrate(cgNnode, j, cgMnode, i, cgLinks);
	}
    }
} // end of class CG

// ----------------------------- CGNode --------------------------------
abstract class CGNode implements Serializable {
    protected int level = 0;
    int getLevel() { return level; }
    int length() { return cgNegLiterals.size() + cgPosLiterals.size(); }
    protected Vector cgNegLiterals = new Vector();
    public Vector getCgNegLiterals() { return cgNegLiterals; }
    protected Vector cgPosLiterals = new Vector();
    public Vector getCgPosLiterals() { return cgPosLiterals; }
    public void removeN(CGLink cgLink) {
	if ( deleted ) return; // node is already dead
	int i = cgLink.getI();
	CGLiteral litI = (CGLiteral) cgNegLiterals.elementAt(i);
	boolean empty = litI.removeLink(cgLink);
	if ( empty ) {
	    deleted = true;
	    deleteLinks();
	}
    }
    public void removeP(CGLink cgLink) {
	if ( deleted ) return; // node is already dead
	int i = cgLink.getJ();
	CGLiteral litI = (CGLiteral) cgPosLiterals.elementAt(i);
	boolean empty = litI.removeLink(cgLink);
	if ( empty ) {
	    deleted = true;
	    deleteLinks();
	}
    }
    private boolean deleted = false;
    public boolean deleted() { return deleted; }
    private void deleteLinks() {
	if ( CG.traceb )
	    System.out.println("Delete CGNode: " + html());
	deleteLinksP(cgPosLiterals);
	deleteLinksN(cgNegLiterals);
    }
    private void deleteLinksP(Vector literalsP) {
	int lng = literalsP.size();
	for ( int i = 0; i < lng; i++ ) {
	    CGLiteral litI = (CGLiteral) literalsP.elementAt(i);
	    Vector links = litI.getCgLinks();
	    int lng2 = links.size();
	    for ( int j = 0; j < lng2; j++ ) {
		CGLink cgLink = (CGLink) links.elementAt(j);
		CGNode nodeN = cgLink.getCgNodeN();
		nodeN.removeN(cgLink);
	    }
	}
    }
    private void deleteLinksN(Vector literalsN) {
	int lng = literalsN.size();
	for ( int i = 0; i < lng; i++ ) {
	    CGLiteral litI = (CGLiteral) literalsN.elementAt(i);
	    Vector links = litI.getCgLinks();
	    int lng2 = links.size();
	    for ( int j = 0; j < lng2; j++ ) {
		CGLink cgLink = (CGLink) links.elementAt(j);
		CGNode nodeP = cgLink.getCgNodeP();
		nodeP.removeP(cgLink);
	    }
	}
    }
    /* connected is not used
    private boolean connected = true;
    public boolean connected() { 
	if (!connected) return false;
	connected = connected(cgNegLiterals) && connected(cgPosLiterals);
	return connected;
    }
    private boolean connected(Vector cgLiterals) {
	int lng = cgLiterals.size();
	for ( int i = 0; i < lng; i++ ) {
	    CGLiteral litI = (CGLiteral) cgLiterals.elementAt(i);
	    Vector cgLinks = litI.getCgLinks(); 
	    if ( 0 == cgLinks.size() ) return false;
	}
	return true;
    }
    */

    public String html() {
	int lngN = cgNegLiterals.size();
	int lngP = cgPosLiterals.size();
	StringBuffer sb = new StringBuffer(
	      "CGNode: deleted= " + deleted +
	      ( 0 == lngP ? " Negative" : 
		( 0 == lngN ? " Positive" : " Mixed" )) + "\n");
	if ( 0 < lngN ) {
	    sb.append("Negative literals: " + lngN + "\n");
	    for (int i = 0; i < lngN; i++) {
		CGLiteral c = (CGLiteral) cgNegLiterals.elementAt(i);
		sb.append(c.html() + "\n");
	    }
	}
	if ( 0 < lngP ) {
	    sb.append("Positive literals: " + lngP + "\n");
	    for (int i = 0; i < lngP; i++) {
		CGLiteral c = (CGLiteral) cgPosLiterals.elementAt(i);
		sb.append(c.html() + "\n");
	    }
	}
	return sb.toString();
    }
}

// ----------------------------- CGNegNode --------------------------------
class CGNegNode extends CGNode { 
    private NegativeClause nc;
    CGNegNode(NegativeClause nc, int level) {
	this.nc = nc;
	this.level = level;
	Vector negativeAtoms = nc.getNegativeAtoms();
	int lng = negativeAtoms.size();
	for (int i = 0; i < lng; i++) {
	    Atom aI = (Atom) negativeAtoms.elementAt(i);
	    cgNegLiterals.addElement(new CGLiteral(aI));
	}
    }
    public NegativeClause getNegativeClause() { return nc; }
    public void integrate(HashSet cgPosNodes, HashSet cgMixNodes, TreeSet cgLinks) {
	int lng = cgNegLiterals.size();
	for (int i = 0; i < lng; i++) {
	    CG.integrate(this, i, cgPosNodes, cgMixNodes, cgLinks);
	}
    }

    public boolean subsume(Vector negativeAtoms) { // negativeAtoms are ground
	Vector clauses = new Vector();
	clauses.addElement(nc.getNegativeAtoms());
	return subsumeResolve(clauses, negativeAtoms);
    }
    static public boolean subsumeResolve(Vector clauses, Vector negativeAtoms) {
	Vector clauses2 = new Vector();
	int lng = clauses.size();
	for (int i = 0; i < lng; i++) { 
	    Vector out = 
		subsumeResolve1((Vector)clauses.elementAt(i), negativeAtoms);
	    if ( null == out ) return true;
	    clauses2.addAll(out);
	}
	return ( 0 == clauses2.size() ? false : 
		 subsumeResolve(clauses2, negativeAtoms) );
    }
    static private Vector subsumeResolve1(Vector clause, Vector negativeAtoms) {
	Atom atom1 = (Atom) clause.elementAt(0);
	Vector tail = (Vector) clause.clone();
	tail.removeElementAt(0);
	Vector out = new Vector();
	int lng = negativeAtoms.size();
	for (int i = 0; i < lng; i++) { 
	    Atom atomI = (Atom) negativeAtoms.elementAt(i);
	    Vector substitutions = atomI.unify(atom1);
	    if ( null == substitutions ) continue;
	    if ( 0 == tail.size() ) return null; // subsumed!!!
	    Vector tailI = Clause.sublis(tail, substitutions);
	    out.addElement(tailI);
	}
	return out;
    }
} // end CGNegNode

// ----------------------------- CGPosNode ----------------------------
class CGPosNode extends CGNode { 
    private PositiveClause pc;
    CGPosNode(PositiveClause pc, int level) {
	this.pc = pc;
	this.level = level;
	Vector positiveAtoms = pc.getPositiveAtoms();
	int lng = positiveAtoms.size();
	for (int i = 0; i < lng; i++) {
	    Atom aI = (Atom) positiveAtoms.elementAt(i);
	    cgPosLiterals.addElement(new CGLiteral(aI));
	}
    }
    public PositiveClause getPositiveClause() { return pc; }
    void integrate(HashSet cgNegNodes, HashSet cgMixNodes, TreeSet cgLinks) {
	int lng = cgPosLiterals.size();
	for (int i = 0; i < lng; i++) {
	    CG.integrate(this, i, cgNegNodes, cgMixNodes, cgLinks);
	}
    }

    public boolean subsume(Vector positiveAtoms) { // positiveAtoms are ground
	Vector clauses = new Vector();
	clauses.addElement(pc.getPositiveAtoms());
	return subsumeResolve(clauses, positiveAtoms);
    }
    static public boolean subsumeResolve(Vector clauses, Vector positiveAtoms) {
	Vector clauses2 = new Vector();
	int lng = clauses.size();
	for (int i = 0; i < lng; i++) { 
	    Vector out = 
		subsumeResolve1((Vector)clauses.elementAt(i), positiveAtoms);
	    if ( null == out ) return true;
	    clauses2.addAll(out);
	}
	return ( 0 == clauses2.size() ? false : 
		 subsumeResolve(clauses2, positiveAtoms) );
    }
    static private Vector subsumeResolve1(Vector clause, Vector positiveAtoms) {
	Atom atom1 = (Atom) clause.elementAt(0);
	Vector tail = (Vector) clause.clone();
	tail.removeElementAt(0);
	Vector out = new Vector();
	int lng = positiveAtoms.size();
	for (int i = 0; i < lng; i++) { 
	    Atom atomI = (Atom) positiveAtoms.elementAt(i);
	    Vector substitutions = atomI.unify(atom1);
	    if ( null == substitutions ) continue;
	    if ( 0 == tail.size() ) return null; // subsumed!!!
	    Vector tailI = Clause.sublis(tail, substitutions);
	    out.addElement(tailI);
	}
	return out;
    }
} // end CGPosNode

// ----------------------------- CGMixNode ----------------------------
class CGMixNode extends CGNode { 
    private MixedClause mc;
    CGMixNode(MixedClause mc, int level) {
	this.mc = mc;
	this.level = level;
	Vector negativeAtoms = mc.getNegativeAtoms();
	int lng = negativeAtoms.size();
	for (int i = 0; i < lng; i++) {
	    Atom aI = (Atom) negativeAtoms.elementAt(i);
	    cgNegLiterals.addElement(new CGLiteral(aI));
	}
	Vector positiveAtoms = mc.getPositiveAtoms();
	lng = positiveAtoms.size();
	for (int i = 0; i < lng; i++) {
	    Atom aI = (Atom) positiveAtoms.elementAt(i);
	    cgPosLiterals.addElement(new CGLiteral(aI));
	}
    }
    public MixedClause getMixedClause() { return mc; }
    void integrate(HashSet cgNegNodes, HashSet cgPosNodes, 
		   HashSet cgMixNodes, TreeSet cgLinks) {
	int lng = cgNegLiterals.size();
	for (int i = 0; i < lng; i++) {
	    CG.integrate(this, i, cgPosNodes, cgMixNodes);
	}
	lng = cgPosLiterals.size();
	for (int i = 0; i < lng; i++) {
	    CG.integrate(this, i, cgNegNodes, cgMixNodes, cgLinks);
	}
    }
    public boolean subsume(Vector negativeAtoms, 
			   Vector positiveAtoms) { // atoms are ground
	Vector clauses = new Vector();
	Vector mcNegativeAtoms = mc.getNegativeAtoms();
	Vector mcPositiveAtoms = mc.getPositiveAtoms();
	int numNegAtoms = mcNegativeAtoms.size();
	clauses.addElement(new PairVectors(mcNegativeAtoms, mcPositiveAtoms));
	return subsumeResolve(numNegAtoms, clauses, negativeAtoms, positiveAtoms);
    }
    static public boolean subsumeResolve(int numNegAtoms, Vector pairs, 
					 Vector negativeAtoms, Vector positiveAtoms) {
	numNegAtoms--;
	Vector pairs2 = new Vector();
	int lng = pairs.size();
	for (int i = 0; i < lng; i++) { 
	    Vector out = 
		subsumeResolve1((PairVectors)pairs.elementAt(i), negativeAtoms);
	    pairs2.addAll(out);
	}
	if ( 0 == pairs2.size() ) return false;
	if ( 0 < numNegAtoms ) 
	    return subsumeResolve(numNegAtoms, pairs2, negativeAtoms, positiveAtoms);
	// resolved away all negative literals; now attack the positive ones
	Vector modifiedPosClauses = new Vector();
	lng = pairs2.size();
	for (int i = 0; i < lng; i++) { 
	    PairVectors pair2I = (PairVectors) pairs2.elementAt(i);
	    modifiedPosClauses.addElement(pair2I.getVec2()); // vec1 is empty ...
	}
	return CGPosNode.subsumeResolve(modifiedPosClauses, positiveAtoms);
    }
    static private Vector subsumeResolve1(PairVectors pair, Vector negativeAtoms) {
	Vector negAtoms = pair.getVec1();
	Vector posAtoms = pair.getVec2();
	Atom atom1 = (Atom) negAtoms.elementAt(0);
	Vector tail = (Vector) negAtoms.clone();
	tail.removeElementAt(0);
	Vector out = new Vector();
	int lng = negativeAtoms.size();
	for (int i = 0; i < lng; i++) { 
	    Atom atomI = (Atom) negativeAtoms.elementAt(i);
	    Vector substitutions = atomI.unify(atom1);
	    if ( null == substitutions ) continue;
	    Vector tailI = Clause.sublis(tail, substitutions); // tail = empty is ok
	    Vector posAtomsI = Clause.sublis(posAtoms, substitutions);
	    out.addElement(new PairVectors(tailI, posAtomsI));
	}
	return out;
    }

} // end CGMixNode

// ----------------------------- CGLiteral -----------------------------
class CGLiteral implements Serializable { 
    private Atom atom;
    private Vector cgLinks = new Vector();

    public CGLiteral(Atom aI) { atom = aI; }
    public CGLiteral(Atom aI, Vector vec) { atom = aI; cgLinks = vec; }
    public Atom getAtom() { return atom; }
    public Vector getCgLinks() { return cgLinks; }
    public void addLink(CGLink cgLink) { cgLinks.addElement(cgLink); }
    public boolean removeLink(CGLink cgLink) { 
	cgLinks.removeElement(cgLink);
	return 0 == cgLinks.size();
    }
    public String html() {
	StringBuffer sb = new StringBuffer("CGLiteral: ");
	sb.append(atom.html() + "\n");
	int lng = cgLinks.size();
	for (int i = 0; i < lng; i++) {
	    CGLink cgLink = (CGLink) cgLinks.elementAt(i);
	    sb.append(cgLink.html() + "\n");
	}
	return sb.toString();
    }
}

// ----------------------------- CGLink -----------------------------
class CGLink implements Serializable, Comparable  { 
    private CGNode cgNodeN;
    public CGNode getCgNodeN() { return cgNodeN; }
    private int i; 
    public int getI() { return i; }
    private CGNode cgNodeP;
    public CGNode getCgNodeP() { return cgNodeP; }
    private int j; 
    public int getJ() { return j; }
    private Vector substitutions;
    public Vector getSubstitutions() { return substitutions; }
    private int rank;
    int getRank() { return rank; }
    CGLink(CGNode cgNodeN, int i, CGNode cgNodeP, int j,
	   Vector substitutions) { 
	this.cgNodeN = cgNodeN; this.i = i;
	this.cgNodeP = cgNodeP; this.j = j;
	this.substitutions = substitutions;
	int levelN = cgNodeN.getLevel();
	int lengthN = cgNodeN.length();
	int levelP = cgNodeP.getLevel();
	int lengthP = cgNodeP.length();
	rank = ( 1 == lengthN && 1 == lengthP ? 0 : 
		 levelN + levelP + lengthN + lengthP );
    }
    public int compareTo(Object o2) {
	if ( this == o2 ) return 0;
	CGLink cgl2 = this;
	try { cgl2 = (CGLink) o2; }
	catch (ClassCastException ignore) { return 0; }
	int rank2 = cgl2.getRank();
	/* to prevent TreeSet omiting an element that has equal rank to
	   and existing element, we return also 1 when rank == rank2 */
	return ( rank < rank2 ? -1 : 1 );
    }
    String html() {
	StringBuffer sb = new StringBuffer("CGLink: ");
	Vector nLiterals = cgNodeN.getCgNegLiterals();
	CGLiteral cgl = (CGLiteral) nLiterals.elementAt(i);
	Atom aI = cgl.getAtom();
	sb.append("nIx: " + i + " " + aI.html());
	nLiterals = cgNodeP.getCgPosLiterals();
	cgl = (CGLiteral) nLiterals.elementAt(j);
	aI = cgl.getAtom();
	sb.append(" pIx: " + j + " " + aI.html());
	sb.append(" rank: " + rank);
	return sb.toString();
    }
    
} // end CGLink
 
// ----------------------------- PairVectors ----------------------------
class PairVectors {
    private Vector vec1;
    private Vector vec2;
    public PairVectors(Vector vec1, Vector vec2) {
	this.vec1 = vec1;
	this.vec2 = vec2;
    }
    public Vector getVec1() { return vec1; }
    public Vector getVec2() { return vec2; }

} // end PairVectors


