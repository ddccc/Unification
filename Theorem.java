// (C) OntoOO Inc 2005 Jan
package fol;

// import java.util.*;

public class Theorem extends ClausedFormula {
    protected ProofStep proofStep;
    public Theorem(Formula formula, ProofStep ps) {
	super(formula);
	proofStep = ps;
    }
    public ProofStep getProofStep() { return proofStep; }
}
