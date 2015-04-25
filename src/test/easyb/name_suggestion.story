import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'name suggestion for reference is given to user'

scenario 'User gives information about article', {
    given 'Valid author and year of reference', {
        param_author = 'Luukkainen, M. and Vihavainen, A.'
        param_year = '1997'
        url = '/api/references/namesuggestion'
        http = new RESTClient('http://localhost:8080/')
    }
    when 'Name suggestion is asked', {
        response = http.get(path: url, query: [author: param_author, year: param_year])
    }
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.name == "Luukkainen1997"
    }
}