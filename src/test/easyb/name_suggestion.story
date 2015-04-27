import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*

description 'name suggestion for reference is given to user'

scenario 'User gives valid information about reference when theres no references yet', {
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

scenario 'User adds a reference and then starts to add new reference with same author and year as in first added reference', {
    given 'Valid reference', {
        data = [
                name  : "Luukkainen1997",
                type  : "book",
                fields: [
                        author   : "Luukkainen, M. and Vihavainen, A.",
                        title    : "a",
                        publisher: "a",
                        year     : "1997",
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
        originalid = response.data.id
    }
    and
    given 'Valid author and year of reference similar as in reference added before when started to add new reference', {
        param_author = 'Luukkainen, M. and Vihavainen, A.'
        param_year = '1997'
        url = '/api/references/namesuggestion'
        http = new RESTClient('http://localhost:8080/')
    }
    and
    when 'Name suggestion is asked', {
        response = http.get(path: url, query: [author: param_author, year: param_year])
    }
    and
    then 'The response is OK!', {
        assert response.status == 200
        assert response.data.success
        assert response.data.name == "Luukkainen1997a"
    }
}