# CreateCandidate
 Backend Service API for MongoDb in Spring MVC


    MIT LICENCE


Till now, with experience in spring-mvc and knowledge acquired so far, I can easily say that spring is completely a waste of time.
It's only telling programmers that they don't know how to implement MVC ARCHITECTURE.

Reusable objects like @bean etc. are mainly for those cases where new state is not dependent on previous state of object, eg. Printing a statement.
Otherwise, new keyword has to be used to take a new memory object... biggest flaw here is that object throgh new keyword if refers to spring bean, null is returned from spring container...

This is flaw because, it says that use reusable objects till the point you need to create a new keyworded object and if you create a new keyworded object that means you have reached the end of flow of logic (in the eyes of spring), a complete blind and non-sense concept. 

This should be more flexible so that logic flow doesn't end there.

🤣😂😝

Being said above,
1. Do edit pom.properties file to correct file path.

2. Developement and testing platform is/was windows 7 basic, intel i3 processor.

3. Mongodb is not using any user/password.

4. All request are being directed to CandidateController inside com.vivek.CreateCandidate package.

5. Clean pre-build files.

6. Tomcat 8.0 was used , eclipse , java 1.13.

