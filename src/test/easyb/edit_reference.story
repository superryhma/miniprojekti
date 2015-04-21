import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can edit references'

scenario 'User edits an reference', {
    given 'Valid article reference in database', {
        data = [
                name  : "editable-article",
                type  : "article",
                fields: [
                        author : "a",
                        title  : "a",
                        journal: "a",
                        year   : "1",
                        volume : "1"
                ],
                tags  : []
        ]
        http = new RESTClient('http://localhost:8080/')
        id = http.post(path: "api/references",
                body: data,
                requestContentType: JSON).data.id
    }
    when 'The reference is edited', {
        data = [
                name  : "everything-changes",
                type  : "article",
                id    : id,
                fields: [
                        author : "b",
                        title  : "b",
                        journal: "b",
                        year   : "2",
                        volume : "2"
                ],
                tags  : ["tag"]
        ]
        http = new RESTClient('http://localhost:8080/')
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
