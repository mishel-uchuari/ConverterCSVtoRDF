package main;

import org.openrdf.model.Model;
import org.openrdf.query.BooleanQuery;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;

public class PruebasModel {
	Repository repo = new SailRepository(new MemoryStore());
	RepositoryConnection conn;
	public PruebasModel(Model pModel) throws RepositoryException {
		
			repo.initialize();
			conn= repo.getConnection();
			conn.add(pModel);
	
		
	}

	public void testModel() throws QueryEvaluationException, RepositoryException, MalformedQueryException {
		String sparql = "SELECT * where {?s ?p ?q}";
	    TupleQueryResult result = conn.prepareTupleQuery(QueryLanguage.SPARQL, sparql).evaluate();
			while(result.hasNext()) {
		    	System.out.println(result.next());
		    }	    
	}
	
	public boolean testModel2() {
		String sparql = "ASK WHERE {<http://opendata.euskadi.eus/AV-GASTEIZ/data/01-01-2016> <http://opendata.euskadi.eus/element/name/CO-8h-Air-Quality> ?s}";
		boolean result=false;
		try {
			BooleanQuery booleanQuery = conn.prepareBooleanQuery(QueryLanguage.SPARQL, sparql);
			result = booleanQuery.evaluate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
