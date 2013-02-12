/*******************************************************************************
 * Copyright (c) 2013 ComTrek.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ComTrek - initial API and implementation
 ******************************************************************************/
package comtrek

class FooterTagLib {

	String nomCreateur = "Bilel Bakini - Hamza BEY - Paolo DE VATHAIRE - St&eacute;ven BERNARD"

	
  String month (){
	  
	  int t = Calendar.MONTH + 1
	  if( t < 10)
	  	return 0 + t.toString()
	  else
	  	return t.toString()
  }
	
  def thisYear = {
    out << Calendar.getInstance().get(Calendar.DATE) << " / " << month() << " / " << Calendar.getInstance().get(Calendar.YEAR)
  }
  
  def creat = {
	  out << nomCreateur
  }

}
