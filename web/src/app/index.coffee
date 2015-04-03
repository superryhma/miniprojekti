angular.module 'web', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ngResource', 'ngRoute']
  .config ($routeProvider) ->
    $routeProvider
      .when "/",
        templateUrl: "app/main/main.html"
        controller: "MainCtrl"
      .otherwise
        redirectTo: "/"
