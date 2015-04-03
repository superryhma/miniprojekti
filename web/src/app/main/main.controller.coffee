angular.module "web"
  .controller "MainCtrl", ($scope) ->
    $scope.types = [
      {
        "name": "book",
        "required": ["author", "title", "year", "publisher"],
        "optional": ["pages"]
      },
      {
        "name": "misc",
        "required": [],
        "optional": ["author", "title", "year"]
      }
    ]
    $scope.selectedType = $scope.types[0]
    $scope.references = [
      {
        "id": 16,
        "name": "MSAM",
        "url": "/api/references/16",
        "created_at": "2015-03-31T17:09:27Z",
        "updated_at": "2015-03-31T17:22:47Z",
        "type": "book",
        "fields": {
          "author": {"value": "Matti", "required": true},
          "title": {"value": "Sammakkokirja", "required": true},
          "year": {"value": 2013, "required": true},
          "publisher": {"value": "Otava", "required": true},
          "pages": {"value": 512, "required": false}
        },
        "tags": [
          "suomi", "lol"
        ]
      },
      {
        "id": 17,
        "name": "KEK",
        "url": "/api/references/17",
        "created_at": "2015-03-31T17:09:27Z",
        "updated_at": "2015-03-31T17:22:47Z",
        "type": "misc",
        "fields": {
          "author": {"value": "Kek Kos", "required": false},
          "title": {"value": "Hehehehehe", "required": false},
          "year": {"value": 2016, "required": false}
        },
        "tags": [
          "lol"
        ]
      }
    ]
  .filter 'unlist', ->
    (input) ->
      return input.join ", "
  .filter 'capitalize', ->
    (input) ->
      return input.charAt(0).toUpperCase() + input.substr(1).toLowerCase()