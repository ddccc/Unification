File: c:/ddc/Java/fol/aaReadMe.txt
Date: Fri Sep 14 11:41:10 2018

This directory contains a first order logic, un-typed, theorem prover.
Its core has a Kowalski type connection graph on which the unification
algorithm operates. The connection graph contains clauses in cnf format.
These clauses represent (optionally) axioms, theorems, lemmas and the 
negated conjecture to be proven. Repeated application of the unification
algorithm (called resolution) on complimentary literals aims to 
create a contradiction by generating an empty new clause; thereby
proving the conjecture.

A wrapper postpones using the connection graph machinery by applying
preprocessing on the conjecture, axioms, theorems and
definitions. See:
Dennis de Champeaux, "Subproblem Finder and Instance Checker, Two
Cooperating Modules for Theorem Provers", JACM,  vol 33, no 4, pp
633-657, 1986 October.
https://dl.acm.org/citation.cfm?doid=6490.6491

Files starting with "Z" show how to use the parser (text->formulas)
for generating input and how to operate the theorem prover.

[This module is a stop-gap component used for deliberation/ decision
making in an agent design pattern/ sub-architecture.]
