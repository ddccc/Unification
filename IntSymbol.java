// (C) OntoOO Inc 2004 Dec
package fol;

public class IntSymbol extends Symbol {
    // protected String name = "";
    int value = 0;
    IntSymbol(String name) { 
	super(name);
	try { value = Integer.parseInt(name); }
	catch (NumberFormatException ex) {}
    }
    public int getValue() { return value; }

}
