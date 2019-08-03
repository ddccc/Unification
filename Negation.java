// (C) OntoOO Inc 2004 Dec
package fol;

// import java.util.*;

public class Negation extends Formula {
    protected Formula formula;
    Negation(Formula formula) {
	this.formula = formula;
	variables = formula.getVariables();
    }
    public Formula getFormula() { return formula; }
    public Formula subst(Variable var, Term term) { 
	// if ( null == variables ) return this;
	if ( !variables.contains(var) ) return this;
	return new Negation(formula.subst(var, term));
    }
    private String htmlString = null;
    public String html() { 
	if ( null == htmlString ) {
	    String f = formula.html();
	    f = f.substring(0, -1 + f.length());
	    htmlString = "~(" + f + ") ";
	}
	return htmlString;
    }

}





