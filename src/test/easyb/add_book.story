import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can add book references'

scenario 'User adds a book', {
    given 'Valid book reference', {
        data = [
                name  : "a-book",
                type  : "book",
                fields: [
                        author   : "a",
                        title    : "a",
                        publisher: "a",
                        year     : "1",
                ],
                tags  : []
        ]
        http = new RESTClient('http://localhost:8080/')
    }
    when 'The book is posted', {
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

scenario 'User adds an book with name already in use', {
    given 'Valid book reference with name already in use', {
        data = [
                name  : "a-book",
                type  : "book",
                fields: [
                        author   : "a",
                        title    : "a",
                        publisher: "a",
                        year     : "1",
                ],
                tags  : []
        ]
        http = new RESTClient('http://localhost:8080/')
        http.handler.failure = { resp, data -> resp.setData(data); return resp }
    }	
    when 'The reference is posted', {
        response = http.post(path: "api/references",
                body: data,
                requestContentType: JSON)
    }
    then 'The response is OK!', {
        assert response.status == 400
        assert !response.data.success
    }
}