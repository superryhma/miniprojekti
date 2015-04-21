import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can edit added references'

scenario 'User adds a book and then modifies it', {
    given 'Valid book reference', {
        data = [
                name  : "to-be-modified",
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
        originalid = response.data.id
    }
    and
    given "A valid modification is given", {
        data = [
                name  : "to-be-modified",
                type  : "book",
                fields: [
                        author   : "b",
                        title    : "b",
                        publisher: "b",
                        year     : "1",
                ],
                tags  : []
        ]
    }
    and
    when 'The book is modified', {
        response = http.put(path: "/api/references/" + response.data.id,
                body: data,
                requestContentType: JSON)
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.id = originalid
    }
}