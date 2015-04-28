import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import java.nio.charset.StandardCharsets

description 'User can get all references in bibtex file'

scenario 'User adds two references and then retrieves references in BiBTeX file', {
    given 'two valid references', {
        data1 = [
                name  : "to_be_retrieved_in_bibtex_1",
                type  : "book",
                fields: [
                        author   : "b",
                        title    : "i",
                        publisher: "b",
                        year     : "1",
                ],
                tags  : ["tex"]
        ]
        data2 = [
                name  : "to_be_retrieved_in_bibtex_2",
                type  : "book",
                fields: [
                        author   : "b",
                        title    : "i",
                        publisher: "b",
                        year     : "1",
                ],
                tags  : ["tex"]
        ]
        http = new RESTClient('http://localhost:8080/')
    }
    when 'The references are posted', {
        	
    }
    then 'The responses are OK!', {
        
    }
    and
    when 'bibtex file is ordered', {
        response = http.get(path: "/api/references/",
                contentType: "text/x-bibtex")
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
    }
}