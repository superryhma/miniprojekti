import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can retrieve an added reference'

scenario 'User adds a book and then retrieves it', {
    given 'Valid book reference', {
        data = [
                name  : "to-be-get",
                type  : "book",
                fields: [
                        author   : "a",
                        title    : "a",
                        publisher: "a",
                        year     : "1",
                ],
                tags  : ["tag"]
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
    when 'The book is retrieved', {
        response = http.get(path: "/api/references/" + originalid)
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.id == originalid
        assert response.data.name == "to-be-get"
        assert response.data.tags == ["tag"]
        assert response.data.type == "book"
        assert response.fields == [
                author   : "a",
                title    : "a",
                publisher: "a",
                year     : "1"
        ]
    }
}