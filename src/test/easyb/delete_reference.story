import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can remove added references'

scenario 'User adds a book and then deletes it', {
    given 'Valid book reference', {
        data = [
                name  : "to-be-deleted",
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
    and
    when 'The book is removed', {
        response = http.delete(path: "/api/references/" + response.data.id)
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
    }
}