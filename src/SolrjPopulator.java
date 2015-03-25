import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputField;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SolrjPopulator {
  public static void main(String[] args) throws IOException, SolrServerException {
    HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/moviedata.movie_rating");
    server.setParser(new XMLResponseParser());
    Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
    for(int i=0;i<1000;++i) {
      SolrInputDocument doc = new SolrInputDocument();
      doc.addField("movieid", 0+i);
      int randomNum = 0 + (int)(Math.random()*5); 
      doc.addField("avgrating", randomNum);
      doc.addField("name", "some movie"+i);
      docs.add(doc);
    }
    try {
        server.add(docs);
        server.commit();

   } catch (SolrServerException | IOException e) {
       e.printStackTrace();
   }
  }
}