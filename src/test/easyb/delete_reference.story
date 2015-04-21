import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can remove references'

scenario 'User delete a article', {
    given 'Delete existing article reference', {
        data = [
                name  : "an-article",
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
        response = http.post(path: "api/references",
                    body: data,
                    requestContentType: JSON)
    }
    when 'The book is deleted', {
        response = http.delete(path: "api/references/" + response.data.id)
    }
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
    }
}