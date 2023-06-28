# NBP-Simple-Poject

NBP-Simple-Poject is a simple api used to convert USD to PLN.

## API documentation

### Demo

if you want to check how the project works, clone the repository.

### Requirements

* JVM
* MySQL database

### Running the project

You need to create a user in the MySql database. Make settings in the application properties file. The project is compiling with gradle.

### Endpoints' description

* `/v1/nbp`

  POST method, saves information about the product to the file and database. Based on the given date, it converts USD to PLN using the NBP api.

  ```
  {
    "name" : "Dell",
    "postingDate" : "2023-01-20",
    "costUsd" : 415
  }
  ```

  * `/v1/nbp/getByAny`
 
  GET method, searches the database for all computers by a piece of text. Parameters: @RequestParam - any String.

   * `/v1/nbp/getByDate`
 
  GET method, searches the database for all computers based on the given date. Parameters: @RequestParam - Date date(Format(yyyy-MM-dd).

   * `/v1/nbp/sortByName`
 
  GET method, sorts by product name. No parameters.

   * `/v1/nbp/sortByDate`
 
  GET method, sorts by posting date. No parameters.

  ### How can I use it?
  
The project can be used to convert USD to PLN based on a given date.
