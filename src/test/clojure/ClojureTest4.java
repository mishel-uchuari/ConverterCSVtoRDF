/*
 * 
 */


import java.io.IOException;

import org.junit.Assert;
import org.junit.experimental.categories.Category;

import main.Pruebas;

public class ClojureTest4 {

	public void test() throws IOException {
		
		Assert.assertEquals(new Pruebas().contieneStatement(), true);
	
	}

}
