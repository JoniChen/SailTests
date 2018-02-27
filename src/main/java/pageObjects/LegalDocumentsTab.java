package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.openqa.selenium.By;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.mongodb.client.model.Filters.*;

public class LegalDocumentsTab {


    public static SelenideElement legalDocTab() {

        return $(By.id("loan-tabs-tab-loanAgreements"));

    }

        public static SelenideElement generateDocsButton() {

        return $("div #loan-tabs-pane-loanAgreements button[class='sign-docs-button btn-outlined btn btn-primary']");
    }

    public static SelenideElement docsSentSuccess() {

        return $("div h3");

    }

    public static SelenideElement docsSentOKButton() {

        return $("button div span[style*='position: relative; padding']");

    }

    public static ElementsCollection sentDocumentsStatus() {

        return $$("#loan-tabs-pane-loanAgreements td");

    }

    public static SelenideElement sentDocumentsID() {

        return $(By.xpath("//div[2]/div/div/div/div/div[2]/table/tbody/tr/td[1]"));

    }
    //MongoDB query for DocumentID using LoanID

    public static String getDocumentID(String LoanID) {


        String user = "admin";
        String database = "admin";
        char[] password = {'5', 'L', 'u', 'y', 'N', 'q', 'r', 'c', 'V', 'Y', 'c', 'o', 'C', 'r', '4', 'q'};

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        MongoCredential credential = MongoCredential.createScramSha1Credential(user, database, password);
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();

        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress("cluster0-shard-00-00-twv4o.mongodb.net", 27017),
                new ServerAddress("cluster0-shard-00-01-twv4o.mongodb.net", 27017),
                new ServerAddress("cluster0-shard-00-02-twv4o.mongodb.net", 27017)),
                Arrays.asList(credential), options);

        MongoDatabase database1 = mongoClient.getDatabase("sail-staging");
        MongoCollection<Document> collection = database1.getCollection("documents");

        String DocumentID = collection.find(eq("loan_id", new ObjectId(LoanID)))
                .projection(Projections.fields(Projections.include("document_id"), Projections.excludeId()))
                .first().toJson();

        return DocumentID.substring(19, 55);


    }


    public static void postSignedStatus(String documentNumber) throws Exception {

        //XMLHTTPRequest xhr = new XMLHTTPRequest();
        String url = "http://staging.sail.com.au/webhooks/docusign/update";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Setting basic post request
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/xml");

        String postXmlData = "<?xml version=\"1.0\"?>\n" +
                "<DocuSignEnvelopeInformation xmlns=\"http://www.docusign.net/API/3.0\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">  \n" +
                "  <EnvelopeStatus>\n" +
                "    <Status>Signed</Status>\n" +
                "    <EnvelopeID>" + documentNumber + "</EnvelopeID>\n" +
                "  </EnvelopeStatus>\n" +
                "</DocuSignEnvelopeInformation>";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postXmlData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postXmlData);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        //printing result from response
        System.out.println(response.toString());
    }

}




