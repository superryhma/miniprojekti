import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'name suggestion for reference is given to user'

scenario 'User gives information about article', {
    given 'Valid author and year of reference', {
        data = [
                author : "Luukkainen, M. and Vihavanen, A.",
                year   : "1997"
        ]
        http = new RESTClient('http://localhost:8080/')
    }
    when 'Name suggestion is asked', {
        response = http.get(path: "api/references/namesuggestion",
                body: data,
                requestContentType: JSON)
    }
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.name == "Luukkainen1997"
    }
}