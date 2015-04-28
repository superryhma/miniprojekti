import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import java.nio.charset.StandardCharsets

description 'User can read references in bibtex format'

scenario 'User adds a book and then retrieves it as BiBTeX', {
    given 'Valid book reference', {
        data = [
                name  : "convert-to-bibtex",
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
    when 'The book is posted', {
        response = http.post(path: "api/references",
                body: data,
                contentType: JSON,
                requestContentType: JSON)
    }
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.id != null
        originalid = response.data.id
    }
    and
    when 'The book is retrieved', {
        response = http.get(path: "/api/references/" + originalid,
                contentType: "text/x-bibtex")
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
        n = response.data.available()
        byte[] bytes = new byte[n];
        response.data.read(bytes, 0, n);
        responseString = new String(bytes, StandardCharsets.UTF_8)
        referenceString =
                "@Book{convert-to-bibtex,\n" +
                "  author = \"b\",\n" +
                "  publisher = \"b\",\n" +
                "  title = \"i\",\n" +
                "  year = \"1\"\n" +
                "}"
        assert responseString == referenceString
    }
}
