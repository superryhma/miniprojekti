import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'User can add article references'

scenario 'User adds an article', {
    given 'Valid article reference', {
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
    }
    when 'The reference is posted', {
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