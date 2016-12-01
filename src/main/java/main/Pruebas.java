/*
 * 
 */
package main;
import java.io.IOException;
import java.util.Iterator;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.Rio;

import clojure.lang.LazySeq;
import clojure.lang.RT;

/**
 * 
 * @author Mishel Uchuari
 * @email dmuv7@hotmail.com
 *
 */

public class Pruebas {
	public static void main(String[] args) throws QueryEvaluationException, RepositoryException, MalformedQueryException, RDFHandlerException, IOException  {
			RT.loadResourceScript("AvenidaGasteiz/main.clj");
		
		LazySeq lazy=(LazySeq)RT.var("AvenidaGasteiz.main", "convertidor")
				.invoke("./data/AV_GASTEIZ.csv");
		Iterator ite =  lazy.iterator();
		Model model = new LinkedHashModel();

		while(ite.hasNext()){
			model.add((Statement)ite.next());
			//System.out.println(ite.next().getClass());
		//	Statement cn=(Statement) RT.var("grafterdatacube.core", "convertidor").invoke(ite.next());
			//System.out.println(cn.getClass());
		}
		Rio.write(model, System.out, RDFFormat.RDFXML);
		//PruebasModel pM = new PruebasModel(model);
		//pM.testModel();
	//	System.out.println(pM.testModel2());
	}
//	public boolean contieneStatement () throws IOException{
//		RT.loadResourceScript("AvenidaGasteiz/main.clj");
//		LazySeq lazy=(LazySeq)RT.var("AvenidaGasteiz.main", "convertidor")
//				.invoke("./data/AV_GASTEIZ.csv");
//		Iterator ite =  lazy.iterator();
//		Statement st=(Statement) ite.next();
////		while(ite.hasNext()){
////			//System.out.println(ite.next().getClass());
////		//	Statement cn=(Statement) RT.var("grafterdatacube.core", "convertidor").invoke(ite.next());
////			//System.out.println(cn.getClass());
////		System.out.println(ite.next());
////		}
//		return lazy.contains(st);
//	}
}