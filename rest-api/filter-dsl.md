
### Using Query Filter

  - Open AMS Provides a Query DSL to filter it's resources (Ex : students,test-scores)

  - The Query API Provides
      - Filter configurations API **GET {resource_path}/filter-config** and
      - Resources Filter API **GET {resource_path}/filter=(FILTER)**


  - Filter configurations API. Ex : **GET /students/filter-config**  will respond as shown below.

  ~~~ json
  {
  "// filter property"   : "// filter property value type"
  "gender": "class org.openams.rest.jpa.entity.enums.Gender",
  "dob": "class java.util.Date"
  "user.userName": "class java.lang.String",
  "mName": "class java.lang.String",
  "height": "class java.lang.Float"
  }
  ~~~

  - Resources Filter API. Ex : **/students?filter=({'fname'=='mac'})**

  - All Filter should be URL Encoded


### Filter DSL

 - A Filter which can't be simplified further is TERMINAL FILTER.
 - A Terminal Filter should be enclosed in '**{**' and '**}**'
 - A Terminal Filter contains KEY , OPERATOR , VALUE

 - **KEY** : Possible values for Key can be found by **'GET {resources}/filter-config'** API, Ex : fName, dob , etc ...

 - **VALUE** : Value can be Array or a Single Value. Ex : '123' , 'maridu', ['3','4','5'] , ['SUSPENDED','INACTIVE'] , etc ...
   - All type of values are enclosed in single codes. (Integer, Float , String, ENUM, Date ...)
   - **''**  enclosed values will be automatically converted to it's respective key formats
   - String, Integer, Float , Long, ENUMs can be represented as their textual representations. Ex: '123' , '34.35' , 'string_value' , 'ACTIVE'
   - DATE can be represented in **GMT yyyy-MM-dd Format**, Ex : '2017-04-21'
   - TIME can be represented in **GMT 24 HH-mm-ss Format** , Ex :  '14-35-56'
   - DATE TIME can be represented in **GMT yyyy-MM-dd-HH-mm-ss Format**, Ex : '2017-04-21'


 - **OPERATOR** can be  any of **== , != , >= , <=** , But only **== , !=** supported for Array values

 - Terminal Filter Syntax :
    - **{'KEY' OPERATOR 'VALUE'}** OR
    - **{'KEY' OPERATOR ['VALUE_1','VALUE_2','VALUE_3']}**



 - FINAL or COMPLEX filters should be enclosed in '**(**' and '**)**'
 - **||** or **&&** can be used to combine multiple filters

     - Ex 1 : (TERMIANL_FILTER_1)
     - Ex 2 : (TERMIANL_FILTER_1 || TERMIANL_FILTER_2)
     - Ex 3 : (TERMIANL_FILTER_1 && TERMIANL_FILTER_2)
     - Ex 4 : ( TERMIANL_FILTER_2 || (TERMIANL_FILTER_1 && TERMIANL_FILTER_2))

     - Ex 4 :  ( {'lName' == 'maridu'} )
     - Ex 4 :  ( {'lName' == ['maridu', 'bandham']} )
     - Ex 4 :  ( {'dob' >= '2017-04-21'} || {'gender' = 'MALE'} )
     - Ex 5 :  ( {'lName' == ['maridu', 'bandham']} && ( {'dob' >= '2017-04-21'} || {'gender' = 'MALE'} ))

- URL Encoding, Ex : **?filter=({'fName'=='Evan'})&page=0&limit=2&sort=+fName** should be
    **?filter=(%7B'fName'%3D%3D'Evan'%7D)&page=0&limit=2&sort=%2BfName**
