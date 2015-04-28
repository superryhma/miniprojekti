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
        originalid = response.data.id
    }
    and
    when 'The book is removed', {
        response = http.delete(path: "/api/references/" + originalid)
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
    }
}

scenario 'User tries to delete unexisting reference', {
    given 'invalid reference id', {
        originalid = 123123
        http = new RESTClient('http://localhost:8080/')
    }
    when 'delete is posted', {
    	http.handler.failure = { resp, data -> resp.setData(data); return resp }
        response = http.delete(path: "/api/references/" + originalid)
    }
    then 'The response is OK!', {
        assert response.status == 404
        assert !response.data.success
    }
}