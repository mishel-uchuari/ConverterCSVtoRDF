/*
 * 
 */
package main;
import java.io.IOException;
import java.util.Iterator;

import org.openrdf.model.Statement;

import clojure.lang.LazySeq;
import clojure.lang.RT;

/**
 * 
 * @author Mishel Uchuari
 *
 */

public class Pruebas {
	public static void main(String[] args) throws IOException {
		System.out.println();
		RT.loadResourceScript("AvenidaGasteiz/main.clj");
		LazySeq lazy=(LazySeq)RT.var("AvenidaGasteiz.main", "convertidor")
				.invoke("./data/AV_GASTEIZ.csv");
		Iterator ite =  lazy.iterator();

		while(ite.hasNext()){
			//System.out.println(ite.next().getClass());
		//	Statement cn=(Statement) RT.var("grafterdatacube.core", "convertidor").invoke(ite.next());
			//System.out.println(cn.getClass());
		System.out.println(ite.next());
		}
	}
	public boolean contieneStatement () throws IOException{
		RT.loadResourceScript("AvenidaGasteiz/main.clj");
		LazySeq lazy=(LazySeq)RT.var("AvenidaGasteiz.main", "convertidor")
				.invoke("./data/AV_GASTEIZ.csv");
		Iterator ite =  lazy.iterator();
		Statement st=(Statement) ite.next();
//		while(ite.hasNext()){
//			//System.out.println(ite.next().getClass());
//		//	Statement cn=(Statement) RT.var("grafterdatacube.core", "convertidor").invoke(ite.next());
//			//System.out.println(cn.getClass());
//		System.out.println(ite.next());
//		}
		return lazy.contains(st);
	}
}