description 'User can add inproceedings references'

scenario "user adds inproceedings with correct fields", {
    given 'POST to /api/reference/ arrives'
    when 'data in json are correct inproceedings data'
    then 'reference is added to db correctly'
}