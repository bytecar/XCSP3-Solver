/**
 * AbsCon - Copyright (c) 2017, CRIL-CNRS - lecoutre@cril.fr
 * 
 * All rights reserved.
 * 
 * This program and the accompanying materials are made available under the terms of the CONTRAT DE LICENCE DE LOGICIEL LIBRE CeCILL which accompanies this
 * distribution, and is available at http://www.cecill.info
 */
package org.xcsp.modeler.problems;

import org.xcsp.common.IVar.Var;
import org.xcsp.modeler.ProblemAPI;

class Riddle3 implements ProblemAPI {

	@Override
	public void model() {
		Var x[] = array("x", size(4), i -> dom(range(i < 2 ? 6 : 9)));

		equal(add(x[0], 1), x[1]);
		equal(add(x[1], 1), x[2]);
		equal(add(x[2], 1), x[3]);
		equal(add(x[0], x[1], x[2], x[3]), 14);
	}
}
