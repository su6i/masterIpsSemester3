# Technologies Avancées du Web   


3rd semester    
Master of Computer Science for Sciences    
University of Montpellier   
Prof: Pierre POMPIDOR  

----------------------------------------
## Final view:
![Home page before login](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/home-page-signied-out.png)
![Home page logged in](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/home-page-signied-in.png)
![Profile page](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/profile.png)
![Products page](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/products.png)
![Product details](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/product-details.png)
![Search Bar](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/search-bar.png)
![Search Bar - Multiple selection](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/search-bar-multiple-selection.png)
![Orders page](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/orders.png)
![Order details page](https://github.com/su6i/technoduwebavance/blob/master/authProject/src/assets/image/order-details.png)
----------------------------------------   
Install:

  - Mongo on Doker: docker run --name mean-mongo -v "/Users/su6i/myMongoDB":/data/db -d -p 27017-27019:27017-27019 mongo    
  - Node.js on Mac:    
    - /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"   
    - brew install node   
 
 - Node.js on Ubuntu:
    - sudo apt install nodejs npm   

 - Express: npm install express --save   
 - Angular: npm install -g @angular/cli   
 
----------------------------------------
Installed modules and tools:   

[Tokei:](https://github.com/XAMPPRocky/tokei) Tokei is a program that displays statistics about your code. Tokei will show the number of files, total lines within those files and code, comments, and blanks grouped by language.   

<pre>
===============================================================================
 Language            Files        Lines         Code     Comments       Blanks
===============================================================================
 CSS                    24          204          190            0           14
 HTML                   24          464          387           27           50
 JavaScript              8          761          635           52           74
 TypeScript             65         2141         1670           28          443
===============================================================================
 Total                 121         3570         2882          107          581
===============================================================================
</pre>

### How to install tokei on MacOS using Homebrew    
`brew install tokei`   

### How to use Tokei   
`tokei ./routes ./models app.js server.js src/app`   

## Modules   

**bcryptjs**: a module to auto-gen a *salt* and *hash* and to *check a password*   

**jsonwebtoken**: JSON Web Token is an Internet standard for creating data with optional *signature and/or optional encryption* whose payload holds JSON that asserts some number of claims.   

**mongoose**: Mongoose is a *MongoDB object modeling* tool designed to work in an asynchronous environment.   

**multer**: Multer is a node.js middleware for handling multipart/form-data, which is primarily used for *uploading files*.   

**passport**: Passport is Express-compatible *authentication middleware* for Node.js. Passport's sole purpose is to authenticate requests, which it does through an extensible set of plugins known as strategies.   

**passport-jwt**: A Passport *strategy* for authenticating with a JSON Web Token.   

    
    
----------------------------------------
Some npm commands:   

   - npm init -y : -y set all questions by default answer
   - npm install
   - npm run start
   - npm install -g nodemon : -g is for global installation
   - npm install nodemon --save-dev : Install a module as the developer dependencies
   - npm install express mongodb --save
   - npm list --depth=0
   - npx serve : File server
   - npm install : will install both "dependencies" and "devDependencies"
   - npm install --production : will only install "dependencies"
   - npm install --dev : will only install "devDependencies"

----------------------------------------
Some Mongo commands:

   - mongo -u <username> -p <password> --authenticationDatabase <dbname> : Log Into MongoDB
   - show dbs : Show All Databases
   - use databaseName : Select Database to Work With
   - db.auth("username", "password") : Authenticate to a Database
   - db.logout() : Log Out From Database
   - show collections;
   - db.getCollectionNames();
   - show users;
   - db.getUsers();
   - show roles
   - cls : Clear Screen
   - db.<collectionName>.find().pretty();
   - db.createCollection("collectionName"); : Create a Collection
   - db.<collectionName>.insert({field1: "value", field2: "value"}) : Insert single document
   - db.<collectionName>.insert([{field1: "value1"}, {field1: "value2"}]) : Insert a Document in a Collection
   - db.<collectionName>.insertMany([{field1: "value1"}, {field1: "value2"}]) : 
   - db.<collectionName>.save({"_id": new ObjectId("jhgsdjhgdsf"), field1: "value", field2: "value"}); : Matching document will be updated; In case, no document matching the ID is found, a new document is created
   - db.<collectionName>.find();
   - db.<collectionName>.find().limit(10);
   - db.<collectionName>.find({"_id": ObjectId("someid")}); : Retrieve records by id
   - db.<collectionName>.find({"_id": ObjectId("someid")}, {field1: 1, field2: 1});
   - db.<collectionName>.find({"_id": ObjectId("someid")}, {field1: 0}); // Exclude field1
   - db.<collectionName>.count();
   - db.dropDatabase()
   - db.<collectionName>.stats() : Get the collection statistics 
   - db.printCollectionStats()
   - db.<collectionName>.latencyStats()
   - db.<collectionName>.dataSize() : Size of the collection
   - db.<collectionName>.storageSize() : Total size of document stored in the collection
   - db.<collectionName>.totalSize() : Total size in bytes for both collection data and indexes
   - db.<collectionName>.totalIndexSize() : Total size of all indexes in the collection
  
----------------------------------------
Some Angular notes:  

* Using Angular Forms is easier than using Two Way Binding method.   

* \# : is for Angular local reference.    

* [()]= "" : Angular Two Way Binding.   

* () = "" : Angular Event method.   

* @Injectable({providedIn: 'root'}) : We use this line in our service to create just ONE service for entire our application and then we don't need to import our service in app.module.ts   

* ng add @angular/material   
    
### Sort (Filter) method in Angular:   
    
      private items: Observable<Item[]>;
      this.itemObservable.pipe (
     map(items => 
      items.filter(item => item.name.toLowerCase().indexOf(query) > -1)) )   
     
### Sort (Filter) method in Node.js
     Product.find()
      .collation({ locale: "fr" })
      .sort({ name: 1 })
      .select("_id name price image category type description nameLowerCase")

- Angular pretty print in JSON format:
  `<pre> {{ YourVariable | json}} </pre>`

----------------------------------------   
Some node and JavaScript useful links: 

   - https://faker.readthedocs.io/en/master/
   - https://www.npmjs.com/package/faker
   - https://deno.land
   - https://www.npmjs.com/package/serve
   - https://www.youtube.com/watch?v=M3BM9TB-8yA : 10 Things I Regret About Node. js — Ryan Dahl
   - https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc?hl=en
   - https://robomongo.org/
   - https://frontendmasters.com/courses/angular-9/ : a good Angular course
   - https://pro.academind.com/p/angular-nodejs-the-mean-stack-guide-2020-edition : a good MEAN stack course
   - https://code.tutsplus.com/courses/javascript-fundamentals
