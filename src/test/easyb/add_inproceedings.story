import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can add inproceedings references'

scenario 'User adds an inproceedings', {
    given 'Valid inproceedings reference', {
        data = [
                name  : "an-inproceedings",
                type  : "inproceedings",
                fields: [
                        author   : "a",
                        title    : "a",
                        booktitle: "a",
                        year     : "1",
                ],
                tags  : []
        ]
        http = new RESTClient('http://localhost:8080/')
    }
    when 'The inproceedings is posted', {
        response = http.post(path: "api/references",
                body: data,
                requestContentType: JSON)
    }
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.id != null
    }
}