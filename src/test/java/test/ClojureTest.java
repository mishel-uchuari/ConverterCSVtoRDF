/*
 * 
 */
package test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import main.Pruebas;

public class ClojureTest {

	public void test() throws IOException {
		Pruebas p = new Pruebas();
		Assert.assertTrue(p.contieneStatement());
		
	}

}
