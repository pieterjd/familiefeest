# Spring Boot with angular

* ``src/main/angular`` contains the default angular app generated bu angular cli
* ``src/main/resources`` contains a folder ``static``. This folder is included in the result jar/war
* the angular app is built, resulting in ``src/main/angular/dist`` folder. the content of this folder is 
copied into the ``static`` folder
* when running the standalone jar, the index.html file is loaded in the browser, and you see the angluar logo
